package app.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity(name = "passengers")
public class Passenger extends User {
}
