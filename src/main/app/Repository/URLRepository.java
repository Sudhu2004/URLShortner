package app.Repository;

import app.Database.URLs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLRepository extends JpaRepository<URLs, Long> {
    Optional<URLs> findBylongURL(String longURL);
    Optional<URLs> findByshortURL(String shortURL);
}
