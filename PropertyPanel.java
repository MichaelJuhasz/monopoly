import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class PropertyPanel extends JPanel
{
   private String name;
   private int width, height, angle;
   private ArrayList<Player> guests = new ArrayList<Player>();
   
   public PropertyPanel(Tile u)
   {
      name = u.name;
      width = 50;
      height = 100;
      angle = ((u.getNumber()-1) / 10) * 90;
      setPreferredSize(new Dimension(50,100));
   }

   public void update(ArrayList<Player> g)
   {
      guests = g;
      super.repaint();
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
      // Rotate tile accorinding to position on the board (i.e. its number)
      g.rotate(Math.toRadians(angle));

      // Draw white box
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, width, height);

      // Draw border around tile
      g.setColor(Color.BLACK);
      g.setStroke(new BasicStroke(3));
      g.drawRect(0, 0, width, height);

      // Write name of tile
      g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
      g.drawString(name, 0, 20);

      // Draw any player tokens that are sitting on this tile
      if (!guests.isEmpty())
      {
         for (int i = 0; i < guests.size(); i++)
         {
            ImageIcon icon = guests.get(i).getIcon();
            g.drawImage(icon.getImage(), 5, height/4 + (i * 20), null);
         }
      }

      g.rotate(-(Math.toRadians(angle)));
   }

}