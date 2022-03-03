/*
  ------------------------------------------------
 | @Name Concert                                |
 | @By Tyler Romanowski      @Created  2.28.22  |
 | @File Main.java           @Modified 2.28.22  |
 | @Comment: An 2D Array Exercise, that handles |
 | input, removal, indexing, and printing       |
 -----------------------------------------------
*/

import java.util.Scanner;

class Main
{
    private static final Concert[][] ticketArray = new Concert[10][10];
    private static final Scanner reader = new Scanner(System.in);


    // Were the Price Changes Happen
    private final static int[] SEATING_CHART = {3, 6};


    // The Prices of Each Type of Seat
    private final static int[] SEATING_PRICES = {50, 40, 30};


    // Main Method
    public static void main(String[] args)
    {
        newSeat();
        System.out.println("---------------------------------------------\n                   Welcome to \n                The Ant Theater\n--------------------------------------------- ");
        System.out.print(display() + "\n");
        optionsLoop(5);
    }


    // Gives the User a List of Options
    private static void optionsLoop(int input)
    {
        int col = 0;

        if(input == 0)
        {
            System.out.println("\n  Input? ");
            input = reader.nextInt();
        }


        while (input != -1)
        {
            switch (input)
            {

                // One Seat
                case 1:
                    System.out.println(display(ticketArray));
                    System.out.print("Which seat would you like?\n Row? ");
                    input = reader.nextInt() - 1;
                    System.out.print(" Col? ");
                    col = reader.nextInt();

                    if(checkSeatsAbove(input, col - 1) > 0 &&  !ticketArray[input][col - 1].getSoldInfo())
                    {
                        System.out.print("There are " + checkSeatsAbove(input, col - 1) + " Seats above you, would you like to Continue? \n(Type [1] to continue with purchase)");
                        if(reader.nextInt() != 1)
                        {
                            System.out.print("Going Back to Menu");
                            optionsLoop(5);
                        }
                    }

                    else if((input >= 0 || col - 1 >= 0) )
                    {
                        System.out.println("That seat costs $" + ticketArray[input][col - 1].getPrice() + "\nDo you still want it? (Type [1] to continue with purchase)");
                        if (reader.nextInt() == 1)
                            System.out.println(buy(input, col - 1));
                    }
                    else
                    {
                        System.out.println("Invalid input");
                        input = 6;
                    }

                    break;


                // Multiple Seats
                case 2:
                    System.out.print("Which seats would you like?\n Row? ");
                    input = reader.nextInt() - 1;
                    System.out.print(" # of Seats? ");
                    System.out.println(multipleBuy(input, reader.nextInt() - 1));
                    input = 6;
                    break;


                // Picks a Seat Depending on Price Point
                case 3:
                    System.out.print(" Which Price Point?");
                    System.out.println(chooseByPrice(reader.nextInt()));
                    break;


                // Displays Screen
                case 4:
                    System.out.println(display);
                    break;



                //Options
                case 5:
                    System.out.println("1. Buy a Specific Seat.\n" + "2. Buy Multiple Seats.\n" + "3. Seat by Price.\n" + "4. Display Seats.\n" + "5. Options.\n" + "6. Exit. \n ");
                    break;

                //Exit
                case 6:
                    System.out.println("Thanks for Visiting!");
                    input = -1;
                    break;

                //Default
                default:
                    System.out.println("Invalid Input :<");
                    break;

            }
            if (input != -1)
            {
                System.out.print("To Access the Menu Type [5]\n Input? ");
                input = reader.nextInt();
            }
        }
    }


    // Checks to See if there are any Seats Above
    private static int checkSeatsAbove(int row, int col)
    {
        int output = 0;
        for(int i = row; i >= 0; i--)
        {
            if (ticketArray[i][col].getSoldInfo())
                output++;
        }
        return output;
    }


