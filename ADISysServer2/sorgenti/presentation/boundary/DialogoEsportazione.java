/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.boundary;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * @author Francesco
 */
public class DialogoEsportazione extends javax.swing.JDialog {

    private static DialogoEsportazione window;

    /**
     * Creates new form DialogoEsportazione
     */
    public DialogoEsportazione(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabellaInfermieri = new javax.swing.JTable();
        pulsanteEsporta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Esportazione pianificazione infermiere");
        setModal(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated();
            }
        });

        tabellaInfermieri.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabellaInfermieri);

        pulsanteEsporta.setText("Esporta");
        pulsanteEsporta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteEsportaActionPerformed();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(pulsanteEsporta, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(1, 1, 1)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pulsanteEsporta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated() {//GEN-FIRST:event_formWindowActivated
        aggiornaTabella();
    }//GEN-LAST:event_formWindowActivated

    private void pulsanteEsportaActionPerformed() {//GEN-FIRST:event_pulsanteEsportaActionPerformed


    }//GEN-LAST:event_pulsanteEsportaActionPerformed


    public static void start() {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                window = new DialogoEsportazione(new javax.swing.JFrame(), true);
                Dimension risoluzioneSchermo = Toolkit.getDefaultToolkit().getScreenSize();
                window.setLocation(new Point((risoluzioneSchermo.width - window.getWidth()) / 2, (risoluzioneSchermo.height - window.getHeight()) / 2));
                window.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pulsanteEsporta;
    private javax.swing.JTable tabellaInfermieri;

    // End of variables declaration//GEN-END:variables
    private void aggiornaTabella() {

    }

}
