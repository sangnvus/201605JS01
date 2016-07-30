package vn.edu.fu.veazy.core.form;

public class UpdateUserForm {
    private String firstName;
    private String lastName;
    private Long dob;
    private String address;
    private String hobby;
    private String bio;
    private String website;
    private String quote;

    public UpdateUserForm() {
        super();
    }

    public UpdateUserForm(String firstName, String lastName, Long dob, String address, String hobby, String bio,
            String website, String quote) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.hobby = hobby;
        this.bio = bio;
        this.website = website;
        this.quote = quote;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
    
}
