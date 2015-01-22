package technion.bookclub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import technion.bookclub.entities.Club;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

	private void refreshClubsList(){
		this.notifyDataSetChanged();
	}
	private void BuildClubsListFromJson(String result) {
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
		} catch (Exception e) {
			System.out
					.println("homepageclubs adapter error :getting Clubs from string FAILED");
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
		public String club_id;
		public TextView name;
		public TextView members_num;
		// public TextView meeting_date;
		public ImageView pic;
	}

	private Context context;

	public HomePageClubsListAdapter(Context con, String clubsDataString) {
		super();
		context = con;
		((HomePageInterface)context).setClubsAdapter(this);
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
		ImageView edit_img = ((ImageView) view.findViewById(R.id.homepage_clubs_edit_img));
		overflowClickListener l = new overflowClickListener(holder,position);
		edit_img.setOnClickListener(l);
		view.setTag(holder);
		Club club = getClub(position);
		holder.name.setText(club.getName());
		holder.members_num.setText(club.getMemeberNum()+" members");
		holder.club_id=club.getClubId();
		Picasso.with(view.getContext()).load(club.getImageUrl()).fit().into(holder.pic);
	    edit_img.setVisibility(View.VISIBLE);
	    view.setOnClickListener(new ClubItemListener(club));
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
		ImageView edit_img = null;
		if (convertView == null) {
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
			edit_img = ((ImageView) view.findViewById(R.id.homepage_clubs_edit_img));
			overflowClickListener l = new overflowClickListener(holder,position);
			edit_img.setOnClickListener(l);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) convertView.getTag();
			edit_img = ((ImageView) view.findViewById(R.id.homepage_clubs_edit_img));
		}

		Club club = getClub(position);
		holder.name.setText(club.getName());
		holder.members_num.setText(club.getMemeberNum()+" members");
		holder.club_id=club.getClubId();
		// holder.meeting_date.setText(club.next_meeting_date);
		//holder.pic.setImageDrawable(context.getResources().getDrawable(R.drawable.club_3));
		Picasso.with(view.getContext()).load(club.getImageUrl()).fit().into(holder.pic);
		edit_img.setVisibility(View.VISIBLE);
		view.setOnClickListener(new ClubItemListener(club));
		
		return view;
	}

	private class overflowClickListener implements OnClickListener {
		private ViewHolder holder;
		private int position;
		public overflowClickListener(ViewHolder h,int p){
			holder=h;
			position = p;
		}
		@Override
		public void onClick(View v) {
			PopupMenu popup = new PopupMenu(context, v);
			popup.getMenuInflater().inflate(R.menu.homepage_clubs_popup,
					popup.getMenu());
			popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					if(item.getTitle().equals("Leave")){
						String club_id = holder.club_id;
						clubs.remove(position);
						leaveClub(club_id);
						((HomePageInterface)context).getMeetingsAdapter().removeClubMeetings(club_id);
						((HomePageInterface)context).removeClubFromLists(club_id);
					}
					//Toast.makeText(context,"You selected the action : " + item.getTitle(),Toast.LENGTH_SHORT).show();
					return true;
				}
			});
			popup.show();
		}
	}
	
	private class ClubItemListener implements OnClickListener {
		Club my_club ;
		public ClubItemListener(Club c){
			my_club=c;
		}
		@Override
		public void onClick(View v) {
			Intent in = new Intent(context.getApplicationContext(),ClubPageActivity.class);
			in.putExtra("clubId", my_club.getClubId());
			in.putExtra("adminId", my_club.getAdminId());
			in.putExtra("name", my_club.getName());
			in.putExtra("location", my_club.getLocation());
			in.putExtra("description", my_club.getDescription());
			in.putExtra("imageUrl", my_club.getImageUrl());
			in.putExtra("memeberNum", my_club.getMemeberNum());
			//TODO: PUT EXTRA DATA - USER ID
			context.startActivity(in);

		}

	}
	
	 private void leaveClub(String club_id){
			AsyncHttpClient client = new AsyncHttpClient();
		     RequestParams params = new RequestParams();
             
		     String user_id = ((HomePageInterface)context).getUserId();
		     params.put("clubId", club_id);
		     params.put("userId", user_id);
		     params.put("op", "leave");
		     client.get("http://jalees-bookclub.appspot.com/joinclub",params, new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode,
							Header[] headers, byte[] response) {
						refreshClubsList();
						String s=new String(response);
						System.out.println(s);			
					}

					@Override
					public void onFailure(int arg0, Header[] arg1,
							byte[] arg2, Throwable arg3) {
						// TODO Auto-generated method stub
					}

				});
	 }
}
