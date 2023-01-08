package application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "flights")
@EqualsAndHashCode() @NoArgsConstructor @RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Flight {
    @Id
    @ToString.Include @Column(nullable=false, updatable=false) @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp @JsonIgnore
    @Column(nullable = false, updatable = false) private Date created;

    @Basic @Column(name="_fid") @NonNull private String fid;

    @Basic @Column(name="_from") @NonNull private String from;

    @Basic @Column(name="_to") @NonNull private String to;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) @JoinColumn(name = "flight_id")
    private List<FlightEvent> events;

    public Flight(String fid, String from, String to,List<FlightEvent> events){
        this(fid,from,to);
        this.setEvents(events);
    }
}
