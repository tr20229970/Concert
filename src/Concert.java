/*
  ------------------------------------------------
 | @Name Concert                                  |
 | @By Tyler Romanowski      @Created  2.28.22    |
 | @File Concert.java        @Modified 3.3.22     |
 | @P-Comment: An 2D Array Exercise, that handles |
 | input, removal, indexing, and printing         |
 |---------------------------------------------   |
 | @Methods updateSoldStatus(bol)                 |
 |getSoldInfo() getInfo() toString() getPrice()   |
 |----------------------------------------------- |
 |@Comment - This is the Concert Class, it        |
 |handles storing the price and the sold Status   |
 |along with returning that information, in both  |
 |the actual representation, and one for display  |
 -------------------------------------------------



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

    //-------------------------------------------------
    // @Method  - OptionsLoop
    // @Args    - int inputPrice
    // @Pre     - Any int
    // @Comment - sets the default values for the obj
    // ------------------------------------------------
    Concert(int inputPrice)
    {
        sold = false;
        price = inputPrice;
    }

    //-------------------------------------------------
    // @Method  - OptionsLoop
    // @Args    - boolean input
    // @Comment - sets the sold status
    // ------------------------------------------------
    void updateSoldStatus(boolean input)
    {
        sold = input;
    }


    //-------------------------------------------------
    // @Method  - getSoldInfo
    // @Post    - Returns a Boolean
    // @Comment - Sends back the sold status
    // ------------------------------------------------
    public boolean getSoldInfo()
    {
        return sold;
    }


    //-------------------------------------------------
    // @Method  - getPrice
    // @Post    - Returns a String
    // @Comment - Sends back the Current Price
    // ------------------------------------------------
    public int getPrice()
    {
        return price;
    }

    //-------------------------------------------------
    // @Method  - getInfo
    // @Post    - Returns a String
    // @Comment - Returns xx, if the obj is sold
    // ------------------------------------------------
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
