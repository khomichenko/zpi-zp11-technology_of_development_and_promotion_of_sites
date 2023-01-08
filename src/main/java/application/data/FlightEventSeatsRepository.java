package application.data;

import application.model.Flight;
import application.model.FlightEventSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FlightEventSeatsRepository extends JpaRepository<FlightEventSeat, Long>, JpaSpecificationExecutor<FlightEventSeat> {
    List<FlightEventSeat> findByIp(String ip);
}
