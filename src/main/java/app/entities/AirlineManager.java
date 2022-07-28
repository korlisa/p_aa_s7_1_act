package app.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Сущность AirlineManager является наследником класса User.
 * Менеджер будет управлять авиапарком, рейсами, билетами.
 *
 * @author Minibaeva Elvira
 */


@Entity(name = "managers")
public class AirlineManager extends User{

}
