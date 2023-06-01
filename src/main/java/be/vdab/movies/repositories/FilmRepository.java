package be.vdab.movies.repositories;
import be.vdab.movies.domain.Film;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FilmRepository {
    private final JdbcTemplate template;

    public FilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Film> rowMapper =
            (result, rowNum) -> new Film(
                    result.getLong("id"),
                    result.getLong("genreId"),
                    result.getString("titel"),
                    result.getInt("voorraad"),
                    result.getInt("gereserveerd"),
                    result.getBigDecimal("prijs")
            );

    public List<Film> findFilmsByGenreId(long genreId) {
        var sql = """
                    select id, genreId, titel, voorraad, gereserveerd, prijs
                    from films
                    where genreId = ?
                    order by titel
                  """;

        return template.query(sql, rowMapper, genreId);
    }

    public Optional<Film> findById(long id) {
        try {
            var sql = """
                    select id, genreId, titel, voorraad, gereserveerd, prijs
                    from films
                    where id = ?
                  """;
            return Optional.of(template.queryForObject(sql, rowMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
    public Optional<Film> findAndLockFilmById (long id) {
        try {
            var sql = """
                        select id, genreId, titel, voorraad, gereserveerd, prijs
                        from films
                        where id = ?
                        for update
                      """;
            return Optional.of(template.queryForObject(sql, rowMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    public boolean updated (Film film) {
        var sql = """
                    update films
                    set gereserveerd = gereserveerd + 1
                    where id = ?
                  """;
        return template.update(sql, film.getId()) == 1;
    }

}
