import javax.swing.JPanel;
import java.util.ArrayList;

abstract class Tile 
{
   public int number;
   public String name;
   public ArrayList<Player> guests = new ArrayList<Player>();
   
   abstract void landedOn(Player p);

   abstract public void leave(Player p);

   public int getNumber()
   {
      return number;
   }

   abstract public JPanel getPanel();
}