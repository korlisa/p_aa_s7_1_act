package app.entities;


import javax.persistence.Entity;


/**
 * Сущность Admin является наследником класса User.
 * Админ будет управлять созданием, удалением, изменением всех пользователей и добавлением ролей.
 *
 * @author Minibaeva Elvira
 */



@Entity(name = "admins")
public class Admin extends User {
}
