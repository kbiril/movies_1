package be.vdab.movies.services;

import be.vdab.movies.domain.Film;
import be.vdab.movies.exceptions.FilmNotFoundException;
import be.vdab.movies.repositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findFilmsByGenreId(long genreId) {
        return filmRepository.findFilmsByGenreId(genreId);
    }

    public Film findById(long id) {
        return filmRepository.findById(id).orElseThrow(
                () -> new FilmNotFoundException(id)
        );
    }
}
