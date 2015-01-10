package technion.bookclub;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageBooksListFragment extends ListFragment{
	private HomePageBooksListAdapter listAdapter;
	//String[] options = {"Edit","Delete","Set As not Available"};
	String[] menuItems = {"Edit","Delete","Mark As Available"};
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listAdapter = new HomePageBooksListAdapter(getActivity());
		setListAdapter(listAdapter);
		//getListView().setDivider(null);
		//this.getListView().setDividerHeight(0);// getListView().setDividerHeight(10); 
    }

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onActivityCreated(savedInstanceState);
		getListView().setDivider(null);
		getListView().setDividerHeight(0);
	}	
	
}
