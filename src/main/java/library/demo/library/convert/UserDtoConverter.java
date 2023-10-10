package library.demo.library.convert;

import library.demo.library.dto.UserDto;
import library.demo.library.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {


    private final ModelMapper modelMapper;

    public UserDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convertUserToUserDto(User user){

        UserDto userDto = modelMapper.map(user,UserDto.class);

        return userDto;
    }
}
