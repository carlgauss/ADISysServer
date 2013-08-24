package integration.xml;

import business.entity.Infermiere;

public class DAOPianificazioneFactory {

    public static DAOPianificazione getPianificazione(Infermiere infermiere) {
        return new XMLDAOPianificazione(infermiere);
    }
}
