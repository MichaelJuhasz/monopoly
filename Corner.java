import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

abstract class Corner extends Tile
{
   // public String name;
   // public int number;
   // protected ArrayList<Player> guests = new ArrayList<Player>();

   public Corner (int num, String n)
   {
      number = num;
      name = n;
   } 

   public void landedOn(Player p)
   {
	  guests.add(p);
	  updateGraphics();
   }
   
   public void leave(Player p)
   {
	   guests.remove(p);
	   updateGraphics();
   }

   abstract void updateGraphics();
}