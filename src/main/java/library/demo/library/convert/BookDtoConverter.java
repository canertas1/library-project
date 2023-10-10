package library.demo.library.convert;

import library.demo.library.dto.BookDto;
import library.demo.library.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter {

    private final ModelMapper modelMapper;

    public BookDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public BookDto convertBookToBookDto(Book book){

        BookDto bookDto = modelMapper.map(book,BookDto.class);
        return bookDto;
    }

}
