package SolidPrinciple;

public class InvoiceDaoDBImpl implements InvoiceDao {
    @Override
    public void save(Invoice invoice){
        // save to db
    }

    /**
     *
     * NOT FOLLOWING OPEN CLOSED PRINCIPLE - SO CREATE A SEPARATE CLASS
     * should not modify existing live code only extend
     *
    public void saveToFile(){
        // save to db
    }
     **/

}
