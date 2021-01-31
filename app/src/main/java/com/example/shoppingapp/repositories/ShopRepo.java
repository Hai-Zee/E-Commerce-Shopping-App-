package com.example.shoppingapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingapp.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {

    private MutableLiveData<List<Product>> listMutableLiveData;

    public LiveData<List<Product>> getProducts() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            listInit();
        }
        return listMutableLiveData;
    }

    private void listInit() {

        List<Product> list = new ArrayList<>();

        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "One plus nord 8",
                27999,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/71gag816F7L._SL1500_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "Samsung Galaxy M51",
                24999,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/710weRkP-nL._SL1500_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "Asus silver laptop",
                34180,
                false,
                "https://images-na.ssl-images-amazon.com/images/I/81RlrM4VopL._SX679_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "OnePlus 8 Pro",
                59999,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/61n6Ovq6EdL._SL1500_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "Vivo V19",
                24990,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/61DlqYmSLOL._SL1024_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "ASUS VivoBook X512JA",
                61650,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/61IAsGiFAKL._SL1500_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "ASUS TUF FX505DT",
                59387,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/81%2BYepXiJpL._SL1500_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "Asus VivoBook 14 Ryzen 7",
                55590,
                false,
                "https://rukminim1.flixcart.com/image/416/416/kbfeljk0/computer/h/a/s/asus-original-imafss69cdydrwr7.jpeg?q=70"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "Avita liber NS14A8INF541",
                36990,
                false,
                "https://rukminim1.flixcart.com/image/416/416/kerfl3k0/computer/9/3/t/avita-v14-thin-and-light-laptop-original-imafvd65w2fufea4.jpeg?q=70"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "One plus nord 8",
                27999,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/71gag816F7L._SL1500_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "Samsung Galaxy M51",
                24999,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/710weRkP-nL._SL1500_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "ASUS VivoBook X512JA",
                61650,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/61IAsGiFAKL._SL1500_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "ASUS TUF FX505DT",
                59387,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/81%2BYepXiJpL._SL1500_.jpg"
        ));
        list.add(new Product(
                String.valueOf(UUID.randomUUID()),
                "OnePlus 8 Pro",
                59999,
                true,
                "https://images-na.ssl-images-amazon.com/images/I/61n6Ovq6EdL._SL1500_.jpg"
        ));
        listMutableLiveData.setValue(list);
    }
}