package technion.bookclub;

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
	
	//int suggestedBooks=0;

	public SuggestedBooksFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.suggested_books, container, false);
		RadioButton button;
		for(int i=1; i<=((ClubPageActivity)getActivity()).suggestedBooks; i++){
			switch (i){
			case 1:
				button=(RadioButton)view.findViewById(R.id.book1);
				button.setText("Book 1");
				button.setVisibility(1);
				break;
			case 2:
				button=(RadioButton)view.findViewById(R.id.book2);
				button.setText("Book 2");
				button.setVisibility(1);
				break;
			case 3:
				button=(RadioButton)view.findViewById(R.id.book3);
				button.setText("Book 3");
				button.setVisibility(1);
				break;
			case 4:
				button=(RadioButton)view.findViewById(R.id.book4);
				button.setText("Book 4");
				button.setVisibility(1);
				break;
			case 5:
				button=(RadioButton)view.findViewById(R.id.book5);
				button.setText("Book 5");
				button.setVisibility(1);
			default:
				break;
			}
		}
		return view;
	}
	
	
	
	

	

}
