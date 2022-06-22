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
import com.example.btl.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ItemViewHolder> {
    private Context context;
    private ArrayList<Book> listBook;
    private BookListener listener;
    public BookAdapter(Context context){
        this.context = context;
    }
    public void setListener(BookListener listener){
        this.listener = listener;
    }
    public void setListBook(ArrayList<Book> listBook){
        this.listBook = listBook;
    }
    public Book getBook(int position){
        return listBook.get(position);
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Book book = listBook.get(position);
        ImageView img = holder.img;
        TextView txtName = holder.txtName, txtPrice = holder.txtPrice;
        Picasso.get().load("http://192.168.142.1:5000/images/" + book.getImage()).fit().centerCrop().into(img);
        txtName.setText(book.getName());
        txtPrice.setText(String.valueOf(book.getPrice()));
    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView txtName, txtPrice;
        ImageView btnCart;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.bookListener(view, listBook.get(getAdapterPosition()));
        }
    }
    public interface BookListener{
        public void bookListener(View view, Book book);
    }
}
