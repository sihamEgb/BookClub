package technion.bookclub;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import technion.bookclub.entities.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserListAdapter extends BaseAdapter {

	private LayoutInflater inflater = null;
	private ArrayList<User> users = new ArrayList<User>();
	Context context;

	public UserListAdapter(Context context, String data) {
		// super(context, R.layout.customlistviewitem, values);
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		getDataFromJson(data);
	}

	@Override
	public int getCount() {
		return users.size();
	}

	@Override
	public Object getItem(int position) {
		return users.get(position);
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
			view = inflater.inflate(R.layout.user_list_item, parent, false);

			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.email = (TextView) view.findViewById(R.id.email);

			view.setTag(holder);

		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		holder.name.setText(users.get(position).getName());
		holder.email.setText(users.get(position).getEmail());

		return view;
	}

	private class ViewHolder {
		public TextView name;
		public TextView email;

	}

	private void getDataFromJson(String result) {

		// ArrayList<Club> results = new ArrayList<Club>();
		try {
			JSONObject obj = new JSONObject(result);

			JSONArray jsonArr = new JSONArray(obj.getString("results"));
			int numOfItems = jsonArr.length();
			JSONObject json;
			for (int i = 0; i < numOfItems; i++) {
				User newUser = new User();
				json = jsonArr.getJSONObject(i);
				newUser = User.constructFromJson(json.toString());
				users.add(newUser);
			}
			// return results;
		} catch (Exception e) {
			e.printStackTrace();
			// return results;

		}
	}
}
