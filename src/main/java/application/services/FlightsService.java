package application.services;

import application.data.FlightsRepository;
import application.model.Flight;
import application.model.FlightEvent;
import application.model.FlightEventSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FlightsService {
    private final FlightsRepository repo;

    public List<Flight> list() {
        return repo.findAll();
    }

    public Flight save(Flight flight) {
        return repo.saveAndFlush(flight);
    }

    public Optional<Flight> findByFId(String fid){
        return repo.findByFid(fid);
    }

    public List<Flight> findByEvent(FlightEvent event){
        return repo.findByEvent(event.getId());
    }

}
