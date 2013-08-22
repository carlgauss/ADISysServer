package integration.xml;

public class DAOPianificazioneFactory {

    private DAOPianificazioneFactory() {

    }

    public static DAOPianificazione getPianificazione() {
        return new XMLDAOPianificazione();
    }
}
