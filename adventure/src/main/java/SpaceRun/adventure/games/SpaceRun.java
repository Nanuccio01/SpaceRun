/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceRun.adventure.games;

import SpaceRun.adventure.GameDescription;
import SpaceRun.adventure.gui.SpaceRunJFrame;
import SpaceRun.adventure.parser.ParserOutput;
import SpaceRun.adventure.type.AdvObject;
import SpaceRun.adventure.type.AdvObjectContainer;
import SpaceRun.adventure.type.Command;
import SpaceRun.adventure.type.CommandType;
import SpaceRun.adventure.type.Room;
import static SpaceRun.adventure.type.Space.getPersonInSpace;
import static SpaceRun.adventure.type.Space.getWeatherByCity;
import static com.sun.tools.javac.util.StringUtils.toUpperCase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gaetano Schiralli, Dafne Spaccavento
 */

/**
 * La classe SpaceRun, eredita dalla classe GameDescription.java,
 * implementa tutti i metodi e gl attrbuti, così da creare il gioco in questione, SpaceRun.
 */
public class SpaceRun extends GameDescription { 

    /**
    * Il metodo init() si occupa del caricamento tramite file di informazioni neecessarie
    * al popolamento delle liste relative ai comandi, alle stanze e agli oggetti, iniziando così
    * la partita con l'assegnazione della stanza iniziale della mappa creata.
     * @throws java.lang.Exception
    */
    @Override
    public void init() throws Exception {
        
        // Variabili
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

        //Comandi
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
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
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza", "spingi", "premi", "pigia", "metti", "inserisci", "ammazza", "uccidi", "spara", "sparalo", "guida", "parti", "attiva", "spegni", "elimina", "cancella", "accendi"});
        getCommands().add(use);
        Command mike = new Command(CommandType.MIKE, "mike");
        mike.setAlias(new String[]{"MIKE","Mike"});
        getCommands().add(mike);
        Command save = new Command(CommandType.SAVE, "salva");
        save.setAlias(new String[]{"salvataggio"});
        getCommands().add(save);
        Command load = new Command(CommandType.LOAD, "carica");
        load.setAlias(new String[]{"riprendi"});
        getCommands().add(load);
        
        //Stanze
        try{
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room incubators = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //0
            incubators.setMikeMessage(setParam[3]);
            incubators.setLook(setParam[4]);
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room scientificCorridor = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //1
            scientificCorridor.setMikeMessage(setParam[3]);
            scientificCorridor.setLook(setParam[4]);
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room observatory = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //2
            observatory.setMikeMessage(setParam[3]);
            observatory.setLook(setParam[4]);          
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room warehouse = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //3
            warehouse.setMikeMessage(setParam[3]);
            warehouse.setLook(setParam[4]);          
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room westCorridor = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //4
            westCorridor.setMikeMessage(setParam[3]);
            westCorridor.setLook(setParam[4]);          
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room dormitory = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //5
            dormitory.setMikeMessage(setParam[3]);
            dormitory.setLook(setParam[4]); 
            dormitory.setVisible(false);
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room conferenceRoom = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //6
            conferenceRoom.setMikeMessage(setParam[3]);
            conferenceRoom.setLook(setParam[4]); 
            conferenceRoom.setLocked(true);
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room controlCabin = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //7
            controlCabin.setMikeMessage(setParam[3]);
            controlCabin.setLook(setParam[4]);     
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#"); 
            Room eggLair = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //8
            eggLair.setMikeMessage(setParam[3]);
            eggLair.setLook(setParam[4]);        
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room commandersThrone = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //9
            commandersThrone.setMikeMessage(setParam[3]);
            commandersThrone.setLook(setParam[4]);       
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room controlRoom = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //10
            controlRoom.setMikeMessage(setParam[3]);
            controlRoom.setLook(setParam[4]);
            controlRoom.setLocked(true);
            lineRoom = brRoom.readLine();
            setParam = lineRoom.split("#");
            Room universalPort = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //11
            universalPort.setMikeMessage(setParam[3]);
            universalPort.setLook(setParam[4]);
            lineRoom = brRoom.readLine();
            universalPort.setLocked(true);
            setParam = lineRoom.split("#");
            Room spacecraft = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //12
            spacecraft.setMikeMessage(setParam[3]);
            spacecraft.setLook(setParam[4]);
            
            //Creazione mappa
            incubators.setNorth(scientificCorridor);
            scientificCorridor.setWest(westCorridor);
            scientificCorridor.setEast(observatory);
            scientificCorridor.setSouth(incubators);
            observatory.setSouth(warehouse);
            observatory.setWest(scientificCorridor);
            warehouse.setNorth(observatory);
            westCorridor.setSouth(conferenceRoom);
            westCorridor.setNorth(dormitory);
            westCorridor.setEast(scientificCorridor);
            conferenceRoom.setNorth(westCorridor);
            dormitory.setSouth(westCorridor);
            dormitory.setEast(controlCabin);
            dormitory.setNorth(controlRoom);
            controlCabin.setEast(eggLair);
            controlCabin.setWest(dormitory);
            eggLair.setNorth(commandersThrone);
            eggLair.setWest(controlCabin);
            commandersThrone.setSouth(eggLair);
            controlRoom.setEast(universalPort);
            controlRoom.setSouth(dormitory);
            universalPort.setWest(controlRoom);
            universalPort.setNorth(spacecraft);
            spacecraft.setSouth(universalPort);

            getRooms().add(incubators);
            getRooms().add(scientificCorridor);
            getRooms().add(observatory);
            getRooms().add(warehouse);
            getRooms().add(westCorridor);
            getRooms().add(dormitory);
            getRooms().add(conferenceRoom);
            getRooms().add(controlCabin);
            getRooms().add(eggLair);
            getRooms().add(commandersThrone);
            getRooms().add(controlRoom);
            getRooms().add(universalPort);
            getRooms().add(spacecraft);
            
            //Oggetti
            lineObjects = brObjects.readLine(); //0
            setParam = lineObjects.split("#");
            AdvObjectContainer backpack = new AdvObjectContainer(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); 
            backpack.setAlias(new String[]{"zainetto", "backpack"});
            backpack.setOpenable(true);
            backpack.setPickupable(false);
            incubators.getObjects().add(backpack);
            
            lineObjects = brObjects.readLine(); //1
            setParam = lineObjects.split("#");
            AdvObject wristwatch = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);                  
            wristwatch.setAlias(new String[]{"orario", "tempo"});
            backpack.add(wristwatch);
            
            lineObjects = brObjects.readLine(); //2
            setParam = lineObjects.split("#");
            AdvObjectContainer drawer = new AdvObjectContainer(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            drawer.setAlias(new String[]{"tiretto", "scaffale"});
            drawer.setOpenable(true);
            drawer.setPickupable(false);
            warehouse.getObjects().add(drawer);
            
            lineObjects = brObjects.readLine(); //3
            setParam = lineObjects.split("#");
            AdvObjectContainer box = new AdvObjectContainer(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            box.setAlias(new String[]{"scatolo"});
            box.setOpenable(true);
            box.setPickupable(false);
            drawer.add(box);
            
            lineObjects = brObjects.readLine(); //4
            setParam = lineObjects.split("#");
            AdvObject paperwork = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            paperwork.setAlias(new String[]{"fogli di carta", "fogli"});
            paperwork.setPickupable(false);
            drawer.add(paperwork);

            lineObjects = brObjects.readLine(); //5
            setParam = lineObjects.split("#");
            AdvObject laserGun = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            laserGun.setAlias(new String[]{"pistola", "pistola laser"});
            laserGun.setUsable(true);
            drawer.add(laserGun);  
            
            lineObjects = brObjects.readLine(); //6
            setParam = lineObjects.split("#");
            AdvObject torch = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            torch.setAlias(new String[]{"torcia", "luce"});
            torch.setUsable(true);
            drawer.add(torch);
            
            lineObjects = brObjects.readLine(); //7
            setParam = lineObjects.split("#");
            AdvObject cup = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            cup.setAlias(new String[]{"tazzina"});
            cup.setPickupable(false);
            box.add(cup);
            
            lineObjects = brObjects.readLine(); //8
            setParam = lineObjects.split("#");
            AdvObject familyPhoto = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            familyPhoto.setAlias(new String[]{"foto", "ritratto", "foto ricordo", "scatto"});
            familyPhoto.setPickupable(false);
            box.add(familyPhoto);
            
            lineObjects = brObjects.readLine(); //9
            setParam = lineObjects.split("#");
            AdvObject pen = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            pen.setAlias(new String[]{"stilo"});
            pen.setPickupable(false);
            box.add(pen);
            
            lineObjects = brObjects.readLine(); //10
            setParam = lineObjects.split("#");
            AdvObjectContainer uniforms = new AdvObjectContainer(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            uniforms.setAlias(new String[]{"vestiti", "panni", "mucchio di robe", "robe"});
            uniforms.setOpenable(true);
            uniforms.setPickupable(false);
            dormitory.getObjects().add(uniforms);
            
            lineObjects = brObjects.readLine(); //11
            setParam = lineObjects.split("#");
            AdvObject badge = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            badge.setAlias(new String[]{"tessera", "badge", "tessera riconoscimento"});
            uniforms.add(badge);
            
            lineObjects = brObjects.readLine(); //12
            setParam = lineObjects.split("#");
            AdvObject universalTranslator = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            universalTranslator.setAlias(new String[]{"traduttore"});
            conferenceRoom.getObjects().add(universalTranslator);
            
            lineObjects = brObjects.readLine(); //13
            setParam = lineObjects.split("#");
            AdvObject camcorders = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            camcorders.setAlias(new String[]{"videocamere", "videocamera", "telecamera"});
            camcorders.setPickupable(false);
            camcorders.setUsable(true);
            controlCabin.getObjects().add(camcorders);
            
            lineObjects = brObjects.readLine(); //14
            setParam = lineObjects.split("#");
            AdvObject videoClips = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            videoClips.setAlias(new String[]{"registrazioni", "video", "filmato"});
            videoClips.setPickupable(false);
            videoClips.setUsable(true);
            controlCabin.getObjects().add(videoClips);
            
            lineObjects = brObjects.readLine(); //15
            setParam = lineObjects.split("#");
            AdvObject maps = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            maps.setAlias(new String[]{"piantina", "mappa navicella", "mappa astronave"});
            maps.setPickupable(false);
            controlCabin.getObjects().add(maps);
            
            lineObjects = brObjects.readLine(); //16
            setParam = lineObjects.split("#");
            AdvObject redButton = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            redButton.setAlias(new String[]{"tasto", "bottone", "pulsante", "bottone rosso", "pulsante rosso"});
            redButton.setPickupable(false);
            redButton.setUsable(true);
            spacecraft.getObjects().add(redButton); 
            
            lineObjects = brObjects.readLine(); //17
            setParam = lineObjects.split("#");
            AdvObject alien = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            alien.setAlias(new String[]{"guardia", "guardia aliena", "alieni", "alieno", "extraterrestre"});
            alien.setPickupable(false);
            alien.setUsable(true);
            controlRoom.getObjects().add(alien); 
            observatory.getObjects().add(alien); 
            
            lineObjects = brObjects.readLine(); //18
            setParam = lineObjects.split("#");
            AdvObject telescope = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            telescope.setAlias(new String[]{"cannocchiale", "binocolo"});
            telescope.setPickupable(false);
            telescope.setUsable(true);
            observatory.getObjects().add(telescope);
                       
            //Impostazione stanza iniziale
            setCurrentRoom(incubators);
            incubators.setVisited(true);
        } catch (IOException e) {
            System.exit(0);
        }
}  
    /**
     * Il metodo nextMove(), gestisce la mossa effettuata dal giocatore.
     *
     * @param p è l'istanza di ParserOutput.java che utilizziamo per la
     * comprensione del comando inserito
     * @param spaceRunJFrame è l' istanza di SpaceRunJFrame.java la quale viene consultata e modificata
     * @param command è la stringa comando così come è stato inserito dall'utente.
     */
    @Override
    public void nextMove(ParserOutput p, SpaceRunJFrame spaceRunJFrame, String command) {      
        if (p.getCommand() == null) {
            spaceRunJFrame.DisplayOutputSetText("Non ho capito cosa devo fare! Prova con un altro comando. \n");
        } else {
            //move
            boolean card = false;
            boolean noroom = false;
            boolean move = false;
            boolean blocked = false;
            if (p.getCommand().getType() == CommandType.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    if (getCurrentRoom().getNorth().isLocked() == false) {
                        if (getCurrentRoom().getId() == 0){
                            Calendar calMin = Calendar.getInstance();
                            int time = calMin.get(Calendar.MINUTE);
                            if ((time%2) != 0){
                                spaceRunJFrame.DisplayOutputSetText("Guarda bene l'orario... Non riesci ad oltrepassare nemmeno la prima stanza... "
                                        + "forse non c’era molto da studiare nel tuo cervello. \n");
                            } else {
                                 setCurrentRoom(getCurrentRoom().getNorth());
                                 move = true;
                            } 
                        } else {
                            setCurrentRoom(getCurrentRoom().getNorth());
                            move = true;
                        } 
                    } else {
                        blocked = true;
                    }
                } else {
                    noroom = true;
                } 
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                if (getCurrentRoom().getSouth() != null) {
                    if (getCurrentRoom().getSouth().isLocked() == false) {
                        setCurrentRoom(getCurrentRoom().getSouth());
                        move = true;
                    } else {
                        blocked = true;
                    }
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
               if (getCurrentRoom().getEast() != null) {
                    if (getCurrentRoom().getEast().isLocked() == false) {
                        setCurrentRoom(getCurrentRoom().getEast());
                        move = true;
                    } else {
                        blocked = true;
                    }
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    if (getCurrentRoom().getWest().isLocked() == false) {
                        setCurrentRoom(getCurrentRoom().getWest());
                        move = true;
                    } else {
                        blocked = true;
                    }
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                 if (p.getObject() != null) {
                    if (p.getObject().isOpenable() == true && p.getObject().isOpen() == false ) {
                        spaceRunJFrame.DisplayOutputSetText(" L'oggetto è chiuso, aprilo per darci un occhiata da vicino \n");
                    } else {
                        if (p.getObject().getId() == 13 || p.getObject().getId() == 14 || p.getObject().getId() == 15){
                            if(card == false){
                                Iterator<AdvObject> invIt = getInventory().iterator();
                                while (invIt.hasNext()) {
                                    AdvObject next = invIt.next();
                                    if (next.getId() == 11){
                                        card = true;    
                                    }
                                }
                            }
                        }
                        if (p.getObject().getId() == 15 && card == true){                                                           
                            spaceRunJFrame.MapDialog();
                            spaceRunJFrame.DisplayOutputSetText(p.getObject().getDescription() + "\n");      
                        } else if ((p.getObject().getId() == 13 || p.getObject().getId() == 14 || p.getObject().getId() == 15) && card == false){
                            spaceRunJFrame.DisplayOutputSetText("Non hai i requisiti necessari per fare ciò. \n");
                        } else {
                            spaceRunJFrame.DisplayOutputSetText(p.getObject().getDescription() + "\n");
                        }
                    }
                } else if (p.getInvObject() != null) {
                    spaceRunJFrame.DisplayOutputSetText(p.getInvObject().getDescription() + "\n");
                } else if (getCurrentRoom().getLook() != null) {
                    if(getCurrentRoom().isVisible() == true){
                        spaceRunJFrame.DisplayOutputSetText(getCurrentRoom().getLook() + "\n" );
                    } else {
                        spaceRunJFrame.DisplayOutputSetText("Non possiedi niente per vedere illuminata la stanza, non vedi nemmeno la punta del tuo naso… "
                                + "corri subito a cercare qualcosa! "
                                + "Magari con un po’ di luce puoi rovistare nelle divise delle guardie.\n");
                    }     
                } else {
                    spaceRunJFrame.DisplayOutputSetText("Non c'è niente di interessante. \n");
                } 
            } else if (p.getCommand().getType() == CommandType.MIKE) {
                if (getCurrentRoom().getMikeMessage() != null) {
                    spaceRunJFrame.DisplayOutputSetText(getCurrentRoom().getMikeMessage() + "\n" );
                } else {
                    spaceRunJFrame.DisplayOutputSetText("Mike non disponibile \n");
                }
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        spaceRunJFrame.DisplayOutputSetText("Hai raccolto: " + p.getObject().getName()+ " ->" + p.getObject().getDescription() + "\n");
                        spaceRunJFrame.InventoryOutputAppend(" -"+ p.getObject().getName() + "\n");
                        if(p.getObject().getId() == 11) {
                            spaceRunJFrame.DisplayOutputSetText("Tessera di riconoscimento presa! Sembra essere di un alieno importante, dovresti aver il lasciapassare per tutte le stanze dell’astronave. \n");
                            Iterator<Room> roomIt = getRooms().iterator();
                            while (roomIt.hasNext()) {
                                Room nextRoom = roomIt.next();
                                if(nextRoom.getId() == 7) {
                                    Iterator<AdvObject> objIt = nextRoom.getObjects().iterator();
                                    while (objIt.hasNext()) {
                                        AdvObject nextObj = objIt.next();
                                        nextObj.setUsable(true);
                                    }
                                }
                                nextRoom.setLocked(false);
                                if(nextRoom.getId() == 11){
                                    nextRoom.setLocked(true);
                                }    
                            }
                        } else if  (p.getObject().getId() == 12) {
                            Iterator<Room> roomIt = getRooms().iterator();
                            while (roomIt.hasNext()) {
                                Room nextRoom = roomIt.next();
                                if(nextRoom.getId() == 1) {
                                    nextRoom.setMikeMessage("Ti consiglio di visitare verso Est, potresti trovare qualcosa di interessante. \n");  
                                } else if(nextRoom.getId() == 4) {
                                    nextRoom.setMikeMessage("Procedi verso Nord, stai andando alla grande. Promettimi però che resti con me a giocare dopo. \n");  
                                } else if(nextRoom.getId() == 5) {
                                    nextRoom.setMikeMessage("Ti consiglio prima di passare da Est ora che possiedi il tesserino! \n");
                                } else if(nextRoom.getId() == 6) {
                                    nextRoom.setMikeMessage("Finalmente possiamo parlare insieme. Piacere Mike, sembra non sei molto sveglio come umano, ma sei simpatico. \n");  
                                } else if(nextRoom.getId() == 7) {
                                    nextRoom.setMikeMessage("Se non lo hai già fatto elimina tutto, spegni le telecamere e dai un’occhiata alla mappa per finire."
                                            + " Non te ne dimenticare, così saprai come scappare. Da questo momento in poi l’astronave sembra essere più cupa ed ostile del previsto."
                                            + " In alcune stanze non ho il permesso di entrare nemmeno io. Non posso dirti altro. Tieniti pronto. \n");   
                                } else if(nextRoom.getId() == 10) {
                                    nextRoom.setMikeMessage("Io non parlo, sto tremando. Sono scosso ed ho troppa paura. \n");  
                                } 
                            }
                        } 
                    } else {
                        spaceRunJFrame.DisplayOutputSetText("Non puoi raccogliere questo oggetto. \n");
                    }
                } else {
                    spaceRunJFrame.DisplayOutputSetText("Non c'è niente da raccogliere.");
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                if (p.getObject() == null && p.getInvObject() == null) {
                    spaceRunJFrame.DisplayOutputSetText("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                p.getObject().setOpen(true);
                                spaceRunJFrame.DisplayOutputSetText("Hai aperto: " + p.getObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    spaceRunJFrame.DisplayOutputSetText("\n" + c.getName() + " contiene: | ");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getCurrentRoom().getObjects().add(next);
                                        spaceRunJFrame.DisplayOutputSetText(next.getName() + " | " );
                                        it.remove();
                                    }
                                    spaceRunJFrame.DisplayOutputSetText("\n");
                                }
                            } else {
                                spaceRunJFrame.DisplayOutputSetText("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                            if (p.getObject().getId() == 10){
                                spaceRunJFrame.DisplayOutputSetText("Tessera di riconoscimento trovata! Speriamo sia utile a qualcosa. Prendila e continua il tuo percorso. \n");
                            } else if (p.getObject().getId() == 0){
                                spaceRunJFrame.DisplayOutputSetText("Hai aperto il tuo zainetto e sembra esserci qualcosa al suo interno."
                                        + " È un vecchio regalo di compleanno, un orologio donato dal tuo ormai lontano padre. "
                                        + "Decidi di custodirlo con cura. Potrebbe tornarti utile.   \n");
                            } else if (p.getObject().getId() == 2){
                                spaceRunJFrame.DisplayOutputSetText("Noti una scatola piena di oggetti con accanto delle scartoffie che potrebbero rivelarti il motivo per il quale sei lì. "
                                        + "Si c'è anche una torcia ed una pistola."
                                        + " Non hai molto tempo però, sbrigati!  \n");
                            } else if (p.getObject().getId() == 3){
                                spaceRunJFrame.DisplayOutputSetText("Vedi una tazza, una foto di famiglia e una penna"
                                        + " Sembra quasi che abbiano preso tutti gli oggetti presenti su una scrivania di un carabiniere! Tranne la pistola ovviamente… \n");
                            }   
                        } else {
                            spaceRunJFrame.DisplayOutputSetText("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof AdvObjectContainer) {
                                p.getInvObject().setOpen(true);
                                spaceRunJFrame.DisplayOutputSetText("Hai aperto nell' inventario: " + p.getInvObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    spaceRunJFrame.DisplayOutputSetText("\n" + c.getName() + " contiene: | ");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getInventory().add(next);
                                        if (next.isPickupable()) {
                                            spaceRunJFrame.InventoryOutputAppend("  "+ next.getName() + "\n");
                                        }
                                        spaceRunJFrame.DisplayOutputSetText(next.getName() + " | " );
                                        it.remove();
                                    }
                                    spaceRunJFrame.DisplayOutputSetText("\n");
                                }
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                        } else {
                            spaceRunJFrame.DisplayOutputSetText("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } else if (p.getCommand().getType() == CommandType.USE) {
                //ricerca oggetti usabili
                if (p.getObject() == null && p.getInvObject() == null) {
                    spaceRunJFrame.DisplayOutputSetText("Non c'è niente da usare qui.");
                } else {
                    if (p.getObject() != null && p.getObject().isUsable()) {
                        if(p.getObject().isUsed() == false){
                            if (p.getObject().getId() == 17){ 
                                p.getObject().setUsed(true);
                            } else if (p.getObject().getId() == 13 || p.getObject().getId() == 14) {
                                if(card == false){
                                    Iterator<AdvObject> invIt = getInventory().iterator();
                                    while (invIt.hasNext()) {
                                        AdvObject next = invIt.next();
                                        if (next.getId() == 11){
                                            card = true;    
                                        }
                                    }
                                }
                                if( card == true && p.getObject().getId() == 13){
                                    p.getObject().setUsed(true);
                                   spaceRunJFrame.DisplayOutputSetText("Telecamere spente. D'ora in poi nessuno sapra' piu' dove sei. \n");
                                } else if( card == true && p.getObject().getId() == 14){
                                    p.getObject().setUsed(true);
                                    spaceRunJFrame.DisplayOutputSetText("File eliminati! Ora nessuno capira' come e quando sei fuggito, sarai un fantasma. Un fantasma ricercato... \n");
                                } else {
                                    p.getObject().setUsed(false);
                                    spaceRunJFrame.DisplayOutputSetText("Non hai i requisiti necessari per fare ciò. Se insisti si attiverà l’allarme di sicurezza. \n"); 
                                }
                            } else {
                                spaceRunJFrame.DisplayOutputSetText("Hai usato: " + p.getObject().getName() + "\n");
                                p.getObject().setUsed(true);
                            }
                            if(p.getObject().getId() == 16) {
                                p.getObject().setUsed(false);
                                spaceRunJFrame.DisplayOutputSetText("Troppo bello per essere vero, serve una password numerica da inserire. "
                                    + "Se sei arrivato in questo punto, sicuramente la possiedi già. "
                                    + "Magari potrebbe servire per identificare chi è al comando della navicella... \n");
                                String psw = spaceRunJFrame.PasswordDialog(); 
                                if("10403".equals(psw)){
                                    spaceRunJFrame.DisplayOutputSetText("\n"+psw + " -> Identificazione riuscita, navicella pronta in decollo! \n\n");
                                    String message = end();
                                    spaceRunJFrame.DisplayOutputSetText(message);
                                    spaceRunJFrame.ExitDialog();
                                } else {
                                   spaceRunJFrame.DisplayOutputSetText("\nCodice identificativo non riconosciuto, riprovare! \n");
                                }
                            } else if (p.getObject().getId() == 17) {
                                boolean gun = false;
                                Iterator<AdvObject> invIt = getInventory().iterator();
                                while (invIt.hasNext()) {
                                    AdvObject next = invIt.next();
                                    if (next.getId() == 5){
                                        next.setUsed(true);
                                        gun = true;
                                    }
                                }
                                if (getCurrentRoom().getId() == 10 && gun == true){
                                    getCurrentRoom().getEast().setLocked(false);
                                    spaceRunJFrame.DisplayOutputSetText("Dalla paura spari. "
                                            + "Il primo colpo gli annienta solo un piede. L’alieno si rialza e si fionda con un salto verso di te in volo. "
                                            + "Tu chiudi gli occhi pensando sei spacciato e BAANGG!!! \nSenza manco accorgetene pianti un colpo laser dritto nelle cervella di questo essere."
                                            + " L’autostima ti sale leggermente, ti senti un eroe per ora. Un eroe cosparso di sangue violaceo… Meglio continuare.  \n");
                                } else if (getCurrentRoom().getId() == 2 && gun == true){
                                    spaceRunJFrame.DisplayOutputSetText("Gli alieni sono troppi, come ti è saltato in mente di imitare Rambo. Muori in una sparatoria epica.  \n");
                                    spaceRunJFrame.DisplayOutputSetText("\nAddio!");
                                    spaceRunJFrame.ExitDialog(); 
                                    spaceRunJFrame.enableElements(false);
                                } else {
                                    p.getObject().setUsed(false);
                                    spaceRunJFrame.DisplayOutputSetText("Non possiedi niente per uccidere l'alieno.  \n");
                                }
                            }  else if (p.getObject().getId() == 18){
                                p.getObject().setUsed(false);
                                spaceRunJFrame.DisplayOutputSetText("Finalmente puoi vedere come se la passano gli amici terrestri, quanta nostalgia... \n");
                                String city = spaceRunJFrame.WeatherDialog();
                                city = city.substring(0,1).toUpperCase() + city.substring(1,city.length()).toLowerCase();
                                String space = getPersonInSpace();
                                spaceRunJFrame.DisplayOutputSetText("\nNella città di "+city+" noti "+getWeatherByCity(city)+".\n");  
                                spaceRunJFrame.DisplayOutputSetText("Inoltre noti, tramite il radar del telescopio, che nello spazio ci sono "+space+" terrestri in orbita. "
                                        + "Forse dopo riuscirai a raggiungerli... non scoraggiarti! \n");
                            }   
                        } else {
                            if (p.getObject().getId() == 17){
                                spaceRunJFrame.DisplayOutputSetText("Alieno già morto stecchito.\n");
                            } else {
                                spaceRunJFrame.DisplayOutputSetText("Oggetto già utilizzato. \n");
                            }
                        }
                    } else if (p.getInvObject() != null && p.getInvObject().isUsable()) {
                        if(p.getInvObject().isUsed() == false){
                            spaceRunJFrame.DisplayOutputSetText("Hai usato: " + p.getInvObject().getName() + "\n");                     
                            p.getInvObject().setUsed(true);
                            if(p.getInvObject().getId() == 6) {
                                spaceRunJFrame.DisplayOutputSetText("Adesso puoi vedere le stanze, attento però, potresti illuminare la faccia di qualche guardia! \n");
                                Iterator<Room> roomIt = getRooms().iterator();
                                while (roomIt.hasNext()) {
                                    Room nextRoom = roomIt.next();
                                    nextRoom.setVisible(true);
                                }
                            } else if (p.getInvObject().getId() == 5) {
                                if (getCurrentRoom().getId() == 10){
                                    p.getInvObject().setUsed(true);
                                    Iterator<AdvObject> objectIt = getCurrentRoom().getObjects().iterator();
                                        while (objectIt.hasNext()) {
                                            AdvObject nextObject = objectIt.next();
                                            if (nextObject.getId() == 17){
                                                nextObject.setUsed(true);
                                            }
                                        }
                                    getCurrentRoom().getEast().setLocked(false);
                                    spaceRunJFrame.DisplayOutputSetText("Dalla paura spari. "
                                            + "Il primo colpo gli annienta solo un piede. L’alieno si rialza e si fionda con un salto verso di te in volo. "
                                            + "Tu chiudi gli occhi pensando sei spacciato e BAANGG!!! \nSenza manco accorgetene pianti un colpo laser dritto nelle cervella di questo essere."
                                            + " L’autostima ti sale leggermente, ti senti un eroe per ora. Un eroe cosparso di sangue violaceo… Meglio continuare.  \n");
                                } else if (getCurrentRoom().getId() == 2){
                                    p.getInvObject().setUsed(false);
                                    spaceRunJFrame.DisplayOutputSetText("Gli alieni sono troppi, come ti è saltato in mente di imitare Rambo. Muori in una sparatoria epica.  \n");
                                    spaceRunJFrame.DisplayOutputSetText("\nAddio!");
                                    spaceRunJFrame.ExitDialog(); 
                                    spaceRunJFrame.enableElements(false);
                                }     
                            }
                        } else {
                            if (p.getInvObject().getId() == 5) {
                                spaceRunJFrame.DisplayOutputSetText("Hai già sparato all'alieno guardia. \n");
                            } else {
                                spaceRunJFrame.DisplayOutputSetText("Oggetto già utilizzato. \n");                               
                            }
                        } 
                    } else {
                            spaceRunJFrame.DisplayOutputSetText("Non puoi usare questo oggetto. \n");     
                    }
                }
            } else if (p.getCommand().getType() == CommandType.SAVE) {
                 try {
                    boolean flag_next = false;  //flag per l'esistenza del next
                    boolean exist_flag = false; //flag per l'esistenza di una partita con lo stesso nome
                    
                        Connection con = DriverManager.getConnection("jdbc:h2:" + "./Database/my", "root", "mypassword");
                        Statement stmt = con.createStatement();
                        String spaceRunDB = "CREATE TABLE IF NOT EXISTS SPACERUN_DB "
                            + "(PartitaId VARCHAR(100), "
                            + "currentRoom INT, "
                            + "inventoryId VARCHAR(100),"
                            + "PRIMARY KEY (PartitaId))";
             
                        stmt.executeUpdate(spaceRunDB);
                    
                    Pattern alfabeto = Pattern.compile("^([a-zA-Z0-9]{2,15})$");
                    String input = spaceRunJFrame.SaveDialog(); 
                    Matcher matcher = alfabeto.matcher(input);
                    if(matcher.matches()){
                        spaceRunJFrame.DisplayOutputSetText(input); 
                        ResultSet rs = stmt.executeQuery("SELECT PartitaID FROM SPACERUN_DB "); 
                        while (rs.next()){
                            String partita = rs.getString(1); 
                            if (partita.equals(input)){
                                exist_flag = true;
                            }
                        }
                        if (exist_flag){
                            spaceRunJFrame.DisplayOutputSetText("\nEsiste già una partita salvata con questo nome. Stai sovrascrivendo la vecchia partita...\n");
                            PreparedStatement pst = con.prepareStatement("UPDATE SPACERUN_DB SET currentRoom = ?, inventoryId = ? WHERE PartitaID = '"+input+"'");
                            pst.setInt(1, getCurrentRoom().getId());
                            Iterator<AdvObject> it = getInventory().iterator();
                            String Obj = "";
                            while (it.hasNext()) {
                                flag_next = true;
                                AdvObject next = it.next();
                                Obj = Obj + next.getId() + "#";
                            }
                            if (flag_next) {
                                pst.setString(2, Obj);
                            } else {
                                Obj = null;
                                pst.setString(2, Obj);
                            }
                            pst.executeUpdate();
                            pst.close();
                        } else{
                            PreparedStatement pstm = con.prepareStatement("INSERT INTO SPACERUN_DB VALUES (?,?,?)");
                            pstm.setString(1, input);
                            pstm.setInt(2, getCurrentRoom().getId());
                            Iterator<AdvObject> it = getInventory().iterator();
                            String Obj = "";
                            while (it.hasNext()) {
                                flag_next = true;
                                AdvObject next = it.next();
                                Obj = Obj + next.getId() + "#";
                            }
                            if (flag_next) {
                                pstm.setString(3, Obj);
                            } else {
                                Obj = null;
                                pstm.setString(3, Obj);
                            }
                            pstm.executeUpdate();
                            pstm.close();
                        } 
                        stmt.close();
                        spaceRunJFrame.DisplayOutputSetText("\nSalvataggio effettuato correttamente!\n");
                    } else {
                        spaceRunJFrame.DisplayOutputSetText("Nome non accettato. Reinserire ID salvataggio.\n");
                    }
                } catch (SQLException ex) {
                    spaceRunJFrame.DisplayOutputSetText(ex.getSQLState() + ": " + ex.getMessage());
                    spaceRunJFrame.DisplayOutputSetText("\nSalvataggio non riuscito\n");
                }    
            } else if (p.getCommand().getType() == CommandType.LOAD) {           
                try {
                    boolean exist_flag = false;
                    String[] objInventory;
                    int i;
                    
                    Connection con = DriverManager.getConnection("jdbc:h2:" + "./Database/my", "root", "mypassword");
                    Statement st = con.createStatement();
                    
                    String spaceRunDB = "CREATE TABLE IF NOT EXISTS SPACERUN_DB "
                        + "(PartitaId VARCHAR(100), "
                        + "currentRoom INT, "
                        + "inventoryId VARCHAR(100),"
                        + "PRIMARY KEY (PartitaId))";
                    st.executeUpdate(spaceRunDB);
                    Pattern alfabeto = Pattern.compile("^([a-zA-Z0-9]{2,15})$");
                    String input = spaceRunJFrame.SaveDialog();	
                    Matcher matcher = alfabeto.matcher(input);
                    if(matcher.matches()){
                        spaceRunJFrame.DisplayOutputSetText("\nCaricamento partita:" + input);          
                        ResultSet rs = st.executeQuery("SELECT PartitaID FROM SPACERUN_DB"); 
                        while (rs.next()){
                            String partita = rs.getString(1);
                            if (partita.equals(input)){
                                exist_flag = true;
                            }
                        }
                        if (exist_flag){
                            spaceRunJFrame.DisplayOutputSetText("\nCaricamento in corso... \n");
                            ResultSet rts = st.executeQuery("SELECT * FROM SPACERUN_DB WHERE PartitaID = '"+input+"'");
                            if (rts.next()) {
                                Iterator<Room> rm = getRooms().iterator();
                                while (rm.hasNext()) {
                                    Room next = rm.next();
                                    if (next.getId() == rts.getInt(2)) {
                                        setCurrentRoom(next);
                                        spaceRunJFrame.DisplayOutputSetText("Ora ti trovi in: " + toUpperCase(getCurrentRoom().getName()));
                                        spaceRunJFrame.DisplayOutputSetText("\n==================================================================\n");
                                        spaceRunJFrame.DisplayOutputSetText(getCurrentRoom().getDescription() + "\n");
                                    }  
                                }
                                if (rts.getString(3) != null) {
                                    objInventory = rts.getString(3).split("#");                           
                                    Iterator<AdvObject> invIt = getInventory().iterator();
                                    List<AdvObject> toRemove =new ArrayList<>();
                                    boolean rimuovi = false;
                                    while (invIt.hasNext()) {
                                        AdvObject next = invIt.next();  
                                        rimuovi = false;
                                        for (i = 0; objInventory.length - 1 >= i; i++) {
                                            if (next.getId() == Integer.parseInt(objInventory[i])) {
                                                rimuovi = true;
                                            } 
                                        }
                                        if(rimuovi == false){
                                           toRemove.add(next); 
                                        }                                       
                                    }
                                    getInventory().removeAll(toRemove);                           
                                    Iterator<Room> roomIt = getRooms().iterator();
                                    while (roomIt.hasNext()) {
                                        Room nextRoom = roomIt.next();
                                        Iterator<AdvObject> objectIt = nextRoom.getObjects().iterator();
                                        while (objectIt.hasNext()) {
                                            AdvObject nextObject = objectIt.next();
                                            for (i = 0; objInventory.length - 1 >= i; i++) {
                                                if (Integer.parseInt(objInventory[i]) == nextObject.getId()) {
                                                    getInventory().add(nextObject);
                                                    objectIt.remove();
                                                }
                                            }                                          
                                            if (nextObject instanceof AdvObjectContainer) {
                                                AdvObjectContainer nextObjectContainer = (AdvObjectContainer) nextObject;
                                                if (!nextObjectContainer.getList().isEmpty()) {
                                                    Iterator<AdvObject> containerIt = nextObjectContainer.getList().iterator();
                                                    while (containerIt.hasNext()) {
                                                        AdvObject nextObjContained = containerIt.next();                                                    

                                                        for (i = 0; objInventory.length - 1 >= i; i++) {
                                                            if (nextObjContained.getId() == Integer.parseInt(objInventory[i])) {
                                                                getInventory().add(nextObjContained);
                                                                containerIt.remove();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                Iterator<AdvObject> objInv = getInventory().iterator();
                                while (objInv.hasNext()) {
                                    AdvObject obj1 = objInv.next();
                                    if(obj1.getId() == 11){
                                        Iterator<Room> roomIt = getRooms().iterator();
                                        while (roomIt.hasNext()) {
                                            Room nextRoom = roomIt.next();
                                            if(nextRoom.getId() == 7) {
                                                Iterator<AdvObject> objIt = nextRoom.getObjects().iterator();
                                                while (objIt.hasNext()) {
                                                    AdvObject nextObj = objIt.next();
                                                    nextObj.setUsable(true);
                                                }
                                            }
                                            nextRoom.setLocked(false);
                                            if(nextRoom.getId() == 11){
                                                nextRoom.setLocked(true);
                                            }    
                                        }
                                    }
                                }
                                if(getCurrentRoom().getId() == 11 || getCurrentRoom().getId() == 12){
                                    Iterator<Room> roomIt = getRooms().iterator();
                                    while (roomIt.hasNext()) {
                                        Room nextRoom = roomIt.next();
                                        Iterator<AdvObject> objectIt = nextRoom.getObjects().iterator();
                                        while (objectIt.hasNext()) {
                                            AdvObject nextObject = objectIt.next();
                                            if(nextObject.getId() == 17) {
                                            nextObject.setUsed(true);
                                            }
                                        }
                                        if(nextRoom.getId() == 11) {
                                            nextRoom.setLocked(false);
                                        } else if(nextRoom.getId() == 12){
                                            nextRoom.setLocked(false);
                                        }    
                                    }
                                    Iterator<AdvObject> invIt = getInventory().iterator();
                                    while (invIt.hasNext()) {
                                        AdvObject next = invIt.next();
                                        if (next.getId() == 5){
                                            next.setUsed(true);
                                        }
                                    } 
                                }    
                                spaceRunJFrame.InventoryOutputSetText("      Zainetto");
                                spaceRunJFrame.InventoryOutputAppend("\n-------------------\n");
                                Iterator<AdvObject> it = getInventory().iterator();
                                while (it.hasNext()) {
                                    AdvObject nextObj = it.next();
                                    spaceRunJFrame.InventoryOutputAppend("\n-" + nextObj.getName());
                                }
                                spaceRunJFrame.InventoryOutputAppend("\n");
                            }
                            rs.close();
                            rts.close();
                            st.close();
                            spaceRunJFrame.enableElements(true);
                        } else {
                            spaceRunJFrame.DisplayOutputSetText("\nCodice inesistente: caricamento non riuscito.");
                        }
                    } else {
                        spaceRunJFrame.DisplayOutputSetText("\nNome non accettato. Reinserire ID caricamento.\n");
                    }
                } catch (SQLException ex) {
                   spaceRunJFrame.DisplayOutputSetText(ex.getSQLState() + ": " + ex.getMessage());
                   spaceRunJFrame.DisplayOutputSetText("\nCaricamento non riuscito\n");
                } catch (Exception ex) {
                    Logger.getLogger(SpaceRun.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else if (p.getCommand().getType() == CommandType.END) {
                String message = differentEnd(command);
                spaceRunJFrame.DisplayOutputSetText(message);
                spaceRunJFrame.DisplayOutputSetText("\nAddio!");
                spaceRunJFrame.ExitDialog();
                spaceRunJFrame.enableElements(false);
            }
            if (noroom) {
                 if (p.getCommand().getType() == CommandType.NORD && getCurrentRoom().getNorth() == null && getCurrentRoom().getId() == 2) {
                    spaceRunJFrame.DisplayOutputSetText("Questa stanza sembra sigillata, forse contiene qualcosa di segreto. Ci sarà un'altra via per entrarci... \n");
                 } else if (p.getCommand().getType() == CommandType.WEST && getCurrentRoom().getWest() == null && getCurrentRoom().getId() == 9) {
                    spaceRunJFrame.DisplayOutputSetText("La porta è ormai sommersa da vermi alieni simili a dei lombrichi enormi che non fanno funzionare bene gli ingranaggi. "
                            + "Da uno spiraglio ci sembrano essere delle navicelle dall’altra parte, ci sarà un altro modo per arrivarci...\n ");
                 } else if (p.getCommand().getType() == CommandType.SOUTH && getCurrentRoom().getSouth() == null && getCurrentRoom().getId() == 8) {
                    spaceRunJFrame.DisplayOutputSetText("Porta Magazzino ormai sigillata.\n ");
                 } else if (p.getCommand().getType() == CommandType.EAST && getCurrentRoom().getEast() == null && getCurrentRoom().getId() == 2) {
                    spaceRunJFrame.DisplayOutputSetText("Sei pazzo? Gli alieni ora non osserveranno più i pianeti ma te, diventeresti un morto che cammina. \n");
                 } else if (p.getCommand().getType() == CommandType.EAST && getCurrentRoom().getEast() == null && getCurrentRoom().getId() == 11) {
                    spaceRunJFrame.DisplayOutputSetText("Porta ormai fuori uso con un piccolo spiraglio, forse ti ricorda qualcosa.  \n");
                 } else {
                     spaceRunJFrame.DisplayOutputSetText("Anche se siamo sullo spazio, è ancora impossibile attraversare i muri...\n");
                 }
            } else if (blocked) {
                if (p.getCommand().getType() == CommandType.SOUTH && getCurrentRoom().getSouth().isLocked() == true && getCurrentRoom().getId() == 4) {
                    spaceRunJFrame.DisplayOutputSetText("Ti sbagli, questa stanza è chiusa, e serve una tessera di riconoscimento per entrare. \n");
                } else if (p.getCommand().getType() == CommandType.EAST && getCurrentRoom().getEast().isLocked() == true && getCurrentRoom().getId() == 10) {
                    spaceRunJFrame.DisplayOutputSetText(" Giri la testa e noti subito che non sei solo… "
                            + "C’è una guardia aliena che sorveglia una porta, ferma lì, quasi impassibile, o scappi indietro o l’affronti! "
                            + "Assicurati che tu sappia come affrontare la guardia, non sono molto socievoli!  "
                            + "Sono alte e robuste quasi quanto un furgone; quindi, se stai pensando di lanciargli la torcia contro, meglio correre via.  \n ");
                } else if (p.getCommand().getType() == CommandType.NORD && getCurrentRoom().getNorth().isLocked() == true && getCurrentRoom().getId() == 5) {
                    spaceRunJFrame.DisplayOutputSetText(" Questa stanza è chiusa, e serve una tessera di riconoscimento per entrare. \n ");
                }else {
                    spaceRunJFrame.DisplayOutputSetText("La porta per quella stanza è bloccata. \n");
                }
            } else if (move) {
                spaceRunJFrame.DisplayOutputSetText("Ora ti trovi in: " + toUpperCase(getCurrentRoom().getName()));
                spaceRunJFrame.DisplayOutputSetText("\n==================================================================\n");
                spaceRunJFrame.DisplayOutputSetText(getCurrentRoom().getDescription() + "\n");
                getCurrentRoom().setVisited(true);   
            }
        } 
        if (getCurrentRoom().isVisited() == true && getCurrentRoom().getId() == 1){
            getCurrentRoom().setDescription("Sei nel luungo principale corridoio desolato dell’astronave... Senti dei rumori da est, sembrano suoni terribili, non sai da dove vuoi procedere...");
        } 
    }
    
    /**
    * Il metodo end(), contiene il messaggio di partita terminata correttamente in maniera completa.
    */
    private String end() {
        String message = ("Finalmente la navicella si alza in aria, inizi a guidarla sbattendo da destra a sinistra lungo le pareti del porto spaziale."
                + " Vedi del fumo fuoriuscire da reattore danneggiato, ma ormai ti hanno scoperto tutti non puoi tornare indietro."
                + " Una grande flotta di navicelle armate aliene si innalza cercando di colpirti con i loro cannoni,"
                + " ma involontariamente hai già messo il pilota automatico verso la terra e sparisci in un tunnel supersonico dinanzi ai loro occhi. "
                + "Ripensi a Mike, sai che ti ha aiutato abbastanza, ma non c’era spazio per lui. "
                + "Non sai nemmeno se fosse la scelta giusta da fare, ma solo una cosa è certa… Ci si vede alieni! A mai più! \n");
    return message;
    }
    
    /**
    * Il metodo differentEnd(), contiene il messaggio di partita terminata prematuramente in base al comando inserito
    * per visualizzare vari tipi di messaggio finale
    * @param command è la stringa comando così come è stato inserito dall'utente.
    */    
    public String differentEnd(String command) {
        String message = "";
        switch (command) {
            case "suicidati": case "ammazzati": case "ucciditi":
                message = ("Decidi di correre contro gli alieni per porre fine alle tue sofferenze.\nL'aria dello spazio gioca brutti scherzi. ");
            break;
                
            case "muori":
                message = ("Infarto istantaneo. E' stato rapido ed indolore.");
            break;
  
            case "end": case "fine": case "exit": case "esci":
                message = ("Se l'umanità avesse una speranza, adesso non ce l'ha più...");
            break;
        }
        return message;
    }
}