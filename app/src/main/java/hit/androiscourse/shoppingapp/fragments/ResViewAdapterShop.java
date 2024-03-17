package hit.androiscourse.shoppingapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import hit.androiscourse.shoppingapp.R;
import hit.androiscourse.shoppingapp.activities.MainActivity;
import hit.androiscourse.shoppingapp.dao.ProductCartDaoImpl;
import hit.androiscourse.shoppingapp.model.Product;

public class ResViewAdapterShop extends RecyclerView.Adapter<ResViewAdapterShop.MyViewHolder>{

    HashMap<String, Product> dataSetMap;

    HashMap<String, Product> cartHashMap;

    List<Product> dataSet;
    Context context;
    private Dialog addItemDialog;
    private Button btnInc;
    private Button btnDec;
    private Button btnConfirm;
    private EditText editTextAmount;

    private Integer amount;



    public ResViewAdapterShop(Context context, HashMap<String, Product> shopDataSet, HashMap<String, Product> dbCartHashMap) {
        this.context = context;

        this.dataSetMap = shopDataSet;
        this.dataSet = new ArrayList<>();
        this.dataSet = new ArrayList<>(dataSetMap.values());
        this.cartHashMap = new HashMap<>();
        for (Product product: dbCartHashMap.values()) {
            this.cartHashMap.put(product.getName(), product);
        }

        amount = 1;

        addItemDialog = new Dialog(context);
        addItemDialog.setContentView(R.layout.add_item_card);
        addItemDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addItemDialog.getWindow().setBackgroundDrawableResource(R.drawable.add_dialog_bg);
        addItemDialog.setCancelable(false);

        editTextAmount = addItemDialog.findViewById(R.id.editTextAddAmount);
        editTextAmount.setText(amount.toString());
        btnInc = addItemDialog.findViewById(R.id.buttonAddIncriment);
        btnDec = addItemDialog.findViewById(R.id.buttonAddDecriment);
        btnConfirm = addItemDialog.findViewById(R.id.buttonAddConfirm);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount ++;
                editTextAmount.setText(amount.toString());

            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount!=1){
                    amount--;
                    editTextAmount.setText(amount.toString());
                }
                else{
                    Toast.makeText(context, "Minimal amount is 1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemDialog.dismiss();
            }
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescpirtion);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_shop, parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageView imageView = holder.imageView;
        TextView textViewTitle = holder.textViewTitle;
        TextView textViewDescription = holder.textViewDescription;
        TextView textViewPrice = holder.textViewPrice;
        CardView cardView = holder.itemView.findViewById(R.id.cardShopItem);


        imageView.setImageResource(dataSet.get(position).getImageId());
        textViewTitle.setText(dataSet.get(position).getName());
        textViewDescription.setText(dataSet.get(position).getDescription());
        textViewPrice.setText(String.valueOf(dataSet.get(position).getPrice()));



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        ProductCartDaoImpl productCartDao = new ProductCartDaoImpl();
                        Product newProduct = dataSet.get(position);
                        AddProductToCart(newProduct, amount);
                        productCartDao.createProduct(cartHashMap.get(newProduct.getName()));
                        Toast.makeText(v.getContext(),"added product to cart " + textViewTitle.getText(), Toast.LENGTH_SHORT).show();
                }

                });
                addItemDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void AddProductToCart(Product product, int amount){
        if(cartHashMap.containsKey(product.getName())){
            Product productInMap = cartHashMap.get(product.getName());
            int oldAmount = productInMap.getAmount();
            productInMap.setAmount(oldAmount + amount);
        }
        else{
            product.setAmount(amount);
            cartHashMap.put(product.getName(), product);
        }

    }


}
