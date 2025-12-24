package app.Service;

import app.Database.URLs;
import app.Repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class URLService {

    @Autowired
    private URLRepository urlRepository;

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 7;

    public String getShortURL(String longURL) {
        Optional<URLs> urLsOptional = urlRepository.findBylongURL(longURL);
        if(urLsOptional.isPresent()) {
            return urLsOptional.get().getShortURL();
        }
        String shortCode = generateShortCode();
        URLs url = new URLs();
        url.setLongURL(longURL);
        url.setShortURL(shortCode);

        urlRepository.save(url);
        return shortCode;
    }

    public String getLongURL(String shortURl) {
        Optional<URLs> urLsOptional = urlRepository.findByshortURL(shortURl);
        if(!urLsOptional.isPresent()) {
            throw new RuntimeException("ShortURL not present");
        }

        return urLsOptional.get().getLongURL();
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
