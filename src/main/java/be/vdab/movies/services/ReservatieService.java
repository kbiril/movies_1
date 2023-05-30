package be.vdab.movies.services;

import be.vdab.movies.domain.Reservatie;
import be.vdab.movies.dto.NieuweReservatie;
import be.vdab.movies.exceptions.FilmNotFoundException;
import be.vdab.movies.repositories.FilmRepository;
import be.vdab.movies.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReservatieService {
    private final ReservatieRepository reservatieRepository;
    private final FilmRepository filmRepository;

    public ReservatieService(ReservatieRepository reservatieRepository, FilmRepository filmRepository) {
        this.reservatieRepository = reservatieRepository;
        this.filmRepository = filmRepository;
    }
@Transactional
    public boolean reserveren (long filmId, NieuweReservatie newReservatie) {
        var film = filmRepository.findAndLockFilmById(filmId)
                .orElseThrow(
                        () -> new FilmNotFoundException(filmId)
                );
        film.reserveren();
        if (!filmRepository.updated(film)) {
            throw new FilmNotFoundException(filmId);
        }

        var reservatie = new Reservatie(newReservatie.klantId(), filmId);

        return reservatieRepository.reserveren(reservatie);
    }
}
