package game.main;

import game.model.logging.Suttogo;
import game.model.main.GameMain;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static game.model.main.GameMain.isGameInitialized;
import static game.model.main.GameMain.perform;

/**
 * Osztály, ami futtatja az összes tesztet és a kimenetüket ellenőrzi is
 */
public class RunTests {
    public static File currentDir = null;

    public static void main(String[] args) {
        GameMain.setAreWeTesting(true);
        GameMain.addAllCommands();
        try {
            File dir = new File("src/hobarts_testing/resources");
            File[] subDirs = dir.listFiles(File::isDirectory);
            int failedTests = 0;
            List<String> discrepancies = new ArrayList<>();

            for (File subDir : subDirs) {
                currentDir = subDir;
                System.out.print("*******~~~~~~~~~~~~~~********~~~~~~~~~~~~~~**********\n"+
                        "---------------------"+subDir.getName()+"------------------------\n"+
                "*******~~~~~~~~~~~~~~********~~~~~~~~~~~~~~**********\n");
                File[] inputFiles = subDir.listFiles((d, name) -> name.endsWith("_in.txt"));

                if (inputFiles != null) {
                    for (File inputFile : inputFiles) {
                        String outputFileName = subDir.getName() + "_actual.txt";
                        File outputFile = new File(subDir, outputFileName);
                        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

                            String line;
                            while ((line = reader.readLine()) != null) {
                                Suttogo.getSuttogo().note("**** "+line+" ****");
                                perform(line);
                                if(isGameInitialized) GameMain.printOut();
                                writer.println(GameMain.lastOutput); // Assuming this method fetches last output
                                GameMain.lastOutput = "";
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Comparison with expected output
                        File expectedOutputFile = new File(subDir, inputFile.getName().replace("_in.txt", "_out.txt"));
                        if (expectedOutputFile.exists()) {
                            List<String> actualLines = Files.readAllLines(outputFile.toPath());
                            List<String> expectedLines = Files.readAllLines(expectedOutputFile.toPath());

                            for (int i = 0; i < Math.min(actualLines.size(), expectedLines.size()); i++) {
                                if (!actualLines.get(i).equals(expectedLines.get(i))) {
                                    discrepancies.add("Mismatch ----- in: " + subDir.getName() + " at line "+i+": expected:" + expectedLines.get(i) + " -------   actual: " + actualLines.get(i) + "");
                                }
                            }
                            if (!discrepancies.isEmpty()) {
                                failedTests++;
                            }
                        }
                    }
                }
            }

            System.out.println("Total failed tests: " + failedTests + " out of " + subDirs.length);
            discrepancies.forEach(System.out::println);
            GameMain.setAreWeTesting(true);

        } catch (Exception e) {
            e.printStackTrace();
            GameMain.setAreWeTesting(true);
        }
    }
}
