import javax.swing.*;
import java.awt.*;

class Corner extends Tile
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
   {}
}