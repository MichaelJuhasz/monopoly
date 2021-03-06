import javax.swing.*;
import java.awt.*;

class Go extends Corner
{
   private CornerPanel goPanel;

   public Go()
   {
      super(1,"GO");
      goPanel = new CornerPanel(this);
   }

   public void landedOn(Player p)
   {
      if (!p.firstTurn) p.payment(200);
      guests.add(p);
      updateGraphics();
   }

   public void updateGraphics()
   {
      goPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return goPanel;
   }
}