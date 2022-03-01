/*
  ------------------------------------------------
 | @Name Concert                                |
 | @By Tyler Romanowski      @Created  2.28.22  |
 | @File Main.java           @Modified 2.28.22  |
 | @Comment : An Array Exercise, that handles   |
 | input, removal, indexing, and printing       |
 -----------------------------------------------
*/

import java.util.Scanner;

class Main
{

    public static void main(String[] args) {
        Concert ticketArray[][] = new Concert[9][10];
        Scanner reader = new Scanner(System.in);
        int input = 0;
        newSeat(ticketArray, 3, 5, 8, 30, 40, 50);

        System.out.println("-------------------------------------------\n                   Welcome to \n                The Ant Theater\n------------------------------------------- ");

        System.out.print(
                "    1. Buy a Seat.\n" +
                        "    2. Buy Mutiple Seats.\n" +
                        "    3. Display Seats.\n" +
                        "    4. Check Prices.\n" +
                        "    5. Options.\n" +
                        "    6. Exit. \n " +
                        "Input? ");
        input = reader.nextInt();
        while (input != -1) {
            switch (input) {

                // One Seat
                case 1:
                    System.out.println(display(ticketArray));
                    System.out.print("Witch seat would you like?\n Row? ");
                    input = reader.nextInt() - 1;
                    System.out.print(" Col? ");
                    System.out.println(buy(ticketArray, input, reader.nextInt() - 1));
                    break;

                // Mutiple Seats
                case 2:
                    System.out.print("Witch seats would you like?\n Row? ");
                    input = reader.nextInt() - 1;
                    System.out.print(" # of Seats? ");
                    System.out.println(buy(ticketArray, input, reader.nextInt() - 1, true));
                    break;

                // Displays Screen
                case 3:
                    System.out.println(display(ticketArray));
                    break;

                // Gets the Price
                case 4:
                    System.out.println(display(ticketArray));
                    System.out.print("Witch seat would you like the Price for?\n Row? ");
                    input = reader.nextInt() - 1;
                    System.out.print(" Col? ");
                    System.out.println(" That Seat Costs $" + ticketArray[input][reader.nextInt() - 1].getPrice());
                    break;

                //Options
                case 5:
                    System.out.println("    1. Buy a Seat.\n" + "    2. Buy Mutiple Seats.\n" + "    3. Display Seats.\n" + "    4. Check Prices.\n" + "    5. Options.\n" + "    6. Exit. \n ");
                    break;

                //Exit
                case 6:
                    System.out.println("Thanks for visting!");
                    break;


                default:
                    System.out.println("Invalid Input!");
                    break;

            }
            System.out.print(" Input? ");
            input = reader.nextInt();
        }
    }

    // Declares all the seats in the Concert Array
    private static void newSeat(Concert[][] ticketArray, int first, int second, int third, int firstPrice, int secondPrice, int thirdPrice)
    {
        for(int i = 0; i < first; i++)
        {
            for(int k = 0; k < ticketArray.length; k++)
            {
                ticketArray[i][k] = new Concert(firstPrice);

            }
        }

        for(int i = first; i < second; i++)
        {
            for(int k = 0; k < ticketArray.length; k++)
            {
                ticketArray[i][k] = new Concert(secondPrice);

            }
        }

        for(int i = second; i < ticketArray.length; i++)
        {
            for(int k = 0; k < ticketArray[i].length; k++)
            {

                ticketArray[i][k] = new Concert(thirdPrice);

            }
        }

    }


    // Displays the Grid of Seats
    static private String display(Concert ticketArray[][])
    {
        String output = "\n-           Please choose a Seat         -\n      [1] [2] [3] [4] [5] [6] [7] [8] [9]";

        for(int i = 0; i < ticketArray.length - 1; i++)
        {
            output += "\n [" + (i + 1) + "] ";
            for(int k = 0; k < ticketArray[i].length - 1; k++)
            {

                output += "  " + ticketArray[i][k].getInfo();
            }
        }
        return output + "\n-                Thank you               -\n";
    }

    // "Buys" the Seat, if the seat is not already taken
    private static String buy(Concert[][] ticketArray, int row, int col)
    {
        if(row >= ticketArray.length - 1 || row < 0 )
            return "Not a Valid Row";
        if(col >= ticketArray[row].length ||  col < 0 )
            return "Not a Valid Column";

        if (ticketArray[row][col].getSoldInfo() == false)
        {
            ticketArray[row][col].updateSoldStatus(true);
            return "Seat [" + (row + 1) + "]" + "[" + (col + 1) + "] is now yours\n";

        }
        else
        {
            return "Sorry that seat is taken";
        }
    }
    private static String buy(Concert[][] ticketArray, int row, int numberOfSeats , boolean mutiple)
    {
        if (mutiple)
        {
            int count = 0;
            int startingSeat = -1;
            String output = "";
            numberOfSeats++;

            if (numberOfSeats >= ticketArray[row].length)
                return "To Many Seats";
            if (row >= ticketArray.length)
                return "Not a Valid Row";

            for (int i = 0; i < ticketArray[row].length + 1; i++)
            {
                if (!(count >= numberOfSeats))
                {
                    if (ticketArray[row][i].getSoldInfo() == false)
                    {
                        count++;
                    }
                    else
                    {
                        System.out.print("Error" + count);
                        count = 0;
                        startingSeat = i;
                    }
                }
                else
                    i = ticketArray[row].length;
            }
            if (count < numberOfSeats)
            {
                return "There is not enough room in this row, please choose another row";
            }

            System.out.print("test" + (startingSeat + numberOfSeats));
            for (int i = startingSeat + 1; i <= (startingSeat + numberOfSeats); i++)
            {
                ticketArray[row][i].updateSoldStatus(true);
                output += "Seat [" + (row + 1) + "]" + "[" + (i + 1) + "] is now yours\n";
            }
            return output;

        }
        return "Error : Wrong Method";
    }

}