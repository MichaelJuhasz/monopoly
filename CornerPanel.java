import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class CornerPanel extends JPanel
{
   private int width, height, angle;
   public String name;
   private ArrayList<Player> guests = new ArrayList<Player>();
   //private ArrayList<BufferedImage> tokens = new ArrayList<BufferedImage>();

   public CornerPanel(Corner c)
   {
      width = 100;
      height = 100;
      name = c.name;
      angle = ((c.getNumber()-1) / 10) * 90;

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

      if (angle == 90) g.translate(height, 0);
      else if (angle == 180) g.translate(width, height);
      else if (angle == 270) g.translate(0, width);
      g.rotate(Math.toRadians(angle));

      // Draw border around tile
      g.setColor(Color.BLACK);
      g.setStroke(new BasicStroke(3));
      g.drawRect(0,0,width,height);

      // Write name of tile
      g.drawString(name, width/2, height/4+20);

      // Draw any player tokens that are sitting on this tile
      if (!guests.isEmpty())
      {
         for (int i = 0; i < guests.size(); i++)
         {
            ImageIcon icon = guests.get(i).getIcon();
 // g.drawImage(icon, (5 + (i * houseWidth)), height/2, null);
            g.drawImage(icon.getImage(), (5 + (i * icon.getIconWidth())), height/2, null);
         }
      }

      g.rotate(-(Math.toRadians(angle)));
   }

}