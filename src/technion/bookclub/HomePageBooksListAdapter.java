package technion.bookclub;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import technion.bookclub.entities.Book;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageBooksListAdapter extends BaseAdapter{
	private Context context;
    private ArrayList<Book> books ;//= new ArrayList<Book>();
    
    private void refreshBooksList(){
    	//adapter.notifyDataSetChanged();
    	this.notifyDataSetChanged();
    	//TODO: ADD IMPLEMENTATION
    	//Toast.makeText(context, "books list should be updated/ a book was deleted" , Toast.LENGTH_SHORT).show();
    }
	private void BuildBooksListFromJson(String result) {
		Book first_item = new Book();
		first_item.setAuthor("");
		first_item.setTitle("Add Book");
		first_item.setBookId("-100");
		books.add(first_item);
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
			System.out.println("homepage books adapter error : getting books from string FAILED");
			//e.printStackTrace();
		}
	}
	
	private Book getBook(int position){
		//TODO: HANDLE POSITION==0 - IT IS THE ELEMENT TO REDIRECT TO ADDING NEW BOOK PAGE
		return books.get(position);
	}
	
	

/*****************************************************************************************************/	
/***************************************SHOULD BE DELTED************************************************/	
/*****************************************************************************************************/	
	
	//private String firstListItemTitle = "Add A New Book";
/*	
	String[] titles={"GOLDFINCH","POLICE","ONE SUMMER",
			          "WELL TEMPERED HEART","CUCKOO'S CALLING","TWELVE YEARS A SLAVE (TIE-IN)","HEIST",
			          "GOLDFINCH","POLICE","ONE SUMMER",
			          "GOLDFINCH","POLICE","ONE SUMMER",};
	String[] authors={"TARTT, DONNA","NESBO, JO","BRYSON, BILL",
			          "SENDKER, JAN PHILIPP","GALBRAITH, ROBERT","NORTHUP, SOLOMON",
			          "SILVA, DANIEL","NESBO, JO","BRYSON, BILL",
			          "TARTT, DONNA","NESBO, JO","BRYSON, BILL",};
	
	String[] pics={"b1","b2","b3","b4",
			       "b5","b6","b7","b1",
			       "b1","b2","b3","b4"};	
	private class Book{
		public String book_title;
		public String book_author;
	    public final Drawable book_pic;
		
		public Book(Drawable p,String name,String auth){
			book_pic=p;
			book_title=name;
			book_author=auth;
		}
	}
	
	int maxClubsNumber = 12;

    private void buildBooks(){
        Resources resources = context.getResources();
        for(int i=0;i<maxClubsNumber;i++){
        	if(i==1)
        		books.add(new Book(resources.getDrawable(R.drawable.plus),"Add A New Book"," "));
        	else switch(i){
        	case 2:
        		books.add(new Book(resources.getDrawable(R.drawable.b2),titles[i],authors[i]));
        		break;
        	case 3: 
        		books.add(new Book(resources.getDrawable(R.drawable.b3),titles[i],authors[i]));
        		break;
        	case 4:
        		books.add(new Book(resources.getDrawable(R.drawable.b4),titles[i],authors[i]));
        		break;
        	case 5: 
        		books.add(new Book(resources.getDrawable(R.drawable.b5),titles[i],authors[i]));
        		break;
        	case 6:
        		books.add(new Book(resources.getDrawable(R.drawable.b6),titles[i],authors[i]));
        		break;
        	case 7: 
        		books.add(new Book(resources.getDrawable(R.drawable.b7),titles[i],authors[i]));
        		break;
        	case 8:
        		books.add(new Book(resources.getDrawable(R.drawable.b1),titles[1],authors[1]));
        		break;
        	case 9:
        		books.add(new Book(resources.getDrawable(R.drawable.b2),titles[2],authors[2]));
        		break;
        	case 10:
        		books.add(new Book(resources.getDrawable(R.drawable.b3),titles[3],authors[3]));
        		break;
        	case 11:
        		books.add(new Book(resources.getDrawable(R.drawable.b4),titles[4],authors[4]));
        		break;
        	case 12:
        		books.add(new Book(resources.getDrawable(R.drawable.b5),titles[5],authors[5]));
        		break;
        	}
	        
        }
    }
 */   

