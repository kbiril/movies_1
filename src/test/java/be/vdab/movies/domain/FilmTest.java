package be.vdab.movies.domain;

import be.vdab.movies.exceptions.SoldOutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class FilmTest {
    private Film test1;
    private Film test2;
    @BeforeEach
    void beforeEach() {
        test1 = new Film(1L, "test1", 10, 9, BigDecimal.ONE);
        test2 = new Film(1L, "test2", 10, 10, BigDecimal.ONE);
    }
    @Test
    void reserveren() {
        test1.reserveren();
        assertThat(test1.getGereserveerd()).isEqualTo(10);
    }

    @Test
    void reserverenVanUitverkochteFilmMislukt() {
        assertThatExceptionOfType(SoldOutException.class).isThrownBy(
                () -> test2.reserveren()
        );
    }
}