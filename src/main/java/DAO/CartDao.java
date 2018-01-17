package DAO;

import Models.ArrayCartModel;
import Models.CartModel;
import Models.CartModelUpdate;
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
    PreparedStatement updatePurchase;
    PreparedStatement deletePurchase;
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

        deletePurchase = connection.prepareStatement("DELETE FROM purchases "+"WHERE "+"gtx1080 = ?,"+
                "gtx1070 = ?,"+
                "x150 = ?,"+
                "x99 = ?,"+
                "ripjaws = ?,"+
                "vengeance = ?,"+
                "hdd = ?,"+
                "ssd = ?,"+
                "keyboard = ?,"+
                "mouse = ?,"+
                "login = ? ;");

        updatePurchase = connection.prepareStatement("UPDATE purchases SET gtx1080 = ?,gtx1070 = ?,x150 = ?," +
                "x99 = ?,ripjaws = ?,vengeance = ?,hdd = ?,ssd = ?,keyboard = ?,mouse = ?,login = ? "+"WHERE "+
                        "gtx1080 = ?,"+
                        "gtx1070 = ?,"+
                        "x150 = ?,"+
                        "x99 = ?,"+
                        "ripjaws = ?,"+
                        "vengeance = ?,"+
                        "hdd = ?,"+
                        "ssd = ?,"+
                        "keyboard = ?,"+
                        "mouse = ?,"+
                        "login = ? ;");
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
    public void putCart(CartModelUpdate cartModelUpdate) throws SQLException {
        updatePurchase.setString(1,cartModelUpdate.getNewtotal1080());
        updatePurchase.setString(2,cartModelUpdate.getNewtotal1070());
        updatePurchase.setString(3,cartModelUpdate.getNewtotalx150());
        updatePurchase.setString(4,cartModelUpdate.getNewtotalx99());
        updatePurchase.setString(5,cartModelUpdate.getNewtotalripjaws());
        updatePurchase.setString(6,cartModelUpdate.getNewtotalvengeance());
        updatePurchase.setString(7,cartModelUpdate.getNewtotalhdd());
        updatePurchase.setString(8,cartModelUpdate.getNewtotalssd());
        updatePurchase.setString(9,cartModelUpdate.getNewtotalkeyboard());
        updatePurchase.setString(10,cartModelUpdate.getNewtotalmouse());
        updatePurchase.setString(11,cartModelUpdate.getNewlogin());
        updatePurchase.setString(12,cartModelUpdate.getTotal1080());
        updatePurchase.setString(13,cartModelUpdate.getTotal1070());
        updatePurchase.setString(14,cartModelUpdate.getTotalx150());
        updatePurchase.setString(15,cartModelUpdate.getTotalx99());
        updatePurchase.setString(16,cartModelUpdate.getTotalripjaws());
        updatePurchase.setString(17,cartModelUpdate.getTotalvengeance());
        updatePurchase.setString(18,cartModelUpdate.getTotalhdd());
        updatePurchase.setString(19,cartModelUpdate.getTotalssd());
        updatePurchase.setString(20,cartModelUpdate.getTotalkeyboard());
        updatePurchase.setString(21,cartModelUpdate.getTotalmouse());
        updatePurchase.setString(22,cartModelUpdate.getLogin());
        updatePurchase.executeUpdate();

    }
    public void deleteCart (CartModel cartModel) throws SQLException {
        deletePurchase.setString(1,cartModel.getTotal1080());
        deletePurchase.setString(2,cartModel.getTotal1070());
        deletePurchase.setString(3,cartModel.getTotalx150());
        deletePurchase.setString(4,cartModel.getTotalx99());
        deletePurchase.setString(5,cartModel.getTotalripjaws());
        deletePurchase.setString(6,cartModel.getTotalvengeance());
        deletePurchase.setString(7,cartModel.getTotalhdd());
        deletePurchase.setString(8,cartModel.getTotalssd());
        deletePurchase.setString(9,cartModel.getTotalkeyboard());
        deletePurchase.setString(10,cartModel.getTotalmouse());
        deletePurchase.setString(11,cartModel.getLogin());
        deletePurchase.executeUpdate();
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
