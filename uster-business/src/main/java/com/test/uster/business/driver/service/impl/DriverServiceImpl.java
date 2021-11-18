package com.test.uster.business.driver.service.impl;

import com.test.uster.business.driver.exception.DriverExeption;
import com.test.uster.business.driver.mapper.DriverMapper;
import com.test.uster.business.driver.service.DriverService;
import com.test.uster.domain.driver.Driver;
import com.test.uster.persistence.driver.entity.DriverEntity;
import com.test.uster.persistence.driver.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Driver service.
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    @Transactional
    public List<Driver> getDrivers() {
        return driverRepository.findAll().stream()
                .map(driverEntity -> DriverMapper.MAPPER.map(driverEntity)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Driver getDriver(final Long idDriver) {
        return DriverMapper.MAPPER.map(
                driverRepository.findById(idDriver).orElseThrow(() ->
                        new DriverExeption(String.format("Driver not found: %s", idDriver)))
        );
    }

    @Override
    @Transactional
    public Driver createDriver(final Driver driver) {
        DriverEntity driverEntity = DriverMapper.MAPPER.map(driver);
        return DriverMapper.MAPPER.map(driverRepository.save(driverEntity));
    }

    @Override
    @Transactional
    public void updateDriver(final Long idDriver, final Driver driver) {
        Driver currentDriver = getDriver(idDriver);

        currentDriver.setName(driver.getName());
        currentDriver.setSurname(driver.getSurname());
        currentDriver.setLicense(driver.getLicense());

        driverRepository.save(DriverMapper.MAPPER.map(currentDriver));
    }

    @Override
    @Transactional
    public void deleteDriver(final Long idDriver) {
        driverRepository.deleteById(idDriver);
    }

    @Override
    @Transactional
    public List<Driver> getAvailableDrivers(final List<Long> busyDriverIds, final String licenseType) {
        return driverRepository.findAvailableDriversOrderByIdDriverAsc(busyDriverIds, licenseType)
                .map(driverEntity -> DriverMapper.MAPPER.map(driverEntity)).collect(Collectors.toList());
    }

}
