package hit.androiscourse.shoppingapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Entity;
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
import java.util.Map;
import java.util.stream.Collectors;

import hit.androiscourse.shoppingapp.R;
import hit.androiscourse.shoppingapp.dao.ProductCartDaoImpl;
import hit.androiscourse.shoppingapp.model.Product;

public class ResViewAdapterCart extends RecyclerView.Adapter<ResViewAdapterCart.CartViewHolder>{

    HashMap<String, Product> dataSetMap;
    List<Product> dataSet;

    private Integer amount;



    public ResViewAdapterCart(HashMap<String, Product> dataSet) {
        this.dataSetMap = dataSet;
        this.dataSet = new ArrayList<>(dataSetMap.values());
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewAmount;
        TextView textViewPrice;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewCartItem);
            textViewTitle = itemView.findViewById(R.id.textViewTitleCartItem);
            textViewDescription = itemView.findViewById(R.id.textViewCartItemDescription);
            textViewAmount = itemView.findViewById(R.id.textViewCartItemAmount);
            textViewPrice = itemView.findViewById(R.id.textViewCartItemPrice);
        }
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_cart, parent,false);
        CartViewHolder myViewHolder = new CartViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ImageView imageView = holder.imageView;
        TextView textViewTitle = holder.textViewTitle;
        TextView textViewDescription = holder.textViewDescription;
        TextView textViewAmount = holder.textViewAmount;
        TextView textViewPrice = holder.textViewPrice;
        CardView cardView = holder.itemView.findViewById(R.id.cardCartItem);

        imageView.setImageResource(dataSet.get(position).getImageId());
        textViewTitle.setText(dataSet.get(position).getName());
        textViewDescription.setText(dataSet.get(position).getDescription());
        textViewAmount.setText(String.valueOf(dataSet.get(position).getAmount()));
        textViewPrice.setText(String.valueOf(dataSet.get(position).getPrice()));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.edit_item_dialog);
                dialog.setCancelable(false);

                Button bntInc = dialog.findViewById(R.id.buttonEditIncriment);
                Button bntDec = dialog.findViewById(R.id.buttonEditDecriment);
                Button bntDel = dialog.findViewById(R.id.buttonEditDelete);
                Button btnConfirm = dialog.findViewById(R.id.buttonEditConfirm);
                EditText editTextAmountDlg = dialog.findViewById(R.id.editTextEditAmount);
                editTextAmountDlg.setText(String.valueOf(dataSet.get(position).getAmount()));
                amount = Integer.parseInt(String.valueOf(editTextAmountDlg.getText()));


                bntInc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amount++;
                        editTextAmountDlg.setText(amount.toString());

                    }
                });

                bntDec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amount--;
                        editTextAmountDlg.setText(amount.toString());
                    }
                });

                bntDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProductCartDaoImpl productCartDao = new ProductCartDaoImpl();
                        String idToDelete ="";

                        for(Map.Entry<String, Product> entry : dataSetMap.entrySet()){
                            if(entry.getValue().equals(dataSet.get(position))){
                                idToDelete = entry.getKey();
                            }
                        }
                        if(!idToDelete.isEmpty()){
                            productCartDao.deleteById(idToDelete);
                        }

                        dialog.dismiss();


                    }
                });

                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProductCartDaoImpl productCartDao = new ProductCartDaoImpl();
                        String idToEdit ="";

                        for(Map.Entry<String, Product> entry : dataSetMap.entrySet()){
                            if(entry.getValue().equals(dataSet.get(position))){
                                idToEdit = entry.getKey();
                            }
                        }
                        if(!idToEdit.isEmpty()){
                            Product productToUpdate = dataSet.get(position);
                            productToUpdate.setAmount(amount);
                            productCartDao.update(idToEdit, productToUpdate);
                        }


                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
