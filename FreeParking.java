import javax.swing.*;
import java.awt.*;

class FreeParking extends Corner
{
   private CornerPanel parkingPanel;
   public FreeParking()
   {
      super(21,"FREE PARKING");
      parkingPanel = new CornerPanel(this);
   }

   public void updateGraphics()
   {
      parkingPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return parkingPanel;
   }
}