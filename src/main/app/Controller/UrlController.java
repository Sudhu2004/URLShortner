package app.Controller;

import app.Service.URLService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
public class UrlController {

    @Autowired
    private URLService urlService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/shortenURL")
    public String shortenURL(
            @RequestParam String longURL,
            HttpServletRequest request,
            Model model
    ) {
        String shortURL = urlService.getShortURL(longURL);
        String baseUrl = request.getScheme() + "://" +
                request.getServerName() + ":" +
                request.getServerPort();

        String fullShortUrl = baseUrl + "/" + shortURL;

        model.addAttribute("longURL", longURL);
        model.addAttribute("shortCode", shortURL);
        model.addAttribute("shortUrl", fullShortUrl);
        return "home";
    }

    @GetMapping("/{shortURL}")
    public ResponseEntity<Void> redirect(
            @PathVariable("shortURL") String shortCode) {

        String longUrl = urlService.getLongURL(shortCode);
        if(longUrl.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }

}
