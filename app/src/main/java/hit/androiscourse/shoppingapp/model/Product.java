package hit.androiscourse.shoppingapp.model;

public class Product  {

    private String name;
    private String description;
    private int amount;

    private float price;
    private int imageId;

    public Product() {
    }

    public Product(String name, String description, int amount,float price, int imageId) {

        this.name = name;
        this.description = description;
        this.amount = amount;
        this.imageId = imageId;
        this.setPrice(price);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price)
    {
        if(this.amount != 0){
            this.price = price*this.amount;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.setPrice(this.price);
    }
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
