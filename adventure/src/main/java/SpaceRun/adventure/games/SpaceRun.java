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
        turn_on.setAlias(new String[]{"attiva"});
        getCommands().add(turn_on);
        Command mike = new Command(CommandType.MIKE, "mike");
        mike.setAlias(new String[]{"MIKE","Mike"});
        getCommands().add(mike);
        
        //Rooms
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
            Room eastCorridor = new Room(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); //4
            eastCorridor.setMikeMessage(setParam[3]);
            eastCorridor.setLook(setParam[4]);          
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
            
            //Maps
            incubators.setNorth(scientificCorridor);
            scientificCorridor.setEast(eastCorridor);
            scientificCorridor.setWest(observatory);
            scientificCorridor.setSouth(incubators);
            observatory.setSouth(warehouse);
            observatory.setEast(scientificCorridor);
            warehouse.setNorth(observatory);
            eastCorridor.setSouth(conferenceRoom);
            eastCorridor.setNorth(dormitory);
            eastCorridor.setWest(scientificCorridor);
            conferenceRoom.setNorth(eastCorridor);
            dormitory.setSouth(eastCorridor);
            dormitory.setWest(controlCabin);
            dormitory.setNorth(controlRoom);
            controlCabin.setWest(eggLair);
            controlCabin.setEast(dormitory);
            eggLair.setNorth(commandersThrone);
            eggLair.setEast(controlCabin);
            commandersThrone.setSouth(eggLair);
            controlRoom.setWest(universalPort);
            controlRoom.setSouth(dormitory);
            universalPort.setEast(controlRoom);

            getRooms().add(incubators);
            getRooms().add(scientificCorridor);
            getRooms().add(observatory);
            getRooms().add(warehouse);
            getRooms().add(eastCorridor);
            getRooms().add(dormitory);
            getRooms().add(conferenceRoom);
            getRooms().add(controlCabin);
            getRooms().add(eggLair);
            getRooms().add(commandersThrone);
            getRooms().add(controlRoom);
            getRooms().add(universalPort);
            getRooms().add(spacecraft);
            
            //Obejcts
            lineObjects = brObjects.readLine(); //0
            setParam = lineObjects.split("#");
            AdvObjectContainer backpack = new AdvObjectContainer(Integer.parseInt(setParam[0]), setParam[1], setParam[2]); 
            backpack.setAlias(new String[]{"zainetto", "zaino", "backpack"});
            backpack.setOpenable(true);
            incubators.getObjects().add(backpack);
            
            lineObjects = brObjects.readLine(); //1
            setParam = lineObjects.split("#");
            AdvObject wristwatch = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);                  
            wristwatch.setAlias(new String[]{"orario", "tempo"});
            backpack.add(wristwatch);
            
            lineObjects = brObjects.readLine(); //2
            setParam = lineObjects.split("#");
            AdvObjectContainer drawer = new AdvObjectContainer(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            drawer.setAlias(new String[]{"tiretto", "robot"});
            drawer.setOpenable(true);
            warehouse.getObjects().add(drawer);
            
            lineObjects = brObjects.readLine(); //3
            setParam = lineObjects.split("#");
            AdvObjectContainer box = new AdvObjectContainer(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            box.setAlias(new String[]{});
            box.setOpenable(true);
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
            box.add(laserGun);  
            
            lineObjects = brObjects.readLine(); //6
            setParam = lineObjects.split("#");
            AdvObject torch = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            torch.setAlias(new String[]{"torcia", "luce"});
            box.add(torch);
            
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
            pen.setAlias(new String[]{});
            pen.setPickupable(false);
            box.add(pen);
            
            lineObjects = brObjects.readLine(); //10
            setParam = lineObjects.split("#");
            AdvObjectContainer uniforms = new AdvObjectContainer(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            uniforms.setAlias(new String[]{"vestiti","panni","mucchio di robe"});
            uniforms.setOpenable(true);
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
            camcorders.setAlias(new String[]{"videocamere"});
            camcorders.setPickupable(false);
            controlCabin.getObjects().add(camcorders);
            
            lineObjects = brObjects.readLine(); //14
            setParam = lineObjects.split("#");
            AdvObject videoClips = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            videoClips.setAlias(new String[]{"registrazioni","video"});
            videoClips.setPickupable(false);
            controlCabin.getObjects().add(videoClips);
            
            lineObjects = brObjects.readLine(); //15
            setParam = lineObjects.split("#");
            AdvObject maps = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            maps.setAlias(new String[]{"piantina","mappa navicella","mappa astronave"});
            maps.setPickupable(false);
            controlCabin.getObjects().add(maps);
            
            lineObjects = brObjects.readLine(); //16
            setParam = lineObjects.split("#");
            AdvObject redButton = new AdvObject(Integer.parseInt(setParam[0]), setParam[1], setParam[2]);
            redButton.setAlias(new String[]{"piantina","mappa navicella","mappa astronave"});
            redButton.setPickupable(false);
            redButton.setPushable(true);
            spacecraft.getObjects().add(redButton); 
                       
            //Set starting room
            setCurrentRoom(incubators);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
}

    @Override
    public void nextMove(ParserOutput p, SpaceRunJFrame spaceRunJFrame) {
        if (p.getCommand() == null) {
            spaceRunJFrame.DisplayOutputSetText("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            boolean blocked = false;
            if (p.getCommand().getType() == CommandType.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    if (getCurrentRoom().getNorth().isLocked() == false) {
                        setCurrentRoom(getCurrentRoom().getNorth());
                        move = true;
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
                    if (p.getObject().isOpenable() == true && p.getObject().isOpen() == false && (p.getObject().getName().contains("cassetto") || p.getObject().getName().contains("compartimento"))) {
                        spaceRunJFrame.DisplayOutputSetText(p.getObject().getName() + " è chiuso");
                    } else {
                        spaceRunJFrame.DisplayOutputSetText(p.getObject().getDescription());
                    }
                } else if (p.getInvObject() != null) {
                    spaceRunJFrame.DisplayOutputSetText(p.getInvObject().getDescription());
                } else if (getCurrentRoom().getLook() != null) {
                    spaceRunJFrame.DisplayOutputSetText(getCurrentRoom().getLook());
                } else {
                    spaceRunJFrame.DisplayOutputSetText("Non c'è niente di interessante qui.");
                }
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        spaceRunJFrame.DisplayOutputSetText("Hai raccolto: " + p.getObject().getDescription());
                    } else {
                        spaceRunJFrame.DisplayOutputSetText("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    spaceRunJFrame.DisplayOutputSetText("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                * Potrebbe non essere la soluzione ottimale.
                 */
                if (p.getObject() == null && p.getInvObject() == null) {
                    spaceRunJFrame.DisplayOutputSetText("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                spaceRunJFrame.DisplayOutputSetText("Hai aperto: " + p.getObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    spaceRunJFrame.DisplayOutputSetText(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getCurrentRoom().getObjects().add(next);
                                        spaceRunJFrame.DisplayOutputSetText(" " + next.getName());
                                        it.remove();
                                    }
                                    spaceRunJFrame.DisplayOutputSetText("");
                                }
                            } else {
                                spaceRunJFrame.DisplayOutputSetText("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                        } else {
                            spaceRunJFrame.DisplayOutputSetText("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof AdvObjectContainer) {
                                AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    spaceRunJFrame.DisplayOutputSetText(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getInventory().add(next);
                                        spaceRunJFrame.DisplayOutputSetText(" " + next.getName());
                                        it.remove();
                                    }
                                    spaceRunJFrame.DisplayOutputSetText("");
                                }
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                            spaceRunJFrame.DisplayOutputSetText("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                        } else {
                            spaceRunJFrame.DisplayOutputSetText("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } else if (p.getCommand().getType() == CommandType.PUSH) {
                //ricerca oggetti pushabili
                if (p.getObject() != null && p.getObject().isPushable()) {
                    spaceRunJFrame.DisplayOutputSetText("Hai premuto: " + p.getObject().getName());
                    if (p.getObject().getId() == 3) {
                        String message = end();
                        spaceRunJFrame.DisplayOutputSetText("\nAddio!");
                    }
                } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                    spaceRunJFrame.DisplayOutputSetText("Hai premuto: " + p.getInvObject().getName());
                    if (p.getInvObject().getId() == 3) {
                        String message = end();
                        spaceRunJFrame.DisplayOutputSetText("\nAddio!");
                    }
                } else {
                    spaceRunJFrame.DisplayOutputSetText("Non ci sono oggetti che puoi premere qui.");
                }
            }  else if (p.getCommand().getType() == CommandType.END) {
                String message = differentEnd(p.getCommand().getName());
                spaceRunJFrame.DisplayOutputSetText(message);
                spaceRunJFrame.DisplayOutputSetText("\nAddio!"); //inserire menu di uscita, salvataggio, resart partita
            }
            if (noroom) {
                 if (p.getCommand().getType() == CommandType.NORD && getCurrentRoom().getNorth() == null && getCurrentRoom().getId() == 2) {
                    spaceRunJFrame.DisplayOutputSetText("Questa stanza sembra sigillata, forse contiene qualcosa di segreto. Ci sarà un'altra via per entrarci... \n");
                 } else if (p.getCommand().getType() == CommandType.EAST && getCurrentRoom().getEast() == null && getCurrentRoom().getId() == 9) {
                    spaceRunJFrame.DisplayOutputSetText("La porta è ormai sommersa da vermi alieni simili a dei lombrichi enormi che non fanno funzionare bene gli ingranaggi. "
                            + "Da uno spiraglio ci sembrano essere delle navicelle dall’altra parte, ci sarà un altro modo per arrivarci...\n ");
                 } else if (p.getCommand().getType() == CommandType.SOUTH && getCurrentRoom().getSouth() == null && getCurrentRoom().getId() == 8) {
                    spaceRunJFrame.DisplayOutputSetText("Porta Magazzino ormai sigillata.\n ");
                 } else if (p.getCommand().getType() == CommandType.WEST && getCurrentRoom().getWest() == null && getCurrentRoom().getId() == 2) {
                    spaceRunJFrame.DisplayOutputSetText("Sei pazzo? Gli alieni ora non osserveranno più i pianeti ma te, diventeresti un morto che cammina. \n");
                 } else {
                     spaceRunJFrame.DisplayOutputSetText("Anche se siamo sullo spazio, è ancora impossibile attraversare i muri...\n");
                 }
            } else if (blocked) {
                if (p.getCommand().getType() == CommandType.SOUTH && getCurrentRoom().getSouth().isLocked() == true && getCurrentRoom().getId() == 4) {
                    spaceRunJFrame.DisplayOutputSetText("Ti sbagliavi, questa stanza è chiusa, e serve una tessera di riconoscimento per entrare. \n");
                } else if (p.getCommand().getType() == CommandType.WEST && getCurrentRoom().getWest().isLocked() == true && getCurrentRoom().getId() == 10) {
                    spaceRunJFrame.DisplayOutputSetText(" Giri la testa e noti subito che non sei solo… C’è una guardia aliena che sorveglia una porta. Assicurati che tu sappia come affrontare la guardia, non sono molto socievoli! \n ");
                } else if (p.getCommand().getType() == CommandType.NORD && getCurrentRoom().getNorth().isLocked() == true && getCurrentRoom().getId() == 5) {
                    spaceRunJFrame.DisplayOutputSetText(" Questa stanza è chiusa, e serve una tessera di riconoscimento per entrare. \n ");
                }else {
                    spaceRunJFrame.DisplayOutputSetText("La porta per quella stanza è bloccata. \n");
                }
            } else if (move) {
                    spaceRunJFrame.DisplayOutputSetText("Ora ti trovi in: " + toUpperCase(getCurrentRoom().getName()));
                    spaceRunJFrame.DisplayOutputSetText("\n==================================================================\n");
                    spaceRunJFrame.DisplayOutputSetText(getCurrentRoom().getDescription() + "\n");    
            }
        }
    }

    private String end() {
        String message = ("Finalmente la navicella si alza in aria, inizi a guidarla sbattendo da destra a sinistra lungo le pareti del porto spaziale."
                + " Vedi del fumo fuoriuscire da reattore danneggiato, ma ormai ti hanno scoperto tutti non puoi tornare indietro."
                + " Una grande flotta di navicelle armate aliene si innalza cercando di colpirti con i loro cannoni, ma involontariamente hai già messo il pilota automatico verso la terra e sparisci in un tunnel supersonico dinanzi ai loro occhi. "
                + "Ripensi a Mike, sai che ti ha aiutato abbastanza, ma non c’era spazio per lui. "
                + "Non sai nemmeno se fosse la scelta giusta da fare, ma solo una cosa è certa… Ci si vede alieni! A mai più!");
    return message;
    }
    
        public String differentEnd (String command) {
            String message = "";
            switch (command) {
                case "suicidati": case "ammazzati": case "ucciditi":
                    message = ("Decidi di correre contro gli alieni per porre fine alle tue sofferenze.\n L'aria dello spazio gioca brutti scherzi. ");
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
