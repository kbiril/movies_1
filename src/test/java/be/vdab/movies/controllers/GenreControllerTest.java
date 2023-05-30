package be.vdab.movies.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.jdbc.Sql;

import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@Sql({"/genres.sql", "/films.sql"})
@AutoConfigureMockMvc
class GenreControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String GENRES = "genres";
    private final static String FILMS = "films";
    private final MockMvc mockMvc;
    private long idTest1;
    private final static Path TEST_RESOURCES = Path.of("src/test/resources");

    GenreControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    private long idVanGenreTest1 () {
        return jdbcTemplate.queryForObject(
                "select id from genres where naam = 'test1'", Long.class);
    }

    @BeforeEach
    void beforeEach() { idTest1 = idVanGenreTest1(); }
    @Test
    void findAllGenres() throws Exception {
        mockMvc.perform(get("/genres"))
                .andExpectAll(
                                status().isOk(),
                                jsonPath("length()").value(countRowsInTable(GENRES))
                              );
    }

    @Test
    void findFilmsByGenreId() throws Exception {
        mockMvc.perform(get("/genres/{genreId}", idTest1))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("length()")
                                .value(countRowsInTableWhere(FILMS, "genreId = " + idTest1))
                );
    }

}