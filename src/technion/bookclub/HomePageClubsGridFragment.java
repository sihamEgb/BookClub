package technion.bookclub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class HomePageClubsGridFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//return inflater.inflate(R.layout.homepage_clubs_fragment_xml, container, false); 
		
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.homepage_clubs_fragment_xml, container, false);
		GridView gView = (GridView)view.findViewById(R.id.homepage_clubs_gridview);
		gView.setAdapter(new HomePageClubsListAdapter(this.getActivity()));
        return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
     
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	
}
