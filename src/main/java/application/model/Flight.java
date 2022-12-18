package application.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "flights")
@EqualsAndHashCode() @NoArgsConstructor @RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Flight {
    @Id
    @ToString.Include @Column(nullable=false, updatable=false) @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(nullable = false, updatable = false) private Date created;

    @Basic @Column @NonNull private String _fid;

    @Basic @Column @NonNull private String _from;

    @Basic @Column @NonNull private String _to;


}
