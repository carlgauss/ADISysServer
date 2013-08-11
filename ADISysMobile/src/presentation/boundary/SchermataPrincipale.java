package presentation.boundary;

import util.FolderManager;

import com.example.adisysmobile.R;
import com.example.adisysmobile.R.layout;
import com.example.adisysmobile.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SchermataPrincipale extends Activity {
	private static final String SUBDIRECTORY = "/"; 
	private static final String PRINCIPAL_DIRECTORY = "ADISysMobile";
	private static final String ESPORTAZIONE_DIRECTORY = "Esportazione";
	private static final String IMPORTAZIONE_DIRECTORY = "Importazione";
	
	private static final String ESPORTAZIONE_PATH = PRINCIPAL_DIRECTORY + SUBDIRECTORY + ESPORTAZIONE_DIRECTORY;
	private static final String IMPORTAZIONE_PATH = PRINCIPAL_DIRECTORY + SUBDIRECTORY + IMPORTAZIONE_DIRECTORY;
	
	private static void makeDirectories() {
		FolderManager.insertFolderIfNotExists(PRINCIPAL_DIRECTORY);
		FolderManager.insertFolderIfNotExists(ESPORTAZIONE_PATH);
		FolderManager.insertFolderIfNotExists(IMPORTAZIONE_PATH);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		makeDirectories();
		setContentView(R.layout.activity_schermata_principale);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.schermata_principale, menu);
		return true;
	}*/

}
