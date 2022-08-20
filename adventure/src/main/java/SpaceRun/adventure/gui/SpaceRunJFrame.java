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
import java.io.File;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;



/**

 *

 *@author Gaetano

 */



public class SpaceRunJFrame extends javax.swing.JFrame {



    private final GameDescription game;



    private final Parser parser;



    private boolean saved = true;



    public static final String SPACERUN_DB = "CREATE TABLE IF NOT EXISTS spaceRunDB (PartitaId VARCHAR PRIMARY KEY, currentRoom INT, inventoryId VARCHAR(100))";



    /**

     * Creates new form SpaceRunJFrame

     */

    public SpaceRunJFrame() {

        initComponents();

        DisplayOutputArea.setLineWrap(true);

        DisplayOutputArea.setWrapStyleWord(true);

        InventoryTextArea.setLineWrap(true);

        InventoryTextArea.setWrapStyleWord(true);



        this.game = new SpaceRun();

        try {

            this.game.init();

        } catch (Exception ex) {

            System.err.println(ex);

        }

        Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));

        parser = new Parser(stopwords);

        try{

            // Visualizzazione messaggi iniziali

            DisplayOutputArea.append("\t\t   !!! BENVENUTI IN SPACERUN !!!\n\n");

            DisplayOutputArea.append("Ti trovi su un’astronave viscida e cupa, si riescono a distinguere solo le luci di pulsanti e sirene costantemente in funzione. "

                + "\nChi ti ha catturato? Come sei entrato lì? Perché sei mezzo morto? Non sai niente di niente. "

                + "\nHai già osservato qualcosa in pochi momenti di vita e ti sembra di aver visto Alieni alti due metri che sembrano aver la funzione di dottori studianti specie viventi di tutto l’universo."

                + "\nNoti che a causa di un malfunzionamento dovuto a delle tubature scollegate, si, proprio quelle che ti consentivano di sopravvivere, che sei l’unico essere vivente in grado di cercare una via di fuga e forse una speranza sull’astronave."

                + "\nCapisci che ti hanno rinchiuso perché sarai il prossimo ad essere esaminato per studiare le tue capacità cognitive. Non sai se ne uscirai vivo…."

                + "\nIstintivamente ti viene voglia di scappare e di tornare a casa. Ti ricordi però che non sei solo e sei nello spazio!  ");        

            DisplayOutputArea.append("\n==================================================================\n");

            DisplayOutputArea.append("Ora ti trovi in: " + toUpperCase(game.getCurrentRoom().getName()));

            DisplayOutputArea.append("\n");

            DisplayOutputArea.append(game.getCurrentRoom().getDescription() + "\n");

            //Inizializzazione della visualizzazione dell'inventario
            InventoryTextArea.setText("     Zainetto");

            InventoryTextArea.append("\n------------------\n");

        }catch (Exception ex) {

            System.err.println(ex);

        }  

    }



    /**

     * This method is called from within the constructor to initialize the form.

     * WARNING: Do NOT modify this code. The content of this method is always

     * regenerated by the Form Editor.

     */

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GameInputField = new javax.swing.JTextField();
        DisplayOutputAreaJsp = new javax.swing.JScrollPane();
        DisplayOutputArea = new javax.swing.JTextArea();
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

        GameInputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GameInputFieldActionPerformed(evt);
            }
        });

        DisplayOutputAreaJsp.setToolTipText("");
        DisplayOutputAreaJsp.setRequestFocusEnabled(false);
        DisplayOutputAreaJsp.setVerifyInputWhenFocusTarget(false);
        DisplayOutputAreaJsp.setWheelScrollingEnabled(false);

        DisplayOutputArea.setColumns(20);
        DisplayOutputArea.setRows(5);
        DisplayOutputArea.setBorder(null);
        DisplayOutputArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        DisplayOutputAreaJsp.setViewportView(DisplayOutputArea);

        NorthButton.setText("NORD");
        NorthButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NorthButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("SALVA");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

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
        InventoryTextArea.setAlignmentX(0.0F);
        InventoryTextArea.setAlignmentY(0.0F);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(InventoryTextAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(LookButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(WestButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(TimeTextAreaJsp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(SouthButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(NorthButton)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GameInputField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EnterButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LoadButton)
                                .addGap(18, 18, 18)
                                .addComponent(SaveButton))
                            .addComponent(DisplayOutputAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DisplayOutputAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GameInputField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EnterButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LoadButton)
                            .addComponent(SaveButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(InventoryTextAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NorthButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(WestButton)
                            .addComponent(EastButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SouthButton)
                        .addGap(46, 46, 46)
                        .addComponent(LookButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TimeTextAreaJsp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    private void EnterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnterButtonActionPerformed

        String command = GameInputField.getText();

        ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        
        GameInputField.setText("");

        DisplayOutputArea.append("\n>> " + command + "\n\n");

        game.nextMove(p, this);

    }//GEN-LAST:event_EnterButtonActionPerformed



    private void SouthButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SouthButtonActionPerformed

        ParserOutput p = parser.parse("sud", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());

        GameInputField.setText("");

        DisplayOutputArea.append("\n>> sud\n\n");

        game.nextMove(p, this);

    }//GEN-LAST:event_SouthButtonActionPerformed



    private void WestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WestButtonActionPerformed

        ParserOutput p = parser.parse("ovest", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());

        GameInputField.setText("");

        DisplayOutputArea.append("\n>> ovest\n\n");

        game.nextMove(p, this);

    }//GEN-LAST:event_WestButtonActionPerformed



    private void NorthButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NorthButtonActionPerformed

        ParserOutput p = parser.parse("nord", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());

        GameInputField.setText("");

        DisplayOutputArea.append("\n>> nord\n\n");

        game.nextMove(p, this);

    }//GEN-LAST:event_NorthButtonActionPerformed



    private void GameInputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GameInputFieldActionPerformed

        String command = evt.getActionCommand();

        ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        
        GameInputField.setText("");

        DisplayOutputArea.append("\n>> " + command + "\n\n");

        game.nextMove(p, this);

    }//GEN-LAST:event_GameInputFieldActionPerformed



    private void EastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EastButtonActionPerformed

        ParserOutput p = parser.parse("est", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());

        GameInputField.setText("");

        DisplayOutputArea.append("\n>> est\n\n");

        game.nextMove(p, this);

    }//GEN-LAST:event_EastButtonActionPerformed



    private void LookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LookButtonActionPerformed

        ParserOutput p = parser.parse("osserva", game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());

        GameInputField.setText("");

        DisplayOutputArea.append("\n>> osserva\n\n");

        if(game.getCurrentRoom().isVisible() ==true){

            game.nextMove(p, this);

        } else {

            DisplayOutputArea.append("Non possiedi niente per vedere illuminata la stanza, non vedi nemmeno la punta del tuo naso… corri subito a cercare qualcosa! "

                    + "Magari con un po’ di luce puoi rovistare nelle divise delle guardie.\n");

        }



    }//GEN-LAST:event_LookButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        sendCommand();
        saveGame();
    }//GEN-LAST:event_SaveButtonActionPerformed



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
    private javax.swing.JTextArea DisplayOutputArea;
    private javax.swing.JScrollPane DisplayOutputAreaJsp;
    private javax.swing.JButton EastButton;
    private javax.swing.JButton EnterButton;
    private javax.swing.JTextField GameInputField;
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



    public void DisplayOutputSetText(String s) {
         DisplayOutputArea.append(s);
    }
    
    public void InventoryOutputSetText(String s) {
         InventoryTextArea.append(s);
    }


