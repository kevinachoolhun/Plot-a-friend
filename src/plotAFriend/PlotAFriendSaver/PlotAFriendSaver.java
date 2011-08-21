package plotAFriend.PlotAFriendSaver;


import plotAFriend.PlotAFriendSaver.R.string;
import plotAFriend.PlotAFriendSaver.Business.MapLocation;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class PlotAFriendSaver extends ListActivity {
	

	private SQLDbAdapter mDbHelper;
  
 
	private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
	public static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;
    
    
    Button btnWebService ;
    Button btnBatteryLevel;
    Button btnConnectivity;
    Button btnMaps;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list);
        mDbHelper = new SQLDbAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());
        
        btnWebService = (Button)findViewById(R.id.btnWS);
        btnWebService.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startWebServiceActivity();
			}
		});
        
        btnBatteryLevel = (Button)findViewById(R.id.btnBattery);
        btnBatteryLevel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checkBatteryLevel();
				
			}
		});
        
        btnConnectivity = (Button)findViewById(R.id.btnConnectivity);
        btnConnectivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				displayConnectivity();
			}

			
		});
        
        btnMaps = (Button)findViewById(R.id.btnGoogleMap);
        btnMaps.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				displayGoogleMaps();
			}

			
		});
        
    }
    
    private void displayGoogleMaps() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, MapLocation.class);
		//startActivityForResult(i, 0);
		startActivity(i);
	}
    
    
    private void displayConnectivity() {
		// TODO Auto-generated method stub
		//Intent i = new Intent(this, ConnectivityMonitor.class);
		startActivity(i);
	}
    
    
    private void checkBatteryLevel(){
    	
    	Intent i = new Intent(this, PAFBatteryManager.class);
		startActivity(i);
	
    }
    
    
    private void startWebServiceActivity() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, WebServiceCall.class);
		startActivity(i);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, string.menu_insert);
        return result;
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
        case INSERT_ID:
        	createFriend();
        
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
		
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
			mDbHelper.deleteFriendLocation(info.id);
			fillData();
			return true;
		
		}
		return super.onContextItemSelected(item);
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent i = new Intent(this, FriendLocation.class);
		i.putExtra(SQLDbAdapter.KEY_ROWID, id);

		startActivityForResult(i, ACTIVITY_EDIT);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
		super.onActivityResult(requestCode, resultCode, data);
		fillData();
			
		
	}


	private void fillData() {
			Cursor notesCursor;
			notesCursor = mDbHelper.fetchAllFriendLocations();
			startManagingCursor(notesCursor);
			
			String[] from = new String[] {SQLDbAdapter.KEY_NAME};
			int[] to = new int[] {R.id.text1};
			
			SimpleCursorAdapter friends = new SimpleCursorAdapter(this, R.layout.friends_row, notesCursor, from, to);
			setListAdapter(friends);
			
		}
	
	
	private void createFriend() {
		Intent i = new Intent(this, FriendLocation.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	
	}

}