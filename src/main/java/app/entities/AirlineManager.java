package app.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Сущность AirlineManager является наследником класса User.
 * Менеджер будет управлять авиапарком, рейсами, билетами.
 *
 * @author Minibaeva Elvira
 */


@Entity
@Table(name = "managers")
public class AirlineManager extends User{

}
