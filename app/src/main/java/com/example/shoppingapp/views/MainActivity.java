package com.example.shoppingapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.databinding.ActivityMainBinding;
import com.example.shoppingapp.model.CartItem;
import com.example.shoppingapp.viewModels.ShopViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    ShopViewModel shopViewModel;
    private int cartQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        // IT IS VIEWBINDING, for Databinding... add layout tag in it's XML & then it will automatically become databinding
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        //                                    -- OR --
//        This is ViewBinding without layout Tags
//        ViewBinding viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//    -----------------------------------------------------------------------------------------------------------
//        To use ActivityMainBinding (Data Binding) , we need to place layout Tag in it's xml
//        ActivityMainBinding activityMainBinding1 = DataBindingUtil.setContentView(this, R.layout.activity_main)
//    -----------------------------------------------------------------------------------------------------------
//        IN KOTLIN -->
//        val activityMainBinding1 = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);

        shopViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {

                int quantity = 0;
                for (CartItem cartItem : cartItems) {
                    quantity += cartItem.getQuantity();
                }
                cartQuantity = quantity;
                // we should call this method so that our menu can drawn again
                invalidateOptionsMenu();
            }
        });
    }


    // this method will enable back button functionality in fragments
    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.cartFragmentID);
        // here if we used android:actionLayout attrubute in menu then we will be getting
        // this below view (actionView) as null
        View actionView = menuItem.getActionView();
        TextView cartBadgeTextView = actionView.findViewById(R.id.cart_badge_textview);

        cartBadgeTextView.setText(String.valueOf(cartQuantity));
        cartBadgeTextView.setVisibility(cartQuantity == 0 ? View.GONE : View.VISIBLE);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // here we are calling onOptionsItemSeleced method
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // **Important**
        // ID of menu item should be same as that of fragment (in nav_graph) on which you want to go.

//        onNavigationSelected() already return boolean value So,
//        NavigationUI.onNavDestinationSelected(item, navController);
//        return super.onOptionsItemSelected(item);

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}