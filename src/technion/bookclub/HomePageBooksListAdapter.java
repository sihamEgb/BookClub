package technion.bookclub;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
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

	private Book getBook(int position){
		//TODO: HANDLE POSITION==0 - IT IS THE ELEMENT TO REDIRECT TO ADDING NEW BOOK PAGE
		return books.get(position);
	}
	
	private String firstListItemTitle = "Add A New Book";
/*****************************************************************************************************/	
/***************************************SHOULD BE DELTED************************************************/	
/*****************************************************************************************************/	
	
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
    
    private ArrayList<Book> books = new ArrayList<Book>();

/*****************************************************************************************************/	
/*****************************************************************************************************/	
	
	private class ViewHolder{
		public TextView book_title;
		public TextView book_author;
	    public ImageView book_pic;
	}
	
	
	private Context context;
  

    
    public HomePageBooksListAdapter(Context con){
    	super();
    	context = con;
    	buildBooks();
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
		holder.book_pic = (ImageView) view.findViewById(R.id.book_img);
		overflowClickListener l = new overflowClickListener();
		((ImageView)view.findViewById(R.id.homepage_edit_img)).setOnClickListener(l);
		view.setTag(holder);
		holder.book_title.setText(book.book_title);
		holder.book_author.setText(book.book_author);
		holder.book_pic.setImageDrawable(book.book_pic);
		if(position==0){
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setVisibility(View.GONE);
		    firstListItemListener first_item_l = new firstListItemListener();
		    view.setOnClickListener(first_item_l);
		}else {
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setVisibility(View.VISIBLE);
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
			holder.book_pic = (ImageView) view.findViewById(R.id.book_img);
			overflowClickListener l = new overflowClickListener();
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setOnClickListener(l);
			view.setTag(holder);
			}
			else{
			view = convertView;
			holder = (ViewHolder) convertView.getTag();
		}
		
		Book book = getBook(position);
		holder.book_title.setText(book.book_title);
		holder.book_author.setText(book.book_author);
		holder.book_pic.setImageDrawable(book.book_pic);
		if(position==0){
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setVisibility(View.GONE);
			firstListItemListener first_item_l = new firstListItemListener();
			view.setOnClickListener(first_item_l);
		}else {
			((ImageView)view.findViewById(R.id.homepage_edit_img)).setVisibility(View.VISIBLE);
		    view.setOnClickListener(null);
		}
	    
		return view;
		
	}
	
	private class overflowClickListener implements  OnClickListener{
		@Override
		public void onClick(View v) {
			PopupMenu popup = new PopupMenu(context, v);
		    popup.getMenuInflater().inflate(R.menu.homepage_books_popup, popup.getMenu());
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
	
	private class firstListItemListener implements OnClickListener{

		@Override
		public void onClick(View v) {
            //Toast.makeText(context, "CLICK SHOULD REDIRECT TO ADD NEW BOOK SCREEN", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(context, HomePage_AddNewBook.class);
			context.startActivity(i);
		}
		
	}
	
}

