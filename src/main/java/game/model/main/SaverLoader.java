package game.model.main;

import game.model.entities.Character;
import game.model.entities.Cleaner;
import game.model.entities.Professor;
import game.model.entities.Student;
import game.model.entities.building.BuildingAI;
import game.model.entities.building.Door;
import game.model.entities.building.Room;
import game.model.entities.items.Item;
import game.model.logging.Suttogo;

import javax.xml.bind.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static game.model.main.GameMain.isGameInitialized;
import static game.model.main.GameMain.perform;

/**Osztály, amely a játék elmentéséért és betöltéséért felelős*/
public class SaverLoader {
    private GameEngine g;
    private BuildingAI bai;

    /**Egy paraméteres konstruktor*/
    public SaverLoader(GameEngine _g){
        g = _g;
        bai = g.getBuilder();
    }

    /**A játékot elmenti a megadott helyre*/
    public boolean saveGame(String name){
        try {
            String path = "src/main/java/game/model/main/games/" + name + ".txt";
            File saved = new File(path);
            try (PrintWriter writer = new PrintWriter(new FileWriter(saved))) {
                //Szobák elmentése
                writer.println("Room-list");
                Map<String, Room> lab = bai.getLabyrinth();
                for (String s: lab.keySet()){
                    writer.println(s);
                }
                int index = bai.getRoomID();
                writer.println("Room-make");
                for (int i=0; i!=index; i++){
                    String roomname = "Room"+i;
                    if (lab.keySet().contains(roomname)){
                        int cap =lab.get(roomname).getCapacity();
                        boolean gassed = lab.get(roomname).getGassed();
                        boolean cursed = lab.get(roomname).getCursed();
                        if (gassed){
                            if(cursed) writer.println("room "+cap+" true true");
                            else writer.println("room "+cap+" true");
                        }else{
                            if(cursed) writer.println("room "+cap+" false true");
                            else writer.println("room "+cap);
                        }
                    }else writer.println("room 0");
                }
                //Szomszédok!!
                writer.println("Neighbours");
                List<Door> doors = new ArrayList<>();
                for (String s: lab.keySet()) {
                    List<Door> doorforroom = lab.get(s).getDoors();
                    for (Door d : doorforroom) {
                        if (!doors.contains(d)) doors.add(d);
                    }
                }
                for (Door d : doors){
                    writer.println(d.create());
                }
                //Takarítók elmentése
                writer.println("Cleaner-list");
                Map<String, Cleaner> cl = g.getCleaners();
                for (String s: cl.keySet()){
                    writer.println(s);
                }
                index = g.getCleanerID();
                writer.println("Cleaner-make");
                for (int i=0; i!=index; i++){
                    String clname = "Cleaner"+i;
                    if (cl.keySet().contains(clname)){
                        boolean par = cl.get(clname).getParalyzed();
                        if (par) writer.println("cleaner true");
                        else writer.println("cleaner");
                    }else writer.println("cleaner");
                }
                //Professzorok elmentése
                writer.println("Prof-list");
                Map<String, Professor> pl = g.getProfessors();
                for (String s: pl.keySet()){
                    writer.println(s);
                }
                index = g.getProfessorID();
                writer.println("Prof-make");
                for (int i=0; i!=index; i++){
                    String plname = "Professor"+i;
                    if (pl.keySet().contains(plname)){
                        boolean par = pl.get(plname).getParalyzed();
                        if (par) writer.println("professor true");
                        else writer.println("professor");
                    }else writer.println("professor");
                }
                //Hallgatók elmentése
                writer.println("Student-list");
                Map<String, Student> sl = g.getStudents();
                for (String s: sl.keySet()){
                    writer.println(s);
                }
                index = g.getStudentID();
                writer.println("Student-make");
                for (int i=0; i!=index; i++){
                    String plname = "Student"+i;
                    if (sl.keySet().contains(plname)){
                        boolean par = sl.get(plname).getParalyzed();
                        if (par) writer.println("student true");
                        else writer.println("student");
                    }else writer.println("student");
                }
                //Karakterek elhelyezése
                writer.println("Char-place");
                Map<String, Character> chars = new HashMap<>();
                chars.putAll(cl);
                chars.putAll(sl);
                chars.putAll(pl);
                for (String s : chars.keySet()){
                    Room r = chars.get(s).getLocation();
                    if (r!=null) {
                        writer.println("roomaddchar " + s + " " + r.getId());
                    }
                }
                //Tárgyak elmentése
                writer.println("Item-list");
                Map<String, Item> il = g.getItems();
                for (String s: il.keySet()){
                    writer.println(s);
                }
                index = g.getItemID();
                writer.println("Item-make");
                for (int i=0; i!=index; i++){
                    for (String s : il.keySet()){
                        if (il.get(s).getNumID().equals(String.valueOf(i))){
                            writer.println(il.get(s).create());
                        }
                    }
                }
                //Tárgyak elhelyezése karaktereknél
                writer.println("Item-to-char");
                for (String s : chars.keySet()){
                    if (chars.get(s).getItems()!=null){
                        for (String item : chars.get(s).getItems().keySet()){
                            writer.println("charadditem " + item + " " + s);
                        }
                    }
                }
                //Tárgyak elhelyezése szobában
                writer.println("Item-to-room");
                for (Room r : lab.values()){
                    List<Item> items = r.getItems();
                    if (items!=null){
                        for (Item i : items){
                            writer.println("roomadditem "+i.getId()+" "+r.getId());
                        }
                    }
                }
                //Current shit - kié a kör
                //Tranzisztor párosítása??
                /*for (String s : il.keySet()){
                    if (il.get(s).create().equals("transistor"){
                        if (!il.get(s).isPairable()){
                            writer.println();
                        }
                    }
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }

            Suttogo.info("Mentes sikeres.");
            return true;
        }catch(Exception e){
            Suttogo.error("Hiba mentes kozben!");
            return false;
        }
    }

    /**A játékot betölti adott elérési útvonalról*/
    public void loadGame(String name){
        try{
            String path = "src/main/java/game/model/main/games/" + name + ".txt";
            BufferedReader reader = null;
            perform("newgame");

            try {
                reader = new BufferedReader(new FileReader(path));
                String line = reader.readLine();
                //Szobák létrehozása
                List<String> roomnames = new ArrayList<>();
                while ((line = reader.readLine()).equals("Room-list")) {
                    line = reader.readLine();
                    roomnames.add(line);
                    Suttogo.note(line);
                }
                while ((line = reader.readLine()).equals("Room-make")){
                    perform(line);
                }
                //Nem szükséges szobák törlése
                Map<String, Room> lab = bai.getLabyrinth();
                List<String> rossz = new ArrayList<>();
                for (String s : lab.keySet()){
                    if (!roomnames.contains(s))
                        rossz.add(s);
                }
                for (String s : rossz){
                    lab.remove(s);
                }
            } catch (IOException e) {
                if (reader == null) Suttogo.error("Nem sikerült a fájlt megnyitni!");
                else Suttogo.error("Hiba a beolvasás közben!");
            }



        }catch(Exception e){
            Suttogo.error("Hiba betoltes kozben!");
            g = null;
        }
    }

}
