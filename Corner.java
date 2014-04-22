import javax.swing.*;
import java.awt.*;

abstract class Corner extends Tile
{
   public String name;
   public int number;
   private ArrayList<Player> guests = new ArrayList<Player>();

   public Corner (int num, String n)
   {
   	number = num;
      name = n;
   } 

   private void landedOn()
   {
      guests.add(p);
      updateGraphics();
   }

   public void leave(Player p)
   {
      guests.remove(p);
      updateGraphics();
   }

   private abstract void updateGraphics()
}