package JavaProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MapActionListener implements ActionListener {
   private JButton check_color = new JButton();
   private Color now = Color.WHITE;
   public Color LIGHTGREEN = new Color(155, 255, 148);
   public Color DARKGREEN = new Color(0, 183, 0);
   private int arrowStat = 0;

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      JButton b= (JButton)e.getSource();
      
      if (b.getText().equals("����������")) {
         now = Color.PINK;
         check_color.setBackground(now);
      }

      else if (b.getText().equals("�Ϲ� ������")) {
         now = Color.GREEN;
         check_color.setBackground(now);
      } else if (b.getText().equals("���� ���� ������")) {
         now = LIGHTGREEN;
         check_color.setBackground(now);
      } else if (b.getText().equals("����� ������")) {
         now = DARKGREEN;
         check_color.setBackground(now);
      }

      else if (b.getText().equals("�Ա�")) {
         now = Color.CYAN;
         check_color.setBackground(now);
      }

      else if (b.getText().equals("���(���)")) {
         now = Color.YELLOW;
         check_color.setBackground(now);
      }

      else if (b.getText().equals("�ⱸ")) {
         now = Color.ORANGE;
         check_color.setBackground(now);
      }

      else if (b.getText().equals("��")) {
         if (arrowStat == 1)
            arrowStat = 0;
         else
            arrowStat = 1;
      } else if (b.getText().equals("������")) {
         if (arrowStat == 2)
            arrowStat = 0;
         else
            arrowStat = 2;
      } else if (b.getText().equals("�Ʒ�")) {
         if (arrowStat == 3)
            arrowStat = 0;
         else
            arrowStat = 3;
      } else if (b.getText().equals("����")) {
         if (arrowStat == 4)
            arrowStat = 0;
         else
            arrowStat = 4;
      }
   }
   public int getStat(){
      return arrowStat;
   }
   public Color getColor(){
      return now;
   }
   public void setColor(Color c){
      now=c;
   }
}