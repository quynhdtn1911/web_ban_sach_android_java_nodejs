package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.controller.ApiService;
import com.example.btl.model.Author;
import com.example.btl.model.Book;
import com.example.btl.model.BookOrder;
import com.example.btl.model.Order;
import com.example.btl.model.User;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOrderAdapter extends RecyclerView.Adapter<ListOrderAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<Order> listOrder;
    public ListOrderAdapter(Context context){
        this.context = context;
        listOrder = new ArrayList<>();
    }
    public Order getOrder(int position){
        return listOrder.get(position);
    }
    public ArrayList<Order> getListOrder(){
        return listOrder;
    }
    public void setListOrder(User user){
        System.out.println("OK");
        ApiService.apiService.getOrder(user.get_id()).enqueue(new Callback<ArrayList<Order>>() {
            @Override
            public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                listOrder = new ArrayList<>();
                System.out.println("okok");
                for(int i=0; i<response.body().size(); ++i){
                    ArrayList<BookOrder> listBookOrder = new ArrayList<>();
                    for(int j=0; j<response.body().get(i).getBookOrders().size(); ++j){
                        String idB = response.body().get(i).getBookOrders().get(j).getBook().getId();
                        String nameB = response.body().get(i).getBookOrders().get(j).getBook().getName();
                        float priceB = response.body().get(i).getBookOrders().get(j).getBook().getPrice();
                        String publisherB = response.body().get(i).getBookOrders().get(j).getBook().getPublisher();
                        Date publishedDateB = response.body().get(i).getBookOrders().get(j).getBook().getPublishedDate();
                        String generesB = response.body().get(i).getBookOrders().get(j).getBook().getGeneres();
                        String imageB = response.body().get(i).getBookOrders().get(j).getBook().getImage();
                        String idA = response.body().get(i).getBookOrders().get(j).getBook().getAuthor().getId();
                        String nameA = response.body().get(i).getBookOrders().get(j).getBook().getAuthor().getName();
                        String yearA = response.body().get(i).getBookOrders().get(j).getBook().getAuthor().getYear();
                        Author author = new Author(idA, nameA, yearA);
                        Book book = new Book(idB, nameB, publisherB, publishedDateB, generesB, author, priceB, imageB);
                        int quantity = response.body().get(i).getBookOrders().get(j).getQuantity();
                        int status = response.body().get(i).getBookOrders().get(j).getStatus();
                        String id = response.body().get(i).getBookOrders().get(j).getId();
                        BookOrder bookOrder = new BookOrder(id, user, book, quantity, status);
                        listBookOrder.add(bookOrder);
                    }
                    String id = response.body().get(i).getId();
                    String address = response.body().get(i).getAddress();
                    int status = response.body().get(i).getStatus();
                    int shipment = response.body().get(i).getShipment();
                    Order order = new Order(id, user, listBookOrder, address, status, shipment);
                    listOrder.add(order);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Order>> call, Throwable t) {

            }
        });
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_order, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        RecyclerView rcListOrder;
        TextView txtId, txtStatus, txtTotal;
        Order order = listOrder.get(position);
        rcListOrder = holder.rcListOrder;
        txtId = holder.txtId;
        txtStatus = holder.txtStatus;
        txtTotal = holder.txtTotal;
        OrderAdapter orderAdapter = new OrderAdapter(context, order.getBookOrders());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rcListOrder.setLayoutManager(layoutManager);
        rcListOrder.setAdapter(orderAdapter);
        txtId.setText("Đơn hàng số " + order.getId());
        if(order.getStatus() == 0) txtStatus.setText("Chờ xác nhận");
        else txtStatus.setText("");
        float total = 0;
        for(int i=0; i<order.getBookOrders().size(); ++i){
            BookOrder bookOrder = order.getBookOrders().get(i);
            total += bookOrder.getQuantity() * bookOrder.getBook().getPrice();
        }
        if(order.getShipment() == 0) total += 30000;
        else total += 20000;
        txtTotal.setText(total + "");
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        RecyclerView rcListOrder;
        TextView txtId, txtStatus, txtTotal;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            rcListOrder = itemView.findViewById(R.id.rcListOrder);
            txtId = itemView.findViewById(R.id.txtId);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtTotal = itemView.findViewById(R.id.txtTotal);
        }
    }
}
