import javax.swing.JPanel;
import java.util.ArrayList;

abstract class Tile 
{
   public int number;
   public String name;
   public ArrayList<Player> guests = new ArrayList<Player>();
   
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

   public int getNumber()
   {
      return number;
   }

   abstract public JPanel getPanel();

   abstract void updateGraphics();
}