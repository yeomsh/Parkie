package JavaProject;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Button extends JFrame{
   private JButton elevator, stair, entrance, exit; 
   private JButton arrowUp, arrowDown, arrowRight, arrowLeft;
   private JButton parking, parking_w, parking_d;
   private MapActionListener action;
   public JPanel panel1;
   public JPanel panel2;

   public Button() {
      panel1 = new JPanel(null);
      panel2 = new JPanel(null);
      
      
      action = new MapActionListener();

      elevator = new JButton("����������");
      elevator.setBackground(Color.PINK);
      elevator.setBounds(1300, 130, 150, 50);
      panel1.add(elevator);
      elevator.addActionListener(action);

      stair = new JButton("���(���)");
      stair.setBackground(Color.YELLOW);
      stair.setBounds(1300, 230, 150, 50);
      panel1.add(stair);
      stair.addActionListener(action);

      entrance = new JButton("�Ա�");
      entrance.setBackground(Color.CYAN);
      entrance.setBounds(1300, 330, 150, 50);
      panel1.add(entrance);
      entrance.addActionListener(action);

      exit = new JButton("�ⱸ");
      exit.setBackground(Color.ORANGE);
      exit.setBounds(1300, 430, 150, 50);
      panel1.add(exit);
      exit.addActionListener(action);

      parking = new JButton("�Ϲ� ������");
      parking.setBackground(Color.GREEN);
      parking.setBounds(1300, 530, 150, 50);
      panel1.add(parking);
      parking.addActionListener(action);

      parking_w = new JButton("���� ���� ������");
      parking_w.setBackground(action.LIGHTGREEN);
      parking_w.setBounds(1300, 630, 150, 50);
      panel1.add(parking_w);
      parking_w.addActionListener(action);

      parking_d = new JButton("����� ������");
      parking_d.setBackground(action.DARKGREEN);
      parking_d.setBounds(1300, 730, 150, 50);
      panel1.add(parking_d);
      parking_d.addActionListener(action);

      arrowUp = new JButton("��");
      arrowUp.setBounds(1300, 330, 150, 50);
      panel2.add(arrowUp);
      arrowUp.addActionListener(action);

      arrowDown = new JButton("�Ʒ�");
      arrowDown.setBounds(1300, 430, 150, 50);
      panel2.add(arrowDown);
      arrowDown.addActionListener(action);

      arrowRight = new JButton("������");
      arrowRight.setBounds(1300, 530, 150, 50);
      panel2.add(arrowRight);
      arrowRight.addActionListener(action);

      arrowLeft = new JButton("����");
      arrowLeft.setBounds(1300, 630, 150, 50);
      panel2.add(arrowLeft);
      arrowLeft.addActionListener(action);
   }
   public int getArrowStat(){
      return action.getStat();
   }
   public Color getcolor(){
      return action.getColor();
   }
   public Color Light(){
      return action.LIGHTGREEN;
   }
   public Color DARK(){
      return action.DARKGREEN;
   }
   public void setcolor(Color c){
      action.setColor(c);
   }
}