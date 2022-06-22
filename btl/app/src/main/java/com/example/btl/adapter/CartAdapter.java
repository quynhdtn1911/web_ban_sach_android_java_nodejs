package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.controller.ApiService;
import com.example.btl.model.Author;
import com.example.btl.model.Book;
import com.example.btl.model.BookOrder;
import com.example.btl.model.Data;
import com.example.btl.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<BookOrder> listBookOrder;
    private ArrayList<String> listChecked;
    public CartAdapter(Context context){
        this.context = context;
        listChecked = new ArrayList<>();
        listBookOrder = new ArrayList<>();
    }
    public void addChecked(String id){
        if(!listChecked.contains(id)) listChecked.add(id);
    }
    public void removeChecked(String id){
        if(listChecked.contains(id)) listChecked.remove(id);
    }
    public ArrayList<String> getListChecked(){
        return listChecked;
    }
    public void setListChecked(ArrayList<String> listChecked){
        this.listChecked = new ArrayList<>();
    }
    public void removeFromCart(User user){
        ApiService.apiService.removeFromCart(listChecked).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();
                if(data.isStatus() == true){
//                    setListBookOrder(user);
                    ApiService.apiService.getCart(user.get_id()).enqueue(new Callback<ArrayList<BookOrder>>() {
                        @Override
                        public void onResponse(Call<ArrayList<BookOrder>> call, Response<ArrayList<BookOrder>> response) {
                            listBookOrder = new ArrayList<>();
                            for(int i=0; i<response.body().size(); ++i){
                                String idB = response.body().get(i).getBook().getId();
                                String nameB = response.body().get(i).getBook().getName();
                                Float priceB = response.body().get(i).getBook().getPrice();
                                String publisherB = response.body().get(i).getBook().getPublisher();
                                Date publishedDateB = response.body().get(i).getBook().getPublishedDate();
                                String generesB = response.body().get(i).getBook().getGeneres();
                                String imageB = response.body().get(i).getBook().getImage();
                                String idA = response.body().get(i).getBook().getAuthor().getId();
                                String nameA = response.body().get(i).getBook().getAuthor().getName();
                                String yearA = response.body().get(i).getBook().getAuthor().getYear();
                                Author author = new Author(idA, nameA, yearA);
                                Book book = new Book(idB, nameB, publisherB, publishedDateB, generesB, author, priceB, imageB);
                                int quantity = response.body().get(i).getQuantity();
                                int status = response.body().get(i).getStatus();
                                String id = response.body().get(i).getId();
                                BookOrder bookOrder = new BookOrder(id, user, book, quantity, status);
                                listBookOrder.add(bookOrder);
                            }
                            listChecked = new ArrayList<>();
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<ArrayList<BookOrder>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }
    public BookOrder getBookOrder(int position){
        return listBookOrder.get(position);
    }
    public ArrayList<BookOrder> getListBookOrder(){
        return listBookOrder;
    }
    public void setListBookOrder(User user){
        ApiService.apiService.getCart(user.get_id()).enqueue(new Callback<ArrayList<BookOrder>>() {
            @Override
            public void onResponse(Call<ArrayList<BookOrder>> call, Response<ArrayList<BookOrder>> response) {
                listBookOrder = new ArrayList<>();
                for(int i=0; i<response.body().size(); ++i){
                    String idB = response.body().get(i).getBook().getId();
                    String nameB = response.body().get(i).getBook().getName();
                    Float priceB = response.body().get(i).getBook().getPrice();
                    String publisherB = response.body().get(i).getBook().getPublisher();
                    Date publishedDateB = response.body().get(i).getBook().getPublishedDate();
                    String generesB = response.body().get(i).getBook().getGeneres();
                    String imageB = response.body().get(i).getBook().getImage();
                    String idA = response.body().get(i).getBook().getAuthor().getId();
                    String nameA = response.body().get(i).getBook().getAuthor().getName();
                    String yearA = response.body().get(i).getBook().getAuthor().getYear();
                    Author author = new Author(idA, nameA, yearA);
                    Book book = new Book(idB, nameB, publisherB, publishedDateB, generesB, author, priceB, imageB);
                    int quantity = response.body().get(i).getQuantity();
                    int status = response.body().get(i).getStatus();
                    String id = response.body().get(i).getId();
                    BookOrder bookOrder = new BookOrder(id, user, book, quantity, status);
                    listBookOrder.add(bookOrder);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<BookOrder>> call, Throwable t) {

            }
        });
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ImageView img;
        TextView txtName, txtAuthor, txtGeneres, txtPrice, txtQuantity;
        CheckBox cb;
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
        cb = holder.checkBox;
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb.isChecked() == true){
                    addChecked(bookOrder.getId());
                }else{
                    removeChecked(bookOrder.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBookOrder.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        ImageView img;
        TextView txtName, txtAuthor, txtGeneres, txtPrice, txtQuantity;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb);
            img = itemView.findViewById(R.id.img);
            txtName = itemView.findViewById(R.id.txtName);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtGeneres = itemView.findViewById(R.id.txtGeneres);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
        }
    }
}
