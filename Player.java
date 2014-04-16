class Player
{
   
   // Two move methods, one that takes a number, one that takes a 
   // specific destination Tile.
   public void move(int n)
   {
      roll = n;
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

   public void double()
   {
      double++;
      if (inJail) inJail = false;
      else
      {
         // pass turn back to player
         if (double == 3) goToJail();
      }
   }

   public void goToJail()
   {
      inJail = true;
      move(/*jail*/, true);
   }

   public void payment(int cost)
   {
      funds += cost;
   }

   public void addAsset(int worth)
   {
      nonCashAssets += worth;
   }

// Getter methods:

   public int getFunds()
   {
      return funds;
   }

   // However deeds are to be stored, this method will return that
   // list in a data structure that should include a contains method.
   public List getDeeds()
   {
      return deeds;
   }

   public int getRoll()
   {
      return roll;
   }

   public int getTotalWorth()
   {
      return funds + nonCashAssets;
   }
}