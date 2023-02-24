package app.dto;

import app.entities.Destination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FlightDto {

    private Destination from;

    private Destination to;

    private LocalDate departureDateTime;

    private LocalDate arrivalDateTime;
}