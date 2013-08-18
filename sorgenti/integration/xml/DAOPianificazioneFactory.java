package integration.xml;

public class DAOPianificazioneFactory {

	private DAOPianificazioneFactory() {
		
	}

	public static DAOPianificazione buildInstance() {
		return new XMLDAOPianificazione();
	}
}
