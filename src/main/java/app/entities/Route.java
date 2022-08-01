package app.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity class for Route
 * @author Andrey Mitukov
 */

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Component
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_from_id")
    @NonNull
    private Destination from;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_to_id")
    @NonNull
    private Destination to;

    @Column
    @NonNull
    LocalDate departureDate;

    @Column
    @NonNull
    LocalDate arrivalDate;

    @Column
    @NonNull
    Integer numberOfSeats;

    @Enumerated(EnumType.STRING)
    Category category;
}
