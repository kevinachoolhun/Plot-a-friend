package plotAFriend.PlotAFriendSaver;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FriendLocation extends Activity {


	private EditText mNameText;
	private EditText mLocationText;
	private Long mRowId;
	private SQLDbAdapter mDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mDbHelper = new SQLDbAdapter(this);
        mDbHelper.open();
        
		setContentView(R.layout.friend_edit);
        setTitle(R.string.edit_friend);
        
        mNameText = (EditText) findViewById(R.id.name);
        mLocationText = (EditText) findViewById(R.id.location);

        Button confirmButton = (Button) findViewById(R.id.confirm);
        
        mRowId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(SQLDbAdapter.KEY_ROWID);
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(SQLDbAdapter.KEY_ROWID)
                                    : null;
        }
        
        populateFields();
        
        
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }

        });
        
        
	}

	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		populateFields();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		super.onSaveInstanceState(outState);
		saveState();
	    outState.putSerializable(SQLDbAdapter.KEY_ROWID, mRowId);

	}

	private void populateFields() {
		
		if (mRowId != null) {
	        Cursor note = mDbHelper.fetchFriend(mRowId);
	        startManagingCursor(note);
	        mNameText.setText(note.getString(
	                    note.getColumnIndexOrThrow(SQLDbAdapter.KEY_NAME)));
	        mLocationText.setText(note.getString(
	                note.getColumnIndexOrThrow(SQLDbAdapter.KEY_LOCATION)));
	    }

	}

	private void saveState() {
		// TODO Auto-generated method stub
		
	        String name = mNameText.getText().toString();
	        String location = mLocationText.getText().toString();

	        if (mRowId == null) {
	            long id = mDbHelper.createFriendLocation(name, location);
	            if (id > 0) {
	                mRowId = id;
	            }
	        } else {
	            mDbHelper.updateFriendLocation(mRowId, name, location);
	        }
	    }
	

	
	
		
}
