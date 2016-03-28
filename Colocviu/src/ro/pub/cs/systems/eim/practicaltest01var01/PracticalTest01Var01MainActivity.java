package ro.pub.cs.systems.eim.practicaltest01var01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends Activity {

	MyListener buttonListener = new MyListener();
	Button add_button;
	Button compute_button;
	EditText next_term_editText;
	EditText all_terms_editText;
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	int oldIntResult = 0;
	String oldAllTerms = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var01_main);

		add_button = (Button) findViewById(R.id.add);
		compute_button = (Button) findViewById(R.id.compute);
		next_term_editText = (EditText) findViewById(R.id.next_term);
		all_terms_editText = (EditText) findViewById(R.id.all_terms);

		add_button.setOnClickListener(buttonListener);
		compute_button.setOnClickListener(buttonListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var01_main, menu);
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

	private class MyListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.add:
				String nextTermString = next_term_editText.getText().toString();
				if (nextTermString.equals(""))
					break;
				String oldAllTermsString;
				if (!all_terms_editText.getText().toString().equals("")) {
					oldAllTermsString = all_terms_editText.getText().toString();
					all_terms_editText.setText(oldAllTermsString + "+"
							+ nextTermString);
				} else {
					all_terms_editText.setText(nextTermString);
				}

				break;
			case R.id.compute:
				/*
				if (oldAllTerms.equals(all_terms_editText.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"The result was allready calculated " + String.valueOf(oldIntResult), Toast.LENGTH_LONG).show();
					break;
				}*/
				Intent intent = new Intent(getApplicationContext(),
						PracticalTest01Var01SecondaryActivity.class);
				String AllTerms = all_terms_editText.getText().toString();
				intent.putExtra("all_terms", AllTerms);
				startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
				break;
			}

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
			String result = intent.getStringExtra("result");
			oldIntResult = Integer.parseInt(result);
			Toast.makeText(this,
					"The activity returned the result " + result, Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	  protected void onSaveInstanceState(Bundle savedInstanceState) {
	    savedInstanceState.putString("cache", String.valueOf(oldIntResult));
	  }
	
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		oldIntResult = Integer.parseInt(savedInstanceState.getString("cache"));
	}
	
}
