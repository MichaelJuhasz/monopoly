import javax.swing.*;
import java.awt.*;

class ParkingPanel extends JPanel
{
   private int width, height;
   private ArrayList<Player> guests = new ArrayList<Player>();
   //private ArrayList<BufferedImage> tokens = new ArrayList<BufferedImage>();

   public ParkingPanel()
   {
      width = 100;
      height = 100;
   }

   public void update(ArrayList<Player> g)
   {
      guests = g;
   }

   public void paintComponent (Graphics g2)
   {
      //super.paintComponent(g2);
      Graphics2D g = (Graphics2D) g2;
      g.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);

      g.rotate(Math.toRadians(180));
      
      // Draw border around tile
      g.setColor(Color.BLACK);
      g.setStroke(new BasicStroke(3));
      g.drawRectangle(0,0,width,height);

      // Write name of tile
      g.drawString("FREE PARKING",width/2,height+20);

      // Draw any player tokens that are sitting on this tile
      if (!guests.isEmpty())
      {
         for (int i = 0; i < guests.size(); i++)
         {
            BufferedImage icon = guests.get(i).getIcon();
            g.drawImage(icon, (5 + (i * houseWidth)), height/2, null);
         }
      }

      // Rotate back to 0
      g.rotate(Math.toRadians(-180));
   }

}