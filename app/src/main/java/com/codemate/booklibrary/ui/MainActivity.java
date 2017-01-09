package com.codemate.booklibrary.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.codemate.booklibrary.data.Book;
import com.codemate.booklibrary.data.Library;
import com.codemate.booklibrary.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, SearchView.OnQueryTextListener {
    private MainPresenter presenter;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        presenter = new MainPresenter(this, new Library());
        presenter.loadAllBooks();
    }

    private void initializeViews() {
        bookAdapter = new BookAdapter();

        RecyclerView bookRecycler = (RecyclerView) findViewById(R.id.bookRecycler);
        bookRecycler.setLayoutManager(new LinearLayoutManager(this));
        bookRecycler.setAdapter(bookAdapter);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        presenter.searchForBooks(newText);
        return false;
    }

    @Override
    public void showBooks(List<Book> books) {
        bookAdapter.updateItems(books);
    }
}
