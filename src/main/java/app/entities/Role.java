package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность Роль
 *
 * @author Minibaeva Elvira
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    ///////////////////////////////-добавленное
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    /////////////////////////////////////////////

    @Override
    public String getAuthority() {
        return getName();
    }

    public Role(String name) {
        this.name = name;
    }
}
