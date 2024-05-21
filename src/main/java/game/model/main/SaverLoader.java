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

    public List<String> getSavedGames(){
        List<String> names = new ArrayList<>();
        File dir = new File("src/main/java/game/model/main/games");
        File[] subDirs = dir.listFiles(File::isDirectory);
        for (File subDir : subDirs){
            names.add(subDir.getName());
        }
        return names;
    }

    /**A játékot elmenti a megadott helyre*/
    public boolean saveGame(String name){
        try {
            String path = "src/main/java/game/model/main/games/" + name + ".txt";
            File saved = new File(path);
            try (PrintWriter writer = new PrintWriter(new FileWriter(saved))) {
                writer.println("newgame");
                writer.println("random-nogo");
                /**
                 * Szobák elmentése
                 */
                writer.println("Room-list");
                Map<String, Room> lab = bai.getLabyrinth();
                for (String s: lab.keySet()){
                    writer.println(s);
                }
                /**
                 * Szobákat létrehozó commandok
                 */
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
                /**
                 * Szobák összekötése ajtókkal, szomszédok inicializálása
                 */
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
                /**
                 * Takarítók elmentése
                 */
                writer.println("Cleaner-list");
                Map<String, Cleaner> cl = g.getCleaners();
                for (String s: cl.keySet()){
                    writer.println(s);
                }
                index = g.getCleanerID();
                /**
                 * Takarítókat létrehozó commandok
                 */
                writer.println("Cleaner-make");
                for (int i=0; i!=index; i++){
                    String clname = "Cleaner"+i;
                    if (cl.keySet().contains(clname)){
                        boolean par = cl.get(clname).getParalyzed();
                        if (par) writer.println("cleaner true");
                        else writer.println("cleaner");
                    }else writer.println("cleaner");
                }
                /**
                 * Professzorok elmentése
                 */
                writer.println("Prof-list");
                Map<String, Professor> pl = g.getProfessors();
                for (String s: pl.keySet()){
                    writer.println(s);
                }
                index = g.getProfessorID();
                /**
                 * Professzorokat létrehozó commandok
                 */
                writer.println("Prof-make");
                for (int i=0; i!=index; i++){
                    String plname = "Professor"+i;
                    if (pl.keySet().contains(plname)){
                        boolean par = pl.get(plname).getParalyzed();
                        if (par) writer.println("professor true");
                        else writer.println("professor");
                    }else writer.println("professor");
                }
                /**
                 * Hallgatók elmentése
                 */
                writer.println("Student-list");
                Map<String, Student> sl = g.getStudents();
                for (String s: sl.keySet()){
                    writer.println(s);
                }
                index = g.getStudentID();
                /**
                 * Hallgatókat létrehozó commandok
                 */
                writer.println("Student-make");
                for (int i=0; i!=index; i++){
                    String plname = "Student"+i;
                    if (sl.keySet().contains(plname)){
                        boolean par = sl.get(plname).getParalyzed();
                        if (par) writer.println("student true");
                        else writer.println("student");
                    }else writer.println("student");
                }
                /**
                 * Tárgyak elmentése
                 */
                writer.println("Item-list");
                Map<String, Item> il = g.getItems();
                for (String s: il.keySet()){
                    writer.println(s);
                }
                index = g.getItemID();
                /**
                 * Tárgyakat létrehozó commandok
                 */
                boolean is = false;
                writer.println("Item-make");
                for (int i=0; i!=index; i++){
                    for (String s : il.keySet()){
                        if (il.get(s).getNumID().equals(String.valueOf(i))){
                            writer.println(il.get(s).create());
                            is=true;
                        }
                    }
                    /**
                     * Ha ez a szám már megsemmisült, akkor is legyen ilyen számú, max a végén ki lesz törölve
                     */
                    if (!is) writer.println("ffp2");
                }
                /**
                 * Karakterek elhelyezése a szobákba
                 */
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
                /**
                 * Tranzisztorok párosítása
                 */
                writer.println("Transistors");
                Map<String, Item> transistors = new HashMap<>();
                for (Item i: il.values()){
                    if (i.getPair()!=null) {
                        if (!transistors.containsValue(i)&&i.getOwner() !=null){
                            writer.println("charadditem " + i.getId() + " " + i.getOwner().getId());
                            writer.println("charadditem " + i.getPair().getId() + " " + i.getOwner().getId());
                            writer.println("pair "+i.getOwner().getId()+" "+i.getId()+" "+i.getPair().getId());
                            transistors.put(i.getPair().getId(), i.getPair());
                            transistors.put(i.getId(), i);
                        }
                    }
                }
                //Eldobni tranzisztort!!
                /**
                 * Tárgyak elhelyezése karaktereknél
                 */
                writer.println("Item-to-char");
                for (String s : chars.keySet()){
                    if (chars.get(s).getItems()!=null){
                        for (String item : chars.get(s).getItems().keySet()){
                            if (!transistors.containsKey(item)) {
                                writer.println("charadditem " + item + " " + s);
                            }
                        }
                    }
                }
                /**
                 * Tárgyak elhelyezése szobákban
                 */
                writer.println("Item-to-room");
                for (Room r : lab.values()){
                    List<Item> items = r.getItems();
                    if (items!=null){
                        for (Item i : items){
                            if (!transistors.containsKey(i.getId())) writer.println("roomadditem "+i.getId()+" "+r.getId());
                        }
                    }
                }
                /**
                 * Ha el van indítva a játék, akkor ide van elmentve a jelenlegi játékos
                 * A jelenlegi játékos még fennmaradó akciópontjai
                 */
                writer.println("Current character + randomness");
                if (g.getCurrent()!=null){
                    writer.println(g.getCurrent().getId());
                    writer.println(g.getCurrent().getActions());
                } else{
                    writer.println("null");
                    writer.println("null");
                }
                /**
                 * A játék randomságát inicializáló command
                 */
                if (g.getRandom()) writer.println("random-go");
                else writer.println("random-nogo");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Suttogo.info("Mentes sikeres.");
            Suttogo.note("Mentes sikeres.");
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

            try {
                reader = new BufferedReader(new FileReader(path));
                String line=reader.readLine();
                /**
                 * Játék inicializálása a newgame-mel, valamint a randomság kikapcsolásával
                 */
                while (!(line.equals("Room-list"))) {
                    perform(line);
                    line = reader.readLine();
                }
                /**
                 * Játékban résztvevő szobák elmentése
                 */
                List<String> rooms = new ArrayList<>();
                while (!(line.equals("Room-make"))) {
                    rooms.add(line);
                    line=reader.readLine();
                }
                /**
                 * Szobák létrehozása
                 */
                while (!(line.equals("Cleaner-list"))) {
                    perform(line);
                    line = reader.readLine();
                }
                /**
                 * Játékban résztvevő takarítók elmentése
                 */
                List<String> cleaners = new ArrayList<>();
                while (!(line.equals("Cleaner-make"))) {
                    cleaners.add(line);
                    line=reader.readLine();
                }
                /**
                 * Takarítók létrehozása
                 */
                while (!(line.equals("Prof-list"))) {
                    perform(line);
                    line = reader.readLine();
                }
                /**
                 * Játékban résztvevő professzorok elmentése
                 */
                List<String> profs = new ArrayList<>();
                while (!(line.equals("Prof-make"))) {
                    profs.add(line);
                    line=reader.readLine();
                }
                /**
                 * Professzorok létrehozása
                 */
                while (!(line.equals("Student-list"))) {
                    perform(line);
                    line = reader.readLine();
                }
                /**
                 * Játékban résztvevő hallgatók elmentése
                 */
                List<String> students = new ArrayList<>();
                while (!(line.equals("Student-make"))) {
                    students.add(line);
                    line=reader.readLine();
                }
                /**
                 * Hallgatók létrehozása
                 */
                while (!(line.equals("Item-list"))) {
                    perform(line);
                    line = reader.readLine();
                }
                /**
                 * Még játékban levő tárgyak elmentése
                 */
                List<String> items = new ArrayList<>();
                while (!(line.equals("Item-make"))) {
                    items.add(line);
                    line=reader.readLine();
                }
                /**
                 * Tárgyak létrehozása
                 */
                while (!(line.equals("Current character + randomness"))) {
                    perform(line);
                    line = reader.readLine();
                }
                /**
                 * Jelenlegi állás betöltése, ha el volt indítva a játék
                 */
                String curr = reader.readLine();
                String actions = reader.readLine();
                if (!(curr.equals("null"))){
                    g.setCurrent(curr);
                    g.getCurrent().setActions(Integer.parseInt(actions));
                }
                /**
                 * Random beállítása
                 */
                perform(reader.readLine());
                /**
                 * A számozás miatt létre kellhetett hozni plusz karaktereket, hogy a számozás jól sikerüljön
                 * Persze lehet, hogy ezek a karakterek már korábban a játék során megsemmisültek
                 * Ezeket a karaktereket töröljük most az ellenőrzőlisták segítségével
                 */
                /**
                 * Ha bármelyik takarító meghalt volna a játék során
                 */
                List<String> ellenor = new ArrayList<>();
                for (String s: cleaners){
                    if (g.getCleaners().get(s) == null) ellenor.add(s);
                }
                for (String s : ellenor)
                    g.getCleaners().remove(s);
                /**
                 * Ha bármelyik hallgató meghalt volna a játék során
                 */
                ellenor.clear();
                for (String s: students){
                    if (g.getStudents().get(s) == null) ellenor.add(s);
                }
                for (String s : ellenor)
                    g.getStudents().remove(s);
                /**
                 * Ha bármelyik professzor meghalt volna a játék során
                 */
                ellenor.clear();
                for (String s: profs){
                    if (g.getProfessors().get(s) == null) ellenor.add(s);
                }
                for (String s : ellenor)
                    g.getProfessors().remove(s);
                /**
                 * A számozás miatt létre kellhetett hozni plusz tárgyakat, hogy a számozás jól sikerüljön
                 * Persze lehet, hogy ezek a tárgyak már korábban a játék során megsemmisültek
                 * Ezeket a tárgyakat töröljük most az ellenőrzőlisták segítségével
                 */
                ellenor.clear();
                for (String s: items){
                    if (g.getItems().get(s) == null) ellenor.add(s);
                }
                for (String s : ellenor)
                    g.getItems().remove(s);
            } catch (IOException e) {
                if (reader == null) Suttogo.error("Nem sikerült a fájlt megnyitni!");
                else Suttogo.error("Hiba a beolvasás közben!");
            }
            Suttogo.info("Betoltes sikeres.");
            Suttogo.note("Betoltes sikeres.");
        }catch(Exception e){
            Suttogo.error("Hiba betoltes kozben!");
            e.printStackTrace();
            g = null;
        }
    }

}