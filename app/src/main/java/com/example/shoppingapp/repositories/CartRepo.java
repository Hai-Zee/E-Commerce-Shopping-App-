package com.example.shoppingapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingapp.model.CartItem;
import com.example.shoppingapp.model.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CartRepo {

    private MutableLiveData<List<CartItem>> cartLiveList = new MutableLiveData<>();
    private MutableLiveData<Double> totalLivePrice = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart() {
        if (cartLiveList.getValue() == null) {
            inItCart();
        }
        calculatePrice();
        return cartLiveList;
    }

    public boolean addItemToCart(Product product) {
        if (cartLiveList.getValue() == null) {
            inItCart();
        }

        List<CartItem> cartList = new ArrayList<>(cartLiveList.getValue());

        for (CartItem cartItem : cartList) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                if (cartItem.getQuantity() == 5) {
                    return false;
                }
                int index = cartList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartList.set(index, cartItem);
                cartLiveList.setValue(cartList);
                calculatePrice();
                return true;
            }
        }
        cartList.add(new CartItem(product, 1));
        cartLiveList.setValue(cartList);
        calculatePrice();
        return true;
    }

    public void inItCart() {
        cartLiveList.setValue(new ArrayList<CartItem>());
        calculatePrice();
    }

    public void removeItemFromCart(CartItem cartItem) {
        if (cartLiveList.getValue() == null) {
            return;
        }

        List<CartItem> cartItemList = new ArrayList<>(cartLiveList.getValue());
        cartItemList.remove(cartItem);
        cartLiveList.setValue(cartItemList);
        calculatePrice();
    }

    public void onQuantityChanged(CartItem cartItem, int quantity) {
        if (cartLiveList.getValue() == null) {
            return;
        }
        List<CartItem> cartItems = new ArrayList<>(cartLiveList.getValue());
//        cartItem.setQuantity(quantity);
//        cartItems.set(cartItems.indexOf(cartItem), cartItem);
        CartItem updatedCartItem = new CartItem(cartItem.getProduct(), quantity);
        cartItems.set(cartItems.indexOf(cartItem), updatedCartItem);
        cartLiveList.setValue(cartItems);
        calculatePrice();
    }

    public LiveData<Double> getTotalPrice() {
        if (totalLivePrice.getValue() == null)
            totalLivePrice.setValue(0.0);

        return totalLivePrice;
    }

    private void calculatePrice() {
        if (cartLiveList.getValue() == null)
            return;

        double totalPrice = 0.0;
        List<CartItem> cartItems = new ArrayList<>(cartLiveList.getValue());
        for (CartItem cartItem : cartItems) {
            totalPrice = totalPrice + (cartItem.getProduct().getPrice() * cartItem.getQuantity());
        }
        totalLivePrice.setValue(totalPrice);
    }
}
