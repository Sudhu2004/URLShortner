package app.Database;

import jakarta.persistence.*;

@Entity
@Table(name = "urls")
public class URLs {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(unique = true)
    private String longURL;

    @Column(length = 2048)
    private String shortURL;

    public URLs() {
    }

    public URLs(long id, String longURL, String shortURL) {
        Id = id;
        this.longURL = longURL;
        this.shortURL = shortURL;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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
}
