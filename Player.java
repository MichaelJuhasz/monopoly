
import java.util.*;
import javax.swing.*;
import java.awt.*;

class Player
{
   private boolean myTurn, canRoll, canUpgrade, canPayFine, inJail;
   private int doubles, rollResult;
   private int funds;
   private int nonCashAssets = 0;
   public ArrayList<Property> deeds = new ArrayList<Property>();
   public String name;
   public Tile currentTile;
   private ImageIcon token;
   public boolean firstTurn = true;

   // Each Player begins on GO (the zeroth position in the tileList)
   // and begins with $1500.  The reason for the firstTurn boolean is
   // that Go's landedOn must be called to draw the icons, however, 
   // that method ordinarily calls the Player's payment method.  This 
   // wouldn't be a problem except that payment references the ControlPanel
   // which is instantiated after the Players.  Thus, firstTurn allows
   // Go to draw the icons, but not call the Players' payment method.  
   public Player(String n, ImageIcon icon)
   {
      name = n;
      token = icon;
      currentTile = Monopoly.tileList.get(0);
      funds = 1500;
      currentTile.landedOn(this);
      firstTurn = false;
   } 

   // This method will control the graphics (populating the sidebar with 
   // appropriate buttons, connected to the correct player), and the actions
   // the player can take in his or her turn.
   public void beginTurn()
   {
      myTurn = true;
      canRoll = true;
      if (inJail) canPayFine = true;
      if (!deeds.isEmpty()) canUpgrade = true;
   }

   public boolean endTurn()
   {
      Object [] options = {"End Turn", "Not Yet"};
      int response = JOptionPane.showOptionDialog(
                          null,
                          "Are you sure you want to end your turn?",
                          "End Turn",
                          JOptionPane.YES_NO_OPTION,
                          JOptionPane.PLAIN_MESSAGE,
                          null,
                          options,
                          options[1]
                       );
      if (response == 0)      
      {
         myTurn = false;
         canRoll = false;
         canPayFine = false;
         canUpgrade = false;
         doubles = 0;

         return true;
      }

      else return false;
   }
   
   // This should get called by a button in the sidebar, which 
   // should be visible so long as canRoll is true.
   public void rollDice()
   {
      if(canRoll && !inJail)
      {
         canRoll = false;
         rollResult = Dice.getInstance().roll(this);

         if (!inJail) move(rollResult);
      }
      else if (canRoll && inJail)
      {
         int gotOut = Dice.getInstance().roll(this);
         canRoll = false;
      }
   }

   // Two move methods, one that takes a number, one that takes a 
   // specific destination Tile.
   public void move(int n)
   {

      int space = currentTile.getNumber() + (n - 1);

      if (space >= 40)
      {
         payment(200);
         space = space - 40;  
      } 

      currentTile.leave(this);

      Tile destinationTile = Monopoly.tileList.get(space);

      currentTile = destinationTile;
      destinationTile.landedOn(this);
   }

   private void move(int destination, boolean toJail)
   {
      Tile destinationTile = Monopoly.tileList.get(destination);
      
      // If you pass GO and you're not going to Jail, collect $200
      if (!toJail)
      {
         if (currentTile.getNumber() > destinationTile.getNumber())
         {
            funds += 200;  
         }
      }

      currentTile.leave(this);

      destinationTile.landedOn(this);

      currentTile = destinationTile;
   }

   public void doubleRoll()
   {
      canRoll = true;
      doubles++;

      if (inJail) 
      {
         inJail = false;
         JOptionPane.showMessageDialog(null,"Good job, you got out of jail!", "Got out of Jail", JOptionPane.INFORMATION_MESSAGE);
         canRoll = false;
      }

      if (doubles == 3) goToJail();
   }

   public void goToJail()
   {
      canRoll = false;
      inJail = true;
      move(10, true);
   }

   // Called by button in control panel that is visible when canPayFine is true.
   public void getOutOfJail()
   {
      if (canPayFine)
      {
         Object [] options = {"Pay Fine", "Not this turn"};
         int response = JOptionPane.showOptionDialog(
	                       null,
	                       "Do you want to pay $50 to get out of jail?",
	                       "Get Out of Jail",
	                       JOptionPane.YES_NO_OPTION,
	                       JOptionPane.PLAIN_MESSAGE,
	                       null,
	                       options,
	                       options[0]
	                    );
         if (response == 0)
         {
            if(funds < 50) JOptionPane.showMessageDialog(null,"Not enough funds!  Good luck rolling.","Not enough funds!", JOptionPane.ERROR_MESSAGE);
            else 
            {
               funds -= 50;
               inJail = false;
               canRoll = false;
               currentTile = Monopoly.tileList.get(10);
            }
         }
      }
   }

   // Another method called by a control panel button
   public void upgrade()
   {
      if(canUpgrade)
      {
         Object [] names = new String[deeds.size()];
         for (int i = 0; i < deeds.size(); i++)
         {
            Property prop = deeds.get(i);
            if (prop instanceof Street) names[i] = prop.name;
         }

         String s = (String)JOptionPane.showInputDialog(null, 
                                    "Which property do you want to upgrade?",
                                    "Property Upgrade",
                                    //JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null, 
                                    names,
                                    names[0]
                                 );
         if (s != null) 
         {
            int result = Arrays.asList(names).indexOf(s);       
            Street choice = (Street)deeds.get(result);
            choice.upgradeStreet(this);
         }
      }
   }

// Setter methods: 
   
   public void payment(int cost)
   {
      funds += cost;
      ControlPanel.getInstance().updateFunds();
   }

   public void addAsset(int worth)
   {
      nonCashAssets += worth;
   }

   public void addDeed(Property p)
   {
      deeds.add(p);
   }


// Getter methods:

   public int getFunds()
   {
      return funds;
   }

   // However deeds are to be stored, this method will return that
   // list in a data structure that should include a contains method.
   public ArrayList<Property> getDeeds()
   {
      return deeds;
   }

   public int getRoll()
   {
      return rollResult;
   }

   public int getTotalWorth()
   {
      return funds + nonCashAssets;
   }

   public ImageIcon getIcon()
   {
      return token;
   }

   public boolean getCanRoll()
   {
      return canRoll;
   }

   public boolean getCanUpgrade()
   {
      return canUpgrade;
   }

   public boolean getCanPayFine()
   {
      return canPayFine;
   }

   public String getName()
   {
      return name;
   }
}