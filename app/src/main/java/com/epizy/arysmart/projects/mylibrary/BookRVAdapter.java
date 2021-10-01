package com.epizy.arysmart.projects.mylibrary;

import static com.epizy.arysmart.projects.mylibrary.BookActivity.BOOK_ID_KEY;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.ViewHolder> {
    private static final String TAG = "BookRVAdapter";
    private ArrayList<Book> books = new ArrayList<>();
    private Context mainContext;
    private String parentActivity;

    public BookRVAdapter(Context mainContext, String parentActivity) {
        this.mainContext = mainContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);//exact code
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtBookName.setText(books.get(position).getName());

        Glide.with(mainContext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.booksCardParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainContext, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                //intent.putExtra("bookName", books.get(position).getName());
                mainContext.startActivity(intent);
            }
        });

        holder.txtAuthorName.setText(books.get(position).getAuthor());
        holder.txtShortDesc.setText(books.get(position).getShortDesc());

        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.booksCardParent);
            holder.expandedRL.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.INVISIBLE);

            if (parentActivity.equals("allBooks")){
                holder.btnDelete.setVisibility(View.GONE);
            }
            else if(parentActivity.equals("alreadyRead"))
            {
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //show Dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(mainContext);
                        builder.setMessage("Are you sure, you want to delete "+books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance(mainContext).removeFromAlreadyRead(books.get(position)))
                                {
                                    Toast.makeText(mainContext,"Book removed",Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                                else
                                    Toast.makeText(mainContext,"Something went wrong, try again",Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();

                    }
                });
            }
            else if(parentActivity.equals("wishList")){
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mainContext);
                        builder.setMessage("Are you sure, you want to delete "+books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance(mainContext).removeFromWishListBooks(books.get(position)))
                                {
                                    Toast.makeText(mainContext,"Book removed",Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                                else
                                    Toast.makeText(mainContext,"Something went wrong, try again",Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();
                    }
                });
            }
            else if(parentActivity.equals("currentlyReading")){
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mainContext);
                        builder.setMessage("Are you sure, you want to delete "+books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance(mainContext).removeFromCurrentlyReadingBooks(books.get(position)))
                                {
                                    Toast.makeText(mainContext,"Book removed",Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                                else
                                    Toast.makeText(mainContext,"Something went wrong, try again",Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();
                    }
                });
            }
            else  if(parentActivity.equals("favorites")){
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mainContext);
                        builder.setMessage("Are you sure, you want to delete "+books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance(mainContext).removeFromFavoriteBooks(books.get(position)))
                                {
                                    Toast.makeText(mainContext,"Book removed",Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                                else
                                    Toast.makeText(mainContext,"Something went wrong, try again",Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();
                    }
                });
            }
        }
        else
        {
            TransitionManager.beginDelayedTransition(holder.booksCardParent);
            holder.expandedRL.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE );
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView  booksCardParent;
        private ImageView imgBook;
        private TextView txtBookName;

        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRL;
        private TextView txtAuthorName, txtShortDesc;
        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            booksCardParent = itemView.findViewById(R.id.booksCardParent);
            imgBook = itemView.findViewById(R.id.bookImage);
            txtBookName = itemView.findViewById(R.id.txtBookName);

            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRL = itemView.findViewById(R.id.expandedRL);
            txtAuthorName = itemView.findViewById(R.id.txtAuthorName);
            txtShortDesc = itemView.findViewById(R.id.txtShortDesc);
            btnDelete= itemView.findViewById(R.id.btnDelete);


            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}