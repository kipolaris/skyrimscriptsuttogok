package game.model.commands.general;

import game.model.commands.iCommand;

import java.io.*;

/**
 * Parancs osztály további parancsok és azok leírásaik megjelenítéséhez
 */
public class Help implements iCommand {
    @Override
    public void execute(String[] cmd) {/**Kiírja a parancsokat és azok rövid leírását*/
        String filePath = "src/main/java/game/model/commands/general/help.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

}
