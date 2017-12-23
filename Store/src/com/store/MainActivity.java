package com.store;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener {

	private EditText etValue;
	private Spinner spType;
	private Button btSetvalue;
	private EditText etKey;
	private Button btGetvalue;
	private Store store;

	/**
	 * Find the Views in the layout
	 */
	private void findViews() {
		etValue = (EditText)findViewById( R.id.et_value );
		spType = (Spinner)findViewById( R.id.sp_type );
		btSetvalue = (Button)findViewById( R.id.bt_setvalue );
		etKey = (EditText)findViewById( R.id.et_key );
		btGetvalue = (Button)findViewById( R.id.bt_getvalue );

		btSetvalue.setOnClickListener( this );
		btGetvalue.setOnClickListener( this );
	}

	/**
	 * Handle button click events
	 */
	@Override
	public void onClick(View v) {
		if ( v == btSetvalue ) {
			// Handle clicks for btSetvalue
			onSetValue();
		} else if ( v == btGetvalue ) {
			// Handle clicks for btGetvalue
			onGetValue();
		}
	}

    private void onGetValue() {
		// TODO Auto-generated method stub
		String strKey = etKey.getText().toString();
		StoreType storeType = (StoreType) spType.getSelectedItem();
		switch (storeType) {
		case Integer:
			etValue.setText(store.getInteger(strKey).toString());
			break;

		case String:
			etValue.setText(store.getString(strKey));
			break;
		}
	}

	private void onSetValue() {
		// TODO Auto-generated method stub
		String strKey = etKey.getText().toString();
		String strValue = etValue.getText().toString();
		StoreType storeType = (StoreType) spType.getSelectedItem();
		
		try {
			switch (storeType) {
			case Integer:
				store.setInteger(strKey, Integer.parseInt(strValue));
				break;
					
			case String:
				store.setString(strKey, strValue);
				break;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "wrong value", Toast.LENGTH_SHORT);
		}
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		store = new Store();
        findViews();
        SpinnerAdapter adapter = new ArrayAdapter<StoreType>(this, android.R.layout.simple_list_item_1, StoreType.values());
		spType.setAdapter(adapter);
        
		
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
