package technion.bookclub;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.picasso.Picasso;

import technion.bookclub.entities.Book;
import technion.bookclub.entities.Club;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookListAdapter extends BaseAdapter {

	private LayoutInflater inflater = null;
	private ArrayList<Book> books = new ArrayList<Book>();
	Context context;

	public BookListAdapter(Context context, String data) {
		// super(context, R.layout.customlistviewitem, values);
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		getDataFromJson(data);
	}

	@Override
	public int getCount() {
		return books.size();
	}

	@Override
	public Object getItem(int position) {
		return books.get(position);
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
			view = inflater.inflate(R.layout.book_list_item, parent, false);

			holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.title);
			holder.language = (TextView) view.findViewById(R.id.language);
			holder.location = (TextView) view.findViewById(R.id.location);
			holder.img = (ImageView) view.findViewById(R.id.img);

			view.setTag(holder);

		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		holder.title.setText(books.get(position).getTitle());
		holder.location.setText(books.get(position).getLocation());
		holder.language.setText(books.get(position).getLanguage());
		Picasso.with(context).load(books.get(position).getImageUrl())
		.resize(50, 50).centerCrop().into(holder.img);

		return view;
	}

	private class ViewHolder {
		public TextView title;
		public TextView location;
		public TextView language;
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
				Book newBook = new Book();
				json = jsonArr.getJSONObject(i);
				newBook = Book.constructFromJson(json.toString());
				books.add(newBook);

			}
			// return results;
		} catch (Exception e) {
			e.printStackTrace();
			// return results;

		}
	}
}
