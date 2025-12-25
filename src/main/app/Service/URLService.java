package app.Service;

import app.Database.URLs;
import app.Database.User;
import app.Repository.URLRepository;
import app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.Optional;
import java.util.Random;

@Service
public class URLService {

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 7;

    public String getShortURL(String longURL) {
        // Get currently logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByuserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ FIX 1: Check if THIS USER already has this URL
        Optional<URLs> urlsOptional = urlRepository.findByLongURLAndUser(longURL, user);
        if(urlsOptional.isPresent()) {
            return urlsOptional.get().getShortURL();
        }

        // Create new short URL
        String shortCode = generateShortCode();
        URLs url = new URLs();
        url.setLongURL(longURL);
        url.setShortURL(shortCode);
        url.setUser(user);  // ✅ FIX 2: SET THE USER! This was missing!

        urlRepository.save(url);
        return shortCode;
    }

    public String getLongURL(String shortURL) {
        Optional<URLs> urlsOptional = urlRepository.findByshortURL(shortURL);
        if(!urlsOptional.isPresent()) {
            throw new RuntimeException("ShortURL not present");
        }

        return urlsOptional.get().getLongURL();
    }

    public String generateShortCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            sb.append(BASE62.charAt(random.nextInt(BASE62.length())));
        }
        return sb.toString();
    }
}