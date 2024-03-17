package hit.androiscourse.shoppingapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hit.androiscourse.shoppingapp.data.FireBaseCollection;
import hit.androiscourse.shoppingapp.data.FireBaseDB;
import hit.androiscourse.shoppingapp.model.Product;

public class ProductDaoImpl implements ProductDao{
    private ArrayList<Product> productArr = new ArrayList<>();
    @Override
    public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<>();

        result = FireBaseDB.products.getAll();


        return result;
    }

    @Override
    public Product getProductById(String id) {
        return FireBaseDB.products.getObjectById(id);
    }

    @Override
    public void createProduct(Product product) {
        FireBaseDB.products.createObject(product);
    }

    @Override
    public void update(String id, Product product) {
        FireBaseDB.products.updateObject(id, product );
    }

    @Override
    public void deleteById(String id) {
        FireBaseDB.products.deleteObject(id);
    }
}
