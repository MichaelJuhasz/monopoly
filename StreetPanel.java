import javax.swing.*;
import java.awt.*;

class StreetPanel extends JPanel
{
   private String name;
   private Color color;
   private Street street;
   private int width, height, angle;

   public StreetPanel(Street s)
   {
      street = s;
      color = s.color;
      name = s.name;
      width = 100;
      height = 30;
      angle = (s.number % 10) * 90;

      // Center the text (maybe) from: http://stackoverflow.com/questions/3213045/centering-text-in-a-jtextarea-or-jtextpane-horizontal-text-alignment
      StyledDocument doc = textPane.getStyledDocument();
      SimpleAttributeSet center = new SimpleAttributeSet();
      StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
      doc.setParagraphAttributes(0, doc.getLength(), center, false);
   }

   public void paintComponent (Graphics g2)
   {
      //super.paintComponent(g2);
      Graphics2D g = (Graphics2D) g2;
      g.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
      
      g.rotate(Math.toRadians(angle));
      g.setColor(color);
      g.fillRectangle(0,0,width,height/4);
      g.setColor(Color.BLACK);
      g.setStroke(new BasicStroke(3));
      g.drawRectangle(0,0,width,height);
      g.drawLine(0,height/4,width,height/4);
      g.drawString(name,width/2,height+20);

}