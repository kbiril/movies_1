package be.vdab.movies.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record NieuweReservatie(@NotNull @Positive long klantId) {

    @Override
    public String toString() {
        return "{\n\"klantId\":" + klantId + "\n}";
    }
}
