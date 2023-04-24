package MiniDFlow.POJO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserRegForm implements Serializable {
    @NotEmpty(message = "username is empty")
    @Size(min = 6, max = 30,message = "username must be 6 to 30 range")
    private String username;
    @NotEmpty(message = "password is empty")
    @Size(min = 6,max = 30,message = "minimum 6 characters and maximum is 30")
    private String password;

    public UserRegForm() {
    }

    public UserRegForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
