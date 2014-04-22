import javax.swing.*;
import java.awt.*;

class RailRoad extends Property
{
   public Player owner;
   public String name;
   private int price;
   private PropertyPanel railRoadPanel;

   public RailRoad(int num, String n)
   {
      super(num, 200, n);
      //name = n;
      //price = 200;
   }

   private void payRent()
   {
      int toll;
      int rails;
      // rails = number of railroads owned by this.owner
      toll = 25 * Math.pow(2, rails - 1);
      p.payment(-toll);
      owner.payment(toll);
   }

   private void updateGraphics()
   {
      railRoadPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return railRoadPanel;
   }
}