package com.test.uster.ui.controller;

import com.test.uster.ui.domain.Driver;
import com.test.uster.ui.domain.Trip;
import com.test.uster.ui.domain.Vehicle;
import com.test.uster.ui.service.ServiceClient;
import com.test.uster.ui.service.helper.HTMLHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The type Home controller.
 */
@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private static final String PREFIX = "html/";
    private static final String CHARSET = "UTF-8";
    private static final String TRIPDATE_FLAG = "##tripdate";
    private static final String VEHICLE_FLAG = "##vehicle";
    private static final String TABLE_FLAG = "##TABLE";
    private static final String TRIPDATE_REQ_PARAM = "tripdate";
    private static final String VEHICLE_REQ_PARAM = "vehicle";
    private static final String DRIVER_REQ_PARAM = "driver";

    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private HTMLHelper htmlHelper;

    /**
     * Home string.
     *
     * @return the string
     */
    @RequestMapping(path = "/home")
    public String home() {
        return getHtmlsource("usterhome.html");
    }

    /**
     * Trip step 1 string.
     *
     * @return the string
     */
    @RequestMapping("/trip1")
    public String tripStep1() {
        return getHtmlsource("trip/create_trip_step_1.html");
    }

    /**
     * Trip step 2 string.
     *
     * @param tripDate the trip date
     * @return the string
     */
    @RequestMapping("/trip2")
    public String tripStep2(@RequestParam(name = TRIPDATE_REQ_PARAM, required = true) final String tripDate) {
        String html = getHtmlsource("trip/create_trip_step_2.html", Pair.of(TRIPDATE_FLAG, tripDate));
        return html.replace(TABLE_FLAG, htmlHelper.generateVehiclesTable(serviceClient.getAvailableVehicles(tripDate)));
    }

    /**
     * Trip step 3 string.
     *
     * @param tripDate  the trip date
     * @param idVehicle the id vehicle
     * @return the string
     */
    @RequestMapping("/trip3")
    public String tripStep3(@RequestParam(name = TRIPDATE_REQ_PARAM, required = true) final String tripDate,
                            @RequestParam(name = VEHICLE_REQ_PARAM, required = true) final String idVehicle) {

        String html = getHtmlsource("trip/create_trip_step_3.html"
                , Pair.of(TRIPDATE_FLAG, tripDate), Pair.of(VEHICLE_FLAG, idVehicle));
        return html.replace(TABLE_FLAG,
                htmlHelper.generateDriversTable(serviceClient.getAvailableDrivers(tripDate, idVehicle)));

    }

    /**
     * Trip creation step string.
     *
     * @param tripDate  the trip date
     * @param idVehicle the id vehicle
     * @param idDriver  the id driver
     * @return the string
     */
    @RequestMapping("/create_trip")
    public String tripCreationStep(@RequestParam(name = TRIPDATE_REQ_PARAM, required = true) final String tripDate,
                                  @RequestParam(name = VEHICLE_REQ_PARAM, required = true) final String idVehicle,
                                  @RequestParam(name = DRIVER_REQ_PARAM, required = true) final String idDriver) {
        String response = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
            Trip trip = serviceClient.createTrip(Long.valueOf(idVehicle),
                    Long.valueOf(idDriver),simpleDateFormat.parse(tripDate));

            response = htmlHelper.generateTripsTable(Arrays.asList(trip));

        } catch (ParseException e) {
            response = "The date is not valid";
        }  catch (Exception e) {
            response = "The trip could not be reserved";
        }

        return getHtmlsource("generic/generic.html").replace(TABLE_FLAG, response);
    }

    /**
     * Trip list string.
     *
     * @return the string
     */
    @RequestMapping("/triplist")
    public String tripList() {
        String html = getHtmlsource("generic/generic.html");
        return html.replace(TABLE_FLAG, htmlHelper.generateTripsTable(serviceClient.getAllTrips()));
    }

    /**
     * Create vehicle view string.
     *
     * @return the string
     */
    @RequestMapping("/create_vehicle_view")
    public String createVehicleView() {
        return getHtmlsource("vehicle/create_vehicle.html");
    }

    /**
     * Vehicle creation string.
     *
     * @param brand   the brand
     * @param model   the model
     * @param license the license
     * @return the string
     */
    @RequestMapping("/create_vehicle")
    public String vehicleCreation(@RequestParam(name = "brand", required = true) final String brand,
                                  @RequestParam(name = "model", required = true) final String model,
                                  @RequestParam(name = "license", required = true) final String license) {
        String response = "";
        try {
            Vehicle vehicle = serviceClient.createVehicle(brand, model, license);

            response = htmlHelper.generateVehiclesTable(Arrays.asList(vehicle));

        } catch (Exception e) {
            response = "The vehicle could not be created";
        }

        return getHtmlsource("generic/generic.html").replace(TABLE_FLAG, response);
    }

    /**
     * Vehicle list string.
     *
     * @return the string
     */
    @RequestMapping("/vehiclelist")
    public String vehicleList() {
        String html = getHtmlsource("generic/generic.html");
        return html.replace(TABLE_FLAG, htmlHelper.generateVehiclesTable(serviceClient.getAllVehicles()));
    }

    /**
     * Create driver view string.
     *
     * @return the string
     */
    @RequestMapping("/create_driver_view")
    public String createDriverView() {
        return getHtmlsource("driver/create_driver.html");
    }

    /**
     * Driver creation string.
     *
     * @param name    the name
     * @param surname the surname
     * @param license the license
     * @return the string
     */
    @RequestMapping("/create_driver")
    public String driverCreation(@RequestParam(name = "name", required = true) final String name,
                                  @RequestParam(name = "surname", required = true) final String surname,
                                  @RequestParam(name = "license", required = true) final String license) {
        String response = "";
        try {
            Driver driver = serviceClient.createDriver(name, surname, license);

            response = htmlHelper.generateDriversTable(Arrays.asList(driver));

        } catch (Exception e) {
            response = "The driver could not be created";
        }

        return getHtmlsource("generic/generic.html").replace(TABLE_FLAG, response);
    }

    /**
     * Driver list string.
     *
     * @return the string
     */
    @RequestMapping("/driverlist")
    public String driverList() {
        String html = getHtmlsource("generic/generic.html");
        return html.replace(TABLE_FLAG, htmlHelper.generateDriversTable(serviceClient.getAllDrivers()));
    }

    private static String readFileContent(File src, String charset) throws IOException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(src);
            byte[] contents = new byte[in.available()];
            in.read(contents);
            return new String(contents, charset);

        }
        finally {
            if (in != null) {
                in.close();
            }
        }
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    private String getHtmlsource(final String htmlFile, Pair<String, String>... flagsToReplace) {
        AtomicReference<String> result = new AtomicReference<>("");

        try {
            File file = getFileFromResource(PREFIX + htmlFile);
            result.set(readFileContent(file, CHARSET));
            Arrays.stream(flagsToReplace).forEach(stringPair -> {
                result.set(result.get().replace(stringPair.getFirst(), stringPair.getSecond()));
            });
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return result.get();
    }

}
