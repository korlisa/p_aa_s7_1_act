package app.entities;

import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchResult{

    private List<List<Flight>> listSearch;


    public void add() {
    }
}
