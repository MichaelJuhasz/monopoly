import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ControlPanel extends JPanel implements ActionListener
{
   private JLabel player = new JLabel();
   private JButton roll = new JButton ("Roll Dice");
   private JButton upgrade = new JButton ("Upgrade Property");
   private JButton fine = new JButton ("Pay Fine");
   private JButton end = new JButton ("End Turn");
   private Player player;

   public controlPanel()
   {
      setLayout(new GridBagLayout());
      
      GridBagConstraints labelC = new GridBagConstraints();
      labelC.fill = GridBagConstraints.HORIZONTAL;
      labelC.gridx = 0;
      labelC.gridy = 0;
      labelC.width = 2;
      labelC.ipady = 20;
      add(player, labelC);

      GridBagConstraints rollC = new GridBagConstraints();
      rollC.fill = GridBagConstraints.HORIZONTAL;
      rollC.gridx = 0;
      rollC.gridy = 1;
      rollC.width = 2;
      roll.addActionListener(this);
      add(roll, rollC);

      GridBagConstraints upgradeC = new GridBagConstraints();
      upgradeC.fill = GridBagConstraints.HORIZONTAL;
      upgradeC.gridx = 0;
      upgradeC.gridy = 2;
      upgrade.addActionListener(this);
      add(upgrade, upgradeC);

      GridBagConstraints fineC = new GridBagConstraints();
      fineC.fill = GridBagConstraints.HORIZONTAL;
      fineC.gridx = 1;
      fineC.gridy = 2;
      fine.addActionListener(this);
      add(fine, fineC);

      GridBagConstraints endC = new GridBagConstraints();
      endC.fill = GridBagConstraints.HORIZONTAL;
      endC.gridx = 0;
      endC.gridy = 3;
      endC.width = 2;
      end.addActionListener(this);
      add(end, endC);
   }

   public void takeATurn(Player p)
   {
      player = p;
      label.setText(p.getName());
      player.beginTurn();
   }

   public void actionPerformed(ActionEvent e)
   {
      JButton b = (JButton)e.getSource();
      if (b == roll)
      {
         if (player.getCanRoll()) player.rollDice();
      }

      if (b == upgrade)
      {
         if (player.getCanUpgrade()) player.upgrade();
         else JOptionPane.showMessageDialog(null,"You have no properties to upgrade.","Nothing to upgrade!", JOptionPane.ERROR_MESSAGE);
      }

      if (b == fine)
      {
         if (player.getCanPayFine()) player.getOutOfJail();
         else JOptionPane.showMessageDialog(null,"You're not in jail.","No fines to pay!", JOptionPane.ERROR_MESSAGE);
      }

      if (b == end)
      {
         if (player.getCanRoll()) JOptionPane.showMessageDialog(null,"You haven't finished rolling.","Keep rolling!", JOptionPane.ERROR_MESSAGE);
         else player.endTurn();
      }
   }
}