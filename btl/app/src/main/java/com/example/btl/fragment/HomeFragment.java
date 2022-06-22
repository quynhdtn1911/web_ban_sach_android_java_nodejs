package com.example.btl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.BookDetailActivity;
import com.example.btl.HomeActivity;
import com.example.btl.R;
import com.example.btl.adapter.BookAdapter;
import com.example.btl.controller.ApiService;
import com.example.btl.model.Author;
import com.example.btl.model.Book;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements BookAdapter.BookListener {
    private EditText txtSearch;
    private TextView txtResult;
    private RecyclerView rcVHVN, rcKT, rcSearch, rcSkill, rcCare;
    private BookAdapter bookAdapterVHVN, bookAdapterKT, bookAdapterSearch, bookAdapterSkill, bookAdapterCare;
    private ArrayList<Book> booksVHVN, booksKT, booksSearch, booksSkill, booksCare;
    private HomeActivity homeActivity;
    public HomeFragment(HomeActivity homeActivity){
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
    private void init(View view){
        txtSearch = view.findViewById(R.id.txtSearch);
        txtResult = view.findViewById(R.id.txtResult);
        txtResult.setText("");
        rcVHVN = view.findViewById(R.id.rcVHVN);
        rcKT = view.findViewById(R.id.rcKT);
        rcSearch = view.findViewById(R.id.rcSearch);
        rcSkill = view.findViewById(R.id.rcSkill);
        rcCare = view.findViewById(R.id.rcCare);
        RecyclerView.LayoutManager layoutManagerVHVN = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerKT = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerSearch = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerSkill = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerCare = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        //hien thi danh sach sach van hoc viet nam
        rcVHVN.setLayoutManager(layoutManagerVHVN);
        bookAdapterVHVN = new BookAdapter(getContext());
        bookAdapterVHVN.setListener(this);
        booksVHVN = new ArrayList<>();
        booksSkill = new ArrayList<>();
        booksCare = new ArrayList<>();
        ApiService.apiService.getBooksByGeneres("Văn học Việt Nam").enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
//                ArrayList<Book> listBook = response.body();
//                System.out.println("ok" + response.body().size());
                for(int i=0; i<response.body().size(); ++i){
                    Book book;
                    String _id = response.body().get(i).getId();
                    String name = response.body().get(i).getName();
                    Float price = response.body().get(i).getPrice();
                    String image = response.body().get(i).getImage();
                    String publisher = response.body().get(i).getPublisher();
                    Date publishedDate = response.body().get(i).getPublishedDate();
                    String generes = response.body().get(i).getGeneres();
                    Author author = new Author();
                    author.setId(response.body().get(i).getAuthor().getId());
                    author.setName(response.body().get(i).getAuthor().getName());
                    author.setYear(response.body().get(i).getAuthor().getYear());
                    book = new Book(_id, name, publisher, publishedDate, generes, author, price, image);
                    booksVHVN.add(book);
                }
                bookAdapterVHVN.setListBook(booksVHVN);
                rcVHVN.setAdapter(bookAdapterVHVN);
//                System.out.println("ok " + listBook.size() + " ok");
            }
            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

            }
        });

        //hien thi danh sach sach kinh te
        rcKT.setLayoutManager(layoutManagerKT);
        bookAdapterKT = new BookAdapter(getContext());
        bookAdapterKT.setListener(this);
        booksKT = new ArrayList<>();
        //hien thi danh sach sach tim kiem
        rcSearch.setLayoutManager(layoutManagerSearch);
        bookAdapterSearch = new BookAdapter(getContext());
        bookAdapterSearch.setListener(this);
        booksSearch = new ArrayList<>();
        ApiService.apiService.getBooksByGeneres("Sách kinh tế - marketing").enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
//                ArrayList<Book> listBook = response.body();
//                System.out.println("ok" + response.body().size());
                for(int i=0; i<response.body().size(); ++i){
                    Book book;
                    String _id = response.body().get(i).getId();
                    String name = response.body().get(i).getName();
                    Float price = response.body().get(i).getPrice();
                    String image = response.body().get(i).getImage();
                    String publisher = response.body().get(i).getPublisher();
                    Date publishedDate = response.body().get(i).getPublishedDate();
                    String generes = response.body().get(i).getGeneres();
                    Author author = new Author();
                    author.setId(response.body().get(i).getAuthor().getId());
                    author.setName(response.body().get(i).getAuthor().getName());
                    author.setYear(response.body().get(i).getAuthor().getYear());
                    book = new Book(_id, name, publisher, publishedDate, generes, author, price, image);
                    booksKT.add(book);
                }
                bookAdapterKT.setListBook(booksKT);
                rcKT.setAdapter(bookAdapterKT);