    // Chooses a seat, depending on the Price
    private static String chooseByPrice(int input)
    {
        while (true)
        {
            // First Price
            if (input == SEATING_PRICES[0])
            {
                for (int i = 0; i < SEATING_CHART[0]; i++)
                {
                    for (int k = 0; k < ticketArray[i].length; k++)
                    {
                        if (!ticketArray[i][k].getSoldInfo())
                        {
                            ticketArray[i][k].updateSoldStatus(true);
                            return "Seat [" + (i + 1) + "]" + "[" + (k + 1) + "] is now purchased\n";
                        }

                    }
                }
                return "No Seats are Available in that Price Range";
            }


            // Second Price
            if (input == SEATING_PRICES[1])
            {
                for (int i = SEATING_CHART[0]; i < SEATING_CHART[1]; i++)
                {
                    for (int k = 0; k < ticketArray[i].length; k++)
                    {
                        if (!ticketArray[i][k].getSoldInfo())
                        {
                            ticketArray[i][k].updateSoldStatus(true);
                            return "Seat [" + (i + 1) + "]" + "[" + (k + 1) + "] is now purchased\n";
                        }

                    }
                }
                return "No Seats are Available in that Price Range";
            }


            // Third Price
            if (input == SEATING_PRICES[2])
            {
                for (int i = SEATING_CHART[1]; i < ticketArray.length - 2; i++)
                {
                    for (int k = 0; k < ticketArray[i].length; k++)
                    {
                        if (!ticketArray[i][k].getSoldInfo())
                        {
                            ticketArray[i][k].updateSoldStatus(true);
                            return "Seat [" + (i + 1) + "]" + "[" + (k + 1) + "] is now purchased\n";
                        }

                    }
                }
                return "No Seats are Available in that Price Range";
            }
            return "No Seats are Available in that Price Range";

        }
    }


    // Declares all the seats in the Concert Array
    private static void newSeat()
    {
        for (int i = 0; i < SEATING_CHART[0]; i++)
        {
            for (int k = 0; k < ticketArray.length; k++)
            {
                ticketArray[i][k] = new Concert(SEATING_PRICES[0]);

            }
        }

        for (int i = SEATING_CHART[0]; i < SEATING_CHART[1]; i++)
        {
            for (int k = 0; k < ticketArray.length; k++)
            {
                ticketArray[i][k] = new Concert(SEATING_PRICES[1]);
            }
        }

        for (int i = SEATING_CHART[1]; i < ticketArray.length - 1; i++)
        {
            for (int k = 0; k < ticketArray[i].length; k++)
            {

                ticketArray[i][k] = new Concert(SEATING_PRICES[2]);
            }
        }

    }


    // Displays the Grid of Seats
    static private String display()
    {
        String output = "\n-             Please choose a Seat           -\n      [1] [2] [3] [4] [5] [6] [7] [8] [9] [10]";

        for (int i = 0; i < ticketArray.length - 1; i++)
        {
            output += "\n [" + (i + 1) + "] ";
            for (int k = 0; k < ticketArray[i].length; k++)
            {

                output += "  " + ticketArray[i][k].getInfo();
            }
        }
        return output + "\n-                  Thank you                 -\n";
    }


    // "Buys" the Seat, if the seat is not already taken
    private static String buy(int row, int col)
    {
        if (row >= ticketArray.length - 1 || row < 0)
            return "Not a Valid Row";
        if (col >= ticketArray[row].length || col < 0)
            return "Not a Valid Column";

        if (!ticketArray[row][col].getSoldInfo())
        {
            ticketArray[row][col].updateSoldStatus(true);
            return "Seat [" + (row + 1) + "]" + "[" + (col + 1) + "] is now purchased\n";
        }
        else
        {
            return "Sorry that seat is taken";
        }
    }


    // "Buys" multiple Seat, if the seat is not already taken
    private static String multipleBuy(int row, int numberOfSeats)
    {
        if (row >= ticketArray.length - 1 || row < 0)
            return "Not a Valid Row";

        if (numberOfSeats > ticketArray[row].length || numberOfSeats <= 1)
            return "Wrong Seat Count";

        int count = 0;
        int startingSeat = -1;
        String output = "";
        numberOfSeats++;


        for (int i = 0; i < ticketArray[row].length; i++)
        {
            if (!(count >= numberOfSeats))
            {
                if (!ticketArray[row][i].getSoldInfo())
                {
                    count++;
                }
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
            ticketArray[row][i].updateSoldStatus(true);
            output += "Seat [" + (row + 1) + "]" + "[" + (i + 1) + "] is now purchased\n";
        }
        return output;
    }
}