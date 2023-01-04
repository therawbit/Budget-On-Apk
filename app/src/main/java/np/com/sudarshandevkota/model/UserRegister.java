package np.com.sudarshandevkota.model;

import androidx.annotation.Size;

public class UserRegister {

    private String name;
    private String username;
    private String password;
    private String confirmPassword;

    public UserRegister(String name, String username, String password, String confirmPassword) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
