package hit.androiscourse.shoppingapp.dao;

import java.util.List;

import hit.androiscourse.shoppingapp.model.Product;


public interface ProductDao {
    List<Product> getAllProducts();
    Product getProductById(String id);
    void createProduct(Product product);

    void update(String id, Product product);
    void deleteById(String id);
}
