package be.vdab.movies.controllers;

import be.vdab.movies.dto.NieuweReservatie;

import be.vdab.movies.services.ReservatieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("reservaties")
@RestController
public class ReservatieController {
    private final ReservatieService reservatieService;

    public ReservatieController(ReservatieService reservatieService) {
        this.reservatieService = reservatieService;
    }

    @PatchMapping("{filmId}")
    boolean reserveren (@PathVariable long filmId, @RequestBody @Valid NieuweReservatie newReservatie) {
        return reservatieService.reserveren(filmId, newReservatie);
    }
}
