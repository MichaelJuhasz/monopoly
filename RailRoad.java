import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class RailRoad extends Property
{
   public Player owner;
// public String name;
   private int price;
   private PropertyPanel railRoadPanel;

   public RailRoad(int num, String n)
   {
      super(num, 200, n);
      //name = n;
      //price = 200;
      railRoadPanel = new PropertyPanel(this);
   }

   public void payRent(Player p)
   {
      int toll;
      int rails = 0;
      ArrayList<Property> properties = owner.getDeeds();

      for (int i = 0; i < properties.size(); i++)
      {
         Property prop = properties.get(i);
         if (prop instanceof RailRoad) rails++;
      }

      toll = (int) (25 * Math.pow(2, rails - 1));
      p.payment(-toll);
      owner.payment(toll);
   }

   public void updateGraphics()
   {
      railRoadPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return railRoadPanel;
   }
}