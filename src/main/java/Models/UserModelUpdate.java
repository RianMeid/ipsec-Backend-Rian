package Models;

import org.hibernate.validator.constraints.Email;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Rian on 13/01/2018.
 */
public class UserModelUpdate {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    @Email
    private String newemail;
    @NotEmpty
    private String newpassword;

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }


    public String getNewemail() {
        return newemail;
    }

    public void setNewemail(String newemail) {
        this.newemail = newemail;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
