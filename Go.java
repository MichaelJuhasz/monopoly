import javax.swing.*;
import java.awt.*;

class Go extends Corner
{
   private GoPanel goPanel;

   public Go()
   {
      super(1,"GO");
      goPanel = new GoPanel(this);
   }

   private void landedOn(Player p)
   {
      p.payment(200);
      guests.add(p);
      updateGraphics();
   }

   private void updateGraphics()
   {
      goPanel.update(guests);
   }
}