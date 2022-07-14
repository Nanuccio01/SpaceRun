/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceRun.adventure;

import SpaceRun.adventure.gui.SpaceRunJFrame;
import SpaceRun.adventure.parser.ParserOutput;
import SpaceRun.adventure.type.AdvObject;
import SpaceRun.adventure.type.Command;
import SpaceRun.adventure.type.Room;
import java.awt.Frame;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pierpaolo
 */
public abstract class GameDescription {

    private final List<Room> rooms = new ArrayList<>();

    private final List<Command> commands = new ArrayList<>();

    private final List<AdvObject> inventory = new ArrayList<>();

    private Room currentRoom;

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<AdvObject> getInventory() {
        return inventory;
    }

    public abstract void init() throws Exception;

    public abstract void nextMove(ParserOutput p, SpaceRunJFrame spaceRunJFrame);
    
    public abstract void differentEnd(String command, SpaceRunJFrame spaceRunJFrame);

}
