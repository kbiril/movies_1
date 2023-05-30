package be.vdab.movies.controllers;

import be.vdab.movies.domain.Film;
import be.vdab.movies.services.FilmService;
import be.vdab.movies.services.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import be.vdab.movies.domain.Genre;

import java.util.List;

@RequestMapping("genres")
@RestController
class GenreController {
    private final GenreService genreService;
    private final FilmService filmService;

    GenreController(GenreService genreService, FilmService filmService) {
        this.genreService = genreService;
        this.filmService = filmService;
    }
    @GetMapping
    List<Genre> findAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping ("{genreId}")
    List<Film> findFilmsByGenreId(@PathVariable long genreId) {
        return filmService.findFilmsByGenreId(genreId);
    }

}
