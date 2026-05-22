package ru.omstu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class DataExtractorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExtractEndpoint() throws Exception {
        String jsonRequest = """
                {
                    "type": "json",
                    "data": "{\\"name\\": \\"Alex\\"}",
                    "path": "name"
                }
                """;

        mockMvc.perform(post("/api/data/extract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("Alex"));
    }
}
