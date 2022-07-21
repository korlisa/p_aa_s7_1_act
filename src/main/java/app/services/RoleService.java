package app.services;

import app.entities.Role;
import app.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * В этом классе сервис для сущности Роль
 *
 * @author Minibaeva Elvira
 */
@NoArgsConstructor
@AllArgsConstructor
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleByName(String name){return roleRepository.findRoleByName(name);}
    public void saveRole (Role role) {
        roleRepository.save(role);
    }
}
