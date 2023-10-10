package library.demo.library.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDto {

    private int id;

    private String nameOfBook;

    private String numberOfPages;

    private LocalDate timeTaken;

    private LocalDate timeGiven;


}
