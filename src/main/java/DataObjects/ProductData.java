package DataObjects;

public class ProductData {

    String name;
    String qty;
    String size;

    public ProductData(String name, String qty, String size){
        this.name = name;
        this.qty = qty;
        this.size = size;
    }

    public String getName(){
        return name;
    }
    public String getQty(){
        return qty;
    }
    public String getSize(){
        return size;
    }
}
