package JavaProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class Map extends JFrame implements ActionListener {
   private JButton button[][];
   private int map[][];
   private JButton choose[][];
   private Button btn;
   private JButton confirm, confirm2;
   Container container;
   private JPanel panel3;
   private String name;

   public Map() {
      this("greenjoa");
   }

   public Map(String title) {
      super(title);
      name=title;
      init();
      this.setVisible(true);
      this.setSize(1500, 1000);
   }


   public void init() {
      panel3 = new JPanel(null);
      container = this.getContentPane();
      confirm2 = new JButton("확인");
      confirm2.setBounds(1300, 830, 150, 50);
      
      btn = new Button();
      map = new int[10][15];
      choose = new JButton[10][15];
      button = new JButton[10][15];
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 15; j++) {
            button[i][j] = new JButton("");
            button[i][j].setBackground(Color.WHITE);
            button[i][j].setSize(80, 80);
            button[i][j].setLocation(80 * j + 50, 80 * i + 105);
            btn.panel1.add(button[i][j]);
            button[i][j].addActionListener(this);
         }
      }

      confirm = new JButton("확인");
      confirm.setBounds(1300, 830, 150, 50);
      confirm.setBackground(Color.WHITE);
      btn.panel1.add(confirm);
      btn.panel2.add(confirm2);
      confirm2.addActionListener(this);
      confirm.addActionListener(this);
      container.add(btn.panel1);
   }

   public void MakeMap() {
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 15; j++) {
            Color nowcol = button[i][j].getBackground();
            if (nowcol == Color.WHITE)
               map[i][j] = 0x000;
            if (nowcol == Color.PINK)
               map[i][j] = 0x304;
            else if (nowcol == Color.GREEN)
               map[i][j] = 0x103;
            else if (nowcol == btn.Light())
               map[i][j] = 0x101;
            else if (nowcol == btn.DARK())
               map[i][j] = 0x102;
            else if (nowcol == Color.CYAN)
               map[i][j] = 0x301;
            else if (nowcol == Color.YELLOW)
               map[i][j] = 0x303;
            else if (nowcol == Color.ORANGE)
               map[i][j] = 0x302;
         }
      }
   }

   public void MakePark() {
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 15; j++) {
            Color nowcol = button[i][j].getBackground();
            if (map[i][j] == 0x000)
               nowcol = Color.WHITE;
            if (map[i][j] == 0x304)
               nowcol = Color.PINK;
            else if (map[i][j] == 0x103)
               nowcol = Color.GREEN;
            else if (map[i][j] == 0x101)
               nowcol = btn.Light();
            else if (map[i][j] == 0x102)
               nowcol = btn.DARK();
            else if (map[i][j] == 0x301)
               nowcol = Color.CYAN;
            else if (map[i][j] == 0x303)
               nowcol = Color.YELLOW;
            else if (map[i][j] == 0x302)
               nowcol = Color.ORANGE;

            button[i][j].setBackground(nowcol);
            button[i][j].setSize(80, 80);
            button[i][j].setLocation(80 * j + 50, 80 * i + 105);
            btn.panel2.add(button[i][j]);
         }
      }
      container.add(btn.panel2);
   }



   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == confirm) {
         btn.panel1.hide();
         container.add(btn.panel2);
         
         MakeMap();
         MakePark();
         btn.setcolor(null);
         btn.panel1.hide();
         container.add(btn.panel2);
      } else if (e.getSource() == confirm2) {
         String str = null;
         try
         {
            FileWriter fw = new FileWriter(name+".txt"); 
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < 10; i++) {
               for (int j = 0; j < 15; j++) {
                  str=Integer.toString(map[i][j]);
                  panel3.add(button[i][j]);
                  button[i][j].setEnabled(false);
                  bw.write(str);
                  bw.newLine();
               } // 줄바꿈
            }
            
            bw.close();
            }
            catch (IOException e1)
            {
               System.err.println(e1); // 에러가 있다면 메시지 출력
               System.exit(1);
            }
      try{
         FileWriter fw2 = new FileWriter(name+"car.txt"); 
         BufferedWriter bw2 = new BufferedWriter(fw2);

         for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
               if((map[i][j]/0x1000)==1){
                  str=Integer.toString(10);
               }
               else
                  str=Integer.toString(0);
               bw2.write(str);
               bw2.newLine();
            }
         } // 줄바꿈
         bw2.close();
      }
         catch (IOException e1)
         {
            System.err.println(e1); // 에러가 있다면 메시지 출력
            System.exit(1);
         }


         btn.panel2.hide();
         btn.setcolor(null);
         container.add(panel3);
      } else {
         for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
               if (e.getSource() == button[i][j] && btn.getcolor() != null) {
                  if (button[i][j].getBackground() == Color.WHITE)
                     button[i][j].setBackground(btn.getcolor());
                  else
                     button[i][j].setBackground(Color.WHITE);
               }

               else if (e.getSource() == button[i][j] && btn.getArrowStat() != 0 && (map[i][j] / 0x100 == 1)) {
                  if (btn.getArrowStat() == 1) {
                     button[i][j].setText("↑");
                     map[i][j] = map[i][j] * 0x10 + 1;
                  }

                  else if (btn.getArrowStat() == 2) {
                     button[i][j].setText("→");
                     map[i][j] = map[i][j] * 0x10 + 2;
                  } else if (btn.getArrowStat() == 3) {
                     button[i][j].setText("↓");
                     map[i][j] = map[i][j] * 0x10 + 3;
                  } else if (btn.getArrowStat() == 4) {
                     button[i][j].setText("←");
                     map[i][j] = map[i][j] * 0x10 + 4;
                  }
               }

               else if (e.getSource() == button[i][j] && (map[i][j] / 0x1000 == 1)) {
                  button[i][j].setText("");
                  map[i][j] = map[i][j] / 0x10;
               }

            }

         }
      }
   }
}