private void sendCommand() {
        if (GameInputField.getText().length() > 0) {
            String command = GameInputField.getText();
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            GameInputField.setText("");
            
            if (p.getCommand() != null && p.getCommand().getType() == CommandType.SAVE) {
                    saveGame(); 

               /* } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.LOAD) {
                    
                    DisplayOutputArea.append("\nChe partita vuoi caricare? Digita il nome della partita");
                    try{
                    int cont = 0;
                    boolean exist_flag = false; //flag per l'esistenza di una partita con lo stesso nome
                    Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db/save");
                    Statement st = conn.createStatement();
                    st.executeUpdate(SPACERUN_DB);
                    ResultSet rs = st.executeQuery("SELECT PartitaID FROM spaceRunDB "); 
                    while (rs.next()){
                         cont = cont+1;
                       } 
                    if (cont > 0){ 
                        for(int i = 1; i <= cont; i++){
                        DisplayOutputArea.append("\n "+rs.getString(i)+"");
                        DisplayOutputArea.append("\nChe partita vuoi caricare? Digita il nome della partita: ");
                        String input = GameInputField.getText();
                        while (rs.next()){
                            String partita = rs.getString(1);
                            if (partita.equals(input)){
                            exist_flag = true; 
                            }
                        if (exist_flag){
                        DisplayOutputArea.append("\nCaricamento della partita "+input+" in corso...");
                        ResultSet rst = st.executeQuery("SELECT * FROM SpaceRunDB WHERE PartitaID = "+input+"");
                        if (rst.getInt(2) == game.getCurrentRoom().getId()){
                            
                        }
                       
                        this.game.setCurrentRoom(next);
                        DisplayOutputArea.append("Ti trovi in: ");
                        DisplayOutputArea.append(game.getCurrentRoom().getName());
                        DisplayOutputArea.append("\n");
                        DisplayOutputArea.append(game.getCurrentRoom().getDescription());
                        
                    
                    } else{
                        PreparedStatement pst = conn.prepareStatement("INSERT INTO spaceRunDB VALUES (?,?,?)");
                        pst.setString(1, input);
                        pst.setInt(2, this.game.getCurrentRoom().getId());
                        Iterator<AdvObject> it = this.game.getInventory().iterator();
                        String Obj = "";
                        while (it.hasNext()) {
                            flag = true;
                            AdvObject next = it.next();
                            Obj = Obj + next.getId() + "#";
                        }

                        if (flag) {
                            pst.setString(3, Obj);
                        } else {
                            Obj = null;
                            pst.setString(3, Obj);
                        }
                        pst.executeUpdate();
                        pst.close();
                    }   
                        }
                    }
                } else {
                      DisplayOutputArea.append("Non ci sono partite salvate in questo momento. "); 
                      //mettere opzione se iniziare una nuova partita
                    }
                    
                    }catch (SQLException ex) {
                    System.err.println(ex.getSQLState() + ": " + ex.getMessage());
                    }
                      // controllare uso variabile true, quando true e quando false
                      
                    if (!saved) {
                        int option = JOptionPane.showConfirmDialog(null, "Ci sono modifiche non salvate. Sicuro di voler caricare una nuova partita?", "Caricamento file", JOptionPane.YES_NO_CANCEL_OPTION);
                        switch (option) {
                            case JOptionPane.YES_OPTION:
                                loadGame();
                                saved = true;
                                break;
                            case JOptionPane.NO_OPTION:
                                saveGame();
                                break;
                            case JOptionPane.CANCEL_OPTION:
                                return;
                            default:
                                break;
                        }
                    } else {
                        loadGame();
                        saved = true;
                    }
                }

            } else {
                DisplayOutputArea.append("\n>> " + command + "\n");
                DisplayOutputArea.setCaretPosition(0);
                DisplayOutputArea.setCaretPosition(DisplayOutputArea.getDocument().getLength());
                if (fast) {
                    DisplayOutputArea.append(game.nextMove(p));
                } else {
                    s.append(game.nextMove(p));
                }
                DisplayOutputArea.setCaretPosition(DisplayOutputArea.getDocument().getLength());

                
                updateInventory();
                saved = false;

                checkEnd(); */
                }
            }
        }
    
    private boolean saveGame() {
      try {
                    boolean flag = false;
                    boolean exist_flag = false; //flag per l'esistenza di una partita con lo stesso nome
                    Connection conn = DriverManager.getConnection("jdbc:h2:./resources");
                    Statement st = conn.createStatement();
                    st.execute("CREATE TABLE IF NOT EXISTS spaceRunDB (PartitaId VARCHAR(100) PRIMARY KEY, currentRoom INT, inventoryId VARCHAR(100))");
                    st.executeUpdate(SPACERUN_DB);
                    DisplayOutputArea.append("\nCon che nome vuoi salvare questa partita?");
                    String input = GameInputField.getText(); //inserire controlli per la parola
                    ResultSet rs = st.executeQuery("SELECT PartitaID FROM spaceRunDB "); 
                    while (rs.next()){
                        String partita = rs.getString(1);
                        if (partita.equals(input)){
                            exist_flag = true;
                        }
                    }
                    if (exist_flag){
                        DisplayOutputArea.append("\nEsiste già una partita salvata con questo nome. Stai sovrascrivendo la vecchia partita...");
                        PreparedStatement pst = conn.prepareStatement("UPDATE spaceRunDB SET currentRoom = ?, inventoryId = ? WHERE PartitaID = "+input+"");
                        pst.setInt(2, this.game.getCurrentRoom().getId());
                        Iterator<AdvObject> it = this.game.getInventory().iterator();
                        String Obj = "";
                        while (it.hasNext()) {
                            flag = true;
                            AdvObject next = it.next();
                            Obj = Obj + next.getId() + "#";
                        }

                        if (flag) {
                            pst.setString(3, Obj);
                        } else {
                            Obj = null;
                            pst.setString(3, Obj);
                        }
                        pst.executeUpdate();
                        pst.close();
                    } else{
                        PreparedStatement pst = conn.prepareStatement("INSERT INTO spaceRunDB VALUES (?,?,?)");
                        pst.setString(1, input);
                        pst.setInt(2, this.game.getCurrentRoom().getId());
                        Iterator<AdvObject> it = this.game.getInventory().iterator();
                        String Obj = "";
                        while (it.hasNext()) {
                            flag = true;
                            AdvObject next = it.next();
                            Obj = Obj + next.getId() + "#";
                        }

                        if (flag) {
                            pst.setString(3, Obj);
                        } else {
                            Obj = null;
                            pst.setString(3, Obj);
                        }
                        pst.executeUpdate();
                        pst.close();
                    } 
                    
                    st.close();
                    DisplayOutputArea.append("\nSalvataggio effettuato correttamente!");
                    saved = true;
                    
                    } catch (SQLException ex) {
                    System.err.println(ex.getSQLState() + ": " + ex.getMessage());
                    DisplayOutputArea.append("\nSalvataggio non riuscito");
                }  
                return saved;      //variabile booleana per il salvataggio
    }
    
    private void loadGame(){
        try {
  
                    boolean flag = false;
                    boolean exist_flag = false; //flag per l'esistenza di una partita con lo stesso nome
                    Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db/save");
                    Statement st = conn.createStatement();
                    st.executeUpdate(SPACERUN_DB);
                    DisplayOutputArea.append("\n");
                    
        } catch (SQLException ex) {
                    System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
}