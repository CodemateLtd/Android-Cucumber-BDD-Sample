package com.codemate.booklibrary.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codemate.booklibrary.data.Book;
import com.codemate.booklibrary.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private final SimpleDateFormat dateFormat;
    private List<Book> items = new ArrayList<>();

    public BookAdapter() {
        dateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_book, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = items.get(position);

        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.date.setText(dateFormat.format(book.getPublishDate()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(List<Book> books) {
        this.items = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView author;
        final TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.bookTitle);
            author = (TextView) itemView.findViewById(R.id.bookAuthor);
            date = (TextView) itemView.findViewById(R.id.bookDate);
        }
    }
}
