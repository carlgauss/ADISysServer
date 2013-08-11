package presentation.boundary;

import com.example.adisysmobile.R;
import com.example.adisysmobile.R.layout;
import com.example.adisysmobile.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SchermataPrincipale extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schermata_principale);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schermata_principale, menu);
		return true;
	}

}
