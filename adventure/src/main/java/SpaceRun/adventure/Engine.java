/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceRun.adventure;

import static com.sun.tools.javac.util.StringUtils.toUpperCase;
import SpaceRun.adventure.games.FireHouseGame;
import SpaceRun.adventure.parser.Parser;
import SpaceRun.adventure.parser.ParserOutput;
import SpaceRun.adventure.type.CommandType;
import static SpaceRun.adventure.type.Time.getTime;
import java.io.File;
import static java.lang.System.out;
import java.util.Scanner;
import java.util.Set;

/**
 * ATTENZIONE: l'Engine è molto spartano, in realtà demanda la logica alla
 * classe che implementa GameDescription e si occupa di gestire I/O sul
 * terminale.
 *
 * @author pierpaolo
 */
public class Engine {

    private final GameDescription game;

    private final Parser parser;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
        parser = new Parser(stopwords);
    }

    public void execute() {
        System.out.println("================================");
        System.out.println("* Adventure v. 0.3 - 2021-2022 *");
        System.out.println("================================");
        String time = getTime();
        try{
            out.println(toUpperCase(game.getCurrentRoom().getName()) + "| Ore: " + time);
            System.out.println();
            System.out.println(game.getCurrentRoom().getDescription());
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
                if (p == null || p.getCommand() == null) {
                    System.out.println("Non capisco quello che mi vuoi dire.");
                } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                    game.differentEnd(command);
                    System.out.println("Addio!");
                    break;
                } else {
                    game.nextMove(p, System.out);
                    System.out.println();
                }
            }
        }catch(NullPointerException e) {
            System.out.println(e);}
        }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine engine = new Engine(new FireHouseGame());
        engine.execute();
    }

}
