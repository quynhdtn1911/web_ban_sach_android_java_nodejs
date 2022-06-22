package com.example.btl.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.HomeActivity;
import com.example.btl.R;
import com.example.btl.adapter.ListOrderAdapter;
import com.example.btl.model.User;

public class OrdersFragment extends Fragment {
    private RecyclerView rcOrder;
    private HomeActivity homeActivity;
    private ListOrderAdapter listOrderAdapter;
    private User user;
    public OrdersFragment(HomeActivity homeActivity){
        this.homeActivity = homeActivity;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = homeActivity.getUser();
        rcOrder = view.findViewById(R.id.rcOrder);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcOrder.setLayoutManager(layoutManager);
        listOrderAdapter = new ListOrderAdapter(getContext());
        listOrderAdapter.setListOrder(user);
        rcOrder.setAdapter(listOrderAdapter);
    }
}
