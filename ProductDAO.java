package Gs_Inventario;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
	  public	Product addProduct(Product product) throws SQLException
	  {
		  String sql = "INSERT INTO products (name, price, stock, category, description) VALUES (?, ?, ?, ?, ?)";
		  
		  try (Connection conn = DatabaseConnection.getConnection();
				  PreparedStatement ps = conn.prepareStatement(sql))
		  {
			  ps.setString	(1, product.getName());
			  ps.setDouble	(2, product.getPrice());
			  ps.setInt		(3, product.getStock());
			  ps.setString	(4, product.getCategory());
			  ps.setString	(5, product.getDescription());			  
		  
			  if	(ps.executeUpdate() > 0)
			  {
				  try	(ResultSet genKeys = ps.getGeneratedKeys())
				  {
					  if (genKeys.next())
						  product.setId(genKeys.getInt(1));
				  }
			  }
		  }
		  return product;
	  }

	  public	Product getProductById(int id) throws SQLException
	  {
		  String sql = "SELECT * FROM products (id) VALUES (?)";
		  try (Connection conn = DatabaseConnection.getConnection();
		  PreparedStatement ps = conn.prepareStatement(sql))
		  {
			  ps.setInt(1, id);
			  try (ResultSet rs = ps.executeQuery())
			  {
				  if (rs.next())
				  {
					  return new Product (
							  rs.getInt("id"),
							  rs.getString("name"),
							  rs.getDouble("price"),
							  rs.getInt("stock"),
							  rs.getString("category"),
							  rs.getString("description")
							  );
				  }
			  }			  
		  }
		  
		  return null;
	  }

	  public	List<Product> getAllProducts() throws SQLException
	  {
		  List<Product> products = new ArrayList<>();
		  String sql = "SELECT * FORM products";
		  
		  try (Connection conn = DatabaseConnection.getConnection();
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ResultSet rs = ps.executeQuery())
		  {
			  while (rs.next())
			  {
				  products.add(new Product (
						  rs.getInt("id"),
						  rs.getString("name"),
						  rs.getDouble("price"),
						  rs.getInt("stock"),
						  rs.getString("category"),
						  rs.getString("description")
						  ));
			  }
		  }
		  
		  return products;
	  }

	  public	boolean updateProduct(Product product) throws SQLException
	  {
		  String sql = "UPDATE products SET name = ?, price = ?, stock = ?, category = ?, description = ? WHERE id = ?";
		  
		  try (Connection conn = DatabaseConnection.getConnection();
				  PreparedStatement ps = conn.prepareStatement(sql))
		  
		  {
			  ps.setString(1, product.getName());
			  ps.setDouble(2, product.getPrice());
			  ps.setInt(3, product.getStock());
			  ps.setString(4, product.getCategory());
			  ps.setString(5, product.getDescription());
			  
			  return ps.executeUpdate() > 0;
		  }
	  }

	  public	boolean deleteProduct(int id) throws SQLException
	  {
		  String sql = "DELETE FORM products WHEWER id = ?";
		  
		  try (Connection conn = DatabaseConnection.getConnection();
				  PreparedStatement ps = conn.prepareStatement(sql))
		  {
			  ps.setInt(1, id);
			  
			  return ps.executeUpdate() > 0;
		  }
	  }
	
}
