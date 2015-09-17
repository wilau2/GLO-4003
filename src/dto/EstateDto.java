package dto;

public class EstateDto 
{
    private String type;
    private String address;
    private Integer price;
    
    public String getAddress() 
    {
        return address;
    }
    
    public void setAddress(String address) 
    {
        this.address = address;
    }

    
    public Integer getPrice() 
    {
        return price;
    }
    
    public void setPrice(Integer price) 
    {
        
        this.price = price;
    }
    
    public String getType() 
    {
        return type;
    }
    
    public void setType(String type) 
    {
        this.type = type;
    }
    

}
