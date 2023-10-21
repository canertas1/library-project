package library.demo.library.service;
import library.demo.library.dto.BoughtBookDto;
import library.demo.library.dto.UserDto;
import library.demo.library.entity.Book;
import library.demo.library.entity.User;
import library.demo.library.repository.BookRepository;
import library.demo.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final  BookRepository bookRepository;




    public void userRegister(UserDto userDto) {

       Optional<User> user1 = userRepository.findById(userDto.getId());


       if (!user1.isPresent()){
         userRepository.save(user1.get());
       }else {
          System.out.println(" User already registered");
       }
    }

    public User userTakeBook(BoughtBookDto boughtBookDto){
        Optional<User> userTemp = userRepository.findById(boughtBookDto.getUserId());
        Optional<Book> bookTemp = bookRepository.findById(boughtBookDto.getBookId());
        LocalDate date = LocalDate.now();


        if (bookTemp.get().getTimeTaken() != null){
            System.out.println("The book has taken ");
            return  null;
        }
        if (userTemp.isEmpty())
            return null;

        Book bookGiven = new Book();
        bookGiven.setNameOfBook(bookTemp.get().getNameOfBook());
        bookGiven.setUser(userTemp.get());
        bookGiven.setNumberOfPages(bookTemp.get().getNumberOfPages());
        bookGiven.setId(bookTemp.get().getId());
        bookGiven.setTimeGiven(boughtBookDto.getGivenTime());
        bookGiven.setTimeTaken(date);
        this.bookRepository.save(bookGiven);


        return userTemp.get();
    }
    public User UserGiveBookToLibrary(int userId){

        Optional<User> user = userRepository.findById(userId);


        if (!user.isPresent())
        {
            System.out.println("The userId is incorrect");
            return null;
        }

        List<Book> books = bookRepository.findAllByUserId(userId);


        for(Book book:books){
            book.setUser(null);
            book.setTimeGiven(null);
            book.setTimeTaken(null);

            bookRepository.save(book);
        }
        return user.get();
    }



    public List<User> getAllUser(Optional<Integer> userId){



        if (userId.isPresent()){

            return  userRepository.findById(userId.get()).stream().toList();
        }else
            return userRepository.findAll();

    }

    public void  deleteUser(int userId){

       User user =  UserGiveBookToLibrary(userId);

        Optional<User> user1 = userRepository.findById(userId);


        userRepository.delete(user1.get());
    }




    }


