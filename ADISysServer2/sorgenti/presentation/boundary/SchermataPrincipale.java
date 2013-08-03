/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.boundary;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class SchermataPrincipale extends javax.swing.JDialog implements Boundary {
	
	private static SchermataPrincipale window;

	/**
	 * Creates new form ADISysGUI
	 */
	public SchermataPrincipale(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
    private void initComponents() {

        etichettaLogo = new javax.swing.JLabel();
        intestazione = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        labelPazienti = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabellaPazienti = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        labelInfermieri = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabellaInfermieri = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        labelInterventi = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabellaInterventi = new javax.swing.JTable();
        barraDeiMenu = new javax.swing.JMenuBar();
        menuModifica = new javax.swing.JMenu();
        menuAggiungiPaziente = new javax.swing.JMenuItem();
        menuAggiungiInfermiere = new javax.swing.JMenuItem();
        menuAggiungiIntervento = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuAggiornaTabelle = new javax.swing.JMenuItem();
        menuFile = new javax.swing.JMenu();
        menuEsportazione = new javax.swing.JMenuItem();
        menuVerifica = new javax.swing.JMenu();
        menuVerificaDati = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADISys - Pianificazione giornaliera");
        setModal(true);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(500, 500));

        jSplitPane2.setBorder(null);
        jSplitPane2.setDividerLocation(380);
        jSplitPane2.setAutoscrolls(true);

        labelPazienti.setFont(labelPazienti.getFont().deriveFont(labelPazienti.getFont().getStyle() | java.awt.Font.BOLD, 14));
        labelPazienti.setForeground(new java.awt.Color(255, 255, 255));
        labelPazienti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPazienti.setText("Pazienti");
        labelPazienti.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tabellaPazienti.setAutoCreateRowSorter(true);
        tabellaPazienti.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabellaPazienti.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabellaPazienti.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabellaPazienti);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(labelPazienti)
                .addGap(0, 85, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(labelPazienti)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(jPanel3);

        labelInfermieri.setFont(labelInfermieri.getFont().deriveFont(labelInfermieri.getFont().getStyle() | java.awt.Font.BOLD, 14));
        labelInfermieri.setForeground(new java.awt.Color(255, 255, 255));
        labelInfermieri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInfermieri.setText("Infermieri");
        labelInfermieri.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tabellaInfermieri.setAutoCreateRowSorter(true);
        tabellaInfermieri.setName("Pazienti"); // NOI18N
        tabellaInfermieri.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabellaInfermieri.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabellaInfermieri);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfermieri)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(labelInfermieri)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel2);

        jSplitPane1.setLeftComponent(jSplitPane2);

        labelInterventi.setFont(labelInterventi.getFont().deriveFont(labelInterventi.getFont().getStyle() | java.awt.Font.BOLD, 14));
        labelInterventi.setForeground(new java.awt.Color(255, 255, 255));
        labelInterventi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInterventi.setText("Interventi");
        labelInterventi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tabellaInterventi.setAutoCreateRowSorter(true);
        tabellaInterventi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabellaInterventi.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabellaInterventi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelInterventi)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(labelInterventi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel1);

        barraDeiMenu.setRequestFocusEnabled(false);

        menuModifica.setText("Dati");

        menuAggiungiPaziente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuAggiungiPaziente.setText("Apri editor pazienti...");
        menuAggiungiPaziente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAggiungiPazienteActionPerformed(evt);
            }
        });
        menuModifica.add(menuAggiungiPaziente);

        menuAggiungiInfermiere.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        menuAggiungiInfermiere.setText("Apri editor infermieri...");
        menuAggiungiInfermiere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAggiungiInfermiereActionPerformed(evt);
            }
        });
        menuModifica.add(menuAggiungiInfermiere);

        menuAggiungiIntervento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuAggiungiIntervento.setText("Apri editor Interventi...");
        menuAggiungiIntervento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAggiungiInterventoActionPerformed(evt);
            }
        });
        menuModifica.add(menuAggiungiIntervento);
        menuModifica.add(jSeparator1);

        menuAggiornaTabelle.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuAggiornaTabelle.setText("Aggiorna dati tabelle");
        menuAggiornaTabelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAggiornaTabelleActionPerformed(evt);
            }
        });
        menuModifica.add(menuAggiornaTabelle);

        barraDeiMenu.add(menuModifica);

        menuFile.setText("Esporta");

        menuEsportazione.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        menuEsportazione.setText("Esporta pianificazione...");
        menuEsportazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEsportazioneActionPerformed(evt);
            }
        });
        menuFile.add(menuEsportazione);

        barraDeiMenu.add(menuFile);

        menuVerifica.setText("Verifica");
        menuVerifica.setToolTipText("");

        menuVerificaDati.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menuVerificaDati.setText("Verifica dei dati");
        menuVerificaDati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerificaDatiActionPerformed(evt);
            }
        });
        menuVerifica.add(menuVerificaDati);

        barraDeiMenu.add(menuVerifica);

        setJMenuBar(barraDeiMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(intestazione)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etichettaLogo))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(intestazione)
                    .addComponent(etichettaLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void menuAggiungiInterventoActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void menuAggiungiPazienteActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void menuAggiungiInfermiereActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void menuVerificaDatiActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void menuAggiornaTabelleActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void formWindowActivated(java.awt.event.WindowEvent evt) {

	}

    private void menuEsportazioneActionPerformed(java.awt.event.ActionEvent evt) {

    }

	public static void start() {

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				window = new SchermataPrincipale(new javax.swing.JFrame(), true);
				Dimension risoluzioneSchermo = Toolkit.getDefaultToolkit().getScreenSize();
				window.setLocation(new Point( (risoluzioneSchermo.width - window.getWidth()) /2, (risoluzioneSchermo.height - window.getHeight()) /2)  );
				window.setVisible(true);

			}
		});

	}

    // Variables declaration - do not modify
    private javax.swing.JMenuBar barraDeiMenu;
    private javax.swing.JLabel etichettaLogo;
    private javax.swing.JLabel intestazione;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JLabel labelInfermieri;
    private javax.swing.JLabel labelInterventi;
    private javax.swing.JLabel labelPazienti;
    private javax.swing.JMenuItem menuAggiornaTabelle;
    private javax.swing.JMenuItem menuAggiungiInfermiere;
    private javax.swing.JMenuItem menuAggiungiIntervento;
    private javax.swing.JMenuItem menuAggiungiPaziente;
    private javax.swing.JMenuItem menuEsportazione;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuModifica;
    private javax.swing.JMenu menuVerifica;
    private javax.swing.JMenuItem menuVerificaDati;
    private javax.swing.JTable tabellaInfermieri;
    private javax.swing.JTable tabellaInterventi;
    private javax.swing.JTable tabellaPazienti;
    // End of variables declaration


}
