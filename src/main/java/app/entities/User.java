package app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.HashSet;
/**
 * Сущность User нужен для SpringSecurity
 * Сущность User является родительской для классов: Admin, AirlineManager, Passenger.
 * Для хранения пользователей выбрана стратегия Joined.
 *
 * @author Minibaeva Elvira
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "email", unique = true) //unique -уникальный
    @Email
    @NotEmpty
    private String email;
    @Column(name = "password")
    @NotEmpty
    private String password;
    @Column(name = "hashPassword")
    @NotEmpty
    private String hashPassword;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    @JsonManagedReference
    private Passenger passenger;

    @Transient
    private String confirm;
    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn( name= "user_id"),
            inverseJoinColumns = @JoinColumn( name= "role_id"))
    private Collection<Role> roles;

    /**
     * Метод для добавления роли в коллекцию ролей
     * @param role
     */
    public void addRoleToCollection(Role role) {
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Конструктор, нужен для аутентификации, используется в UserService
     * в методе loadUserByUsername()
     * @param email
     * @param password
     * @param roles
     */
    public User(String email, String password, Collection<Role> roles, Passenger passenger) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.passenger = passenger;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addRoleToUser(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }
}
