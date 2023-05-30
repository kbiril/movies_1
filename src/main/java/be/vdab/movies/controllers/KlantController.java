package be.vdab.movies.controllers;

import be.vdab.movies.domain.Klant;
import be.vdab.movies.services.KlantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("klanten")
@RestController
public class KlantController {

    private final KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping(params = "woord")
    List<Klant> findKlantenWoord (String woord) {
        return klantService.findKlantenWoord(woord);
    }
}
