/*
  --------------------------------------------------
 | @Name Concert                                   |
 | @By Tyler Romanowski      @Created  2.28.22     |
 | @File Main.java           @Modified 3.21.22     |
 | @P-Comment: An 2D Array Exercise, that handles  |
 | input, removal, indexing, and printing          |
 |-------------------------------------------------|
 | @Comment: This is the Main Class for the        |
 | Concert Program, it handles, the creation of    |
 | the array, declaration of elements in the array |
 | & updating objects in the array. The main       |
 | purpose of the program, is to display and sell  |
 | sets for a Concert hall program                 |
 ---------------------------------------------------
*/

import java.util.Scanner;


class Main
{

    // The Amount of seats in the Theater
    private final static int[] SEATING_SIZE = {9,10};

    // Were the Price Changes Happen
    private final static int[] SEATING_CHART = {3, 6};


    // The Prices of Each Type of Seat
    private final static int[] SEATING_PRICES = {50, 40, 30};

    private static final Concert[][] ticketArray = new Concert[SEATING_SIZE[0]][SEATING_SIZE[1]];
    private static final Scanner reader = new Scanner(System.in);

/*
    -------------------------------------------------
   *  @Method  - main
   *  @Args    - String[] args
   *  @Comment - The Main Method of the Program
     ------------------------------------------------

 */
    public static void main(String[] args)
    {
        newSeat();
        System.out.println("" +
                "-----------------------------------------------\n" +
                "                    Welcome to \n" +
                "                 The Ant Theater" +
                "\n----------------------------------------------- ");
        System.out.print(display() + "\n");
        optionsLoop(5);
    }



/*
    -------------------------------------------------
    * @Method  - OptionsLoop
    * @Args    - int input
    * @Pre     - Any #
    * @Comment - Gives the User a List of Options
    ------------------------------------------------

 */
    private static void optionsLoop(int input)
    {
        int col = 0;


        while (input != -1)
        {
            switch (input)
            {

                // One Seat
                case 1:
                    System.out.println(display());
                    System.out.print("Which seat would you like?\n Row? ");
                    input = reader.nextInt() - 1; //temp storage for the row #
                    System.out.print(" Col?  ");
                    buyRequest(input, reader.nextInt() - 1);
                    break;


                // Multiple Seats
                case 2:
                    System.out.print("Which seats would you like?\n Row? ");
                    input = reader.nextInt() - 1; //temp storage for the row #
                    System.out.print(" # of Seats? ");
                    System.out.println(multipleBuy(input, reader.nextInt() - 1));
                    input = 6;
                    break;


                // Picks a Seat Depending on Price Point
                case 3:
                    System.out.print(" Which Price Point? ");
                    System.out.println(chooseByPrice(reader.nextInt()));
                    break;


                // Displays Screen
                case 4:
                    System.out.println(display());
                    break;


                //Options
                case 5:
                    System.out.println("1. Buy a Specific Seat.\n" +
                            "2. Buy Multiple Seats.\n" +
                            "3. Seat by Price.\n" +
                            "4. Display Seats.\n" +
                            "5. Options.\n" +
                            "6. Exit. \n ");
                    break;

                //Exit
                case 6:
                    System.out.println("Thanks for Visiting! ");
                    input = -1;
                    break;

                //Default
                default:
                    System.out.println("Invalid Input :< ");
                    break;

            }
            if (input != -1)
            {
                System.out.print("To Access the Menu Type [5]\n Input? ");
                input = reader.nextInt();
            }
        }
    }


    /*---------------------------------------------------
    * @Method  -  checkSeatsAbove
    * @Args    -  int row, int col
    * @Post    -  int value of # Seats above the input
    * @Comment -  After user inputs, the row, and col
    * they would like, this method valid them, checks to
    * see if there are any seats above, prints out the
    * seats above, and the price and asks if the user likes
    * to continue
     --------------------------------------------------

     */
    private static void buyRequest(int row, int col)
    {
        if((row < 0 || col < 0) || row > ticketArray.length - 1 || col > ticketArray[row].length - 1)
            inValidInput();

        if(ticketArray[row][col].getSoldInfo())
            inValidInput(4);

        if(checkSeatsAbove(row, col) > 0)
        {
            System.out.print("There are " + checkSeatsAbove(row, col) + " Seats above you, would you like to Continue? " +
                    "\n(Type [1] to continue with purchase)");

            if(reader.nextInt() != 1)
            {
                System.out.print("Going Back to Menu");
                optionsLoop(5);
            }
        }


        System.out.println("That seat costs $" + ticketArray[row][col].getPrice() +
                    "\nDo you still want it? (Type [1] to continue with purchase)");
        if (reader.nextInt() == 1)
            System.out.println(buy(row, col));
    }



