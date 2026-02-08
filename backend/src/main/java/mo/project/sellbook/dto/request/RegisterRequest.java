package mo.project.sellbook.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import mo.project.sellbook.model.UserGender;

public class RegisterRequest {

    @NotBlank
    @Size(max = 255)
    private String userName;

    @NotBlank
    @Size(max = 100)
    private String number; // số điện thoại

    @NotBlank
    @Size(max = 255)
    private String address;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @NotNull
    private UserGender gender;

    private String avatarUrl;

    private String socialAccount;

    public RegisterRequest(String userName, String number, String address, String email, String password, UserGender gender, String avatarUrl, String socialAccount) {
        this.userName = userName;
        this.number = number;
        this.address = address;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.socialAccount = socialAccount;
    }

    public RegisterRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSocialAccount() {
        return socialAccount;
    }

    public void setSocialAccount(String socialAccount) {
        this.socialAccount = socialAccount;
    }
}
