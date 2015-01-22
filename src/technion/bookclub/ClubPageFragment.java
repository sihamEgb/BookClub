package technion.bookclub;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import android.graphics.Bitmap;
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
	public String date;
	
	public ClubPageFragment(){}

	public ClubPageFragment(String description, String memeberNum,
			String imageURL, String date) {
		this.description = description;
		this.memeberNum = memeberNum;
		this.imageURL = imageURL;
		this.date=date;
		// Required empty public constructor
	}

	public void setMembers(String memeberNum) {
		this.memeberNum = memeberNum;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.club_page, container, false);
		TextView des = (TextView) rootView.findViewById(R.id.Club_Info);
		des.setText(description);
		//
		TextView memebers = (TextView) rootView
				.findViewById(R.id.participants_num);
		memebers.setText(memeberNum);
		
		TextView meetingDate=(TextView) rootView.findViewById(R.id.meetingDate);
		meetingDate.setText(this.date);

		ImageView img = (ImageView) rootView.findViewById(R.id.Club_Image);
		Picasso.with(rootView.getContext()).load(imageURL).resize(180, 200)
				.centerCrop().into(img);

		// Picasso.with(context) .load(url) .resize(50, 50) .centerCrop()
		// .into(imageView)
		return rootView;
	}
	/*
	 * public static Fragment newInstance() { NextMeetingFragment fragment = new
	 * NextMeetingFragment(); Bundle args = new Bundle();
	 * fragment.setArguments(args); return fragment; }
	 */

}
