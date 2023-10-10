package library.demo.library.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book")
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")

    private  User user;

    private String nameOfBook;

    private String numberOfPages;

    private LocalDate timeTaken;

    private LocalDate timeGiven;


}
