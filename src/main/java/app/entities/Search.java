package app.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Class Search
 *
 * @author Eugene Kolyshev
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "t_search")
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 //  @JoinColumn(name = "search_id")
    private List<Route> routes;

}
