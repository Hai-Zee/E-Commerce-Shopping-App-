package com.example.shoppingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.model.Product;
import com.example.shoppingapp.databinding.ShopRowBinding;

public class ShopListAdapter extends ListAdapter<Product, ShopListAdapter.ViewHolder> {

    Context context;
    ShopInterface shopInterface;

    public ShopListAdapter(
            Context context,
            ShopInterface shopInterface
//                              @NonNull DiffUtil.ItemCallback<Product> diffCallback  As we have directly passed it in super()
    ) {
        super(Product.itemCallback);// we can also implement diffCallBack in this Adapter class itself
                                // but it is easy to manage in model class
                                // So, we will implement it in Model class
        this.context = context;
        this.shopInterface = shopInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ShopRowBinding shopRowBinding = ShopRowBinding.inflate(layoutInflater, parent, false);
        shopRowBinding.setShopInterface(shopInterface);
        return new ViewHolder(shopRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product currentProduct = getItem(position);
        holder.shopRowBinding.setProduct(currentProduct);
        // you should always call this method becoz in case if any bindings are pendings
        // then it will execute that pending bindings i.e: setting the data bindings
        holder.shopRowBinding.executePendingBindings();

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        //--------------------------------------------------------------------------------------
//        holder.name.setText(currentProduct.getName());
//        // Price
//        String priceInString = String.valueOf(currentProduct.getPrice());
//        holder.price.setText(priceInString);
//        // Status: isAvailable or Not
//        String statusInString = String.valueOf(currentProduct.isAvailable());
//
//        Glide.with(holder.imageView).load(currentProduct.getImageUrl()).into(holder.imageView);
//
//        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    // In List Adapter getItemCount() method doesn't exist here

    class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView name, price, status;
//        Button addToCartButton;

        ShopRowBinding shopRowBinding;
//        View itemView;

        public ViewHolder(@NonNull ShopRowBinding customRowBinding) {
            super(customRowBinding.getRoot());

            // don't need to find views here. IT'S DATABINDING ya fir VIEWBINDING
            this.shopRowBinding = customRowBinding;
//            itemView = this.shopRowBinding.getRoot();

//            imageView = itemView.findViewById(R.id.image_view);
//            name = itemView.findViewById(R.id.product_name);
//            price = itemView.findViewById(R.id.price_tag);
//            status = itemView.findViewById(R.id.status);
//            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }

    public interface ShopInterface {
        void onItemAdded(Product product);
        void onItemClicked(Product product);
    }
}