//                System.out.println("ok " + listBook.size() + " ok");
            }
            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

            }
        });

        //hien thi danh sach sach ky năng va phat trien ban than
        rcSkill.setLayoutManager(layoutManagerSkill);
        bookAdapterSkill = new BookAdapter(getContext());
        bookAdapterSkill.setListener(this);
        booksSkill = new ArrayList<>();
        ApiService.apiService.getBooksByGeneres("Sách kĩ năng và phát triển bản thân").enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
//                ArrayList<Book> listBook = response.body();
//                System.out.println("ok" + response.body().size());
                for(int i=0; i<response.body().size(); ++i){
                    Book book;
                    String _id = response.body().get(i).getId();
                    String name = response.body().get(i).getName();
                    Float price = response.body().get(i).getPrice();
                    String image = response.body().get(i).getImage();
                    String publisher = response.body().get(i).getPublisher();
                    Date publishedDate = response.body().get(i).getPublishedDate();
                    String generes = response.body().get(i).getGeneres();
                    Author author = new Author();
                    author.setId(response.body().get(i).getAuthor().getId());
                    author.setName(response.body().get(i).getAuthor().getName());
                    author.setYear(response.body().get(i).getAuthor().getYear());
                    book = new Book(_id, name, publisher, publishedDate, generes, author, price, image);
                    booksSkill.add(book);
                }
                bookAdapterSkill.setListBook(booksSkill);
                rcSkill.setAdapter(bookAdapterSkill);
//                System.out.println("ok " + listBook.size() + " ok");
            }
            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

            }
        });

        //hien thi danh sach sach cham soc-nuoi day tre
        rcCare.setLayoutManager(layoutManagerCare);
        bookAdapterCare = new BookAdapter(getContext());
        bookAdapterCare.setListener(this);
        booksCare = new ArrayList<>();
        ApiService.apiService.getBooksByGeneres("Sách chăm sóc-nuôi dạy con").enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
//                ArrayList<Book> listBook = response.body();
//                System.out.println("ok" + response.body().size());
                for(int i=0; i<response.body().size(); ++i){
                    Book book;
                    String _id = response.body().get(i).getId();
                    String name = response.body().get(i).getName();
                    Float price = response.body().get(i).getPrice();
                    String image = response.body().get(i).getImage();
                    String publisher = response.body().get(i).getPublisher();
                    Date publishedDate = response.body().get(i).getPublishedDate();
                    String generes = response.body().get(i).getGeneres();
                    Author author = new Author();
                    author.setId(response.body().get(i).getAuthor().getId());
                    author.setName(response.body().get(i).getAuthor().getName());
                    author.setYear(response.body().get(i).getAuthor().getYear());
                    book = new Book(_id, name, publisher, publishedDate, generes, author, price, image);
                    booksCare.add(book);
                }
                bookAdapterCare.setListBook(booksCare);
                rcCare.setAdapter(bookAdapterCare);
//                System.out.println("ok " + listBook.size() + " ok");
            }
            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

            }
        });

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println(txtSearch.getText());
                String key = txtSearch.getText().toString();
                booksSearch = new ArrayList<>();
                ApiService.apiService.searchBook(key).enqueue(new Callback<ArrayList<Book>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                        booksSearch = response.body();
                        bookAdapterSearch.setListBook(booksSearch);
                        rcSearch.setAdapter(bookAdapterSearch);
                        if(booksSearch.size() == 0){
                            txtResult.setText("Không tìm thấy kết quả");
                        }else{
                            txtResult.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

                    }
                });
            }
        });


//        bookAdapterKT.setListBook(new ArrayList<>());
//        bookAdapterKT.setListener(this);

        //admin@gmail.com
        //123456
    }
    @Override
    public void bookListener(View view, Book book) {
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        intent.putExtra("book", book);
        intent.putExtra("user", getHomeActivity().getUser());
        startActivity(intent);
    }
}
