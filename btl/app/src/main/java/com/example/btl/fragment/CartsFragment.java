package com.example.btl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.HomeActivity;
import com.example.btl.OrderActivity;
import com.example.btl.R;
import com.example.btl.adapter.CartAdapter;
import com.example.btl.model.User;

public class CartsFragment extends Fragment{
    private RecyclerView rcCart;
    private Button btnRemove, btnAdd;
    private CartAdapter cartAdapter;
    private HomeActivity homeActivity;
    private User user;

    public CartsFragment(HomeActivity homeActivity){
        this.homeActivity = homeActivity;
    }

    public HomeActivity getHomeActivity() {
        return homeActivity;
    }

    public void setHomeActivity(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_carts, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
    private void init(View view){
        user = homeActivity.getUser();
        rcCart = view.findViewById(R.id.rcCart);
        btnRemove = view.findViewById(R.id.btnRemove);
        btnAdd = view.findViewById(R.id.btnAdd);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcCart.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(getContext());
        cartAdapter.setListBookOrder(user);
        rcCart.setAdapter(cartAdapter);

        btnRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cartAdapter.removeFromCart(user);
                if(cartAdapter.getListBookOrder().size() == cartAdapter.getListChecked().size()){
                    btnRemove.setVisibility(View.INVISIBLE);
                    btnAdd.setVisibility(View.INVISIBLE);
                }else{
                    btnRemove.setVisibility(View.VISIBLE);
                    btnAdd.setVisibility(View.VISIBLE);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("listSelected", cartAdapter.getListChecked());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(cartAdapter.getItemCount() == 0){
            btnRemove.setVisibility(View.INVISIBLE);
            btnAdd.setVisibility(View.INVISIBLE);
        }else{
            btnRemove.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
        }
    }
}
