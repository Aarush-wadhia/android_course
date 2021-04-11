package org.yash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> newsArrayList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_item_list, parent, false);
        return new ViewHolder(view);
    }

    public NewsAdapter(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(newsArrayList.get(position).getTitle());
        holder.description.setText(newsArrayList.get(position).getDescription());
//        holder.category.setText(newsArrayList.get(position).getCategory());
        holder.pubdate.setText(newsArrayList.get(position).getPubdate());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, category, pubdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            category = itemView.findViewById(R.id.category);
            pubdate = itemView.findViewById(R.id.pubdate);
        }
    }
}
