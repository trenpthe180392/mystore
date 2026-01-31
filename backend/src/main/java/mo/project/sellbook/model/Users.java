package mo.project.sellbook.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import org.hibernate.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "NVARCHAR(255)", nullable = false)
    private String userName;
    @Column(columnDefinition = "NVARCHAR(100)",nullable = false)
    private String number;
    @Column(length = 255,nullable = false)
    private String address;
    @Column(length = 100,nullable = false)
    private String email;
    @Column(length = 255,nullable = false)
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    @Column(length=20,nullable=false)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    @Column(length = 10,nullable = false)
    private UserGender gender;
    @Column(length = 255,nullable = true)
    private String avatarUrl;
    @Column(length=255,nullable = true)
    private String SocialAccount;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive;
    @OneToMany(mappedBy = "user")
    private List<Orders> orders = new ArrayList<>();
    public Users() {
    }
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Carts cart;

    public Users(Integer id, String userName, String number, String address, String email, String passwordHash, UserRole role, UserGender gender, String avatarUrl, String socialAccount, Boolean isActive) {
        this.id = id;
        this.userName = userName;
        this.number = number;
        this.address = address;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        SocialAccount = socialAccount;
        this.isActive = isActive;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
        return SocialAccount;
    }

    public void setSocialAccount(String socialAccount) {
        SocialAccount = socialAccount;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role=" + role +
                ", gender=" + gender +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", SocialAccount='" + SocialAccount + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
