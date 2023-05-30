package be.vdab.movies.services;

import be.vdab.movies.domain.Klant;
import be.vdab.movies.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class KlantService {
    private final KlantRepository klantRepository;

    public KlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    public List<Klant> findKlantenWoord (String woord) {
        return klantRepository.findKlantenWoord(woord);
    }

}
