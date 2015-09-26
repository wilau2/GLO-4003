package ca.ulaval.glo4003.b6.housematch.user.domain;

public class Role {

   public static String SELLER = "seller";

   public static String BUYER = "buyer";

   public static String ADMIN = "admin";

   private String role;

   public Role(String role) {
      this.role = role;
   }

   public String getRoles() {
      return role.toString();
   }

   public boolean asSeller() {
      return role.equals(SELLER);
   }

   public boolean asAdmin() {
      return role.equals(ADMIN);
   }

   public boolean asBuyer() {
      return role.equals(BUYER);
   }

}