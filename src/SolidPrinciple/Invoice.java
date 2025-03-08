package SolidPrinciple;

public class Invoice {

    // has a relationship with marker
    // Invoice -> has a marker
    private Marker marker;

    private int quantity;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public int calculatePrice(){
        return this.marker.getPrice() * quantity;
    }


    /**
     *
     * REMOVE THESE METHODS TO FOLLOW SINGLE RESPONSIBILITY
     *
    // this won't be following single responsibility AS INVOICE CALCULATION CAN CHANGE BECAUSE OF USECASE - GST NEW NORMS
    public int calculatePrice(){
        return this.marker.getPrice() * quantity;
    }

    // this won't be following single responsibility
    public void printInvoice(){
        // print
    }

    // this won't be following single responsibility
    public void saveToDB(){
        // save to db
    }
     **/
}
