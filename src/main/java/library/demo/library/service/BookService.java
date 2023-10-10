package library.demo.library.service;

import library.demo.library.dto.BookDto;
import library.demo.library.dto.BookUpdateDto;
import library.demo.library.entity.Book;
import library.demo.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository ;





    public Book save(BookDto bookDto){

        Optional<Book> book1 = bookRepository.findById(bookDto.getId());
        if (book1.isPresent())
            System.out.println("the book is already present");


        Book savedBook = new Book();
        savedBook.setNameOfBook(bookDto.getNameOfBook());
        savedBook.setNumberOfPages(bookDto.getNumberOfPages());

        return bookRepository.save(savedBook);
    }

    public void deleteBook(int bookId){

       Optional<Book> book = bookRepository.findById(bookId);

        if (book.isEmpty()){

            System.out.println("there is no book with  given the bookId");
            throw new RuntimeException("there is no book with given the bookId");

        }else {
            bookRepository.deleteById(bookId);

        }

    }


    public List<Book> getAllBook(){

        return bookRepository.findAll();
    }

    public Book getById(int id){

        return bookRepository.findById(id).get();
    }

    public Book update(BookUpdateDto bookUpdateDto, int bookId){

        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isEmpty())
            throw new RuntimeException("the bookDd is incorrect");

        if ((bookUpdateDto.getNameOfBook() != null))
        book.get().setNameOfBook(bookUpdateDto.getNameOfBook());

        if ((bookUpdateDto.getNumberOfPages() != null))
        book.get().setNumberOfPages(bookUpdateDto.getNumberOfPages());

        return bookRepository.save(book.get());

    }



}
