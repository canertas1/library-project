package library.demo.library.repository;

import library.demo.library.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfirmationRepository extends JpaRepository<ConfirmationToken,Long> {


    ConfirmationToken findByConfirmationToken(String confirmationToken);


}
