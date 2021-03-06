package ca.ulaval.glo4003.b6.housematch.domain.user;

public class Role {

   public static final String SELLER = "seller";

   public static final String BUYER = "buyer";

   public static final String ADMIN = "admin";

   private String role;

   public Role(String role) {
      this.role = role;
   }

   public String getRoles() {
      return role.toString();
   }

   boolean hasSeller() {
      return role.equals(SELLER);
   }

   boolean hasAdmin() {
      return role.equals(ADMIN);
   }

   boolean hasBuyer() {
      return role.equals(BUYER);
   }

}
