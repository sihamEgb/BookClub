package technion.bookclub;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class HomePageMeetingsListAdapter extends BaseAdapter {
	
/**********************************************************************************/	
/******************************SHOULD BE DELETED***********************************/	
/**********************************************************************************/	
	
	String[] dates = {"Sunday, Dec 21","Monday, Dec 29","Friday, Jan 10","Wednesday, Jan 23",
			          "Sunday, Feb 11","Monday, Dec 19","Friday, Feb 20","Wednesday, Feb 23",
			          "Friday, March 1","Monday, March 19","Sunday, March 20","Sunday, March 26"};
	String[] hours={"19:00","18:30","12:45","13:30",
			      "18:00","09:30","11:15","17:30",
			      "19:00","18:30","12:45","13:30"};
	String[] names = {"Science Readers Club","Fiction Readers Club","GDG Readers Club","DRedaers Book Club",
			          "HReaders Club","AReaders Club","GDG Readers Club","DRedaers Book Club",
			          "HReaders Club","AReaders Club","GDG Readers Club","DRedaers Book Club"};
	String[] desc = {"discussing MANHATTAN IN REVERSE book","discussing INHERITANCE book",
			"discussing PALE DEMON book","discussing TALES OF BAUCHELAIN & KORBAL BROACH. VOL.1 book",
			"discussing SHADOWRISE/SHADOWMARCH III book","discussing PLENTY book",
			"discussing ONE SUMMER book","discussing INHERITANCE book",
			"discussing MANHATTAN IN REVERSE book","discussing INHERITANCE book",
			"discussing MANHATTAN IN REVERSE book","discussing INHERITANCE book"};
	String[] locations={"Technion(Haifa)","  ","Technion(Haifa)"," ",
			            "Technion(Haifa)","  ","Technion(Haifa)"," ",
			            "Technion(Haifa)","  "," ","Technion(Haifa)"};
	private class Meeting{
		public String meeting_date;
		public String meeting_hour;
        public String meeting_club_name;
        public String meeting_desc;
        public String meeting_location;
		
		public Meeting(String mDate,String mHour,String cName,String mDes,String mLoc){
			meeting_date=mDate;
			meeting_hour=mHour;
	        meeting_club_name=cName;
	        meeting_desc=mDes;
	        meeting_location=mLoc;
		}
	}
	
    private ArrayList<Meeting> meetings = new ArrayList<Meeting>();
    
    private void buildMeetings(){
        for(int i=0;i<12;i++){
        		meetings.add(new Meeting(dates[i],hours[i],names[i],desc[i],locations[i]));
        }
    }
	
/**********************************************************************************/
/**********************************************************************************/	
	private Meeting getMeeting(int position){
		return meetings.get(position);
	}
	private class ViewHolder{
		public TextView meeting_date;
		public TextView meeting_hour;
        public TextView meeting_club_name;
        public TextView meeting_desc;
        public TextView meeting_location;
	}
	
	
	private Context context;

    
    public HomePageMeetingsListAdapter(Context con){
    	super();
    	context = con;
    	buildMeetings();
    }
	@Override
	public int getCount() {
		if(meetings==null){
			return 0;
		}
		return meetings.size();
	}

	@Override
	public Object getItem(int position) {
		if(meetings==null){
			return null;
		}
		View view;
		ViewHolder holder;
		Meeting meeting = getMeeting(position);
		//LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		LayoutInflater inflater =(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.meetings_list_item, null);
		holder = new ViewHolder();
		holder.meeting_date = (TextView) view.findViewById(R.id.meeting_date);
		holder.meeting_hour = (TextView) view.findViewById(R.id.meeting_hour);
		holder.meeting_club_name = (TextView) view.findViewById(R.id.meeting_club_name);
		holder.meeting_location = (TextView) view.findViewById(R.id.meeting_location);
		holder.meeting_desc = (TextView) view.findViewById(R.id.meeting_desc);
		view.setTag(holder);
		holder.meeting_date.setText(meeting.meeting_date);
		holder.meeting_hour.setText(meeting.meeting_hour);
		holder.meeting_club_name.setText(meeting.meeting_club_name);
		holder.meeting_location.setText(meeting.meeting_location);
		holder.meeting_desc.setText(meeting.meeting_desc);
		overflowClickListener l = new overflowClickListener();
		((ImageView)view.findViewById(R.id.homepage_meetings_edit_img)).setOnClickListener(l);
		return view;
	}

	@Override
	public long getItemId(int position) {
		if(meetings==null){
			return -1;
		}
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;
		
		if(convertView == null){
			//LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			LayoutInflater inflater =(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.meetings_list_item, null);
			holder = new ViewHolder();
			holder.meeting_date = (TextView) view.findViewById(R.id.meeting_date);
			holder.meeting_hour = (TextView) view.findViewById(R.id.meeting_hour);
			holder.meeting_club_name = (TextView) view.findViewById(R.id.meeting_club_name);
			holder.meeting_location = (TextView) view.findViewById(R.id.meeting_location);
			holder.meeting_desc = (TextView) view.findViewById(R.id.meeting_desc);
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) convertView.getTag();
		}
		
		Meeting meeting = getMeeting(position);
		holder.meeting_date.setText(meeting.meeting_date);
		holder.meeting_hour.setText(meeting.meeting_hour);
		holder.meeting_club_name.setText(meeting.meeting_club_name);
		holder.meeting_location.setText(meeting.meeting_location);
		holder.meeting_desc.setText(meeting.meeting_desc);
		overflowClickListener l = new overflowClickListener();
		((ImageView)view.findViewById(R.id.homepage_meetings_edit_img)).setOnClickListener(l);
		return view;
	}

	private class overflowClickListener implements  OnClickListener{
		@Override
		public void onClick(View v) {
			PopupMenu popup = new PopupMenu(context, v);
		    popup.getMenuInflater().inflate(R.menu.homepage_meetings_popup, popup.getMenu());
            popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {	 
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(context, "You selected the action : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            popup.show();
		}
	}
}
