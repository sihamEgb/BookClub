package technion.bookclub;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class BookPageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.club_activity);
		Bundle b = getIntent().getExtras();
		String bookId = b.getString("bookId");
		String title = b.getString("title");
		/*
		in.putExtra("ownerId", myBook.getOwnerId());
		in.putExtra("title", myBook.getTitle());
		in.putExtra("location", myBook.getLocation());
		in.putExtra("author", myBook.getAuthor());
		in.putExtra("imageUrl", myBook.getImageUrl());
		in.putExtra("language", myBook.getLanguage());
		*/
		setTitle(title);
		Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// menu.findItem(R.id.action_websearch).setVisible(true);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
		/*
		 * switch (item.getItemId()) { case R.id.action_websearch: // create
		 * intent to perform web search for this planet Intent intent = new
		 * Intent(Intent.ACTION_WEB_SEARCH);
		 * intent.putExtra(SearchManager.QUERY, getActionBar().getTitle()); //
		 * catch event that there's no activity to handle intent if
		 * (intent.resolveActivity(getPackageManager()) != null) {
		 * startActivity(intent); } else { Toast.makeText(this,
		 * R.string.app_not_available, Toast.LENGTH_LONG).show(); } return true;
		 * default: return super.onOptionsItemSelected(item);
		 */
	}
}
