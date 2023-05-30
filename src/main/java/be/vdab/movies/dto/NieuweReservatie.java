package be.vdab.movies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record NieuweReservatie(@NotNull @Positive long klantId) {

    @Override
    public String toString() {
        return "NieuweReservatie[klantId:" + klantId + "]";
    }
}
