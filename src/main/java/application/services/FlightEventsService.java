package application.services;

import application.data.FlightEventsRepository;
import application.model.FlightEvent;
import application.model.FlightEventSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FlightEventsService {
    private final FlightEventsRepository repo;
    public List<FlightEvent> findBySeat(FlightEventSeat seat){
        return repo.findBySeat(seat.getId());
    }
}
