package JavaProject;

import java.util.ArrayList;

import javax.swing.JButton;

public class Astar {
   public ArrayList<A_Data> openList;
   public ArrayList<A_Data> closedList;
   public int u_x, u_y;
   public int e_x, e_y;
   public int a_index;
   public int last_count;

   public Astar(int u_x, int u_y) {

      U_Map.button[u_x][u_y].setText("¡Ú");
      if (U_Map.map[u_x][u_y] % 0x10 == 1) {
      u_x = u_x + 1;
      u_y = u_y;
   } else if (U_Map.map[u_x][u_y] % 0x10 == 2) {
      u_x = u_x;
      u_y = u_y - 1;
      } else if (U_Map.map[u_x][u_y] % 0x10 == 3) {
      u_x = u_x - 1;
      u_y = u_y;
   } else if (U_Map.map[u_x][u_y] % 0x10 == 4) {
      u_x = u_x;
      u_y = u_y + 1;
   }
      
      this.u_x = u_x;
      this.u_y = u_y;
      a_index = 1;
      last_count = -1;
      openList = new ArrayList<A_Data>();
      closedList = new ArrayList<A_Data>();

      e_x = -1;
      e_y -= 1;

      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 15; j++) {
            if (U_Map.map[i][j] == 0x301) {
               e_x = i;
               e_y = j;
               break;
            }
         }
      }

      openList.add(new A_Data(u_x, u_y, e_x, e_y, a_index++));
   }

   public void closed_push(JButton button[][]) {
      U_Map.button[openList.get(0).x][openList.get(0).y].setText("¡Ú");
      closedList.add(openList.get(0));
      openList.remove(0);
      last_count++;
   }

   public void check_f_direction(int map[][],JButton button[][]) {
      if (closedList.get(last_count).y > 0) {
         if (map[closedList.get(last_count).x][closedList.get(last_count).y - 1] == 0x000
               || (closedList.get(last_count).x) == e_x && (closedList.get(last_count).y - 1) == e_y) {
            openList.add(new A_Data(closedList.get(last_count).x, closedList.get(last_count).y - 1, e_x, e_y,
                  a_index++));
         }
      }
      if (closedList.get(last_count).x > 0) {
         if (map[closedList.get(last_count).x - 1][closedList.get(last_count).y] == 0x000
               || (closedList.get(last_count).x - 1) == e_x && (closedList.get(last_count).y) == e_y) {
            openList.add(new A_Data(closedList.get(last_count).x - 1, closedList.get(last_count).y, e_x, e_y,
                  a_index++));
         }
      }
      
      if (closedList.get(last_count).y < 14) {
         if (map[closedList.get(last_count).x][closedList.get(last_count).y + 1] == 0x000
               || (closedList.get(last_count).x) == e_x && (closedList.get(last_count).y + 1) == e_y) {
            openList.add(new A_Data(closedList.get(last_count).x, closedList.get(last_count).y + 1, e_x, e_y,
                  a_index++));
         }
      }
      
      if (closedList.get(last_count).x < 9) {
         if (map[closedList.get(last_count).x + 1][closedList.get(last_count).y] == 0x000
               || (closedList.get(last_count).x + 1) == e_x && (closedList.get(last_count).y) == e_y) {
            openList.add(new A_Data(closedList.get(last_count).x + 1, closedList.get(last_count).y, e_x, e_y,
                  a_index++));
         }
      }
   }

   public void sort() {
      check_f_direction(U_Map.map,U_Map.button);

      int index = 0;

      A_Data temp;
      for (int i = 0; i < openList.size() - 1; i++) {
         for (int j = i + 1; j < openList.size(); j++) {
            if (openList.get(i).distance > openList.get(j).distance) {
               temp = openList.get(i);
               openList.remove(i);
               openList.add(i, openList.get(j-1));
               openList.remove(j);
               openList.add(j, temp);
            }
         }
      }
   }

   public void findWay() {
      boolean check = true;
   
      while(check)
      {
         closed_push(U_Map.button);
         sort();
         if ((closedList.get(closedList.size() - 1).x) == e_x && (closedList.get(closedList.size() - 1).y) == e_y) {
            check = false;
         }
      }
   }
}