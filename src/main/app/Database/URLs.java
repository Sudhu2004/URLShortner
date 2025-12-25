package app.Database;

import jakarta.persistence.*;

@Entity
@Table(name = "urls",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "longURL"})  // ✅ Add this!
        })
public class URLs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String longURL;

    @Column(length = 2048)
    private String shortURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public URLs() {
    }

    public URLs(String longURL, String shortURL, User user) {
        this.longURL = longURL;
        this.shortURL = shortURL;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
