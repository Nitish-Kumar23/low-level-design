package SolidPrinciple;

public class LSPExample {

    public static void main(String[] args) {

        // without LSP and single responsibility
        Bike motorCycle = new MotorCycle();
        Bike bicycle = new Bicycle();
        try {
            motorCycle.accelerate();
            bicycle.accelerate();
            motorCycle.turnOnEngine();
            bicycle.turnOnEngine();
        }catch (AssertionError e){
            System.out.println("Exception occurred");
        }

        // follows LSP, now bicyle don't even have engine method or responsibility
        BikeV2 bicycleV2 = new BicycleV2();
        MotorsizedBike motorBike = new MotorBike();
        bicycleV2.accelerate();
        motorBike.accelerate();
        motorBike.turnOnEngine();

    }


}
