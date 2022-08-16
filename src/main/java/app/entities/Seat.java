package app.entities;

import app.util.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setSeatNumber(String s) {
    }

    public void setCategory(Category category) {
    }

    public boolean isRegistered() {
        return true;
    }

    public boolean isSold() {
        return true;
    }

    public Object getCategory() {
        return app.entities.Category.BUSINESS;
    }
}