    /*---------------------------------------------------
   *  @Method  -  checkSeatsAbove
   *  @Args    -  int row, int col
   *  @Pre     -  valid row, and col
   *  @Post    -  int value of # Seats above the input
   *  @Comment -  Gives the User a List of Options
      --------------------------------------------------
     */

    private static int checkSeatsAbove(int row, int col)
    {
        if(row < 0 || row > ticketArray.length - 1)
            inValidInput(1);
        if((col > ticketArray[row].length - 1 || col < 0 ))
            inValidInput(2);


        int output = 0;
        for(int i = row; i >= 0; i--)
            if (ticketArray[i][col].getSoldInfo())
                output++;
        return output;
    }


    /*
    ---------------------------------------------------
   *  @Method  -  chooseByPrice
   *  @Args    -  int input
   *  @Pre     -  any int
   *  @Post    -  String displaying the Seats Bought
   *  @Comment -  Buys a seat, depending on if it's
   *  available in that price range or not
      --------------------------------------------------
     */
    private static String chooseByPrice(int input)
    {
        for(int loop = 0; loop < (ticketArray.length * ticketArray[loop].length); loop++)
        {

            // First Price
            if (input == SEATING_PRICES[0])
            {
                for (int i = 0; i < SEATING_CHART[0]; i++)
                    for (int k = 0; k < ticketArray[i].length; k++)
                        if (!ticketArray[i][k].getSoldInfo())
                        {
                            ticketArray[i][k].updateSoldStatus(true);
                            return "Seat [" + (i + 1) + "]" + "[" + (k + 1) + "] is now purchased\n";
                        }
                return "No Seats are Available in that Price Range";
            }


            // Second Price
            if (input == SEATING_PRICES[1])
            {
                for (int i = SEATING_CHART[0]; i < SEATING_CHART[1]; i++)
                    for (int k = 0; k < ticketArray[i].length; k++)
                        if (!ticketArray[i][k].getSoldInfo())
                        {
                            ticketArray[i][k].updateSoldStatus(true);
                            return "Seat [" + (i + 1) + "]" + "[" + (k + 1) + "] is now purchased\n";
                        }
                return "No Seats are Available in that Price Range";
            }


            // Third Price
            if (input == SEATING_PRICES[2])
            {
                for (int i = SEATING_CHART[1]; i < ticketArray.length - 2; i++)
                    for (int k = 0; k < ticketArray[i].length; k++)
                        if (!ticketArray[i][k].getSoldInfo())
                        {
                            ticketArray[i][k].updateSoldStatus(true);
                            return "Seat [" + (i + 1) + "]" + "[" + (k + 1) + "] is now purchased\n";
                        }

                return "No Seats are Available in that Price Range";
            }

            // No Seats Available
            return "No Seats are Available in that Price Range";

        }
        return "";
    }


    /*
    ---------------------------------------------------
    * @Method  -  newSeat
    * @Comment -  Declares the Objects in the Array to
    * the Current Value
     --------------------------------------------------
     */
    private static void newSeat()
    {

        // 1st Price
        for (int i = 0; i < SEATING_CHART[0]; i++)
            for (int k = 0; k < SEATING_SIZE[1]; k++)
                ticketArray[i][k] = new Concert(SEATING_PRICES[0]);


        // 2nd Price
        for (int i = SEATING_CHART[0]; i < SEATING_CHART[1]; i++)
            for (int k = 0; k < SEATING_SIZE[1]; k++)
                ticketArray[i][k] = new Concert(SEATING_PRICES[1]);


        // 3rd Price
        for (int i = SEATING_CHART[1]; i < ticketArray.length; i++)
            for (int k = 0; k < SEATING_SIZE[1]; k++)
                ticketArray[i][k] = new Concert(SEATING_PRICES[2]);


    }


