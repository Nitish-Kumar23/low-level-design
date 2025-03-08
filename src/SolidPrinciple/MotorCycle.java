package SolidPrinciple;

public class MotorCycle implements Bike {

    private boolean isEngine;
    private int speed;
    @Override
    public void turnOnEngine() {
        isEngine = true;
    }

    @Override
    public void accelerate() {
        speed = speed+10;
    }
}
