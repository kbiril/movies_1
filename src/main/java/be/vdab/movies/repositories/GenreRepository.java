package be.vdab.movies.repositories;

import be.vdab.movies.domain.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepository {
    private final JdbcTemplate template;

    public GenreRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Genre> rowMapper =
            (result, rowNum) -> new Genre(
                    result.getLong("id"),
                    result.getString("naam")
            );

    public List<Genre> findAllGenres() {
        var sql = """
                    select id, naam
                    from genres
                    order by naam
                  """;
        return template.query(sql, rowMapper);
    }

}
