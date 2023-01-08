package application.data;

import application.model.FlightEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightEventsRepository  extends JpaRepository<FlightEvent, Long>, JpaSpecificationExecutor<FlightEvent> {
    @Query(value = "SELECT s.flightEvent FROM FlightEventSeat s WHERE s.id=:seat")
    List<FlightEvent> findBySeat(@Param("seat") Long seatId);
}