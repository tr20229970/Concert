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

    public static void main(String[] args)
    {
        Concert ticketArray[][] = new Concert[9][10];
        Scanner reader = new Scanner(System.in);
        int temp = 0;
        newSeat(ticketArray, 3, 5, 8, 30, 40, 50);

        System.out.println("-------------------------------------------\n                   Welcome to \n                The Ant Theater\n-------------------------------------------\n ");
        System.out.println(display(ticketArray));

        while(temp != -1)
        {
            System.out.print("Witch seat would you like?\n Row? ");
            temp = reader.nextInt() - 1;
            System.out.print(" Col? ");

            System.out.println(buy(ticketArray, temp, reader.nextInt() - 1));
            System.out.println(display(ticketArray));
        }
    }


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
        return output + "\n-                 Thank you              -\n";
    }
    private static String buy(Concert[][] ticketArray, int row, int col)
    {
        if(ticketArray[row][col].getSoldInfo() == false)
        {
            ticketArray[row][col].updateSoldStatus(true);
            return "Seat [" + (row + 1) + "]" + "[" + (col + 1) + "] is now yours\n";

        }
        else
        {
            return "Sorry that seat is taken";
        }
    }



}