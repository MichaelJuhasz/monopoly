
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

class Player
{
   private boolean myTurn, canRoll, canUpgrade, canPayFine, inJail;
   private int doubles, rollResult;
   private int funds = 0;
   private int nonCashAssets = 0;
   public ArrayList<Property> deeds = new ArrayList<Property>();
   public String name;
   public Tile currentTile = tileList.get(0);
   private ImageIcon token;

   public Player(String n, ImageIcon icon)
   {
      name = n;
      token = icon;
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
         rollResult = Dice.getInstance().roll(this);
         if (doubles == 0 || doubles >= 3) canRoll = false;
         move(rollResult);
      }
      else if (canRoll && inJail)
      {
         int gotOut = Dice.getInstance().roll(this);
      }
   }

   // Two move methods, one that takes a number, one that takes a 
   // specific destination Tile.
   public void move(int n)
   {
      int space = currentTile.getNumber() + n;

      if (space > 40)
      {
         funds += 200;
         space = space - 40;  
      } 

      currentTile.leave(this);

      Tile destinationTile = tileList.get(space);
      destinationTile.landedOn(this);

      currentTile = destinationTile;
   }

   private void move(int destination, boolean toJail)
   {
      if (!toJail)
      {
         // if Go is between position and destination, funds += 200;
         // Call landedOn method of Tile
      }

      currentTile.leave(this);

      Tile destinationTile = tileList.get(destination);
      destinationTile.landedOn(this);

      currentTile = destinationTile;
   }

   public void doubleRoll()
   {
      doubles++;

      if (inJail) 
      {
         inJail = false;
         JOptionPane.showMessageDialog(null,"Good job, you got out of jail!", "Got out of Jail", JOptionPane.INFORMATION_MESSAGE);
      }

      if (doubles == 3) goToJail();
   }

   public void goToJail()
   {
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
            }
         }
      }
   }

   public void payment(int cost)
   {
      funds += cost;
   }

   public void addAsset(int worth)
   {
      nonCashAssets += worth;
   }

   public void addDeed(Property p)
   {
      deeds.add(p);
   }

   // Another method called by a control panel button
   public void upgrade()
   {
      if(canUpgrade)
      {
         Object [] names = new String[deeds.size()];
         for (int i = 0; i < deeds.size(); i++)
         {
            names[i] = deeds.get(i).name;
         }

         Object result = JOptionPane.showInputDialog(null, 
         	                        "Which property do you want to upgrade?",
         	                        "Property Upgrade",
         	                        JOptionPane.QUESTION_MESSAGE,
         	                        null, 
         	                        names,
         	                        names[0]
         	                     );
         if (result instanceof Integer) {        	 
            deeds.get(((Integer) result).intValue()).upgrade(this);
         }
      }
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