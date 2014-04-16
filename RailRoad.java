import javax.swing.*;
import java.awt.*;

class RailRoad extends Property
{
   public Player owner;
   private String name;
   private int price;

   public RailRoad(String n)
   {
      name = n;
      price = 200;
   }

   private void payRent()
   {
      int toll;
      int rails;
      // rails = number of railroads owned by this.owner
      toll = 25 * Math.pow(2, rails - 1);
      p.payment(toll * -1);
      owner.payment(toll);
   }