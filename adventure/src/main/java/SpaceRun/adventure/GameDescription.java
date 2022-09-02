package SpaceRun.adventure;

import SpaceRun.adventure.gui.SpaceRunJFrame;
import SpaceRun.adventure.parser.ParserOutput;
import SpaceRun.adventure.type.AdvObject;
import SpaceRun.adventure.type.Command;
import SpaceRun.adventure.type.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe generalizza le caratteristiche di qualsiasi gioco. 
 */
public abstract class GameDescription {

    private final List<Room> rooms = new ArrayList<>();

    private final List<Command> commands = new ArrayList<>();

    private final List<AdvObject> inventory = new ArrayList<>();

    private Room currentRoom;

    /**
     * Questo metodo restituisce la lista delle stanze.
     *
     * @return rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }
    
    /**
     * Questo metodo restituisce la lista di comandi.
     *
     * @return commands
     */
    public List<Command> getCommands() {
        return commands;
    }
    
    /**
     * Questo metodo restituisce la variabile stanza corrente.
     *
     * @return currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * Questo metodo modifica la variabile stanza corrente. 
     *
     * @param currentRoom  L'oggetto, di classe Room, da inserire in currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    /**
     * Questo metodo restituisce la lista degli oggetti nell'inventario. 
     *
     * @return inventory
     */
    public List<AdvObject> getInventory() {
        return inventory;
    }
    
    /**
    * Il metodo init(), nel momento in cui viene implementato, 
    * si occupa del caricamento tramite file di informazioni neecessarie al popolamento 
    * delle liste relative ai comandi, alle stanze e agli oggetti, iniziando così
    * la partita con l'assegnazione della stanza iniziale della mappa creata.
    */
    public abstract void init() throws Exception;
    
    /**
     * Il metodo nextMove(), nel momento in cui viene implementato,
     * gestisce la mossa effettuata dal giocatore.
     *
     * @param p è l'istanza di ParserOutput.java che utilizziamo per la
     * comprensione del comando inserito
     * @param spaceRunJFrame è l' istanza di SpaceRunJFrame.java la quale viene consultata e modificata
     * @param command è la stringa comando così come è stato inserito dall'utente.
     */
    public abstract void nextMove(ParserOutput p, SpaceRunJFrame spaceRunJFrame, String command);

}
