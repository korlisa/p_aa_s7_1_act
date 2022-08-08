package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Сущность Роль
 *
 * @author Minibaeva Elvira
 */


@NoArgsConstructor
@Data
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;


    @Override
    public String getAuthority() {
        return getName();
    }

    public Role(String name) {
        this.name = name;
    }
}
