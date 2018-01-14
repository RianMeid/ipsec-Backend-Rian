package Models;

import org.hibernate.validator.constraints.Email;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Rian on 13/01/2018.
 */
public class UserModel {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;

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



}
