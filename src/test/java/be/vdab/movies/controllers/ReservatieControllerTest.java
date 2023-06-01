package be.vdab.movies.controllers;

import be.vdab.movies.dto.NieuweReservatie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Sql({"/genres.sql", "/films.sql", "/klanten.sql"})
@AutoConfigureMockMvc
class ReservatieControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String FILMS = "films";
    private final static String RESERVATIES = "reservaties";
    private final MockMvc mockMvc;

    private long filmIdTest1;
    private long filmIdTest2;
    private long klantIdTest1;

    private NieuweReservatie nieuweReservatie;

    ReservatieControllerTest (MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private long idVanFilmTest1 () {
        return jdbcTemplate.queryForObject(
                "select id from films where titel = 'titelTest1'", Long.class);
    }
    private long idVanKlantTest1 () {
        return jdbcTemplate.queryForObject(
                "select id from klanten where familienaam = 'test1' and voornaam = 'test1'", Long.class);
    }
    private long idVanFilmTest2 () {
        return jdbcTemplate.queryForObject(
                "select id from films where titel = 'titelTest2'", Long.class);
    }
    @BeforeEach
    void beforeEach() {
        filmIdTest1 = idVanFilmTest1();
        filmIdTest2 = idVanFilmTest2();
        klantIdTest1 = idVanKlantTest1();

        nieuweReservatie = new NieuweReservatie(klantIdTest1);
    }

    @Test
    void reserveren() throws Exception {
        mockMvc.perform(patch("/reservaties/{filmId}", filmIdTest1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(nieuweReservatie.toString()))
                .andExpect(status().isOk());
        assertThat(countRowsInTableWhere(FILMS,
                "id = " + filmIdTest1 + " and titel = 'titelTest1' and gereserveerd = 1")).isOne();
        assertThat(countRowsInTableWhere(RESERVATIES,
                "klantId = " + klantIdTest1 + " and filmId = " + filmIdTest1)).isOne();
    }

    @Test
    void reserverenVanUitverkochteFilmMislukt() throws Exception {
        mockMvc.perform(patch("/reservaties/{filmId}", filmIdTest2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nieuweReservatie.toString()))
                        .andExpect(status().isConflict());
    }

    @Test
    void reserverenVanOnbestaandeFilmMislukt() throws Exception {
        mockMvc.perform(patch("/reservaties/{filmId}", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nieuweReservatie.toString()))
                        .andExpect(status().isNotFound());
    }
}