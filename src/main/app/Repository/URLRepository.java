package app.Repository;

import app.Database.URLs;
import app.Database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URLs, Long> {
    Optional<URLs> findByshortURL(String shortURL);
    Optional<URLs> findByLongURLAndUser(String longURL, User user);  // ✅ Add this!
}
