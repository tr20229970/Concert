public class Concert
{
    boolean sold;
    int price;

    Concert()
    {
        sold = false;
        price = 30;
    }
    Concert(int inputPrice)
    {
        sold = false;
        price = inputPrice;
    }
    void updateSoldStatus(boolean input)
    {
        sold = input;
    }

    public boolean getSoldInfo()
    {
       return sold;
    }
    public int getPrice()
    {
        return price;
    }
    public String getInfo()
    {
        if(sold)
            return "XX";
        return "" + price;
    }
    public String toString()
    {
        return "Current Price $" + getPrice() + " Is Sold? " + getSoldInfo();
    }

}
