import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class CardTile extends Tile
{
   private PropertyPanel cardPanel;
   // public String name;
   // public int number;
   private ArrayList<Player> guests = new ArrayList<Player>();

   public CardTile(String n, int num)
   {
      name = n;
      number = num;   
      cardPanel = new PropertyPanel(this);
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

   private void updateGraphics()
   {
      cardPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return cardPanel;
   }
}