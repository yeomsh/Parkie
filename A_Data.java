package JavaProject;

public class A_Data {
   public int x;
   public int y;
   public int index;
   public double distance;
   
   public A_Data(int x,int y,int u_x, int u_y,int index){
      this.x=x;
      this.y=y;
      this.index=index;
      this.distance=Math.sqrt(Math.pow((x-u_x),2)+Math.pow((y-u_y), 2));
   }
}