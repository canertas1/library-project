package library.demo.library.controller;

import library.demo.library.dto.BoughtBookDto;
import library.demo.library.dto.UserDto;
import library.demo.library.entity.User;
import library.demo.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;




    @PostMapping("/userRegister")
    public ResponseEntity<String> userRegister( @RequestBody UserDto userDto){

        userService.userRegister(userDto);
       return new ResponseEntity<>("The user has been registered",HttpStatus.OK);

    }


    // o
    @PostMapping("/userTakeBook")
    public ResponseEntity<String>  userTakeBook(@RequestBody BoughtBookDto boughtBookDto){


        userService.userTakeBook(boughtBookDto);
        return ResponseEntity.ok("The user bought the book");
    }



    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser( @RequestParam(value = "id",required = false)  Optional<Integer> userId){

        return new ResponseEntity<>(userService.getAllUser(userId), HttpStatus.OK);
    }

    @PostMapping("/userGiveBookToLibrary/{id}")
    public ResponseEntity<User> userGiveBookToLibrary(@PathVariable int id){

        return new ResponseEntity<>(userService.UserGiveBookToLibrary(id),HttpStatus.OK);

    }

    @DeleteMapping("/deleteUser/{id}")
    public  ResponseEntity<String> deleteUser(@PathVariable int id){

        userService.deleteUser(id);
        return ResponseEntity.ok("User has been deleted");

    }


}
