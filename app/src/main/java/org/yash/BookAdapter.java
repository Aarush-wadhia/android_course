package org.yash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{

    private ArrayList<Books> booksList;
    private Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    public BookAdapter(Context context){
        this.context = context;
    }

    public void setBooksList(ArrayList<Books> booksList) {
        this.booksList = booksList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: navigate to book detail page
                Toast.makeText(context, "normal click", Toast.LENGTH_SHORT).show();
            }
        });

        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //TODO: delete the selected book
                Toast.makeText(context, "long click", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        holder.book_name.setText(booksList.get(position).getBook_name());
        Glide.with(context).load(booksList.get(position).getBook_image()).into(holder.book_image);
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout parent;
        private TextView book_name;
        private ImageView book_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            book_name = itemView.findViewById(R.id.book_name);
            book_image = itemView.findViewById(R.id.book_image);
        }
    }
}
