// health, login and register are predefined on the sent API documentation 
// setEntity - sending atributes
// Methods for positive and negative scenarios can be united, it can be put in one method, but decided to leave it separated, since it's more clear


package com.mycompany.tc;

//imports the necessary classes for making HTTP requests
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;



public class Test_Case {
    // Define basic API URL which requests will be sent on
    private static final String BASE_URL = "http://44.204.239.34:5000/api";

    // Define logger for logging of information
    private static final Logger logger = Logger.getLogger(Test_Case.class.getName());

    // Creation of HttpClient instance which will be used for sending requests
    private static CloseableHttpClient client;

    // Method that will be activated before all tests - used for initialization
    @BeforeAll
    static void setup() {
        // Creation of new HTTP client that will be used for sending requests
        client = HttpClients.createDefault();
        // Logging of the start with HTTP client work
        logger.info("Starting HTTP client...");
    }

    // Method that will be activated after all tests - used for resources clean up
    @AfterAll
    static void teardown() throws IOException {
        // Closing of the HTTP client
        client.close();
        // logging of the HTTP client closure
        logger.info("Closing HTTP client...");
    }

    // Test for GET /health - Pozitive scenario (server is healthy)
    //For this method, there is no negative scenario created, since we have positive and negative case during the getting of status itself
        @Test
    @DisplayName("Test GET /health - Positive Scenario")
    //There is no any return of data - that's why it's used void type of method
    void testHealthCheck_Positive() throws IOException {
        // Creation of HTTP GET request for endpoint /health
        HttpGet request = new HttpGet(BASE_URL + "/health");
        try (CloseableHttpResponse response = client.execute(request)) {
            // Logging of the responce with status code
            logger.info("GET /health response: " + response.getCode());
            // It's checking expected status code(200) with the second parameter that is responce received from the server
            assertEquals(200, response.getCode(), "Expected status 200");
        }
    }

    // Test for POST /login - Pozitive scenario (valid data for login)
    @Test
    @DisplayName("Test POST /login - Positive Scenario")
    void testLogin_Positive() throws IOException {
        // Creation of HTTP POST request for endpoint /login
        HttpPost request = new HttpPost(BASE_URL + "/login");
        // Set of request body with JSON data (valid user data)
        request.setEntity(new StringEntity("{\"username\":\"testuser\", \"password\":\"password123\"}"));
        // Set header that content is JSON, since it's suggested in documentation
        request.setHeader("Content-Type", "application/json");

        // Executing of The request and getting responce
        try (CloseableHttpResponse response = client.execute(request)) {
            // Logging of the responce with status code
            logger.info("POST /login response: " + response.getCode());
            // It's checking expected status code(200) for successful login with the responce received from the server
            assertEquals(200, response.getCode(), "Expected status 200 for successful login");
        }
    }

    // Test for POST /login - Negative scenario (invalid data for login)
    @Test
    @DisplayName("Test POST /login - Negative Scenario")
    void testLogin_Negative() throws IOException {
        // Creation of HTTP POST request for endpoint /login
        HttpPost request = new HttpPost(BASE_URL + "/login");
        // Set of request body with JSON data (invalid user data)
        request.setEntity(new StringEntity("{\"username\":\"invaliduser\", \"password\":\"wrongpassword\"}"));
        // Set header that content is JSON
        request.setHeader("Content-Type", "application/json");

        // Executing of The request and getting responce
        try (CloseableHttpResponse response = client.execute(request)) {
            // Logging of the responce with status code
            logger.info("POST /login negative response: " + response.getCode());
            // It's checking if it is responce code status 400 (Not found) for invalid login
            assertEquals(400, response.getCode(), "Expected status 400 for bad client request");
        }
    }

