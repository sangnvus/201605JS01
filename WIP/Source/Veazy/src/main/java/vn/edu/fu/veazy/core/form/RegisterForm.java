package vn.edu.fu.veazy.core.form;

/**
 * @author minhnn
 *
 */
public class RegisterForm {

    private String username;
    private String encryptedPassword;
    private String email;

    public RegisterForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
