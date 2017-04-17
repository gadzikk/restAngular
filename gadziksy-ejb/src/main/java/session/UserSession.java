package session;

import converter.LocalDateAdapter;

import javax.enterprise.context.SessionScoped;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by gadzik on 14.04.17.
 */
@SessionScoped
public class UserSession implements Serializable {

    private static final long serialUID = 213152232;

    private Boolean loggedIn;
    private String email;
    private LocalDate creationDate;
    private BigDecimal money;

    public UserSession() {
    }

    public UserSession(Boolean loggedIn, String email, LocalDate creationDate, BigDecimal money) {
        this.loggedIn = loggedIn;
        this.email = email;
        this.creationDate = creationDate;
        this.money = money;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "email='" + email + '\'' +
                ", creationDate=" + creationDate +
                ", money=" + money +
                '}';
    }
}
