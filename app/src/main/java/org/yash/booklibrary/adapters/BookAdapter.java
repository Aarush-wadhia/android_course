package org.yash.booklibrary.adapters;

import android.content.Intent;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.yash.booklibrary.BookDetail;
import org.yash.booklibrary.Constant;
import org.yash.booklibrary.R;
import org.yash.booklibrary.modal.Books;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{

    ArrayList<Books> bookList;
    ViewGroup parent;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        this.parent = parent;
        View v = layoutInflater.inflate(R.layout.book_list_item, parent, false);
        return new ViewHolder(v);
    }

    public BookAdapter(ArrayList<Books> bookList) {
        this.bookList = bookList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.book_name.setText(bookList.get(position).getBookName());
        holder.book_author.setText(bookList.get(position).getBookAuthor());
        Glide.with(parent.getContext()).load(bookList.get(position).getImageUrl()).into(holder.image_url);
        holder.short_description.setText(bookList.get(position).getShortDescription());

        if (bookList.get(position).isExpandable()){
            TransitionManager.beginDelayedTransition(holder.parent_linear);
            holder.short_description.setVisibility(View.VISIBLE);
            holder.up_arrow.setVisibility(View.VISIBLE);
            holder.down_arrow.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(holder.parent_linear);
            holder.short_description.setVisibility(View.GONE);
            holder.up_arrow.setVisibility(View.GONE);
            holder.down_arrow.setVisibility(View.VISIBLE);
        }

        holder.image_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), BookDetail.class);
                int id = bookList.get(position).getId();
                intent.putExtra(Constant.BOOK_ID, id);
                parent.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_url, up_arrow, down_arrow;
        TextView book_name, book_author, short_description;
        LinearLayout parent_linear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_url = itemView.findViewById(R.id.image_url);
            book_name = itemView.findViewById(R.id.book_name);
            book_author = itemView.findViewById(R.id.author_name);
            up_arrow = itemView.findViewById(R.id.up_arrow);
            down_arrow = itemView.findViewById(R.id.down_arrow);
            short_description = itemView.findViewById(R.id.short_description);
            parent_linear = itemView.findViewById(R.id.parent);

            up_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Books books = bookList.get(getAdapterPosition());
                    books.setExpandable(false);
                    notifyItemChanged(getAdapterPosition());
                }
            });

            down_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Books books = bookList.get(getAdapterPosition());
                    books.setExpandable(true);
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
