import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Monopoly
{
   public static ArrayList<Tile> tileList = new ArrayList<Tile>();

   public static JPanel setUpBoard ()
   {
      Color purple = new Color(195,67,238);
      Color lightBlue = new Color(165,225,232);
      Color pink = new Color(230,0,176);
      Color orange = new Color(255,150,31);
      Color red = new Color (255,31,31);
      Color yellow = new Color (255,236,31);
      Color green = new Color(6,125,8);
      Color darkBlue = new Color(6,48,125);

      // Bottom of the board
      tileList.add(new Go());
      Street tile2 = new Street(2,2,10,30,90,160,250,60,50,"MEDDITERRANEAN AVENUE", purple);
      tileList.add(tile2);
      tileList.add(new CardTile("COMMUNITY CHEST",3));
      Street tile4 = new Street(4,4,20,60,180,320,450,60,50,"BALTIC AVENUE", purple);
      tileList.add(tile4);
      tileList.add(new TaxTile(5,"INCOME TAX",TaxTile.TaxType.INCOME));
      tileList.add(new RailRoad(6,"READING RAILROAD"));
      Street tile7 = new Street(7,6,30,90,270,400,550,100,50,"ORIENTAL AVENUE",lightBlue);
      tileList.add(tile7);
      tileList.add(new CardTile("CHANCE",8));
      Street tile9 = new Street(9,6,30,90,270,400,550,100,50,"VERMONT AVENUE",lightBlue);
      tileList.add(tile9);
      Street tile10 = new Street(10,8,40,100,300,450,600,120,50,"CONNECTICUT AVENUE",lightBlue);
      tileList.add(tile10);
      
      // Left side of the board
      tileList.add(new Jail());
      Street tile12 = new Street(12,10,50,150,450,625,750,140,100,"ST. CHARLES PLACE",pink);
      tileList.add(tile12);
      Utility tile13 = new Utility(13,"ELECTRIC COMPANY");
      tileList.add(tile13);
      Street tile14 = new Street(14,10,50,150,450,625,750,140,100,"STATES AVENUE",pink);
      tileList.add(tile14);
      Street tile15 = new Street(15,12,60,180,500,700,900,160,100,"VIRGINIA AVENUE",pink);
      tileList.add(tile15);
      tileList.add(new RailRoad(16,"PENNSYLVANIA RAILROAD"));
      Street tile17 = new Street(17,14,70,200,550,750,950,180,100,"ST. JAMES PLACE",orange);
      tileList.add(tile17);
      tileList.add(new CardTile("COMMUNITY CHEST",18));
      Street tile19 = new Street(19,14,70,200,550,750,950,180,100,"TENNESSEE AVENUE", orange);
      tileList.add(tile19);
      Street tile20 = new Street(20,16,80,220,600,800,1000,200,100,"NEW YORK AVENUE",orange);
      tileList.add(tile20);

      // Top of the board
      tileList.add(new FreeParking());
      Street tile22 = new Street(22,18,90,250,700,875,1050,220,150,"KENTUCKY AVENUE",red);
      tileList.add(tile22);
      tileList.add(new CardTile("CHANCE",23));
      Street tile24 = new Street(24,18,90,250,700,875,1050,220,150,"INDIANA AVENUE",red);
      tileList.add(tile24);
      Street tile25 = new Street(25,20,100,300,750,935,1100,240,150,"ILLINOIS AVENUE",red);
      tileList.add(tile25);
      tileList.add(new RailRoad(26,"B & O RAILROAD"));
      Street tile27 = new Street(27,22,110,330,800,975,1150,260,150,"ATLANTIC AVENUE",yellow);
      tileList.add(tile27);
      Street tile28 = new Street(28,22,110,330,800,975,1150,260,150,"VENTNOR AVENUE",yellow);
      tileList.add(tile28);
      Utility tile29 = new Utility(29,"WATER WORKS");
      tileList.add(tile29);
      Street tile30 = new Street(30,24,120,360,850,1025,1200,280,150,"MARVIN GARDENS",yellow);
      tileList.add(tile30);

      // Right side of board
      tileList.add(new GoToJail());
      Street tile32 = new Street(32,26,130,390,900,1100,1275,300,200,"PACIFIC AVENUE",green);
      tileList.add(tile32);
      Street tile33 = new Street(33,26,130,390,900,1100,1275,300,200,"NORTH CAROLINA AVENUE",green);
      tileList.add(tile33);
      tileList.add(new CardTile("COMMUNITY CHEST",34));
      Street tile35 = new Street(35,28,140,450,1000,1200,1400,320,200,"PENNSYLVANIA AVENUE",green);
      tileList.add(tile35);
      tileList.add(new RailRoad(36,"SHORT LINE"));
      tileList.add(new CardTile("CHANCE",37));
      Street tile38 = new Street(38,35,175,500,1100,1300,1500,350,200,"PARK PLACE",darkBlue);
      tileList.add(tile38);
      tileList.add(new TaxTile(39,"LUXURY TAX",TaxTile.TaxType.LUXURY));
      Street tile40 = new Street(40,50,200,600,1400,1700,2000,400,200,"BOARDWALK",darkBlue);
      tileList.add(tile40);
   
      // After all the Streets have been instantiated, we can group them (tediously)
      
      // Purples
      tile2.setGroup(tile2, tile4);
      tile4.setGroup(tile2, tile4);

      // Light Blues
      tile7.setGroup(tile7, tile9, tile10);
      tile9.setGroup(tile7, tile9, tile10);
      tile10.setGroup(tile7, tile9, tile10);

      // Pinks
      tile12.setGroup(tile12, tile14, tile15);
      tile14.setGroup(tile12, tile14, tile15);
      tile15.setGroup(tile12, tile14, tile15);

      // Oranges
      tile17.setGroup(tile17, tile19, tile20);
      tile19.setGroup(tile17, tile19, tile20);
      tile20.setGroup(tile17, tile19, tile20);

      // Reds
      tile22.setGroup(tile22, tile24, tile25);
      tile24.setGroup(tile22, tile24, tile25);
      tile25.setGroup(tile22, tile24, tile25);

      // Yellows
      tile27.setGroup(tile27, tile28, tile30);
      tile28.setGroup(tile27, tile28, tile30);
      tile30.setGroup(tile27, tile28, tile30);

      // Greens
      tile32.setGroup(tile32, tile33, tile35);
      tile33.setGroup(tile32, tile33, tile35);
      tile35.setGroup(tile32, tile33, tile35);

      // Dark Blues
      tile38.setGroup(tile38, tile40);
      tile40.setGroup(tile38, tile40);

      // Grab the various JPanels instantiated by the Tile instances and
      // group them into other JPanels
      JPanel south = new JPanel();
      south.setLayout(new BoxLayout(south, BoxLayout.LINE_AXIS));
      south.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      south.setPreferredSize(new Dimension(650,100));
      
      // west needs to be loaded backwards, with the largest element loaded first
      JPanel west = new JPanel();
      west.setLayout(new BoxLayout(west, BoxLayout.PAGE_AXIS));
      west.setPreferredSize(new Dimension(100,450));

      JPanel north = new JPanel();
      north.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
      north.setPreferredSize(new Dimension(650,100));

      JPanel east = new JPanel();
      east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
      east.setPreferredSize(new Dimension(100,450));

      // Because we'll be using BorderLayout from the gameboard panel,
      // and because of how regions overlap in BorderLayout, the south
      // and north panels must contain all the corners.

      // This should be "GO" through "JAIL"
      for (int i = 0; i < 11; i++)
      {
         south.add(tileList.get(i).getPanel());
      }

      // ST. CHARLES PLACE through NEW YORK AVENUE 
      for (int i = 19; i >= 11; i--)
       {
          west.add(tileList.get(i).getPanel());
       }

       // FREE PARKING through GO TO JAIL
       for (int i = 20; i < 31; i++)
       {
          north.add(tileList.get(i).getPanel());
       }

       // PACIFIC AVENUE through BOARDWALK
       for (int i = 31; i < 40; i++)
       {
          east.add(tileList.get(i).getPanel());
       }

       ImageIcon logo = new ImageIcon("images/logo.jpg");
       JLabel monoLabel = new JLabel();
       monoLabel.setIcon(logo);

      // Make the game board JPanel and load it with these guys
      JPanel gameBoard = new JPanel(new BorderLayout());

      gameBoard.add(monoLabel, BorderLayout.CENTER);
      gameBoard.add(south, BorderLayout.SOUTH);
      gameBoard.add(west, BorderLayout.WEST);
      gameBoard.add(north, BorderLayout.NORTH);
      gameBoard.add(east, BorderLayout.EAST);

      return gameBoard;
   }


   public static void main(String [] args)
   {
      JFrame gameWindow = new JFrame("Monopoly");
      JPanel gameBoard = setUpBoard();
      gameWindow.add(gameBoard, BorderLayout.CENTER);
      
      gameWindow.setVisible(true);
      gameWindow.setSize(880,650);
      gameWindow.setResizable(false);
      gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Set up a custom input dialog
      String [] options = {"OK"};
      JPanel panel1 = new JPanel();
      JLabel lbl1 = new JLabel("How many people are playing?");
      JTextField txt = new JTextField(10);
      panel1.add(lbl1);
      panel1.add(txt);

      String input = null;
      while (input == null)
      {
         int selectedOption = JOptionPane.showOptionDialog(
                                      null, 
                                      panel1, 
                                      "Get ready to Monopolize", 
                                      JOptionPane.NO_OPTION, 
                                      JOptionPane.QUESTION_MESSAGE, 
                                      null, 
                                      options, 
                                      options[0]
                                    );
            if(selectedOption == 0)
            {
               input = txt.getText();
            } 
      }

      int playerNum = Integer.parseInt(input);

      // Create a CircularLinkedList of Players to be 
      // populated in the following for loop
      CircularLinkedList players = new CircularLinkedList();

      // Set up another custom input dialog
      JPanel panel2 = new JPanel();
      JLabel lbl2 = new JLabel();
      panel2.add(lbl2);
      panel2.add(txt);

      for (int i = 1; i <= playerNum; i++)
      {
         String p = null;
         lbl2.setText("What is player "+i+"'s name?");
         while (p == null)
         {
            int selected = JOptionPane.showOptionDialog(
                                       null, 
                                      panel2, 
                                      "Get ready to Monopolize", 
                                      JOptionPane.NO_OPTION, 
                                      JOptionPane.QUESTION_MESSAGE, 
                                      null, 
                                      options , 
                                      options[0]
                                    );
            if(selected == 0)
            {
               p = txt.getText();
            } 
         }

         // icon stuff
         String [] choices = {"Cannon", "Dog", "Hat", "Horse", "Iron", "Ship", "Thimbal", "Wheelbarrow"};
         String iconChoice = null;
         while (iconChoice == null)
         {
            iconChoice = (String)JOptionPane.showInputDialog(
                                   null,
                                   "Which token does player "+i+" want?",
                                   "Get ready to Monopolize",
                                   //JOptionPane.DEFAULT_OPTION,
                                   JOptionPane.QUESTION_MESSAGE,
                                   null,
                                   choices,
                                   choices[0]
                                );
         }
         ImageIcon icon = new ImageIcon("images/"+iconChoice+".png");

         Player pl = new Player(p, icon);
         
         players.add(pl);
      }

      ControlPanel controlPanel = ControlPanel.getInstance();
      controlPanel.setList(players);

      gameWindow.add(controlPanel, BorderLayout.EAST);

      controlPanel.takeATurn((Player)players.getNodeAt(0).getData());
   }
}