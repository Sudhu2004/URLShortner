package app.Service;

import app.Database.User;
import app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public void registerUser(
            User user
    ) {
        System.out.println("Registering User: ");
        if(userRepository.findByuserName(user.getUserName()).isPresent()) {
            throw new RuntimeException("User Name Already Exists");
        }

        if (userRepository.findByemail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        System.out.println("Setting Password...");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        System.out.println("Saving User...");
        userRepository.save(user);
        System.out.println("User saved: " + user.getUserName());
    }
}
