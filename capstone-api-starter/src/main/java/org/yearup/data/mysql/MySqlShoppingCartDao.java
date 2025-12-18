package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    private ProductDao pd;

    @Autowired
    public MySqlShoppingCartDao(DataSource ds, ProductDao pd) {
        super(ds);
        this.pd = pd;
    }

    @Override
    public ShoppingCart getByUserId(int userId) {

        ShoppingCart cart = new ShoppingCart();

        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                 int productId = rs.getInt("product_id");
                 int quantity = rs.getInt("quantity");

                Product p = pd.getById(productId);

                ShoppingCartItem item = new ShoppingCartItem();
                item.setProduct(p);
                item.setQuantity(quantity);

                cart.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public void addProduct(int userId, int productId) {
        String checkSql = "SELECT quantity FROM shopping_cart WHERE user_id = ? AND product_id = ?";
        String insertSql = "INSERT INTO shopping_cart (user_id, product_id,quantity) VALUES (?,?,1)";
        String updateSql = "UPDATE shopping_cart SET quantity = quantity + 1 WHERE user_id = ? AND product_id = ?";

        try (Connection c = getConnection()) {
            PreparedStatement checkStatement = c.prepareStatement(checkSql);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, productId);
            ResultSet rs = checkStatement.executeQuery();

            if (rs.next()) {
                PreparedStatement updateStatement = c.prepareStatement(updateSql);
                updateStatement.setInt(1, userId);
                updateStatement.setInt(2, productId);
                updateStatement.executeUpdate();
            } else {
                PreparedStatement insertStatment = c.prepareStatement(insertSql);
                insertStatment.setInt(1, userId);
                insertStatment.setInt(2, productId);
                insertStatment.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCart(int userId) {
        String sql = "DELETE FROM shopping_cart WHERE user_id = ?";

        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
