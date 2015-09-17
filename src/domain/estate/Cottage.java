package domain.estate;

public class Cottage implements Estate
{
    
    private String address;
    private Integer price;
    
    public Cottage(String address, Integer price) 
    {
        this.address = address;
        this.price = price;
    }

}
