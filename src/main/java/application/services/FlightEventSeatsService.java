package application.services;

import application.data.FlightEventSeatsRepository;
import application.data.FlightsRepository;
import application.model.Flight;
import application.model.FlightEventSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FlightEventSeatsService {
    private final FlightEventSeatsRepository repo;
    public List<FlightEventSeat> findByIp(String ip){
        return repo.findByIp(ip);
    }
}