    //---------------------------------------------------
    // @Method  -  display
    // @Post    -  String displaying the Seats Bought
    // @Comment -  Returns the Seating Chart as a String
    // --------------------------------------------------
    static private String display()
    {
        String output = ("\n-             Please choose a Seat           -\n      [1] [2] [3] [4] [5] [6] [7] [8] [9] [10]");

        for (int i = 0; i < ticketArray.length; i++)
        {
            output += ("\n [") + (i + 1) +("] ");
            for (int k = 0; k < ticketArray[i].length; k++)
                output += ("  ") + (ticketArray[i][k].getInfo());
        }
        return output + "\n-                  Thank you                 -\n";
    }


    //------------------------------------------------------
    // @Method  -  buy
    // @Args    - int row, int col
    // @Post    -  String displaying if they bought a seat
    // @Comment -  "Buys" the Seat, if the seat is not
    // already taken
    // -----------------------------------------------------
    private static String buy(int row, int col)
    {

        // Not Valid -- Inputs
        if (row > ticketArray.length  || row < 0)
            inValidInput(1);
        if (col > ticketArray[row].length || col < 0)
            inValidInput(2);

        if (!ticketArray[row][col].getSoldInfo())
        {
            ticketArray[row][col].updateSoldStatus(true);
            return "Seat [" + (row + 1) + "]" + "[" + (col + 1) + "] is now purchased\n";
        }

        return "Sorry that seat is taken";
    }


    //------------------------------------------------------
    // @Method  -  inValidInput
    // @Comment -  Prints out That row or Column is invalid,
    // brings user back to menu
    // -----------------------------------------------------
    private static void  inValidInput()
    {
        System.out.println("Invalid input");
        optionsLoop(5);
    }


    //------------------------------------------------------
    // @Method  -  inValidInput (int)
    // @Args    - int input
    // @Comment -  Prints out different error depending on
    // input
    // -----------------------------------------------------
    private static void  inValidInput(int input)
    {
        switch(input)
        {
            // Random Invalid
            default:
                inValidInput();

            // Row Invalid
            case 1:
                System.out.println("That Row # is Invalid");
                optionsLoop(5);

            // Col Invalid
            case 2:
                System.out.println("That Col # is Invalid");
                optionsLoop(5);

            // Seat Count Invalid
            case 3:
                System.out.println("Wrong Seat Count");
                optionsLoop(5);

            case 4:
                System.out.println("Seat Already Sold");
                optionsLoop(5);

        }
    }


    //------------------------------------------------------
    // @Method  -  multipleBuy
    // @Args    - int row, int numberOfSeats
    // @Post    -  String displaying the possible number
    // seats they bought
    // @Comment -  "Buys" the Seat, if the seat is not
    // already taken
    // -----------------------------------------------------
    private static String multipleBuy(int row, int numberOfSeats)
    {
        if (row >= ticketArray.length || row < 0)
            inValidInput(1); // Invalid Row

        if (numberOfSeats > ticketArray[row].length || numberOfSeats <= 1)
            inValidInput(3); // Wrong Seat Count


        int count = 0;
        int startingSeat = -1;
        String output = "";
        numberOfSeats++;


        for (int i = 0; i < ticketArray[row].length; i++)
        {
            if (!(count >= numberOfSeats))
            {
                if (!ticketArray[row][i].getSoldInfo())
                    count++;
                else
                {
                    count = 0;
                    startingSeat = i;
                    if((startingSeat + numberOfSeats) > ticketArray[row].length)
                        return "There is not enough room in this row, please choose another row";
                }
            }
            else
                i = ticketArray[row].length;
        }
        if (count < numberOfSeats)
            return "There is not enough room in this row, please choose another row";

        for (int i = startingSeat + 1; i <= (startingSeat + numberOfSeats); i++)
        {
            System.out.println(i);
            output += (buy(row, i));
        }

        return output;
    }
}