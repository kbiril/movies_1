package be.vdab.movies.controllers;

import be.vdab.movies.dto.NieuweReservatie;
import net.minidev.json.JSONValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@Sql({"/genres.sql", "/films.sql", "/klanten.sql"})
@AutoConfigureMockMvc
class ReservatieControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String GENRES = "genres";
    private final static String FILMS = "films";
    private final static String KLANTEN = "klanten";
    private ObjectMapper mapper = new ObjectMapper();
    private final MockMvc mockMvc;
    private long genreIdTest1;
    private long filmIdTest1;
    private long klantIdTest1;

    private NieuweReservatie nieuweReservatie;
    private final static Path TEST_RESOURCES = Path.of("src/test/resources");

    ReservatieControllerTest (MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    private long idVanGenreTest1 () {
        return jdbcTemplate.queryForObject(
                "select id from genres where naam = 'test1'", Long.class);
    }

    private long idVanFilmTest1 () {
        return jdbcTemplate.queryForObject(
                "select id from films where titel = 'titelTest1'", Long.class);
    }
    private long idVanKlantTest1 () {
        return jdbcTemplate.queryForObject(
                "select id from klanten where familienaam = 'test1' and voornaam = 'test1'", Long.class);
    }
    @BeforeEach
    void beforeEach() {
        genreIdTest1 = idVanGenreTest1();
        filmIdTest1 = idVanFilmTest1();
        klantIdTest1 = idVanKlantTest1();

        nieuweReservatie = new NieuweReservatie(idVanKlantTest1());
    }

//    @Test
//    void reserveren() throws Exception {
//        mockMvc.perform(patch("/reservaties/{filmId}", filmIdTest1)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.readValue(nieuweReservatie))
//                .
//        )
//    }
}