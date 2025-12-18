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
}
