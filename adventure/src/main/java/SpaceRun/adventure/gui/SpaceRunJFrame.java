/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SpaceRun.adventure.gui;

import SpaceRun.adventure.games.SpaceRun;
import SpaceRun.adventure.parser.Parser;
import SpaceRun.adventure.parser.ParserOutput;
import SpaceRun.adventure.type.CommandType;
import SpaceRun.adventure.type.Command;
import SpaceRun.adventure.type.Room;
import SpaceRun.adventure.type.AdvObject;
import SpaceRun.adventure.type.AdvObjectContainer;
import SpaceRun.adventure.type.Inventory;
import SpaceRun.adventure.GameDescription;
import SpaceRun.adventure.Utils;
import static com.sun.tools.javac.util.StringUtils.toUpperCase;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 *
 * @author Gaetano
 */
public class SpaceRunJFrame extends javax.swing.JFrame {
    
    private final GameDescription game;

    private final Parser parser;
    
    /**
     * Creates new form SpaceRunJFrame
     */
    public SpaceRunJFrame() {
        initComponents();
        
        this.game = new SpaceRun();
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
        parser = new Parser(stopwords);
        
        // Visualizzazione messaggi iniziali
        GameTextArea.append("Ti trovi su un’astronave viscida e cupa, si riescono a distinguere solo le luci di pulsanti e sirene costantemente in funzione. "
                + "\nChi ti ha catturato? Come sei entrato lì? Perché sei mezzo morto? Non sai niente di niente. "
                + "\nHai già osservato qualcosa in pochi momenti di vita e ti sembra di aver visto Alieni alti due metri che sembrano aver la funzione di dottori studianti specie viventi di tutto l’universo."
                + "\nNoti che a causa di un malfunzionamento dovuto a delle tubature scollegate, si, proprio quelle che ti consentivano di sopravvivere, che sei l’unico essere vivente in grado di cercare una via di fuga e forse una speranza sull’astronave."
                + "\nCapisci che ti hanno rinchiuso perché sarai il prossimo ad essere esaminato per studiare le tue capacità cognitive. Non sai se ne uscirai vivo…."
                + "\nIstintivamente ti viene voglia di scappare e di tornare a casa. Ti ricordi però che non sei solo e sei nello spazio!  ");
        GameTextArea.append("\n====================================================================================================================================\n");
        GameTextArea.append(toUpperCase(game.getCurrentRoom().getName()));
        GameTextArea.append("\n");
        GameTextArea.append(game.getCurrentRoom().getDescription());
        
        // Inizializzazione della visualizzazione dell'inventario
        InventoryTextArea.setText("\tInventario");
        InventoryTextArea.append("\n-------------------------");
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GameTextField = new javax.swing.JTextField();
        GameTextAreaJsp = new javax.swing.JScrollPane();
        GameTextArea = new javax.swing.JTextArea();
        NorthButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        LookButton = new javax.swing.JButton();
        EnterButton = new javax.swing.JButton();
        SouthButton = new javax.swing.JButton();
        LoadButton = new javax.swing.JButton();
        WestButton = new javax.swing.JButton();
        EastButton = new javax.swing.JButton();
        InventoryTextAreaJsp = new javax.swing.JScrollPane();
        InventoryTextArea = new javax.swing.JTextArea();
        TimeTextAreaJsp = new javax.swing.JScrollPane();
        TimeTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SpaceRun");

        GameTextField.setText("Inserisci comando..."); // NOI18N
        GameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GameTextFieldActionPerformed(evt);
            }
        });

        GameTextArea.setColumns(20);
        GameTextArea.setRows(5);
        GameTextAreaJsp.setViewportView(GameTextArea);

        NorthButton.setText("NORD");
        NorthButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NorthButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("SALVA");

        LookButton.setText("OSSERVA");
        LookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LookButtonActionPerformed(evt);
            }
        });

        EnterButton.setText("INVIO");
        EnterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnterButtonActionPerformed(evt);
            }
        });

        SouthButton.setText("SUD");
        SouthButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SouthButtonActionPerformed(evt);
            }
        });

        LoadButton.setText("CARICA");

        WestButton.setText("OVEST");
        WestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WestButtonActionPerformed(evt);
            }
        });

        EastButton.setText("EST");
        EastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EastButtonActionPerformed(evt);
            }
        });

        InventoryTextArea.setColumns(20);
        InventoryTextArea.setRows(5);
        InventoryTextAreaJsp.setViewportView(InventoryTextArea);

        TimeTextArea.setColumns(20);
        TimeTextArea.setRows(5);
        TimeTextAreaJsp.setViewportView(TimeTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(InventoryTextAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(EastButton)
                                .addGap(90, 90, 90)
                                .addComponent(WestButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NorthButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SouthButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(TimeTextAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(GameTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EnterButton))
                    .addComponent(GameTextAreaJsp, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LoadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GameTextAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EnterButton)
                            .addComponent(GameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SaveButton)
                            .addComponent(LoadButton))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(InventoryTextAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(NorthButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(WestButton)
                            .addComponent(EastButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SouthButton)
                        .addGap(34, 34, 34)
                        .addComponent(LookButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TimeTextAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnterButtonActionPerformed
        String command = evt.getActionCommand();
        ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
 
        game.nextMove(p, this);
    }//GEN-LAST:event_EnterButtonActionPerformed

    private void SouthButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SouthButtonActionPerformed
        ParserOutput p = parser.parse("sud", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        GameTextField.setText("");
        GameTextArea.append("\n>> sud\n");
        game.nextMove(p, this);
        
    }//GEN-LAST:event_SouthButtonActionPerformed

    private void WestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WestButtonActionPerformed
        ParserOutput p = parser.parse("ovest", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        GameTextField.setText("");
        GameTextArea.append("\n>> ovest\n");
        game.nextMove(p, this);
    }//GEN-LAST:event_WestButtonActionPerformed

    private void NorthButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NorthButtonActionPerformed
        ParserOutput p = parser.parse("nord", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        GameTextField.setText("");
        GameTextArea.append("\n>> nord\n");
        game.nextMove(p, this);
    }//GEN-LAST:event_NorthButtonActionPerformed

    private void GameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GameTextFieldActionPerformed
        
        String command = evt.getActionCommand();
        ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        game.nextMove(p, this);
    }//GEN-LAST:event_GameTextFieldActionPerformed

    private void EastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EastButtonActionPerformed
        ParserOutput p = parser.parse("est", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        GameTextField.setText("");
        GameTextArea.append("\n>> est\n");
        game.nextMove(p, this);
    }//GEN-LAST:event_EastButtonActionPerformed

    private void LookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LookButtonActionPerformed
        ParserOutput p = parser.parse("osserva", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        GameTextField.setText("");
        GameTextArea.append("\n>> osserva\n");
        game.nextMove(p, this);
    }//GEN-LAST:event_LookButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpaceRunJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SpaceRunJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EastButton;
    private javax.swing.JButton EnterButton;
    private javax.swing.JTextArea GameTextArea;
    private javax.swing.JScrollPane GameTextAreaJsp;
    private javax.swing.JTextField GameTextField;
    private javax.swing.JTextArea InventoryTextArea;
    private javax.swing.JScrollPane InventoryTextAreaJsp;
    private javax.swing.JButton LoadButton;
    private javax.swing.JButton LookButton;
    private javax.swing.JButton NorthButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton SouthButton;
    private javax.swing.JTextArea TimeTextArea;
    private javax.swing.JScrollPane TimeTextAreaJsp;
    private javax.swing.JButton WestButton;
    // End of variables declaration//GEN-END:variables
   
    public void GameTextAreaSetText(String s) {
         GameTextArea.setText(s);
    }
}