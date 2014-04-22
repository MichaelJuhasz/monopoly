import javax.swing.*;
import java.awt.*;

class FreeParking extends Corner
{
   private ParkingPanel parkingPanel;
   public FreeParking()
   {
      super(21,"FREE PARKING");
      parkingPanel = new ParkingPanel(this);
   }

   public void landedOn()
   {}

   private void updateGraphics()
   {
      parkingPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return parkingPanel;
   }
}