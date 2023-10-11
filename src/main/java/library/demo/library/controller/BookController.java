package library.demo.library.controller;
import library.demo.library.dto.BookDto;
import library.demo.library.dto.BookUpdateDto;
import library.demo.library.entity.Book;
import library.demo.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

   private final BookService bookService;



    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody BookDto bookDto){

        return new ResponseEntity<>(bookService.save(bookDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){

        bookService.deleteBook(id);
        return new ResponseEntity<>("The book has been deleted",HttpStatus.OK);
    }




    @GetMapping("/{id}")
    public  ResponseEntity<Book> getById( @PathVariable(value = "id") int id){

        return new ResponseEntity<>(bookService.getById(id) , HttpStatus.OK);
    }


    // bütün bookları dönemessin page işlemi yap

    @GetMapping("/list")
    public  ResponseEntity<List<Book>> list(){

        return new ResponseEntity<>(bookService.getAllBook() , HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Book> update(@RequestBody BookUpdateDto bookUpdateDto, @PathVariable  int id){

        return new ResponseEntity<>(bookService.update(bookUpdateDto,id),HttpStatus.OK);


    }



}
