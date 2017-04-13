package request;

import java.math.BigDecimal;

/**
 * Created by gadzik on 12.04.17.
 */
public class SaveAccountRequest {

    private String email;
    private String password;
    private BigDecimal money;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
