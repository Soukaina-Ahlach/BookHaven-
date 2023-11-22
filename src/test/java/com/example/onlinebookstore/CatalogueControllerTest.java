package com.example.onlinebookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.example.onlinebookstore")
public class CatalogueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCataloguePage() throws Exception {
        mockMvc.perform(get("/catalogue"))
                .andExpect(status().isOk());
    }

    @Test
    void testGenreCataloguePage() throws Exception {
        List<String> genres = Arrays.asList(
                "adventure","biography","comedy","cooking","fantasy","fiction","history","romance"
        );

        for (String genre : genres) {
            mockMvc.perform(get("/catalogue/" + genre))
                    .andExpect(status().isOk());
        }
    }

    @Test
    void testCheckoutPage() throws Exception {
        mockMvc.perform(get("/checkout"))
                .andExpect(status().isOk());
    }

}

