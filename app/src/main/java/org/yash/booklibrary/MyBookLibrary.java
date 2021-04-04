package org.yash.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.yash.booklibrary.adapters.BookAdapter;
import org.yash.booklibrary.modal.Books;

import java.util.ArrayList;

public class MyBookLibrary extends AppCompatActivity {

    RecyclerView book_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book_library);
        book_list = findViewById(R.id.book_list);
        BookAdapter bookAdapter = new BookAdapter(Utils.getInstance().getAllBooksData());
        book_list.setLayoutManager(new LinearLayoutManager(this));
        book_list.setAdapter(bookAdapter);
    }
}