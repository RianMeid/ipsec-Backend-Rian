package DAO;

import Models.LoginModel;
import Models.UserModel;
import Models.UserModelUpdate;
import lombok.val;
import org.omg.CORBA.PUBLIC_MEMBER;

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
    PreparedStatement updateUser;
    PreparedStatement deleteUser;

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

        updateUser = connection.prepareStatement("UPDATE  users SET username = ?, userpassword = ?, admin = ? WHERE "+
        " username = ?, userpassword = ?, admin = ?;");

        deleteUser = connection.prepareStatement("DELETE FROM users WHERE "+
        " username = ?, userpassword = ?, admin = ?;");
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
    public void updateUser (UserModelUpdate userModel) throws SQLException {
        updateUser.setString(1,userModel.getNewemail());
        updateUser.setString(2,userModel.getNewpassword());
        updateUser.setBoolean(3,false);
        updateUser.setString(4,userModel.getEmail());
        updateUser.setString(5,userModel.getPassword());
        updateUser.setBoolean(6,false);
        updateUser.executeUpdate();


    }
    public void deleteUser (UserModel userModel) throws SQLException {
        deleteUser.setString(1,userModel.getEmail());
        deleteUser.setString(2,userModel.getPassword());
        deleteUser.setBoolean(3,false);
        deleteUser.executeUpdate();
    }
}
