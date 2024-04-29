package game.model.main;

import game.*;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws Exception {
            /*Scanner scanner = new Scanner(System.in);
            CamembertTest camembertTest = new CamembertTest();
            GasTest gasTest = new GasTest();
            InventoryTest inventoryTest = new InventoryTest();
            KillTest killTest = new KillTest();
            MergeTest mergeTest = new MergeTest();
            MoveTest moveTest = new MoveTest();
            RagTest ragTest = new RagTest();
            SlideRuleTest slideRuleTest = new SlideRuleTest();
            SplitTest splitTest = new SplitTest();
            TransistorTest transistorTest = new TransistorTest();

            int choice;
            do {
                System.out.println("Choose a test to run:");
                System.out.println("1. Camembert Test");
                System.out.println("2. Gas Test");
                System.out.println("3. Inventory Test");
                System.out.println("4. Kill Test");
                System.out.println("5. Merge Test");
                System.out.println("6. Move Test");
                System.out.println("7. Rag Test");
                System.out.println("8. Slide Rule Test");
                System.out.println("9. Split Test");
                System.out.println("10. Transistor Test");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        camembertTest.initialize();
                        camembertTest.useCamembert();
                        break;
                    case 2:
                        gasTest.initialize();
                        gasTest.MaskDoesntBreak();
                        gasTest.MaskBreaks();
                        gasTest.NoMask();
                        break;
                    case 3:
                        inventoryTest.initialize();
                        inventoryTest.PicksUp();
                        inventoryTest.CantPickUp();
                        inventoryTest.Drops();
                        break;
                    case 4:
                        killTest.initialize();
                        killTest.NoProtection();
                        killTest.CupsDontBreak();
                        killTest.CupsBreak();
                        killTest.TVSZDontBreaks();
                        killTest.TVSZDBreaks();
                        killTest.CupsAndTVSZ();
                        break;
                    case 5:
                        mergeTest.initialize();
                        mergeTest.CapacityOverload();
                        mergeTest.TwoOrdinary();
                        mergeTest.OrdinaryAndGassed();
                        mergeTest.OrdinaryAndCursed();
                        mergeTest.OrdinaryAndGassedCursed();
                        mergeTest.TwoGassed();
                        mergeTest.GassedAndCursed();
                        mergeTest.GassedAndGassedCursed();
                        mergeTest.TwoCursed();
                        mergeTest.CursedAndGassedCursed();
                        mergeTest.TwoGassedCursed();
                        break;
                    case 6:
                        moveTest.initialize();
                        moveTest.WorksBothWays();
                        moveTest.OneWayOnly();
                        moveTest.FullRoom();
                        break;
                    case 7:
                        ragTest.initialize();
                        ragTest.ActivateRag();
                        ragTest.decRagParProf();
                        ragTest.destroyRag();
                        ragTest.parProf();
                        break;
                    case 8:
                        slideRuleTest.initialize();
                        slideRuleTest.slideRuleFound();
                        break;
                    case 9:
                        splitTest.splitNormal();
                        splitTest.splitGassed();
                        splitTest.splitCursed();
                        splitTest.splitGassedCursed();
                        break;
                    case 10:
                        transistorTest.initialize();
                        transistorTest.transistorPair();
                        transistorTest.transistorPlace();
                        transistorTest.transistorUnpair();
                        transistorTest.transistorUnsuccessful();
                        transistorTest.transistorSuccesful();
                        transistorTest.transToGasParalyze();
                        transistorTest.transToGasProt();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 10.");
                        break;
                }
            } while (choice != 0);

            scanner.close();*/
            static Deque<Integer> stack = new ArrayDeque<>();

            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);

                HashMap<String, iCommand> map = new HashMap<>();
                /*map.put("exit", new Exit());
                map.put("list", new List());
                map.put("push", new Push());
                map.put("pop", new Pop());
                map.put("dup", new Dup());
                map.put("read", new Read());
                map.put("write", new Write());
                map.put("+", new Add());
                map.put("-", new Sub());
                map.put("*", new Mult());
                map.put("/", new Div());*/

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] cmd = line.split(" ");
                    Command command = map.get(cmd[0]);
                    if (command != null) {
                        command.execute(cmd);
                    }
                }
            }
        }
    }
