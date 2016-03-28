package ro.pub.cs.systems.eim.practicaltest01var01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PracticalTest01Var01SecondaryActivity extends Activity {

	String stringCompute = null;
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var01_secondary);

		
		Intent intent2 = getIntent();
		stringCompute = intent2.getStringExtra("all_terms");
		String[] parts = stringCompute.split("\\+");
		int result = 0;
		for (int i = 0; i < parts.length; i++) {
			result += Integer.parseInt(parts[i]);
		}
		Intent intent = new Intent();
		intent.putExtra("result", String.valueOf(result));
		setResult(RESULT_OK, intent);
		finish();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.practical_test01_var01_secondary, menu);
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
}
