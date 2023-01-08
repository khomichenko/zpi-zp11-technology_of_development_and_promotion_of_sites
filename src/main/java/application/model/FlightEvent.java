package application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Data
@Table(name = "flights_events")
@EqualsAndHashCode() @NoArgsConstructor
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true) @JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightEvent {

    @Id
    @ToString.Include @Column(nullable=false, updatable=false) @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp @JsonIgnore
    @Column(nullable = false, updatable = false) private Date created;

    @Column(nullable = false, updatable = false) @NonNull private Date departure;
    @Column(nullable = false, updatable = false) @NonNull private Date arrival;

    @ManyToOne() @JsonIgnore
    private Flight flight;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) @JoinColumn(name = "flight_event_id")
    private List<FlightEventSeat> seats;

    public static FlightEvent random(Date start, Date end) {
        FlightEvent event = new FlightEvent();
        event.departure = new Date(ThreadLocalRandom.current().nextLong(start.getTime(), end.getTime()));
        event.arrival = new Date(ThreadLocalRandom.current().nextLong(event.departure.getTime(), event.departure.getTime()+8*60*60*1*1000));
        event.seats = FlightEventSeat.randoms();
        return event;
    }

    public static List<FlightEvent> randoms(Date start, Date end, Integer count) {
        List<FlightEvent> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(random(start,end));
        }
        return list;
    }

    public static List<FlightEvent> randoms(Integer count) {
        return randoms(new Date(), new Date(new Date().getTime()+24*60*60*1*1000),count);
    }

}
