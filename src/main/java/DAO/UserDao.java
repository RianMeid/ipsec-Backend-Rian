package DAO;

import Models.LoginModel;
import Models.UserModel;
import lombok.val;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rian on 13/01/2018.
 */
public class UserDao {
    Connection connection;

    PreparedStatement getLogin;
    PreparedStatement setUser;

    public UserDao(Connection connection) throws SQLException{
        this.connection = connection;
        prepareStatements();
    }

    private void prepareStatements() throws SQLException{
        getLogin = connection.prepareStatement("SELECT admin FROM users"
                +" WHERE username = ?"
                +" AND userpassword = ?");
        setUser = connection.prepareStatement("INSERT INTO users (username,userpassword,admin) "+"VALUES("+
        "?,"+
        "?,"+
        "?);");
    }
    public LoginModel getLogin (UserModel userModel) throws SQLException{

        getLogin.setString(1,userModel.getEmail());
        getLogin.setString(2,userModel.getPassword());
        val loginmodel = new LoginModel();
        val resultset = getLogin.executeQuery();
        while (resultset.next()) {
            loginmodel.setIsadmin(resultset.getBoolean("admin"));
            loginmodel.setLoggedin(true);

    }
        return loginmodel;
    }

    public void setUser (UserModel userModel) throws SQLException{
        setUser.setString(1,userModel.getEmail());
        setUser.setString(2,userModel.getPassword());
        setUser.setBoolean(3,false);
        setUser.executeUpdate();


    }
}
