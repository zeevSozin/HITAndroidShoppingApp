package hit.androiscourse.shoppingapp.data;

import hit.androiscourse.shoppingapp.model.Product;
import hit.androiscourse.shoppingapp.model.UserEntity;

public class MockDb {
    public  static MyCollection<UserEntity> users;
    public  static MyCollection<Product> products;
    public  static MyCollection<Product> selectedProducts;

    public MockDb() {
        initDb();
    }

    private  void initDb(){
        users = new MyCollection<>();
        products = new MyCollection<>();
        selectedProducts = new MyCollection<>();
//        users.createObject(new UserEntity(1, new User("admin", "Aa123456")));
//
//        products.createObject(new ProductEntity(-1,new Product("Bread","white bread",600, R.drawable.bread )));
//        products.createObject(new ProductEntity(-1,new Product("Milk","Milk 3% fat",200, R.drawable.milk)));
//        products.createObject(new ProductEntity(-1, new Product("Eggs","Eggs 12 in cardboard",500, R.drawable.eggs)));
//        products.createObject(new ProductEntity(-1, new Product("Butter","Butter 200g",500, R.drawable.butter)));
//        products.createObject(new ProductEntity(-1, new Product("Cheese","Cheese Emek 300g",500, R.drawable.cheese)));
//        products.createObject(new ProductEntity(-1, new Product("Yogurt","Yogurt Prili 300g",150, R.drawable.yogurt)));

    }


}
