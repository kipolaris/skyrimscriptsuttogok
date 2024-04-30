package game.model.commands.general;

import game.model.commands.iCommand;

import java.io.*;

public class Help implements iCommand {
    @Override
    public void execute(String[] cmd) {
        String filePath = "help.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "help";
    }
}
