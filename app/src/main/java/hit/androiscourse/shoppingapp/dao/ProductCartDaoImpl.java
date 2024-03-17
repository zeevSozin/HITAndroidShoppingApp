package hit.androiscourse.shoppingapp.dao;

import java.util.List;

import hit.androiscourse.shoppingapp.data.FireBaseDB;
import hit.androiscourse.shoppingapp.model.Product;

public class ProductCartDaoImpl implements ProductDao{
    @Override
    public List<Product> getAllProducts() {
        return FireBaseDB.selectedProducts.getAll();
    }

    @Override
    public Product getProductById(String id) {
        return FireBaseDB.selectedProducts.getObjectById(id);
    }

    @Override
    public void createProduct(Product product) {
        FireBaseDB.selectedProducts.createObject(product);

    }

    @Override
    public void update(String  id, Product product) {
        FireBaseDB.selectedProducts.updateObject(id, product );
    }

    @Override
    public void deleteById(String id) {
        FireBaseDB.selectedProducts.deleteObject(id);
    }
}
