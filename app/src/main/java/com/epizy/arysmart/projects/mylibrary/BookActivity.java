package com.epizy.arysmart.projects.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    //instance for key
    public static final String BOOK_ID_KEY = " bookId";

    private TextView txtBName, txtAName, txtPages, txtDescription;
    private Button btnAddToCurrentlyReadingList, btnAddToWantToReadList, btnAddToAlreadyReadList, btnAddToFavoriteList;
    private ImageView bookImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initView();


        //get that extra value from intent
        Intent intent = getIntent();
        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if (bookId != -1){
                Book inComingBook = Utils.getInstance(this).getBookById(bookId);
                if(null != intent){
                    setData(inComingBook);

                    handleAlreadyRead(inComingBook);
                    handleWishListBooks(inComingBook);
                    handleCurrentlyReadingBooks(inComingBook);
                    handleFavoriteBooks(inComingBook);
                }
            }
        }
    }

    private void handleFavoriteBooks(Book inComingBook) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();

        boolean isExistInFavorites = false;

        for (Book b:favoriteBooks) {
            if (b.getId() == inComingBook.getId())
                isExistInFavorites = true;

        }

        if (isExistInFavorites == true)
            btnAddToFavoriteList.setEnabled(false);
        else{
            btnAddToFavoriteList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToFavorite(inComingBook)){
                        Toast.makeText(BookActivity.this, "Book Added",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoritesActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something wrong happened, Try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(Book inComingBook) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean isExistInCurrentlyReadingBooks = false;

        for (Book b:currentlyReadingBooks) {
            if (b.getId() == inComingBook.getId())
                isExistInCurrentlyReadingBooks = true;
        }

        if (isExistInCurrentlyReadingBooks == true)
            btnAddToCurrentlyReadingList.setEnabled(false);
        else{
            btnAddToCurrentlyReadingList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReadingBook(inComingBook)){
                        Toast.makeText(BookActivity.this, "Book Added",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingListActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something wrong happened, Try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWishListBooks(Book inComingBook) {
        ArrayList<Book> wishListBooks = Utils.getInstance(this).getWishListBooks();

        boolean isExistInWishListBooks = false;

        for (Book b:wishListBooks) {
            if (b.getId() == inComingBook.getId())
                isExistInWishListBooks = true;

        }

        if (isExistInWishListBooks == true){
            btnAddToWantToReadList.setEnabled(false);
        }
        else{
            btnAddToWantToReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToWishListBooks(inComingBook)){
                        Toast.makeText(BookActivity.this, "Book Added",Toast.LENGTH_SHORT).show();
                        //ToDO: make and activity
                        Intent intent = new Intent(BookActivity.this, WishListActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something wrong happened, Try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Enable and disable button
     * add the book to Already Read Book ArrayList
     * @param inComingBook
     */
    private void handleAlreadyRead(Book inComingBook) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean isExistInAlreadyRead = false;

        for (Book b:alreadyReadBooks) {
            if (b.getId() == inComingBook.getId())
                isExistInAlreadyRead = true;

        }

        if (isExistInAlreadyRead == true)
            btnAddToAlreadyReadList.setEnabled(false);
        else{
            btnAddToAlreadyReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(inComingBook)){
                        Toast.makeText(BookActivity.this, "Book Added",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something wrong happened, Try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        txtBName.setText(book.getName());
        txtAName.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);
    }

    private void initView() {
        txtBName = findViewById(R.id.txtBName);
        txtAName = findViewById(R.id.txtAName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToCurrentlyReadingList = findViewById(R.id.btnAddToCurrentlyReadingList);
        btnAddToWantToReadList = findViewById(R.id.btnAddToWantToReadList);
        btnAddToAlreadyReadList = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToFavoriteList = findViewById(R.id.btnAddToFavoriteList);

        bookImage = findViewById(R.id.bookImage);
    }
}