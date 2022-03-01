/*
  ------------------------------------------------
 | @Name Concert                                |
 | @By Tyler Romanowski      @Created  2.28.22  |
 | @File Concert.java        @Modified 3.1.22  |
 | @Comment: An 2D Array Exercise, that handles |
 | input, removal, indexing, and printing       |
 |--------------------------------------------- |
 | @Methods updateSoldStatus(bol)               |
 |getSoldInfo() getInfo() toString() getPrice() |
 -----------------------------------------------
*/

public class Concert
{
    private boolean sold;
    private int price;

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
