package resources;

import DAO.CartDao;
import DAO.JDBC;
import DAO.UserDao;
import Models.ArrayCartModel;
import Models.CartModel;
import Models.UserModel;
import com.google.inject.Singleton;
import services.CartService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rian on 14/01/2018.
 */
@Singleton
@Path("/CartService")
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {

    private final CartService service;

    @Inject()
    public CartResource(CartService service) {
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("GUEST")
    @Path("/Purchase")
    public void setPurchase(@Valid CartModel cartModel) throws SQLException {
        System.out.println((cartModel.getLogin()));

        JDBC jdbc = new JDBC();
        CartDao cartDao = new CartDao();
        UserDao userDao = new UserDao(jdbc.getConnection());
        UserModel userModel = new UserModel();
        userModel.setEmail(cartModel.getLogin());
        userModel.setPassword(cartModel.getPassword());
        if (userDao.getLogin(userModel).loggedin){
            cartDao.setCart(cartModel);
            System.out.println((cartModel.getLogin()));

        };



    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("GUEST")
    @Path("/getallPurchase")
    public List<ArrayCartModel> findAllPurchases() {
        return service.findAllPurchases();
    }
}
