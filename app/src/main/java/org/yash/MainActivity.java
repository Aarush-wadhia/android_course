package org.yash;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView libRecView;
    private BookAdapter bookAdapter;
    private Thread getBookThread;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        libRecView = findViewById(R.id.libRecView);
        bookAdapter = new BookAdapter(this);
        libRecView.setAdapter(bookAdapter);
        libRecView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        databaseHelper = new DatabaseHelper(this);
        getAllBooks(databaseHelper);
    }

    private void getAllBooks(DatabaseHelper dbHelper) {
        getBookThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor cursor = db.query("library", null, null, null, null, null, null);
                    if (null != cursor) {
                        if (cursor.moveToFirst()) {
                            ArrayList<Books> booksList = new ArrayList<>();
                            int idIndex = cursor.getColumnIndex("id");
                            int nameIndex = cursor.getColumnIndex("name");
                            int authorIndex = cursor.getColumnIndex("author");
                            int image_urlIndex = cursor.getColumnIndex("image_url");
                            int descriptionIndex = cursor.getColumnIndex("description");

                            Log.d("Count: ", String.valueOf(cursor.getCount()));
                            for (int i = 0; i < cursor.getCount(); i++) {
                                Books books = new Books();
                                books.setId(cursor.getInt(idIndex));
                                books.setBook_name(cursor.getString(nameIndex));
                                books.setBook_author(cursor.getString(authorIndex));
                                books.setBook_image(cursor.getString(image_urlIndex));
                                books.setBook_description(cursor.getString(descriptionIndex));
                                booksList.add(books);

                                cursor.moveToNext();
                            }

                            if (null != booksList) {
                                bookAdapter.setBooksList(booksList);
                            } else {
                                bookAdapter.setBooksList(new ArrayList<>());
                            }
                        }
                    }
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        getBookThread.start();
    }
}
