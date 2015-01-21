package technion.bookclub;

import technion.bookclub.entities.SuggestedBook;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class SuggestNewBook extends DialogFragment {
	public SuggestNewBook() {

	}
	
    public static SuggestNewBook newInstance() {
    	SuggestNewBook frag = new SuggestNewBook();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }
//
//	public Dialog onCreateDialog(Bundle savedInstanceState) {
//		Dialog view = this..inflate(R.layout.suggest_new_book, container, false);
//		return view;
//	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	getDialog().setTitle("Suggest new book:");
        View v = inflater.inflate(R.layout.suggest_new_book, container, false);
        Button button = (Button)v.findViewById(R.id.Suggest);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
//                // When button is clicked, call up to owning activity.
//            	
////                (((Fragment) v.getRootView()).getActivity());
            	NewBookSuggestion(v.getRootView());
            	
            }
        });
        return v;
    }
    
    public void NewBookSuggestion(View v){
		EditText edit=(EditText)v.findViewById(R.id.Edit_title);
		String bookName=edit.getText().toString().trim();
		if(bookName.equals("")){
			return;
		}
    	((ClubPageActivity)this.getActivity()).NewBookSuggestion(bookName);
    	dismiss();
    }
}
