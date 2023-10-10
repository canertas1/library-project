package library.demo.library.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoughtBookDto {

    private int userId;

    private int bookId;

    private  LocalDate givenTime;
}
