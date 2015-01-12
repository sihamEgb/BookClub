package technion.bookclub;

//import android.app.Activity;
import technion.bookclub.entities.Book;
import technion.bookclub.entities.Club;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/*
 * this is the listView fragment
 */
public class BooksResultsFragment extends ListFragment {

	ListView myListView;
	BookListAdapter adapter;
	private static String data;

	private String title, language, location;

	public BooksResultsFragment(String searchResult, String title,
			String language, String location) {
		data = searchResult;
		this.title = title;
		this.language = language;
		this.location = location;
	}

	public BookListAdapter getAdapter() {
		return adapter;
	}

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.books_results, container,
				false);

		adapter = new BookListAdapter(getActivity(), data);

		TextView v2 = (TextView) rootView.findViewById(R.id.books_results_id);
		v2.setText("Book title: " + title + " written in: " + language
				+ " offered in: " + location);

		setListAdapter(adapter);

		return rootView;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Do your stuff..

		Book myBook = (Book) adapter.getItem(position);
		Intent in = new Intent(getActivity().getApplicationContext(),
				BookPageActivity.class);
		in.putExtra("bookId", myBook.getBookId());
		in.putExtra("ownerId", myBook.getOwnerId());
		in.putExtra("title", myBook.getTitle());
		in.putExtra("location", myBook.getLocation());
		in.putExtra("author", myBook.getAuthor());
		in.putExtra("imageUrl", myBook.getImageUrl());
		in.putExtra("language", myBook.getLanguage());
		startActivity(in);

	}

	/*
	 * public static Fragment newInstance(int newColor) { BooksResultsFragment
	 * fragment = new BooksResultsFragment(); Bundle args = new Bundle(); //
	 * args.putInt("someInt", i); // args.putString("someTitle", string);
	 * fragment.setArguments(args); return fragment; }
	 */

}
