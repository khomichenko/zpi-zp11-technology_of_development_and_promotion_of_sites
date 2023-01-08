package application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Data
@Table(name = "flights_events_seats")
@EqualsAndHashCode() @NoArgsConstructor
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true) @JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightEventSeat {
    @Id
    @ToString.Include @Column(nullable=false, updatable=false) @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp @JsonIgnore
    @Column(nullable = false, updatable = false) private Date created;

    @Basic @Column(name="_sid") @NonNull private String sid;

    @Basic @Column(name="price") @NonNull private Integer price;

    @Basic @Column(name="username") private String username;
    @Basic @Column(name="passport") private String passport;
    @Basic @Column(name="comment") private String comment;
    @Basic @Column(name="ticket") private String ticket;
    @Basic @Column(name="ip") private String ip;
    @ManyToOne() @JsonIgnore
    private FlightEvent flightEvent;

    public static List<FlightEventSeat> randoms() {
        List<FlightEventSeat> list = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            for (char a = 'a'; a <='f'; a++ ){
                Integer price = ThreadLocalRandom.current().nextInt(200, 2000);
                String username = Math.random() < 0.7 ? new Faker().name().fullName() : null;
                FlightEventSeat fs = new FlightEventSeat(""+(i+1)+(""+a).toUpperCase(),price);
                if (username!=null) {
                    fs.setUsername(username);
                }
                list.add(fs);
            }
        }
        //
        // 1A 1B 1C   1D 1E 1F
        // 2A 2B 2C   2D 2E 2F
        // 3A 3B 3C   3D 3E 3F
        // ...
        // 19A 19B 19C   19D 19E 19F
        return list;
    }
}
