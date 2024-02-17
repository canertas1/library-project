package library.demo.library.service;
import library.demo.library.dto.BookStockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class BookStockClientService {

    @Value("${demo.library.bookurl}")
    String url;

    public int getBookStock(String bookName) throws NullPointerException  {


            WebClient webClient = WebClient.create();

            BookStockDto bookStockDto = webClient.get()
                    .uri(url+bookName)
                    .retrieve()
                    .bodyToMono(BookStockDto.class)
                    .block();
            return bookStockDto.getBookStock();


    }

}
