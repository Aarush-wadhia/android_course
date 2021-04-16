package org.yash;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ArrayList<Students> studentsArrayList = new ArrayList<>();
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        textView = findViewById(R.id.textView);
        try {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            Cursor cursor = db.query("students", null, null, null, null, null, "id DESC");
            if (null != cursor){
                if (cursor.moveToFirst()){
                    for (int i=0; i < cursor.getCount(); i++) {
                        Students students = new Students();
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String email = cursor.getString(cursor.getColumnIndex("email"));
                        students.setId(id);
                        students.setEmail(email);
                        students.setName(name);
                        studentsArrayList.add(students);
                        cursor.moveToNext();
                    }
                    cursor.close();
                    db.close();
                } else {
                    cursor.close();
                    db.close();
                }
            } else {
                db.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String text = "";
        for (Students s: studentsArrayList) {
            text += s.getId() + "\n" + s.getName() + "\n" + s.getEmail() + "\n*******\n";
        }
        textView.setText(text);
    }
}
