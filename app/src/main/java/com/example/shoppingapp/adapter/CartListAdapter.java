package com.example.shoppingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.databinding.CartRowBinding;
import com.example.shoppingapp.model.CartItem;

public class CartListAdapter extends ListAdapter<CartItem, CartListAdapter.ViewHolder> {

    CartInterface cartInterface;

    public CartListAdapter(CartInterface cartInterface) {
        super(CartItem.itemCallback);
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartRowBinding cartRowBinding = CartRowBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cartRowBinding.setCartItem(getItem(position));
        holder.cartRowBinding.setCartInterface(cartInterface);
        // you should always call this method becoz in case if any bindings are pendings
        // then it will execute that pending bindings i.e: setting the data bindings
        holder.cartRowBinding.executePendingBindings();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CartRowBinding cartRowBinding;

        public ViewHolder(@NonNull CartRowBinding cartRowBinding) {
            super(cartRowBinding.getRoot());

            this.cartRowBinding = cartRowBinding;
//
//            cartRowBinding.cartDeleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    cartInterface.onItemDelete(getItem(getAdapterPosition()));
//                }
//            });

            cartRowBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {   // this int i is position (array index)
                    // of selected item of spinner.

                    int newQuantity = i + 1;

                    if (newQuantity == getItem(getAdapterPosition()).getQuantity())
                        return;

                    cartInterface.onQuantityChanged(getItem(getAdapterPosition()), newQuantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    public interface CartInterface {
        void onItemDelete(CartItem cartItem);
        void onQuantityChanged(CartItem cartItem, int quantity);
    }
}
