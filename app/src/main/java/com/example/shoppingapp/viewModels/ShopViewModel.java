package com.example.shoppingapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingapp.repositories.CartRepo;
import com.example.shoppingapp.model.CartItem;
import com.example.shoppingapp.model.Product;
import com.example.shoppingapp.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();
    CartRepo cartRepo = new CartRepo();
    MutableLiveData<Product> productMutableLiveData = new MutableLiveData<>();

    public LiveData<List<Product>> getProducts(){
        return shopRepo.getProducts();
    }

    public void setProduct(Product product){
        productMutableLiveData.setValue(product);
    }

    public LiveData<Product> getProduct(){
        return productMutableLiveData;
    }

    public LiveData<List<CartItem>> getCart(){
        return cartRepo.getCart();
    }

    public boolean addItemToCart(Product product){
        return cartRepo.addItemToCart(product);
    }

    public void removeItemFromCart(CartItem cartItem){
        cartRepo.removeItemFromCart(cartItem);
    }

    public void onQuantityChanged(CartItem cartItem, int quantity){
        cartRepo.onQuantityChanged(cartItem, quantity);
    }

    public LiveData<Double> getTotalPrice(){
        return cartRepo.getTotalPrice();
    }

    public void resetCart(){
        cartRepo.inItCart();
    }
}
