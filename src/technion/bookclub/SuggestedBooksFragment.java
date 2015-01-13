package technion.bookclub;

import technion.bookclub.entities.Club;
import technion.bookclub.entities.SuggestedBook;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class SuggestedBooksFragment extends Fragment {
	
	public int suggestedBooks;
	public SuggestedBook[] books;

	public SuggestedBooksFragment(SuggestedBook[] books,int suggestedBooks) {
		this.books=new SuggestedBook[suggestedBooks];
		this.suggestedBooks=suggestedBooks;
		this.books=books;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.suggested_books, container, false);
		RadioButton button;
		for(int i=1; i<=suggestedBooks; i++){
			System.out.println(i);
			switch (i){
			case 1:
				button=(RadioButton)view.findViewById(R.id.book1);
				button.setText(books[i-1].getSuggestedBookId());
				button.setVisibility(0);
				break;
			case 2:
				button=(RadioButton)view.findViewById(R.id.book2);
				button.setText(books[i-1].getSuggestedBookId());
				button.setVisibility(0);
				break;
			case 3:
				button=(RadioButton)view.findViewById(R.id.book3);
				button.setText(books[i-1].getSuggestedBookId());
				button.setVisibility(0);
				break;
			case 4:
				button=(RadioButton)view.findViewById(R.id.book4);
				button.setText(books[i-1].getSuggestedBookId());
				button.setVisibility(0);
				break;
			case 5:
				button=(RadioButton)view.findViewById(R.id.book5);
				button.setText(books[i-1].getSuggestedBookId());
				button.setVisibility(0);
			default:
				break;
			}
		}
		return view;
	}
	
	
	
	

	

}
