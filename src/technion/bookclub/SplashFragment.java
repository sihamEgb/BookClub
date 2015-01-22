package technion.bookclub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SplashFragment extends Fragment {

//	private static final String TAG = "MainFragment";
	public SplashFragment(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.splash, 
	            container, false);
	    return view;
	}
	
	public static Fragment newInstance(){
		SplashFragment fragment = new SplashFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}
	
}
