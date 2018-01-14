package DAO;

import Models.ArrayCartModel;
import Models.CartModel;
import lombok.val;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rian on 14/01/2018.
 */
public class CartDao {
    Connection connection;
    PreparedStatement setPurchase;
    PreparedStatement getPurchase;
    public CartDao()  {
        this.connection = new JDBC().getConnection();
        try {
            prepareStatements();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepareStatements() throws SQLException {
        setPurchase = connection.prepareStatement("INSERT INTO purchases (gtx1080,gtx1070,x150," +
                "x99,ripjaws,vengeance,hdd,ssd,keyboard,mouse,login) "+"VALUES("+
                "?,"+
                "?,"+
                "?,"+
                "?,"+
                "?,"+
                "?,"+
                "?,"+
                "?,"+
                "?,"+
                "?,"+
                "?);");
        getPurchase = connection.prepareStatement("SELECT  * FROM purchases");
    }

    public void setCart(CartModel cartModel) throws SQLException {
        setPurchase.setString(1,cartModel.getTotal1080());
        setPurchase.setString(2,cartModel.getTotal1070());
        setPurchase.setString(3,cartModel.getTotalx150());
        setPurchase.setString(4,cartModel.getTotalx99());
        setPurchase.setString(5,cartModel.getTotalripjaws());
        setPurchase.setString(6,cartModel.getTotalvengeance());
        setPurchase.setString(7,cartModel.getTotalhdd());
        setPurchase.setString(8,cartModel.getTotalssd());
        setPurchase.setString(9,cartModel.getTotalkeyboard());
        setPurchase.setString(10,cartModel.getTotalmouse());
        setPurchase.setString(11,cartModel.getLogin());
        setPurchase.executeUpdate();


    }
    public List<ArrayCartModel> findCart() {
        val arraylist = new ArrayList<ArrayCartModel>();

        try {
            val resultset = getPurchase.executeQuery();
            while (resultset.next()) {
                arraylist.add(
                        new ArrayCartModel(resultset.getString(1),
                                resultset.getString(2),
                                resultset.getString(3),
                                resultset.getString(4),
                                resultset.getString(5),
                                resultset.getString(6),
                                resultset.getString(7),
                                resultset.getString(8),
                                resultset.getString(9),
                                resultset.getString(10),
                                resultset.getString(11)));


            }
            resultset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arraylist;
    }
}
