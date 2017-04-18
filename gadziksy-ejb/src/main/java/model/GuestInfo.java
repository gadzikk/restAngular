package model;

/**
 * Created by gadzik on 17.04.17.
 */
public class GuestInfo {

    private Long id;
    private String email;

    public GuestInfo() {
    }

    public GuestInfo(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
