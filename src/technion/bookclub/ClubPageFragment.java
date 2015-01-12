package technion.bookclub;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//import android.view.ViewGroup;
import android.support.v4.app.Fragment;
//import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class ClubPageFragment extends Fragment {
	public String description;
	public String memeberNum;
	public String imageURL;
	
	public ClubPageFragment(String description, String memeberNum, String imageURL) {
		this.description=description;
		this.memeberNum=memeberNum;
		this.imageURL=imageURL;
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.club_page, container,
				false);
		TextView des=(TextView)rootView.findViewById(R.id.Club_Info);
		des.setText(description);
//		
		TextView memebers=(TextView)rootView.findViewById(R.id.participants_num);
		memebers.setText(memeberNum);
		
		ImageView img=(ImageView)rootView.findViewById(R.id.Club_Image);
		Picasso.with(rootView.getContext()).load(imageURL).into(img);
		return rootView;
	}
	public static Fragment newInstance() {
		NextMeetingFragment fragment = new NextMeetingFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

}
