package be.vdab.movies.repositories;

import be.vdab.movies.domain.Reservatie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ReservatieRepository {
    private final JdbcTemplate template;

    public ReservatieRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Reservatie> rowMapper =
            (result, rowNum) -> new Reservatie(
                    result.getLong("klantId"),
                    result.getLong("filmId"),
                    result.getObject("reservatie", LocalDateTime.class)
            );

    public boolean reserveren (Reservatie reservatie) {
        var sql = """
                    insert into reservaties (klantId, filmId, reservatie)
                    values (?, ?, ?)
                  """;

        return template.update(sql,
                reservatie.getKlantId(), reservatie.getFilmId(), reservatie.getReservatie()) == 1;
    }
}
