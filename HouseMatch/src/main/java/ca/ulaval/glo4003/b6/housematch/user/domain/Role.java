package ca.ulaval.glo4003.b6.housematch.user.domain;

public class Role {

   public final static String SELLER = "seller";

   public final static String BUYER = "buyer";

   public final static String ADMIN = "admin";

   private String role;

   public Role(String role) {
      this.role = role;
   }

   public String getRoles() {
      return role.toString();
   }

   public boolean hasSeller() {
      return role.equals(SELLER);
   }

   public boolean hasAdmin() {
      return role.equals(ADMIN);
   }

   public boolean hasBuyer() {
      return role.equals(BUYER);
   }

}
