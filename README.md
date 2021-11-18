# TechnicalTest

Stack tecnológico utilizado:

  * Java 8
  * spring-boot
  * REST API
  * jpa
  * junit
  * h2
  * html


Instrucciones de compilación / arranque:
  * Compilar el proyecto maven: "clean install".


  * Ejecutar los mádulos:
    * .\TechnicalTest\uster-ws\src\main\java\com\test\uster\ws\UsterWebserviceApp.java (Webservice)
    * .\TechnicalTest\uster-ui\src\main\java\com\test\uster\ui\UsterUIApp.java (UI)


  * Ficheros de configuración:
    * .\TechnicalTest\uster-ws\src\main\resources\application.yml (Webservice)
    * .\TechnicalTest\uster-ui\src\main\resources\application.yml (UI básica)

  * Accesos a la app (valores por defecto):
    * Webservice: http://localhost:9101/
    * H2 console: http://localhost:9101/h2
    * UI (básica): http://localhost:9102/home


  * Adicionalmente se pueden usar los servicios REST API, usando, por ejemplo, Postman:
```
    POST http://localhost:9101/trips
    Content-Type: application/json
    Body:
    {
        "vehicle": "1",
        "driver": "1",
        "tripDate": "2021-11-21"
    }
```

  * Si se desea se cargar la BD con datos dotos dummy en la consola h2:

```
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(1, 'Brand B1', 'Model B', 'B');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(2, 'Brand B2', 'Model B', 'B');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(3, 'Brand B3', 'Model B', 'B');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(4, 'Brand B4', 'Model B', 'B');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(5, 'Brand B5', 'Model B', 'B');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(6, 'Brand A1', 'Model A', 'A');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(7, 'Brand A2', 'Model A', 'A');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(8, 'Brand C1', 'Model C', 'C');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(9, 'Brand C2', 'Model C', 'C');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(10, 'Brand C3', 'Model C', 'C');
    insert into VEHICLE(vehicleid, brand, model, LICENSEREQUIRED ) values(11, 'Brand D1', 'Model D', 'D');

    insert into DRIVER values(1, 'B', 'Driver B1', 'Surname');
    insert into DRIVER values(2, 'B', 'Driver B2', 'Surname');
    insert into DRIVER values(3, 'B', 'Driver B3', 'Surname');
    insert into DRIVER values(4, 'B', 'Driver B4', 'Surname');
    insert into DRIVER values(5, 'B', 'Driver B5', 'Surname');
    insert into DRIVER values(6, 'B', 'Driver B6', 'Surname');
    insert into DRIVER values(7, 'A', 'Driver A1', 'Surname');
    insert into DRIVER values(8, 'A', 'Driver A2', 'Surname');
    insert into DRIVER values(9, 'A', 'Driver A3', 'Surname');
    insert into DRIVER values(10, 'D', 'Driver D1', 'Surname');

    insert into trip values(1, 1, '2021-11-18');
    insert into trip values(1, 1, '2021-11-19');
    insert into trip values(1, 1, '2021-11-20');
    insert into trip values(2, 2, '2021-11-19');
    insert into trip values(2, 2, '2021-11-21');
    insert into trip values(3, 6, '2021-11-20');
```
