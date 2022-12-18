package application.services;

import application.data.FlightsRepository;
import application.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
