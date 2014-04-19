class Player
{

   private boolean myTurn, canRoll, canUpgrade, canPayFine, inJail;
   private int doubles, rollResult, funds = 0; nonCashAssets = 0;
   public ArrayList<Property> deeds = new ArrayList<Property>();
   public String name;

   public Player(String n)
   {
      name = n;
      // icon should be set somehow.
   } 

   // This method will control the graphics (populating the sidebar with 
   // apropriate buttons, connected to the correct player), and the actions
   // the player can take in his or her turn.
   public void beginTurn()
   {
      myTurn = true;
      canRoll = true;
      if (inJail) canPayFine = true;
      if (deeds.length > 0) canUpgrade = true;
   }

   public void endTurn()
   {
      // Print a message asking if player is sure.
      myTurn = false;
      canRoll = false;
      canPayFine = false;
      canUpgrade = false;
      doubles = 0;
   }
   
   // This should get called by a button in the sidebar, which 
   // should be visible so long as canRoll is true.
   private void rollDice()
   {
      if(canRoll && !inJail)
      {
         rollResult = Dice.roll(this);
         if (doubles == 0 || doubles >= 3) canRoll = false;
         move(rollResult);
      }
      else if (canRoll && inJail)
      {
         int gotOut = Dice.roll(this);
      }
   }

   // Two move methods, one that takes a number, one that takes a 
   // specific destination Tile.
   public void move(int n)
   {
      // Move n number of spaces forward.
   	  // If Go is passed, funds+= 200;
   }

   private void move(Tile destination, boolean toJail)
   {
      if (!toJail)
      {
         // if Go is between position and destination, funds += 200;
         // Call landedOn method of Tile
      }

      // Draw token on this Tile
   }

   public void doubleRoll()
   {
      doubles++;

      if (inJail) 
      {
         inJail = false;
         JOptionPane.showMessageDialog(null,"Good job, you got out of jail!","Got out of Jail");
      }

      if (doubles == 3) goToJail();
   }

   public void goToJail()
   {
      inJail = true;
      move(/*jail*/, true);
   }

   // Called by button in sidebar that is visible when canPayFine is true.
   private void getOutOfJail()
   {
      if (canPayFine)
      {
         int response = JOptionPane.showOptionDialog(
	                       null,
	                       "Do you want to $50 to get out of jail?",
	                       "Get Out of Jail",
	                       JOptionPane.YES_NO_OPTION,
	                       JOptionPane.PLAIN_MESSAGE,
	                       null,
	                       {"Pay Fine", "Not this turn"},
	                       "Pay Fine"
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

   private void upgrade()
   {
      if(canUpgrade)
      {
         String [] names = new String[deeds.length];
         for (int i = 0; i < deeds.length; i++)
         {
            names[i] = deeds[i].name;
         }

         int result = JOptionPane.showInputDialog(null, 
         	                        "Which property do you want to upgrade?",
         	                        "Property Upgrade",
         	                        JOptionPane.QUESTION_MESSAGE,
         	                        null,
         	                        names,
         	                        names[0]
         	                     );
         deeds[result].upgrade(this);
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
}