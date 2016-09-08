package comp5216.sydney.edu.au.hyra.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import comp5216.sydney.edu.au.hyra.R;

/**
 * Created by apple on 10/16/15.
 */
public class BooksFragment extends Fragment {

    EditText ISBN;
    EditText author;
    EditText bookTitle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.books_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ISBN = (EditText)getActivity().findViewById(R.id.isbnValue);
        author = (EditText)getActivity().findViewById(R.id.authorValue);
        bookTitle = (EditText)getActivity().findViewById(R.id.bookTitle);
    }

    public String getISBN() {
        return ISBN.getText().toString();
    }

    public String getAuthor() {
        return author.getText().toString();
    }

    public String getBookTitle() {
        return bookTitle.getText().toString();
    }
}
