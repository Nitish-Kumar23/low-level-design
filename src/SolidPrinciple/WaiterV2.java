package SolidPrinciple;

/**
 *
 * Follows Interface segregation
 */
public class WaiterV2 implements WaiterInterface{
    @Override
    public void serveCustomer() {
        System.out.println("server customers");
    }

    @Override
    public void takeOrder() {
        System.out.println("take orders");
    }
}
