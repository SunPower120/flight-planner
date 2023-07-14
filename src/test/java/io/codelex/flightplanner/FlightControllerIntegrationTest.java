package io.codelex.flightplanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private String flightJson = "{\"from\": {\"country\":\"USA\", \"city\":\"New York\", \"airport\":\"JFK\"}," +
            "\"to\": {\"country\":\"UK\", \"city\":\"London\", \"airport\":\"Heathrow\"}," +
            "\"carrier\": \"American Airlines\"," +
            "\"departureTime\": \"2023-09-30T08:00:00\"," +
            "\"arrivalTime\": \"2023-09-30T20:00:00\"}";

    @Test
    @WithMockUser(username = "codelex-admin", password = "Password123")
    public void shouldCreateFlight() throws Exception {


        mockMvc.perform(put("/admin-api/flights")
                        .with(httpBasic("codelex-admin", "Password123")) // add basic auth
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(flightJson))
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser
    public void shouldClearFlights() throws Exception {
        mockMvc.perform(post("/testing-api/clear")).andExpect(status().isOk());
    }

}
