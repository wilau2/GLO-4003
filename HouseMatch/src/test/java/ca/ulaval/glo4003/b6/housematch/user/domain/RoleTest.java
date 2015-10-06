package ca.ulaval.glo4003.b6.housematch.user.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RoleTest {

   @Test
   public void hasAdminShouldReturnTrueOnlyIfRoleIsActuallyAdmin() {
      // Given
      Role unexistingRole = new Role("Not a real role");
      Role admin = new Role("admin");

      // Then
      assertTrue(admin.hasAdmin());
      assertFalse(unexistingRole.hasAdmin());
   }

   @Test
   public void hasBuyerShouldReturnTrueOnlyIfRoleIsActuallyBuyer() {
      // Given
      Role unexistingRole = new Role("Not a real role");
      Role buyer = new Role("buyer");

      // Then
      assertTrue(buyer.hasBuyer());
      assertFalse(unexistingRole.hasBuyer());
   }

   @Test
   public void hasSellerShouldReturnTrueOnlyIfRoleIsActuallySeller() {
      // Given
      Role unexistingRole = new Role("Not a real role");
      Role seller = new Role("seller");

      // Then
      assertTrue(seller.hasSeller());
      assertFalse(unexistingRole.hasSeller());
   }

}
