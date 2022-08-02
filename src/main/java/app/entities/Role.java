package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Сущность Роль
 *
 * @author Minibaeva Elvira
 */

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;


    @Override
    public String getAuthority() {
        return getName();
    }
}
