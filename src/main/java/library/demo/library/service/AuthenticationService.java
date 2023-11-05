package library.demo.library.service;
import library.demo.library.config.JwtService;
import library.demo.library.dto.AuthenticationRequest;
import library.demo.library.dto.AuthenticationResponse;
import library.demo.library.dto.RegisterRequest;
import library.demo.library.dto.ResetPasswordDto;
import library.demo.library.entity.ConfirmationToken;
import library.demo.library.entity.PasswordResetToken;
import library.demo.library.entity.Role;
import library.demo.library.entity.User;
import library.demo.library.repository.ConfirmationRepository;
import library.demo.library.repository.PasswordResetTokenRepository;
import library.demo.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationRepository confirmationRepository;
    private final UserRepository userRepository;
    private  final EmailService emailService;

    private final PasswordResetTokenRepository passwordResetTokenRepository;



    public AuthenticationResponse register(RegisterRequest registerRequest) {

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setRole(Role.USER);
        user.setName(registerRequest.getName());
        user.setEnabled(false);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        repository.save(user);


        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationRepository.save(confirmationToken);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Complete Registration");
        simpleMailMessage.setText("To confirm your account, please click here :" +
                " "+"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(simpleMailMessage);




        String token = jwtService.generateToken(user);
        AuthenticationResponse auth = new AuthenticationResponse();
        auth.setToken(token);
        return auth;
    }

    public void confirmMail(String confirmationToken){

        ConfirmationToken confirmation = confirmationRepository.findByConfirmationToken(confirmationToken);



        if(confirmation != null){

            User user = userRepository.findByEmail(confirmation.getUser().getEmail()).get();
            user.setEnabled(true);
            userRepository.save(user);
        }else{

            throw new IllegalArgumentException("This confirmation token is incorrect : "+confirmationToken);
        }

    }

    public void  resetPassword(String email){

        User user = userRepository.findByEmail(email).orElseThrow();

        if (user == null)
            throw new IllegalArgumentException("Username not found exception");


        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(user,token);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Reset Password");
        simpleMailMessage.setText("To reset your password, please click here :" +
                " "+"http://localhost:8080/reset-password?token="+token);
        emailService.sendEmail(simpleMailMessage);

    }

    public void confirmResetToken(ResetPasswordDto dto){

       String temp = validatePasswordResetToken(dto.getToken());


       User user = userRepository.findByEmail(dto.getEmail()).get();

       if (user.getEmail().equals(dto.getEmail())  &&
               passwordEncoder.matches(dto.getPassword(), user.getPassword
                       ()) == true && temp.equals("access")){

           user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
           userRepository.save(user);

       }else {
           throw new IllegalArgumentException("User email or password is incorrect ");

       }
    }

    public void createPasswordResetTokenForUser(User user,String token){

        PasswordResetToken passwordResetToken = new PasswordResetToken(user,token);

        passwordResetTokenRepository.save(passwordResetToken);

    }

    public String validatePasswordResetToken(String token){

        final PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);

         if(passToken != null && isTokenExpired(passToken)==false){
             return "access";
         }else {
             return null;
         }


    }

    public boolean isTokenExpired(PasswordResetToken token){

        final Calendar calendar = Calendar.getInstance();
        return token.getExpiryDate().before(calendar.getTime());
    }


    public void forgetPassword(String email){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText("Click on the link below to reset your password");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Reset Password");
        emailService.sendEmail(simpleMailMessage);

    }


    public AuthenticationResponse authentication(AuthenticationRequest request) {

        if(request.getEmail() == null || request.getPassword() == null)
            throw new IllegalArgumentException("Email or password can't be empty");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow();


        String token = jwtService.generateToken(user);
        AuthenticationResponse auth = new AuthenticationResponse();
        auth.setToken(token);
        return auth;
    }
}
