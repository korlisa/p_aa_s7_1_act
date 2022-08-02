package app;

import app.controllers.AircraftRestController;
import app.entities.Aircraft;
import app.services.AircraftService;
import app.services.Fleet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirlineApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AircraftService aircraftService;

    /**
     * Aircraft creation test - check <b>id</b> for null & check field <b>brand</b>
     * should return code 201
     *
     * @see AircraftRestController
     */
    @Test
    public void whenCreateAircraft_thenStatus201() {

        Aircraft aircraft = createTestAircraft();
        ResponseEntity<Aircraft> response = restTemplate.postForEntity("/api/aircraft", aircraft, Aircraft.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody().getId());
        assertEquals(response.getBody().getBrand(), "Boeing");
    }

    /**
     * Get Aircraft test - check field <b>brand</b>
     * should return code 200
     *
     * @see AircraftRestController
     */
    @Test
    public void whenGetAircraft_thenStatus200() {

        long id = createTestAircraft().getId();
        Aircraft aircraft = restTemplate.getForObject("/api/aircraft/{id}", Aircraft.class, id);
        assertEquals(aircraft.getBrand(), "Boeing");
    }

    /**
     * Aircraft update test - check <b>id</b> for null & check field <b>brand</b>
     * should return code 200
     *
     * @see AircraftRestController
     */
    @Test
    public void whenUpdateAircraft_thenStatus200() {

        createTestAircraft();
        Aircraft aircraftUpdatedBrand = createTestNewBrandAircraft();
        HttpEntity<Aircraft> entity = new HttpEntity<>(aircraftUpdatedBrand);

        ResponseEntity<Aircraft> response = restTemplate.exchange("/api/aircraft/", HttpMethod.PUT, entity, Aircraft.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody().getId());
        assertEquals(response.getBody().getBrand(), aircraftUpdatedBrand.getBrand());
    }

    /**
     * Aircraft delete test - check <b>id</b> & check field <b>brand</b>
     * should return code 200
     *
     * @see AircraftRestController
     */
    @Test
    public void whenDeleteAircraft_thenStatus200() {

        long id = createTestAircraft().getId();
        ResponseEntity<Aircraft> response = restTemplate.exchange("/api/aircraft/{id}", HttpMethod.DELETE, null, Aircraft.class, id);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), id);
        assertEquals(response.getBody().getBrand(), "Boeing");

    }

    /**
     * A couple of helper methods - creating an Aircraft.
     * The first method is with entering into the database,
     * the second method is without entering with change <b>brand</b> field
     */
    private Aircraft createTestAircraft() {
        Aircraft aircraft = new Fleet().createBoeing737();
        aircraftService.save(aircraft);
        return aircraft;
    }

    private Aircraft createTestNewBrandAircraft() {
        Aircraft aircraft = new Fleet().createBoeing737();
        aircraft.setBrand("NewBrand");
        return aircraft;
    }


}
