package Zuul2;

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Game
{

    private Parser parser;
    private Room currentRoom;
    private boolean gameWon;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {

        createRooms();
        parser = new Parser();
        gameWon = false;
        player = new Player("Jak");

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room clearing, icyForest, icyHill, caveEntrance, cave,
                caveUnderground, desert, desertCave, river, finish;

        // create the rooms
        clearing = new Room("in the middle of a clearing in a forest.");
        icyForest = new Room("in a icy tundra, the ground is frozen solid.");
        icyHill = new Room("on top of an icy hill.");
        caveEntrance = new Room("infront an entrance of a cave. ");
        cave = new Room("in a cave.");
        caveUnderground = new Room("under a cave.");
        desert = new Room("in a desert.");
        desertCave = new Room("in a cave under the sand.");
        river = new Room("next to a large river.");
        finish = new Room("on the finish line.");

        // initialise room exits
        river.setExits("west", clearing);
        river.setExits("east", finish);

        clearing.setExits("north", icyForest);
        clearing.setExits("east", river);
        clearing.setExits("south", desert);
        clearing.setExits("west", caveEntrance);

        icyForest.setExits("north", icyHill);
        icyForest.setExits("south", clearing);

        icyHill.setExits("south", icyForest);

        caveEntrance.setExits("west", cave);
        caveEntrance.setExits("east", clearing);

        cave.setExits("east", caveEntrance);
        cave.setExits("down", caveUnderground);

        caveUnderground.setExits("up", cave);
        caveUnderground.setExits("east", desertCave);

        desert.setExits("north", clearing);
        desert.setExits("down", desertCave);

        //TODO: Change exit depending on desertStat
        desertCave.setExits("up", desert);
        desertCave.setExits("west", caveUnderground);

        
        
        clearing.addItem(new Item("Book", 9));

        
        desert.addItem(new Item ("Sand", 1));

        
        river.addItem(new Item("Water", 1));

        currentRoom = clearing;  // start game at the clearing of the forest
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Prints the current location of the player and his exit options.
     */
    public void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());

        //System.out.println("You are " + currentRoom.getDescription());
        //System.out.print(currentRoom.getExitString());
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Robin!");
        System.out.println("World of Robin is a new, incredibly adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();

        printLocationInfo();


        /*
        if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }
         */
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if (command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
            printHelp();
        }
        else
        {
            if (commandWord.equals("go"))
            {
                goRoom(command);
            }
            else
            {
                if (commandWord.equals("look"))
                {
                    look();
                }
                else
                {
                    if (commandWord.equals("eat"))
                    {
                        eat();

                    }
                    else
                    {
                        if (commandWord.equals("take"))
                        {
                            takeItem(command);

                        }
                        else
                        {
                            if (commandWord.equals("dig"))
                            {
                                dig(command);

                            }
                            else
                            {
                                if (commandWord.equals("quit"))
                                {
                                    wantToQuit = quit(command);
                                }
                            }
                        }
                    }
                }
            }
        }

        return wantToQuit;
    }

    private void eat()
    {
        System.out.println(currentRoom.somethingToEat());
    }

// implementations of user commands:
    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You have no memmory of this place...");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());

        //System.out.println("   go quit help look eat");
    }

    private void look()
    {
        System.out.println(currentRoom.getDetailedLongDescription());
    }

    /**
     * Take items
     */
    private void takeItem(Command command)
    {
        if (command.getCommandWord().equals("take"))
        {
            if (!command.hasSecondWord())
            {
                // if there is no second word, we don't know where to go...
                System.out.println("Take what?");
                return;
            }
            String itemName = command.getSecondWord();
            Item item = player.pickUpItem(itemName);

            if (item == null)
            {
                System.out.println("Can't pick up the item: " + itemName);
            }
            else
            {
                System.out.println("Picked up " + item.getDescription());
            }

        }
    }

    /**
     * Dig into something
     */
    private void dig(Command command)
    {

        if (command.getCommandWord().equals("dig"))
        {
            String area = currentRoom.getDescription();
            int desertStat = currentRoom.getDesertStat();

            if (!command.hasSecondWord())
            {
                // if there is no second word, we don't know where to dig...
                System.out.println("Dig into what?");
                return;

            }
            //String sv = "ground";
            if ((command.getSecondWord().equals("ground"))
                    && (area.contains("desert")))
            {

                if (desertStat < 3)
                {
                    System.out.println("Digging into the sand");
                    currentRoom.addDesertStat();
                }
                else
                {
                    System.out.println("You can't dig further down!");
                }
            }

            if ((command.getSecondWord().equals("ground"))
                    && (area.contains("forest")))
            {
                System.out.println("You dug into the ground, "
                        + "got an infection and died." + "\n");
                gameOver();
            }

            if ((command.getSecondWord().equals("ground"))
                    && (area.contains("tundra")))
            {
                System.out.println("You dug into the cold icy ground, "
                        + "you died of hyperthermia." + "\n");
                gameOver();
            }

            else
            {
                if (!area.contains("desert"))
                {
                    System.out.println("You can't dig here!");
                }
            }
        }
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command)
    {

        if (command.getCommandWord().equals("go"))
        {
            if (!command.hasSecondWord())
            {
                // if there is no second word, we don't know where to go...
                System.out.println("Go where?");
                return;
            }

            String direction = command.getSecondWord();

            // Try to leave current room.
            Room nextRoom = currentRoom.getExit(direction);

            if (nextRoom == null)
            {
                System.out.println("You can't go that way!");
            }
            else
            {
                currentRoom = nextRoom;
                printLocationInfo();
            }
        }
    }

    /**
     * Ends the game
     */
    private void gameOver()
    {
        if (gameWon == true)
        {
            System.out.println("You Won!!!!");
        }
        else
        {
            System.out.println("Game over!!");

        }
        System.exit(0);

    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Quit what?");
            return false;
        }
        else
        {
            return true;  // signal that we want to quit
        }
    }
}
