package org.yash;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

    public interface DeleteBook {
        void onDeleteBookResult(int bookId, int position);
    }

    private ArrayList<Books> booksList;
    private Context context;
    private DeleteBook deleteBook;
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
                Intent intent = new Intent(context, BookDetails.class);
                intent.putExtra(Constants.INTENT_BOOK_ID, booksList.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setMessage("Are you sure you want to delete this book?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            deleteBook = (DeleteBook) context;
                            deleteBook.onDeleteBookResult(booksList.get(position).getId(), position);
                        } catch (ClassCastException e) {
                            e.printStackTrace();
                        }
                    }
                }).create().show();
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
