package hit.androiscourse.shoppingapp.data;

import hit.androiscourse.shoppingapp.R;
import hit.androiscourse.shoppingapp.model.Product;

public class FireBaseDB {


    public  static FireBaseCollection<Product> products = new FireBaseCollection<>("shopProducts");
    public  static FireBaseCollection<Product> selectedProducts = new FireBaseCollection<>("cartProducts");

    public FireBaseDB(){
        //initDB();
    }

    private void initDB() {
        products.createObject(new Product("Bread","white bread",1,15.7f, R.drawable.bread ));
        products.createObject(new Product("Milk","Milk 3% fat",1, 10f,R.drawable.milk));
        products.createObject(new Product("Eggs","Eggs 12 in cardboard",1,10f ,R.drawable.eggs));
        products.createObject( new Product("Butter","Butter 200g",1, 10f,R.drawable.butter));
        products.createObject( new Product("Cheese","Cheese Emek 300g",1,10f ,R.drawable.cheese));
        products.createObject( new Product("Yogurt","Yogurt Prili 300g",1,13f, R.drawable.yogurt));


    }
}
