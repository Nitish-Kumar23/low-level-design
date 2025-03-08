package SolidPrinciple;

/**
waiter is implementing methods not even required thus breaking
 1. Single responsiblity
 2. Open Closed
 3. Liskov substitution
 4. Interface segragation
**/
public class Waiter implements RestaurantEmployee{
    @Override
    public void serveCustomer() {

    }

    @Override
    public void takeOrder() {

    }

    @Override
    public void cookFood() {

    }

    @Override
    public void decideMenu() {

    }
}
