package Models;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by Rian on 13/01/2018.
 */

public class LoginModel {
    @NotEmpty
    public Boolean isadmin;
    @NotEmpty
    public Boolean loggedin;

    public void setIsadmin(Boolean isadmin){
        this.isadmin = isadmin;

    }
    public void setLoggedin(Boolean loggedin){
        this.loggedin = loggedin;
    }


}
