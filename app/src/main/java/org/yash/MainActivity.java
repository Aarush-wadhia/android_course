package org.yash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView contactRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationContext();
        setContentView(R.layout.activity_main);
        contactRecView = findViewById(R.id.contactRecView);
        ArrayList<Contacts> contactsArrayList = new ArrayList<>();
        contactsArrayList.add(new Contacts("Yash", "yashwadhia@gmail.com"));
        contactsArrayList.add(new Contacts("Priyadarshani", "priyadarshani@gmail.com"));
        contactsArrayList.add(new Contacts("Vaibhav", "vaibhavsaidsomething@gmail.com"));
        ContactAdapter contactAdapter = new ContactAdapter(contactsArrayList);
        contactRecView.setLayoutManager(new LinearLayoutManager(this));
        contactRecView.setAdapter(contactAdapter);
    }
}