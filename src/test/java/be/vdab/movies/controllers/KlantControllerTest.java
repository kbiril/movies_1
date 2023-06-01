package be.vdab.movies.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Sql("/klanten.sql")
@AutoConfigureMockMvc
class KlantControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String KLANTEN = "klanten";
    private final MockMvc mockMvc;

    KlantControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void findKlantenWoord() throws Exception {
        mockMvc.perform(get("/klanten")
                .param("woord", "test"))
                .andExpectAll(
                                status().isOk(),
                                jsonPath("length()").value(
                                countRowsInTableWhere(KLANTEN, "familienaam like '%test%'"))
                              );
    }
}