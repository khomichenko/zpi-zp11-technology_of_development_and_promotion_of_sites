package application.controllers.results;

import application.model.Flight;
import application.model.FlightEvent;
import application.model.FlightEventSeat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
public class FlightEventSeatResult {
    private Flight flight;
    private FlightEvent event;
    private final FlightEventSeat seat;
}
