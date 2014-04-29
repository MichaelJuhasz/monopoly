
import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 *  Player objects serve as models/controllers for the states and behaviors 
 *  most obvious associated with the entities playing Monopoly.
 *  
 * @author:  Michael Juhasz
 * @version: Last modified 4/28/14
 */

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
   
   /**
    * Each Player begins on GO (the zeroth position in the tileList)
    * and begins with $1500.  The reason for the firstTurn boolean is
    * that Go's landedOn must be called to draw the icons, however, 
    * that method ordinarily calls the Player's payment method.  This 
    * wouldn't be a problem except that payment references the ControlPanel
    * which is instantiated after the Players.  Thus, firstTurn allows
    * Go to draw the icons, but not call the Players' payment method. 
    *
    * @param  n    a String used as the Player's name 
    * @param  icon an ImageIcon to graphically identify the Player 
    */
   public Player(String n, ImageIcon icon)
   {
      name = n;
      token = icon;
      currentTile = Monopoly.tileList.get(0);
      funds = 1500;
      currentTile.landedOn(this);
      firstTurn = false;
   } 
 
   /**
    * This method will control the graphics (populating the sidebar with 
    * appropriate buttons, connected to the correct player), and the actions
    * the player can take in his or her turn.
    */
   public void beginTurn()
   {
      myTurn = true;
      canRoll = true;
      if (inJail) canPayFine = true;
      if (!deeds.isEmpty()) canUpgrade = true;
   }

   /** 
    *  Activated by a button in the ControlPanel once canRoll is false,
    *  endTurn asks the player if they're sure they want to end the turn,
    *  then resets boolean, turn-related states to false, and doubles to 0.
    *  
    * @return   a boolean used by the ControlPanel to determine if the next
    *           player's turn should start
    */
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
   
   /**
    *  rollDice is also called from the ControlPanel, and in turn 
    *  calls on a method in the Dice class.  If the Player is inJail,
    *  the roll is used to determine whether he gets out.  Otherwise
    *  the value of the roll is passed to move.
    */
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

   /**
    *  This move method is called by rollDice, which passes it
    *  an argument that serves to increment the Player's virtual
    *  position on the tileList, leaving the old Tile and landing
    *  on the new one.
    *
    *  @param n  the result of the dice roll, used for the length of the move
    */
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

   /**
    *  This move method is called not by a dice roll, but when the
    *  Player is sent to a specific Tile.  The actions are otherwise
    *  much the same as the above method, but a specific destination
    *  is passed.
    *
    *  @param destination  the position on the tileList of the destination Tile
    *  @param toJail       true if the Player is on his way to Jail and 
    *                      not collecting $200 for passing Go
    */
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

   /**
    *  Called by Dice.roll, doubleRoll increments double, reseting canRoll
    *  and watching for three consecutive double rolls, which sends the 
    *  Player to Jail (for speeding).
    */
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

   /**
    *  A helper method that gets called by doubleRoll and the GoToJail Tile's
    *  landedOn.  As of yet, the only method to call the secondary move.
    */
   public void goToJail()
   {
      canRoll = false;
      inJail = true;
      move(10, true);
   }

   /**
    *  Another method called from ControlPanel, if the Player is currently
    *  inJail.  Offers to let the player pay $50 to get out of Jail.
    *  Prevents Player from rolling or moving afterwards.
    */
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

   /**
    *  The last of the ControlPanel methods, upgrade creates a readable 
    *  list of Streets in the Player's deeds ArrayList and allows the
    *  player to select one to upgrade. If a valid selection, that 
    *  Tile's upgradeStreet method is called.
    */
   public void upgrade()
   {
      if(canUpgrade)
      {
         ArrayList<Object> deedNames = new ArrayList<Object>();
         for (int i = 0; i < deeds.size(); i++)
         {
            Property prop = deeds.get(i);
            if (prop instanceof Street) deedNames.add(prop.name);
         }

         String[] names = deedNames.toArray(new String[deedNames.size()]); 

         String s = (String)JOptionPane.showInputDialog(null, 
                                    "Which property do you want to upgrade?",
                                    "Property Upgrade",
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

   /**
    *  Increments of decrements funds; calls ControlPanel's updateFunds
    */
   
   public void payment(int cost)
   {
      funds += cost;
      ControlPanel.getInstance().updateFunds();
   }

   /**
    *  Adds the value of purchased assets to Player's running total
    */
   public void addAsset(int worth)
   {
      nonCashAssets += worth;
   }

   /**
    *  Adds a purchased Property to the Player's deeds list.
    */
   public void addDeed(Property p)
   {
      deeds.add(p);
   }

   /**
    *  @return funds  used by Properties and ControlPanel
    */

   public int getFunds()
   {
      return funds;
   }

   /**
    *  @return deeds  used by Properties
    */
   public ArrayList<Property> getDeeds()
   {
      return deeds;
   }

   /**
    *  @return rollResult  used by Utilities to determine fee
    */
   public int getRoll()
   {
      return rollResult;
   }

   /**
    *  @return    used by INCOME TAX to determine 10% of total worth
    */
   public int getTotalWorth()
   {
      return funds + nonCashAssets;
   }

   /**
    *  @return token  used by Tiles (ultimately Panels) to draw tokens
    */
   public ImageIcon getIcon()
   {
      return token;
   }

   /**
    *  @return canRoll  used by ControlPanel to enable dice roll button
    */
   public boolean getCanRoll()
   {
      return canRoll;
   }

   /**
    *  @return canUpgrade  used by ControlPanel to enable upgrade button
    */
   public boolean getCanUpgrade()
   {
      return canUpgrade;
   }

   /**
    *  @return canPayFine  used by ControlPanel to enable pay fine button
    */
   public boolean getCanPayFine()
   {
      return canPayFine;
   }

   /**
    *  @return name  used by ControlPanel to print name
    */
   public String getName()
   {
      return name;
   }
}