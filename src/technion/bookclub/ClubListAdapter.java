package technion.bookclub;

import java.util.ArrayList;

import technion.bookclub.entities.Club;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClubListAdapter extends BaseAdapter {

	private LayoutInflater inflater = null;
	private ArrayList<Club> clubs = new ArrayList<Club>();
	Context context;

	public ClubListAdapter(Context context) {
		// super(context, R.layout.customlistviewitem, values);
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		initData();
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

			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.location = (TextView) view.findViewById(R.id.location);
			holder.number = (TextView) view.findViewById(R.id.number);
			holder.img = (ImageView) view.findViewById(R.id.img);

			view.setTag(holder);

		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		holder.name.setText(clubs.get(position).getName());
		holder.location.setText(clubs.get(position).getLocation());
		holder.number.setText(clubs.get(position).getNumber());

		// holder.img.setText(clubs.get(position).getName());

		return view;
	}

	private void initData() {
		

	}

	private class ViewHolder {
		public TextView name;
		public TextView location;
		public TextView number;
		public ImageView img;

	}
}