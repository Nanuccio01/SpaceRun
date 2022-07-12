/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceRun.adventure.games;

import static com.sun.tools.javac.util.StringUtils.toUpperCase;
import SpaceRun.adventure.GameDescription;
import SpaceRun.adventure.gui.SpaceRunJFrame;
import SpaceRun.adventure.parser.ParserOutput;
import SpaceRun.adventure.type.AdvObject;
import SpaceRun.adventure.type.AdvObjectContainer;
import SpaceRun.adventure.type.Command;
import SpaceRun.adventure.type.CommandType;
import SpaceRun.adventure.type.Room;
import java.io.BufferedReader;
import static SpaceRun.adventure.type.Time.getTime;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe GameDescription. L'Engine è fatto in modo che possa
 * eseguire qualsiasi gioco che estende GameDescription, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (nextMove) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author pierpaolo
 */
public class SpaceRun extends GameDescription { //aggiunta abstract per errore

    @Override
    public void init() throws Exception {
        
        String lineRoom;
        String lineObjects;
        String[] setParam;
        FileReader frRoom;
        frRoom=new FileReader("./resources/roomDesc");
        BufferedReader brRoom;
        brRoom = new BufferedReader(frRoom);
        FileReader frObjects;
        frObjects=new FileReader("./resources/objectDesc");
        BufferedReader brObjects;
        brObjects = new BufferedReader(frObjects);
        
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi", "ispeziona", "esamina", "leggi", "osserva", "ascolta"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi", "ruba"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{"fruga", "rovista"});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi","pigia"});
        getCommands().add(push);
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza", "rompi", "metti", "inserisci", "uccidi", "sali", "spara", "guida", "parti"});
        getCommands().add(use);
        Command turn_on = new Command(CommandType.TURN_ON, "accendi");
        use.setAlias(new String[]{"attiva"});
        getCommands().add(turn_on);
        
