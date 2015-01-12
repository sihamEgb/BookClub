package technion.bookclub;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import technion.bookclub.entities.Book;
import technion.bookclub.entities.Club;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageClubsListAdapter extends BaseAdapter {

	private ArrayList<Club> clubs;

	private void BuildClubsListFromJson(String result) {
		Club first_item = new Club();
		first_item.setMemeberNum("");
		first_item.setName("Create Club");
		clubs.add(first_item);
		try {
			JSONObject obj = new JSONObject(result);
			JSONArray jsonArr = new JSONArray(obj.getString("results"));
			int numOfItems = jsonArr.length();
			JSONObject json;
			for (int i = 0; i < numOfItems; i++) {
				Club newBook = new Club();
				json = jsonArr.getJSONObject(i);
				newBook = Club.constructFromJson(json.toString());
				clubs.add(newBook);
			}
			// return results;
		} catch (Exception e) {
			System.out
					.println("homepageclubs adapter error :getting Clubs from string FAILED");
			// e.printStackTrace();
		}
	}

	/**********************************************************************************/
	/****************************** SHOULD BE DELETED ***********************************/
	/**********************************************************************************/
	/*
	 * private class Club{ public String club_name; public String
	 * club_members_num; public String next_meeting_date; public final Drawable
	 * club_pic;
	 * 
	 * public Club(Drawable p,String name,String num,String d){ club_pic=p;
	 * club_name=name; club_members_num=num; next_meeting_date=d; } }
	 * 
	 * int maxClubsNumber = 25; private ArrayList<Club> clubs = new
	 * ArrayList<Club>();
	 * 
	 * private void buildClubs(){ Resources resources = context.getResources();
	 * for(int i=1;i<25;i++){ if(i==1){ clubs.add(new
	 * Club(resources.getDrawable(
	 * R.drawable.plus),"Create Your Own Club"," "," ")); continue; }
	 * clubs.add(new
	 * Club(resources.getDrawable(R.drawable.club_3),"Club Number"+
	 * i,String.valueOf
	 * (i+5)+" members","next meeting: Friday 2/12/2014 5:00pm")); } }
	 */
	/**********************************************************************************/
	/**********************************************************************************/
	/**********************************************************************************/

	private Club getClub(int position) {
		return clubs.get(position);
	}

	private class ViewHolder {
		public TextView name;
		public TextView members_num;
		// public TextView meeting_date;
		public ImageView pic;
	}

	private Context context;

	public HomePageClubsListAdapter(Context con, String clubsDataString) {
		super();
		context = con;
		clubs = new ArrayList<Club>();
		BuildClubsListFromJson(clubsDataString);
		// buildClubs();
	}

	@Override
	public int getCount() {
		if (clubs == null) {
			return 0;
		}
		return clubs.size();
	}

	@Override
	public Object getItem(int position) {
		if (clubs == null) {
			return null;
		}
		View view;
		ViewHolder holder;
		Club club = getClub(position);
		// LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.homepage_clubs_list_item, null);
		holder = new ViewHolder();
		holder.name = (TextView) view.findViewById(R.id.club_name);
		holder.members_num = (TextView) view.findViewById(R.id.club_mem_num);
		// holder.meeting_date = (TextView)
		// view.findViewById(R.id.club_next_date);
		holder.pic = (ImageView) view.findViewById(R.id.club_img);
		overflowClickListener l = new overflowClickListener();
		((ImageView) view.findViewById(R.id.homepage_clubs_edit_img))
				.setOnClickListener(l);
		view.setTag(holder);
		holder.name.setText(club.getName());
		holder.members_num.setText(club.getMemeberNum());
		// holder.meeting_date.setText(club.next_meeting_date);
		// TODO: GET IMAGE FROM URL
		holder.pic.setImageDrawable(context.getResources().getDrawable(
				R.drawable.club_3));
		if (position == 0) {
			((ImageView) view.findViewById(R.id.homepage_clubs_edit_img))
					.setVisibility(View.GONE);
			firstListItemListener first_item_l = new firstListItemListener();
			view.setOnClickListener(first_item_l);
		} else {
			((ImageView) view.findViewById(R.id.homepage_clubs_edit_img))
					.setVisibility(View.VISIBLE);
			view.setOnClickListener(null);
		}
		return view;
	}

	@Override
	public long getItemId(int position) {
		if (clubs == null) {
			return -1;
		}
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;

		if (convertView == null) {
			// LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.homepage_clubs_list_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.club_name);
			holder.members_num = (TextView) view
					.findViewById(R.id.club_mem_num);
			// holder.meeting_date = (TextView)
			// view.findViewById(R.id.club_next_date);
			holder.pic = (ImageView) view.findViewById(R.id.club_img);
			overflowClickListener l = new overflowClickListener();
			((ImageView) view.findViewById(R.id.homepage_clubs_edit_img))
					.setOnClickListener(l);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) convertView.getTag();
		}

		Club club = getClub(position);
		holder.name.setText(club.getName());
		holder.members_num.setText(club.getMemeberNum());
		// holder.meeting_date.setText(club.next_meeting_date);
		// TODO: GET IMAGE FROM URL
		holder.pic.setImageDrawable(context.getResources().getDrawable(
				R.drawable.club_3));

		if (position == 0) {
			((ImageView) view.findViewById(R.id.homepage_clubs_edit_img))
					.setVisibility(View.GONE);
			firstListItemListener first_item_l = new firstListItemListener();
			view.setOnClickListener(first_item_l);
		} else {
			((ImageView) view.findViewById(R.id.homepage_clubs_edit_img))
					.setVisibility(View.VISIBLE);
			view.setOnClickListener(null);
		}
		return view;
	}

	private class overflowClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			PopupMenu popup = new PopupMenu(context, v);
			popup.getMenuInflater().inflate(R.menu.homepage_clubs_popup,
					popup.getMenu());
			popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					Toast.makeText(context,
							"You selected the action : " + item.getTitle(),
							Toast.LENGTH_SHORT).show();
					return true;
				}
			});
			popup.show();
		}
	}

	private class firstListItemListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// Toast.makeText(context,
			// "CLICK SHOULD REDIRECT TO ADD NEW CLUB SCREEN",
			// Toast.LENGTH_SHORT).show();
			Intent i = new Intent(context, HomePage_AddNewClub.class);
			context.startActivity(i);
		}

	}
}