    // Test for POST /register - Pozitive scenario (valid data for registration)
    @Test
    @DisplayName("Test POST /register - Positive Scenario")
    void testRegister_Positive() throws IOException {
        // Creation of HTTP POST request for endpoint /register
        HttpPost request = new HttpPost(BASE_URL + "/register");
        // Set of request body with JSON data (valid data for registration)
        String jsonPayload = "{"
                + "\"username\":\"newuser\","
                + "\"password\":\"newpassword123\","
                + "\"email\":\"newuser@example.com\","
                + "\"firstName\":\"John\","
                + "\"lastName\":\"Doe\","
                + "\"middleName\":\"Michael\""
                + "}";
        request.setEntity(new StringEntity(jsonPayload));
        // Set header that content is JSON
        request.setHeader("Content-Type", "application/json");

        // Executing of The request and getting responce
        try (CloseableHttpResponse response = client.execute(request)) {
            // Logging of the responce with status code
            logger.info("POST /register response: " + response.getCode());
            // It's checking if it is responce code status 201 (Created) for successfull restration
            //Regardless teh documentation status code is set on OK status (200), it was put status 201 (Created) since this is the appropriate status for creation of new resource on server - For discussion
            assertEquals(201, response.getCode(), "Expected status 201 for successful registration");
        }
    }

    // Test for POST /register - Negative scenario (missing mandatory fields)
    @Test
    @DisplayName("Test POST /register - Negative Scenario")
    void testRegister_Negative() throws IOException {
        // Creation of HTTP POST request for endpoint /register
        HttpPost request = new HttpPost(BASE_URL + "/register");
        // Set of request body with JSON data (missing user name)
        String jsonPayload = "{"
                + "\"username\":\"\","
                + "\"password\":\"newpassword123\","
                + "\"email\":\"newuser@example.com\","
                + "\"firstName\":\"John\""
                + "}"; // missing user name
        request.setEntity(new StringEntity(jsonPayload));
        // Set header that content is JSON
        request.setHeader("Content-Type", "application/json");

        // Executing of the request and getting responce
        try (CloseableHttpResponse response = client.execute(request)) {
            // Logging of the responce with status code
            logger.info("POST /register negative response: " + response.getCode());
            // It's checking the responce status code 400 (Bad Request) for incomplete data 
            assertEquals(400, response.getCode(), "Expected status 400 for missing username");
        }
    }
    
    //sending GET request to server in order to get user data with ID which is forwarded toward URL
    // Test for GET /user/{user_id} - Pozitive scenario (valid user)
    @Test
    @DisplayName("Test GET /user/{user_id} - Positive Scenario")
    void testGetUser_Positive() throws IOException {
        // Creation of HTTP GET request for endpoint /user/{user_id} with valid user ID
        HttpGet request = new HttpGet(BASE_URL + "/user/1");
        try (CloseableHttpResponse response = client.execute(request)) {
            // Logging of the responce with status code
            logger.info("GET /user/1 response: " + response.getCode());
            // It's checking the responce status code 200(OK) for valid user ID
            assertEquals(200, response.getCode(), "Expected status 200 for valid user ID");

            // Likewise, it can be checked if responce contains expected data (i.e user name)
            String responseBody = new String(response.getEntity().getContent().readAllBytes());
            // it's checking if responce has user name
            assertTrue(responseBody.contains("\"username\":\"testuser\""), "Expected username to be in the response");
        }
    }

    // Test for GET /user/{user_id} - Negative scenario (non-existent user)
    @Test
    @DisplayName("Test GET /user/{user_id} - Negative Scenario")
    void testGetUser_Negative() throws IOException {
        // Creation of HTTP GET request for endpoint /user/{user_id} with invalid user ID
        HttpGet request = new HttpGet(BASE_URL + "/user/9999");
        try (CloseableHttpResponse response = client.execute(request)) {
            // Logging of the responce with status code
            logger.info("GET /user/9999 negative response: " + response.getCode());
            
            if (response.getStatusLine().getStatusCode() == 404) {
                logger.info("User not found: 404 Not Found");
            } else if (response.getStatusLine().getStatusCode() == 400) {
                logger.info("Bad request: 400 Token is required");
            } else if (response.getStatusLine().getStatusCode() == 401) {
                logger.info("Unauthorized: 401 Invalid token");
            }

            // It's checking if it is responce status code 404 (Not Found) for invalid user ID        
            assertEquals(404, response.getCode(), "Expected status 404 for non-existent user ID");
        }
    }
}