        //Rooms
        try{
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room hall = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            hall.setLook(setParam[3]);
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room livingRoom = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            livingRoom.setLook(setParam[3]);
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room kitchen  = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            kitchen.setLook(setParam[3]);
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room bathroom = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            bathroom.setLook(setParam[3]);
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room yourRoom = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            yourRoom.setLook(setParam[3]); 
            
            //Maps
            kitchen.setEast(livingRoom);
            livingRoom.setNorth(hall);
            livingRoom.setWest(kitchen);
            hall.setSouth(livingRoom);
            hall.setWest(yourRoom);
            hall.setNorth(bathroom);
            bathroom.setSouth(hall);
            yourRoom.setEast(hall);
            getRooms().add(kitchen);
            getRooms().add(livingRoom);
            getRooms().add(hall);
            getRooms().add(bathroom);
            getRooms().add(yourRoom);
            //Obejcts
            lineObjects = brObjects.readLine();
            setParam = lineObjects.split("#");
            AdvObject battery = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            battery.setAlias(new String[]{"batterie", "pile", "pila"});
            bathroom.getObjects().add(battery);
            
            lineObjects = brObjects.readLine();
            setParam = lineObjects.split("#");
            AdvObjectContainer wardrobe = new AdvObjectContainer(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            wardrobe.setAlias(new String[]{"guardaroba", "vestiario"});
            wardrobe.setOpenable(true);
            wardrobe.setPickupable(false);
            wardrobe.setOpen(false);
            yourRoom.getObjects().add(wardrobe);
            
            lineObjects = brObjects.readLine();
            setParam = lineObjects.split("#");
            AdvObject toy = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            toy.setAlias(new String[]{"gioco", "robot"});
            toy.setPushable(true);
            toy.setPush(false);
            wardrobe.add(toy);
            
            //Set starting room
            setCurrentRoom(hall);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
}

    @Override
    public void nextMove(ParserOutput p, SpaceRunJFrame spaceRunJFrame) {
        if (p.getCommand() == null) {
            spaceRunJFrame.GameTextAreaSetText("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            if (p.getCommand().getType() == CommandType.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    setCurrentRoom(getCurrentRoom().getNorth());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                if (getCurrentRoom().getSouth() != null) {
                    setCurrentRoom(getCurrentRoom().getSouth());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    setCurrentRoom(getCurrentRoom().getWest());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.INVENTORY) {
                boolean isNotEmpty = getInventory() != null && !getInventory().isEmpty();
                if(isNotEmpty){
                    spaceRunJFrame.GameTextAreaSetText("Nel tuo inventario ci sono:");
                    for (AdvObject o : getInventory()) {
                        spaceRunJFrame.GameTextAreaSetText(o.getName() + ": " + o.getDescription());
                    }
                } else spaceRunJFrame.GameTextAreaSetText("Inventario vuoto, sarà ora che ti sbrighi se vuoi trovare una via di fuga...");  
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                if (getCurrentRoom().getLook() != null) {
                    spaceRunJFrame.GameTextAreaSetText(getCurrentRoom().getLook());
                } else {
                    spaceRunJFrame.GameTextAreaSetText("Non c'è niente di interessante qui.");
                }
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        spaceRunJFrame.GameTextAreaSetText("Hai raccolto: " + p.getObject().getDescription());
                    } else {
                        spaceRunJFrame.GameTextAreaSetText("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    spaceRunJFrame.GameTextAreaSetText("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                * Potrebbe non esssere la soluzione ottimale.
                 */
                if (p.getObject() == null && p.getInvObject() == null) {
                    spaceRunJFrame.GameTextAreaSetText("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                spaceRunJFrame.GameTextAreaSetText("Hai aperto: " + p.getObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    spaceRunJFrame.GameTextAreaSetText(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getCurrentRoom().getObjects().add(next);
                                        spaceRunJFrame.GameTextAreaSetText(" " + next.getName());
                                        it.remove();
                                    }
                                    spaceRunJFrame.GameTextAreaSetText("");
                                }
                            } else {
                                spaceRunJFrame.GameTextAreaSetText("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                        } else {
                            spaceRunJFrame.GameTextAreaSetText("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof AdvObjectContainer) {
                                AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    spaceRunJFrame.GameTextAreaSetText(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getInventory().add(next);
                                        spaceRunJFrame.GameTextAreaSetText(" " + next.getName());
                                        it.remove();
                                    }
                                    spaceRunJFrame.GameTextAreaSetText("");
                                }
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                            spaceRunJFrame.GameTextAreaSetText("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                        } else {
                            spaceRunJFrame.GameTextAreaSetText("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } else if (p.getCommand().getType() == CommandType.PUSH) {
                //ricerca oggetti pushabili
                if (p.getObject() != null && p.getObject().isPushable()) {
                    spaceRunJFrame.GameTextAreaSetText("Hai premuto: " + p.getObject().getName());
                    if (p.getObject().getId() == 3) {
                        end();
                    }
                } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                    spaceRunJFrame.GameTextAreaSetText("Hai premuto: " + p.getInvObject().getName());
                    if (p.getInvObject().getId() == 3) {
                        end();
                    }
                } else {
                    spaceRunJFrame.GameTextAreaSetText("Non ci sono oggetti che puoi premere qui.");
                }
            }
            if (noroom) {
                spaceRunJFrame.GameTextAreaSetText("Anche se siamo sullo spazio, è ancora impossibile attraversare i muri...");
            } else if (move) {
                String time = getTime();
                spaceRunJFrame.GameTextAreaSetText(toUpperCase(getCurrentRoom().getName()) + "| Ore: " + time);
                spaceRunJFrame.GameTextAreaSetText("================================================");
                spaceRunJFrame.GameTextAreaSetText(getCurrentRoom().getDescription());
            }
        }
    }

    private void end() {
        System.out.print("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...\ntu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...\nè stata una morte CALOROSA...addio!");
        System.exit(0);
    }
    
        @Override
        public void differentEnd (String command) {

            switch (command) {
                case "suicidati": case "ammazzati": case "ucciditi":
                    System.out.print("Decidi di correre contro gli alieni per porre fine alle tue sofferenze.\n L'aria dello spazio gioca brutti scherzi. ");
                break;
                
                case "muori":
                    System.out.println("Infarto istantaneo. E' stato rapido ed indolore.");
                break;
  
                case "end": case "fine": case "exit": case "esci":
                    System.out.println("Se l'umanità avesse una speranza, adesso non ce l'ha più...");
                break;
            }
        }
}