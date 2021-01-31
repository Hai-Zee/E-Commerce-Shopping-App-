package com.example.shoppingapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapter.ShopListAdapter;
import com.example.shoppingapp.model.Product;
import com.example.shoppingapp.viewModels.ShopViewModel;
import com.example.shoppingapp.databinding.FragmentShopBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface {

//    Context context;
    ShopListAdapter shopListAdapter;
    FragmentShopBinding fragmentShopBinding;
    ShopViewModel shopViewModel;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(
//                container.getContext(),
//                2,
//                GridLayoutManager.VERTICAL,
//                false
//        );
//        recyclerView.setLayoutManager(gridLayoutManager);

//        return inflater.inflate(R.layout.fragment_shop, container, false);
//        // IT Is ViewBinding, without layout tags
        fragmentShopBinding = FragmentShopBinding.inflate(inflater, container,
                false);
        return fragmentShopBinding.getRoot();

//        **Without placing layout Tags, it is ViewBinding**
//        ViewBinding fragmentShopBinding = DataBindingUtil.inflate(inflater,
//                R.layout.fragment_shop, container, false);
//        return fragmentShopBinding.getRoot();

        //        **With layout Tags, it is DataBinding**
//        FragmentShopBinding fragmentShopBinding = DataBindingUtil.inflate(inflater,
//                R.layout.fragment_shop, container, false);
//        return fragmentShopBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter = new ShopListAdapter(requireContext(), this);
        fragmentShopBinding.recyclerView.setAdapter(shopListAdapter);
        fragmentShopBinding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));
        fragmentShopBinding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);

        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                shopListAdapter.submitList(products);
            }
        });
        navController = Navigation.findNavController(view);
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        this.context = context;
//    }


    @Override
    public void onItemAdded(Product product) {
        boolean isAdded = shopViewModel.addItemToCart(product);
        if (isAdded) {
            Snackbar.make(requireView(), product.getName() + "added to cart", Snackbar.LENGTH_LONG)
                    .setAction("Goto cart", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            navController.navigate(R.id.action_shopFragment_to_cartFragment);
                        }
                    }).show();
        }else {
            Snackbar.make(requireView(),"Maximum quantity reached", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClicked(Product product) {
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_shopFragment_to_productDetailFragment);
    }
}