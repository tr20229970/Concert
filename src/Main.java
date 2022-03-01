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

    public static void main(String[] args)
    {
        Concert[][] ticketArray = new Concert[10][10];
        Scanner reader = new Scanner(System.in);
        int input = 0;
        int temp = 0;

        // Were the Price Changes Happen
        final int[] SEATING_CHART = {3, 6};


        // The Prices of Each Type of Seat
        final int firstPrice = 30;
        final int secondPrice = 40;
        final int thirdPrice = 50;


        newSeat(ticketArray, SEATING_CHART[0], SEATING_CHART[1], firstPrice, secondPrice, thirdPrice);

        System.out.println("-------------------------------------------\n                   Welcome to \n                The Ant Theater\n------------------------------------------- ");

        System.out.print(display(ticketArray) +
                "\n1. Buy a Seat.\n" +
                "2. Buy Multiple Seats.\n" +
                "3. Seat by Price.\n" +
                "4. Display Seats.\n" +
                "5. Check Prices.\n" +
                "6. Options.\n" +
                "7. Exit. \n " +
                "Input? ");

        input = reader.nextInt();

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
                    temp = reader.nextInt();

                    if ((temp - 2 >= 0))
                        if (ticketArray[input][temp - 2].getSoldInfo() && !(ticketArray[input][temp - 1].getSoldInfo()))
                        {
                            System.out.println("The Seat above you is Taken, Would you still like that seat\n Type [1] to continue with purchase");
                            if (reader.nextInt() != 1)
                                break;
                        }
                    if(input >= 0 || temp - 1 >= 0)
                    {
                        System.out.println("That seat costs $" + ticketArray[input][temp - 1].getPrice() + "\nDo you still want it? (Type [1] to continue with purchase)");
                        if (reader.nextInt() == 1)
                            System.out.println(buy(ticketArray, input, temp - 1));
                    }
                    else
                    {
                        System.out.println("Invalid input");
                        input = 6;
                    }

                    break;


                // Multiple Seats
                case 2:
                    System.out.print("Witch seats would you like?\n Row? ");
                    input = reader.nextInt() - 1;
                    System.out.print(" # of Seats? ");
                    System.out.println(multipleBuy(ticketArray, input, reader.nextInt() - 1));
                    input = 6;
                    break;


                // Picks a Seat Depending on Price Point
                case 3:
                    System.out.print(" Witch Price Point?");
                    System.out.println(chooseByPrice(ticketArray, SEATING_CHART, firstPrice, secondPrice, thirdPrice, reader.nextInt()));
                    break;


                // Displays Screen
                case 4:
                    System.out.println(display(ticketArray));
                    break;


                // Gets the Price
                case 5:
                    System.out.println(display(ticketArray));
                    System.out.print("Witch seat would you like the Price for?\n Row? ");
                    input = reader.nextInt() - 1;
                    System.out.print(" Col? ");
                    System.out.println(" That Seat Costs $" + ticketArray[input][reader.nextInt() - 1].getPrice());
                    break;


                //Options
                case 6:
                    System.out.println("    1. Buy a Seat.\n" + "    2. Buy Multiple Seats.\n" + "    3. Seat by Price.\n" + "    4. Display Seats.\n" + "    5. Check Prices.\n" + "    6. Options.\n" + "    7. Exit. \n ");
                    break;

                //Exit
                case 7:
                    System.out.println("Thanks for Visiting!");
                    input = -1;
                    break;

                //Default
                default:
                    System.out.println("Invalid Input!");
                    break;

            }
            if (input != -1)
            {
                System.out.print("If you are not sure type [6]\n Input? ");
                input = reader.nextInt();
            }
        }
    }


    // Chooses a seat, depending on the Price
    private static String chooseByPrice(Concert[][] ticketArray, int[] chart, int firstPrice, int secondPrice, int thirdPrice, int input)
    {
        boolean run = true;
        while (run)
        {


            // First Price
            if (input == firstPrice)
            {
                for (int i = 0; i < chart[0]; i++)
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
            if (input == secondPrice)
            {
                for (int i = chart[0]; i < chart[1]; i++)
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
            if (input == thirdPrice)
            {
                for (int i = chart[1]; i < ticketArray.length - 2; i++)
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

            else
                run = false;
        }
        return "No Seats are Available in that Price Range";
    }


    // Declares all the seats in the Concert Array
    private static void newSeat(Concert[][] ticketArray, int first, int second, int firstPrice, int secondPrice, int thirdPrice)
    {
        for (int i = 0; i < first; i++)
        {
            for (int k = 0; k < ticketArray.length; k++)
            {
                ticketArray[i][k] = new Concert(firstPrice);

            }
        }

        for (int i = first; i < second; i++)
        {
            for (int k = 0; k < ticketArray.length; k++)
            {
                ticketArray[i][k] = new Concert(secondPrice);
            }
        }

        for (int i = second; i < ticketArray.length - 1; i++)
        {
            for (int k = 0; k < ticketArray[i].length; k++)
            {

                ticketArray[i][k] = new Concert(thirdPrice);
            }
        }

    }


    // Displays the Grid of Seats
    static private String display(Concert[][] ticketArray)
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
    private static String buy(Concert[][] ticketArray, int row, int col)
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
    private static String multipleBuy(Concert[][] ticketArray, int row, int numberOfSeats)
    {
        int count = 0;
        int startingSeat = -1;
        String output = "";
        numberOfSeats++;

        if (row >= ticketArray.length - 1 || row < 0)
            return "Not a Valid Row";

        if (numberOfSeats > ticketArray[row].length || numberOfSeats <= 1)
            return "Wrong Seat Count";


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
        {
            return "There is not enough room in this row, please choose another row";
        }

        for (int i = startingSeat + 1; i <= (startingSeat + numberOfSeats); i++)
        {
            ticketArray[row][i].updateSoldStatus(true);
            output += "Seat [" + (row + 1) + "]" + "[" + (i + 1) + "] is now purchased\n";
        }
        return output;
}

}