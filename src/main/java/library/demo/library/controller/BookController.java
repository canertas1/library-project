package library.demo.library.controller;
import library.demo.library.dto.BookDto;
import library.demo.library.dto.BookStockDto;
import library.demo.library.dto.BookUpdateDto;
import library.demo.library.entity.Book;
import library.demo.library.service.BookService;
import library.demo.library.service.BookStockClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

   private final BookService bookService;
   private final BookStockClientService bookStockClientService;





    // event fırlatılacak ve karşı tarafta stockserviside o eventi  yakalasın ve ona göre olay gerçeklesin
    // kafka çalışması
    // cassandara rabbitmq  liqibase
    // spring ile ilgili teknolojilere yüzeysel bilgiler
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
