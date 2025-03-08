package SolidPrinciple;

public class Bicycle implements Bike{

    private int speed;

    /**
     *
     * child class should only extend functionality not reduce
     * this child class narrows down the functionality of parent (bike) thus breaking Liskov substitution principle
     * it also breaks Interface segregation principle
     *
     */
    @Override
    public void turnOnEngine() {
        throw new AssertionError("there is no engine");
    }

    @Override
    public void accelerate() {
        // do something
        speed = speed+10;

    }
}
