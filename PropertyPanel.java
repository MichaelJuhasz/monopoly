import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class PropertyPanel extends JPanel
{
   private String name;
   private int width, height, angle;
   private ArrayList<Player> guests = new ArrayList<Player>();
   //private ArrayList<BufferedImage> tokens = new ArrayList<BufferedImage>();

   public PropertyPanel(Utility u)
   {
      name = u.name;
      width = 50;
      height = 100;
      angle = (u.getNumber() / 10) * 90;

      // Center the text (maybe) from: http://stackoverflow.com/questions/3213045/centering-text-in-a-jtextarea-or-jtextpane-horizontal-text-alignment
      StyledDocument doc = textPane.getStyledDocument();
      SimpleAttributeSet center = new SimpleAttributeSet();
      StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
      doc.setParagraphAttributes(0, doc.getLength(), center, false);
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
      
      // Rotate tile accorinding to position on the board (i.e. its number)
      g.rotate(Math.toRadians(angle));

      // Draw border around tile
      g.setColor(Color.BLACK);
      g.setStroke(new BasicStroke(3));
      g.drawRect(0,0,width,height);

      // Write name of tile
      g.drawString(name,width/2,height+20);

      // Draw any player tokens that are sitting on this tile
      if (!guests.isEmpty())
      {
         for (int i = 0; i < guests.size(); i++)
         {
            ImageIcon icon = guests.get(i).getIcon();
            g.drawImage(icon, (5 + (i * houseWidth)), height/2, null);
         }
      }

      g.rotate(-(Math.toRadians(angle)));
   }

}