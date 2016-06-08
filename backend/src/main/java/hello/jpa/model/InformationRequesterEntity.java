package hello.jpa.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class InformationRequesterEntity {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String username;

    private String password;

    private String surname;

    private String email;

    private String apiCallbackUrl;

    private String ftpURL;

    private String ftpLogin;

    private String ftpPass;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="users_roles",
            joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id"))
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy="owner")
    private Set<SubscriptionEntity> subscriptions;

    public InformationRequesterEntity() {
    }

    public InformationRequesterEntity(String username, String password, String surname, String email, String apiCallbackUrl, String ftpURL, String ftpLogin, String ftpPass, Set<RoleEntity> roles) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.email = email;
        this.apiCallbackUrl = apiCallbackUrl;
        this.ftpURL = ftpURL;
        this.ftpLogin = ftpLogin;
        this.ftpPass = ftpPass;
        this.roles = roles;
    }

    public InformationRequesterEntity(InformationRequesterEntity user) {
        this.id=user.getId();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.surname=user.getSurname();
        this.email=user.getEmail();
        this.apiCallbackUrl=user.getApiCallbackUrl();
        this.ftpURL=user.getFtpURL();
        this.ftpLogin=user.getFtpLogin();
        this.ftpPass=user.getFtpPass();
        this.roles=user.getRoles();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApiCallbackUrl() {
        return apiCallbackUrl;
    }

    public void setApiCallbackUrl(String apiCallbackUrl) {
        this.apiCallbackUrl = apiCallbackUrl;
    }

    public String getFtpURL() {
        return ftpURL;
    }

    public void setFtpURL(String ftpURL) {
        this.ftpURL = ftpURL;
    }

    public String getFtpLogin() {
        return ftpLogin;
    }

    public void setFtpLogin(String ftpLogin) {
        this.ftpLogin = ftpLogin;
    }

    public String getFtpPass() {
        return ftpPass;
    }

    public void setFtpPass(String ftpPass) {
        this.ftpPass = ftpPass;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
