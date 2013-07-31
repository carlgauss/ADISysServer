/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.boundary;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.sound.midi.ControllerEventListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Francesco
 */
public class EditorPazienti extends javax.swing.JDialog {
	/**
	 * Creates new form dialogoPazienti
	 */
	public EditorPazienti(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		modelloListaCellulari = new DefaultListModel<>();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
    private void initComponents() {

        immagineTestataPazienti = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabellaPazienti = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        txErrore = new javax.swing.JLabel();
        labelCellulare = new javax.swing.JLabel();
        txCellularePaziente = new javax.swing.JFormattedTextField();
        labelCognome = new javax.swing.JLabel();
        txCognomePaziente = new javax.swing.JTextField();
        labelNome = new javax.swing.JLabel();
        txNomePaziente = new javax.swing.JTextField();
        pulsanteOk = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        labelID = new javax.swing.JLabel();
        labelDataNascita = new javax.swing.JLabel();
        txDataNascita = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCellulari = new javax.swing.JList();
        pRemCellulare = new javax.swing.JButton();
        pAddCellulare = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        pulsanteAggiungi = new javax.swing.JButton();
        pulsanteModifica = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 480));
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tabellaPazienti.setToolTipText("Premere \"p\" per info sul paziente");
        tabellaPazienti.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabellaPazienti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabellaPazientiKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tabellaPazienti);

        txErrore.setForeground(new java.awt.Color(255, 0, 0));
        txErrore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txErrore.setText("Messaggio");

        labelCellulare.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCellulare.setText("Cellulare");
        labelCellulare.setToolTipText("");
        labelCellulare.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txCellularePaziente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        labelCognome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCognome.setText("Cognome Paziente");
        labelCognome.setToolTipText("");
        labelCognome.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNome.setText("Nome Paziente");
        labelNome.setToolTipText("");
        labelNome.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        pulsanteOk.setText("Ok");
        pulsanteOk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pulsanteOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteOkActionPerformed(evt);
            }
        });

        jLabel3.setText("ID:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelID.setText("-1");
        labelID.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelDataNascita.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDataNascita.setText("Data di nascita (GG/MM/AAAA)");
        labelDataNascita.setToolTipText("");
        labelDataNascita.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txDataNascita.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        listaCellulari.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listaCellulari);

        pRemCellulare.setBorder(null);
        pRemCellulare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pRemCellulareActionPerformed(evt);
            }
        });

        pAddCellulare.setBorder(null);
        pAddCellulare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pAddCellulareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(labelCognome, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(labelCellulare, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(txNomePaziente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txCognomePaziente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(txCellularePaziente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pAddCellulare))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataNascita)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(txDataNascita, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel3)
                                .addGap(15, 15, 15)
                                .addComponent(labelID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txErrore, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pRemCellulare)
                        .addGap(35, 35, 35)
                        .addComponent(pulsanteOk, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCognome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCellulare, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txNomePaziente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txCognomePaziente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txCellularePaziente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pAddCellulare))
                .addGap(5, 5, 5)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(labelDataNascita, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txDataNascita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(labelID))
                        .addGap(10, 10, 10)
                        .addComponent(txErrore))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pRemCellulare)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(pulsanteOk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jToolBar1.setFloatable(false);

        pulsanteAggiungi.setText("Aggiungi Paziente");
        pulsanteAggiungi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteAggiungiActionPerformed(evt);
            }
        });
        jToolBar1.add(pulsanteAggiungi);

        pulsanteModifica.setText("Modifica Paziente");
        pulsanteModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteModificaActionPerformed(evt);
            }
        });
        jToolBar1.add(pulsanteModifica);

        jLabel1.setText("Una volta selezionato un paziente premere 'P' per visualizzarne le informazioni.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(immagineTestataPazienti)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(immagineTestataPazienti)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
	private static EditorPazienti window;

	private void pulsanteOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteOkActionPerformed

	}//GEN-LAST:event_pulsanteOkActionPerformed

	private void pulsanteModificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteModificaActionPerformed

	}//GEN-LAST:event_pulsanteModificaActionPerformed

	private void pulsanteAggiungiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteAggiungiActionPerformed

	}//GEN-LAST:event_pulsanteAggiungiActionPerformed

	private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

	}//GEN-LAST:event_formWindowActivated

	private void pAddCellulareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pAddCellulareActionPerformed

                
	}//GEN-LAST:event_pAddCellulareActionPerformed

	private void pRemCellulareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pRemCellulareActionPerformed

	}//GEN-LAST:event_pRemCellulareActionPerformed

    private void tabellaPazientiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabellaPazientiKeyPressed

    }//GEN-LAST:event_tabellaPazientiKeyPressed


	/**
	 * @param args the command line arguments
	 */
	public static void start() {

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				window = new EditorPazienti(new javax.swing.JFrame(), true);
				Dimension risoluzioneSchermo = Toolkit.getDefaultToolkit().getScreenSize();
				window.setLocation(new Point( (risoluzioneSchermo.width - window.getWidth()) /2, (risoluzioneSchermo.height - window.getHeight()) /2)  );
				window.setVisible(true);
			}
		});
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel immagineTestataPazienti;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelCellulare;
    private javax.swing.JLabel labelCognome;
    private javax.swing.JLabel labelDataNascita;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelNome;
    private javax.swing.JList listaCellulari;
    private javax.swing.JButton pAddCellulare;
    private javax.swing.JButton pRemCellulare;
    private javax.swing.JButton pulsanteAggiungi;
    private javax.swing.JButton pulsanteModifica;
    private javax.swing.JButton pulsanteOk;
    private javax.swing.JTable tabellaPazienti;
    private javax.swing.JFormattedTextField txCellularePaziente;
    private javax.swing.JTextField txCognomePaziente;
    private javax.swing.JFormattedTextField txDataNascita;
    private javax.swing.JLabel txErrore;
    private javax.swing.JTextField txNomePaziente;
    // End of variables declaration//GEN-END:variables
	
    //Dichiarazione modello lista cellulari pazienti
    private DefaultListModel<String> modelloListaCellulari;
}
