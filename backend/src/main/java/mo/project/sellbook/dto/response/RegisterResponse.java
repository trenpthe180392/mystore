package mo.project.sellbook.dto.response;


public class RegisterResponse {

    private Integer id;
    private String userName;
    private String email;
    private String number;
    private String role;
    private Boolean isActive;
    private String avatarUrl;

    public RegisterResponse() {}

    public RegisterResponse(Integer id, String userName, String email,
                            String number, String role,
                            Boolean isActive, String avatarUrl) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.number = number;
        this.role = role;
        this.isActive = isActive;
        this.avatarUrl = avatarUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