/*****************************************************************************************************/	
/*****************************************************************************************************/	
	
	private class ViewHolder{
		public String book_id;
		public TextView book_title;
		public TextView book_author;
	    public ImageView book_pic;
	    public TextView book_availability;
	}
	  
    public HomePageBooksListAdapter(Context con,String booksDataString){
    	super();
    	context = con;
        books = new ArrayList<Book>();
    	BuildBooksListFromJson(booksDataString);
    	//buildBooks();
    }
	@Override
	public int getCount() {
		if(books==null){
			return 0;
		}
		return books.size();
	}

	@Override
	public Object getItem(int position) {
		if(books==null){
			return null;
		}
		View view;
		ViewHolder holder;
		Book book = getBook(position);
		//LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		LayoutInflater inflater =(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.homepage_books_list_item, null);
		holder = new ViewHolder();
		holder.book_title = (TextView) view.findViewById(R.id.book_title);
		holder.book_author = (TextView) view.findViewById(R.id.book_author);
		holder.book_availability = (TextView) view.findViewById(R.id.book_avail);
		holder.book_pic = (ImageView) view.findViewById(R.id.book_img);
		overflowClickListener l = new overflowClickListener(holder,position);
		((ImageView)view.findViewById(R.id.homepage_edit_img)).setOnClickListener(l);
		view.setTag(holder);
		holder.book_title.setText(book.getTitle());
		holder.book_author.setText(book.getAuthor());
		holder.book_id=book.getBookId();
		String book_av = "Available";
		if(book.isAvailable()==false){
			book_av = "Not Available";
		}
		holder.book_availability.setText(book_av); 

		//TODO: CHANGE THIS LINE AND TAKE IMAGE FROM BOOK.URL
		holder.book_pic.setImageDrawable(context.getResources().getDrawable(R.drawable.gray_book_group));
		if(position==0){
			
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setVisibility(View.GONE);
			holder.book_availability.setVisibility(View.GONE);
		    firstListItemListener first_item_l = new firstListItemListener();
		    view.setOnClickListener(first_item_l);
		}else {
			Picasso.with(view.getContext()).load(book.getImageUrl()).into(holder.book_pic);
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setVisibility(View.VISIBLE);
			holder.book_availability.setVisibility(View.VISIBLE);
			view.setOnClickListener(null);
		}
		return view;
	}

	@Override
	public long getItemId(int position) {
		if(books==null){
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
			view = inflater.inflate(R.layout.homepage_books_list_item, null);
			holder = new ViewHolder();
			holder.book_title = (TextView) view.findViewById(R.id.book_title);
			holder.book_author = (TextView) view.findViewById(R.id.book_author);
			holder.book_availability = (TextView) view.findViewById(R.id.book_avail);
			holder.book_pic = (ImageView) view.findViewById(R.id.book_img);
			overflowClickListener l = new overflowClickListener(holder,position);
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setOnClickListener(l);
			view.setTag(holder);
			}
			else{
			view = convertView;
			holder = (ViewHolder) convertView.getTag();
		}
		
		Book book = getBook(position);
		holder.book_title.setText(book.getTitle());
		holder.book_author.setText(book.getAuthor());
		holder.book_id=book.getBookId();
		String book_av = "Available";
		holder.book_availability.setTextColor(context.getResources().getColorStateList(R.color.green_900));
		if(book.isAvailable()==false){
			book_av = "Not Available";
			holder.book_availability.setTextColor(context.getResources().getColorStateList(R.color.red_900));
		}
		holder.book_availability.setText(book_av);
		//TODO: CHANGE THIS LINE AND TAKE IMAGE FROM BOOK.URL
		holder.book_pic.setImageDrawable(context.getResources().getDrawable(R.drawable.gray_book_group));
		if(position==0){
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setVisibility(View.GONE);
			holder.book_availability.setVisibility(View.GONE);
			firstListItemListener first_item_l = new firstListItemListener();
			view.setOnClickListener(first_item_l);
		}else {
			Picasso.with(view.getContext()).load(book.getImageUrl()).into(holder.book_pic);
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setVisibility(View.VISIBLE);
			holder.book_availability.setVisibility(View.VISIBLE);
		    view.setOnClickListener(null);
		}
	    
		return view;
		
	}
	
	private class overflowClickListener implements  OnClickListener{
		private ViewHolder holder;
		private int position;
		public overflowClickListener(ViewHolder h,int p){
			holder=h;
			position=p;
		}
		@Override
		public void onClick(View v) {
			PopupMenu popup = new PopupMenu(context, v);
		    popup.getMenuInflater().inflate(R.menu.homepage_books_popup, popup.getMenu());
            popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {	 
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    //Toast.makeText(context, "You selected the action : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    
                    if(item.getTitle().equals("Available")){
                    	if(!holder.book_availability.getText().equals("Available")){
                    		changeBookStateInServer(holder.book_id,holder);
                    	}
                    	//Toast.makeText(context, "You selected the action : available" , Toast.LENGTH_SHORT).show();
                    }else if(item.getTitle().equals("Not Available")){
                    	if(!holder.book_availability.getText().equals("Not Available")){
                    		changeBookStateInServer(holder.book_id,holder);
                    	}
                    	//Toast.makeText(context, "You selected the action : not available" , Toast.LENGTH_SHORT).show();
                    }else if(item.getTitle().equals("Remove")){
                    	books.remove(position);
                    	removeBookFromServer(holder.book_id);
                    	//Toast.makeText(context, "You selected the action : remove" , Toast.LENGTH_SHORT).show();
                    }
                    
                    return true;
                }
            });
            popup.show();
		}
	}
	
	private class firstListItemListener implements OnClickListener{

		@Override
		public void onClick(View v) {
            //Toast.makeText(context, "CLICK SHOULD REDIRECT TO ADD NEW BOOK SCREEN", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(context, HomePage_AddNewBook.class);
			i.putExtra("userId", ((HomePageInterface)context).getUserId());
			context.startActivity(i);
		}
		
	}
	
	 private void changeBookStateInServer(String book_id,final ViewHolder holder){
		  AsyncHttpClient client = new AsyncHttpClient();
         RequestParams params = new RequestParams();
         params.put("bookId", book_id);
         //params.put("state", book_new_state);
         client.get("http://jalees-bookclub.appspot.com/changebookstatus",params, new AsyncHttpResponseHandler() {
                     @Override
                     public void onSuccess(int statusCode,Header[] headers, byte[] response) {
                         String s = new String(response);
                         if(s.equals("true")){
                        	 holder.book_availability.setTextColor(context.getResources().getColorStateList(R.color.green_900));
                        	 holder.book_availability.setText("Available");
                         }
                         else if(s.equals("false")){
                        	 holder.book_availability.setTextColor(context.getResources().getColorStateList(R.color.red_900));
                        	 holder.book_availability.setText("Not Available");
                         }
                     }
                     
                     @Override
                     public void onFailure(int arg0, Header[] arg1,
                             byte[] arg2, Throwable arg3) {
                     }
                 });
	 }
	 
	 private void removeBookFromServer(String book_id){
		  AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("bookId", book_id);
        //params.put("state", book_new_state);
        client.get("http://jalees-bookclub.appspot.com/deletebook",params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode,Header[] headers, byte[] response) {
                        String s = new String(response);
                        if(s.equals("success")){
                        	refreshBooksList();
                        }else if(s.equals("fail")){
                        	//Toast.makeText(context, "try again later" , Toast.LENGTH_SHORT).show();
                        }
                    }
                    
                    @Override
                    public void onFailure(int arg0, Header[] arg1,
                            byte[] arg2, Throwable arg3) {
                    	Toast.makeText(context, "try again later" , Toast.LENGTH_SHORT).show();
                    }
                });
	 }
}

