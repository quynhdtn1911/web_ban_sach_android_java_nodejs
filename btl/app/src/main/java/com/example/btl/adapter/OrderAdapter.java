package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.model.BookOrder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<BookOrder> listBookOrder;
    public OrderAdapter(Context context, ArrayList<BookOrder> listBookOrder){
        this.context = context;
        this.listBookOrder = listBookOrder;
    }
    public BookOrder getBookOrder(int position){
        return listBookOrder.get(position);
    }
    public ArrayList<BookOrder> getListBookOrder(){
        return listBookOrder;
    }
    public void setListBookOrder(ArrayList<BookOrder> listBookOrder){
        this.listBookOrder = listBookOrder;
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_order, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ImageView img;
        TextView txtName, txtAuthor, txtGeneres, txtPrice, txtQuantity;
        BookOrder bookOrder = listBookOrder.get(position);
        img = holder.img;
        txtName = holder.txtName;
        txtAuthor = holder.txtAuthor;
        txtGeneres = holder.txtGeneres;
        txtPrice = holder.txtPrice;
        txtQuantity = holder.txtQuantity;
        Picasso.get().load("http://192.168.142.1:5000/images/" + bookOrder.getBook().getImage()).fit().centerCrop().into(img);
        txtName.setText(bookOrder.getBook().getName());
        txtAuthor.setText(bookOrder.getBook().getAuthor().getName());
        txtGeneres.setText(bookOrder.getBook().getGeneres());
        txtPrice.setText(bookOrder.getBook().getPrice() + "");
        txtQuantity.setText(bookOrder.getQuantity() + "");
    }

    @Override
    public int getItemCount() {
        return listBookOrder.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtName, txtAuthor, txtGeneres, txtPrice, txtQuantity;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtName = itemView.findViewById(R.id.txtName);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtGeneres = itemView.findViewById(R.id.txtGeneres);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
        }
    }
}
