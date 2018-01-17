package resources;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

import DAO.JDBC;
import DAO.UserDao;
import Models.LoginModel;
import Models.UserModel;
import Models.UserModelUpdate;
import com.google.inject.Singleton;
/**
 * Created by Rian on 13/01/2018.
 */

@Singleton
@Path("/LoginService")
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("GUEST")
    @Path("/Login")
    public LoginModel setLogin(@Valid UserModel userModel) throws SQLException {

        JDBC jdbc = new JDBC();
        UserDao userDao = new UserDao(jdbc.getConnection());
        System.out.println(userDao.getLogin(userModel));
        return userDao.getLogin(userModel);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("GUEST")
    @Path("/Register")
    public void setRegister(@Valid UserModel userModel) throws SQLException{
        JDBC jdbc = new JDBC();
        UserDao userDao = new UserDao(jdbc.getConnection());
        userDao.setUser(userModel);

    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("GUEST")
    @Path("/Register")
    public void updateLogin(UserModelUpdate userModel) throws SQLException {
        JDBC jdbc = new JDBC();
        UserDao userDao = new UserDao(jdbc.getConnection());
        UserModel oldusermodel = new UserModel();
        userModel.setEmail(userModel.getEmail());
        userModel.setPassword(userModel.getPassword());
        if (userDao.getLogin(oldusermodel).loggedin){
            userDao.updateUser(userModel);
        }
    }
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("GUEST")
    @Path("/Register")
    public void deleteLogin(@Valid UserModel userModel) throws SQLException {
        JDBC jdbc = new JDBC();
        UserDao userDao = new UserDao(jdbc.getConnection());
        System.out.println(userDao.getLogin(userModel));
        if (userDao.getLogin(userModel).loggedin){
            userDao.deleteUser(userModel);
        };
    }

}
