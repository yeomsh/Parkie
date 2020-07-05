package JavaProject;

public class Fee {
      private String location;
      private int fee;
      
      public Fee(String locatoin, int fee) {
         this.location=locatoin;
         this.fee=fee;
      }
      public int getFee() {
         return fee;
      }
      public String getLocation() {
         return location;
      }
      
   }