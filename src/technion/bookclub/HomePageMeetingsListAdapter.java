package technion.bookclub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import technion.bookclub.entities.Meeting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class HomePageMeetingsListAdapter extends BaseAdapter {

    private LinkedHashMap<String,Meeting> meetings ;
    private LinkedHashMap<String,String> club_names;
    private ArrayList<String> club_ids_list;
    
    private String getClubName(String club_id){
    	return club_names.get(club_id);
    }
	private Meeting getMeeting(int position){
		return meetings.get(club_ids_list.get(position));
	}
	private class ViewHolder{
		public TextView meeting_date;
		public TextView meeting_hour;
        public TextView meeting_club_name;
        public TextView meeting_desc;//book
        public TextView meeting_location;
	}
	
	private Context context;

    public HomePageMeetingsListAdapter(Context con,LinkedHashMap<String,Meeting> club_meetings,LinkedHashMap<String,String> titles){
    	super();
    	context = con;
    	((HomePageInterface)context).setMeetingsAdapter(this);
    	meetings = club_meetings;
    	club_names =  titles; 
    	club_ids_list = new ArrayList<String>();
    	for(Map.Entry<String, Meeting> entry  : club_meetings.entrySet()){	
    		club_ids_list.add(entry.getKey());
    	}
    }
    
	public void removeClubMeetings(String club_id){
		if(meetings.containsKey(club_id)){
			meetings.remove(club_id);
		}
		if(club_names.containsKey(club_id)){
			club_names.remove(club_id);
		}
		for(int i=0;i<club_ids_list.size();i++){
			if(club_ids_list.get(i).equals(club_id)){
				club_ids_list.remove(i);
			}
		}
		this.notifyDataSetChanged();
	}

    private String getDateFromDateString(String date){
    	String[] s= date.split(" ");
    	return (s[0]+" "+s[1]+" "+s[2]);
    }
    
    private String getHourFromDateString(String date){
    	String[] s= date.split(" ");
    	String[] h = (s[3].split(":"));
    	return h[0]+":"+h[1];
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
		holder.meeting_date.setText(getDateFromDateString(meeting.getDate()));
		holder.meeting_hour.setText(getHourFromDateString(meeting.getDate()));
		holder.meeting_club_name.setText(getClubName(meeting.getClubId()));
		holder.meeting_location.setText(meeting.getLocation());
		holder.meeting_desc.setText(meeting.getTitle());
		overflowClickListener l = new overflowClickListener();
		ImageView iv = ((ImageView)view.findViewById(R.id.homepage_meetings_edit_img));
		iv.setOnClickListener(l);
		iv.setVisibility(View.GONE);
		//((ImageView)view.findViewById(R.id.homepage_meetings_edit_img)).setOnClickListener(l);
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
		holder.meeting_date.setText(getDateFromDateString(meeting.getDate()));
		holder.meeting_hour.setText(getHourFromDateString(meeting.getDate()));
		holder.meeting_club_name.setText(getClubName(meeting.getClubId()));
		holder.meeting_location.setText(meeting.getLocation());
		holder.meeting_desc.setText(meeting.getTitle());
		overflowClickListener l = new overflowClickListener();
		ImageView iv = ((ImageView)view.findViewById(R.id.homepage_meetings_edit_img));
		iv.setOnClickListener(l);
		iv.setVisibility(View.GONE);
		//((ImageView)view.findViewById(R.id.homepage_meetings_edit_img)).setOnClickListener(l);
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
