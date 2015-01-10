package technion.bookclub;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.picasso.Picasso;

import technion.bookclub.entities.Club;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
//import android.widget.ImageView;
import android.widget.TextView;

public class ClubListAdapter extends BaseAdapter {

	private LayoutInflater inflater = null;
	private ArrayList<Club> clubs = new ArrayList<Club>();
	Context context;

	public ClubListAdapter(Context context, String data) {
		// super(context, R.layout.customlistviewitem, values);
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		getDataFromJson(data);
		// initData(data);
	}

	@Override
	public int getCount() {
		return clubs.size();
	}

	@Override
	public Object getItem(int position) {
		return clubs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;
		ViewHolder holder = null;
		if (null == convertView) {
			view = inflater.inflate(R.layout.club_list_item, parent, false);
			// view.setOnClickListener(new MyListener());
			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.name);

			holder.description = (TextView) view.findViewById(R.id.description);
			holder.number = (TextView) view.findViewById(R.id.membersNum);
			holder.img = (ImageView) view.findViewById(R.id.img);

			view.setTag(holder);

		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		holder.name.setText(clubs.get(position).getName());
		holder.description.setText(clubs.get(position).getDescription());
		holder.number.setText("Participants:"
				+ clubs.get(position).getMemeberNum());
		Picasso.with(context).load(clubs.get(position).getImageUrl())
				.resize(50, 50).centerCrop().into(holder.img);

		return view;
	}

	private class ViewHolder {
		public TextView name;
		public TextView description;
		public TextView number;
		public ImageView img;

	}

	private void getDataFromJson(String result) {

		// ArrayList<Club> results = new ArrayList<Club>();
		try {
			JSONObject obj = new JSONObject(result);

			JSONArray jsonArr = new JSONArray(obj.getString("results"));
			int numOfItems = jsonArr.length();
			JSONObject json;
			for (int i = 0; i < numOfItems; i++) {
				Club newClub = new Club();
				json = jsonArr.getJSONObject(i);
				newClub = Club.constructFromJson(json.toString());
				clubs.add(newClub);
			}
			// return results;
		} catch (Exception e) {
			e.printStackTrace();
			// return results;

		}
	}

}