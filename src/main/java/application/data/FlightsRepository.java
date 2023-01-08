package application.data;

import application.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlightsRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
    Optional<Flight> findByFid(String _fid);
    @Query(value = "SELECT e.flight FROM FlightEvent e WHERE e.id=:event")
    List<Flight> findByEvent(@Param("event") Long eventId);
}