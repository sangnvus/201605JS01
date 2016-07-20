package vn.edu.fu.veazy.core.form;

/**
 * @author minhnn
 *
 */
public class RegisterForm {

    private String username;
    private String password;
    private String email;

    public RegisterForm() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
