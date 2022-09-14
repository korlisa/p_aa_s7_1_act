package app.entities;

import app.util.Category;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@Data
public class SearchRoute {

    private String from;

    private String to;

    private String departureDate;

    // TODO Make usability over time when app is running
    //private String category;
}
