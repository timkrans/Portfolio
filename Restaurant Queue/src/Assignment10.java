// Description: takes user imput and 
//moves to the method of the linklist 
//to allow for the user to get what they desire

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assignment10 {
    public static void main(String[] args) {
    	launch(args);
    	char input1;
        String line;
        
        //create a linked list to be used in this method.
        LinkedList restaurantQueue = new LinkedList();

        try {
            // print out the menu
            System.out.println("Welcome to ClosedTable Management Service:");
            printMenu();

            // create a BufferedReader object to read input from a keyboard
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.print("What action would you like to perform?\n");
                line = stdin.readLine().trim();  //read a line
                input1 = line.charAt(0);
                input1 = Character.toUpperCase(input1);

                if (line.length() == 1)   //check if a user entered only one character
                {
                    switch (input1) {
                        case 'A':   //Add guest
                            System.out.println("Please enter the name of the guest for the reservation:");
                            String name = stdin.readLine().trim();
                            System.out.println("Please enter the size of the party:");
                            String input = stdin.readLine().trim();
                            int size = Integer.parseInt(input);
                            restaurantQueue.add(size, name);
                            System.out.println("reservation successfully added.\n");
                            break;
                        case 'C':
                            System.out.println("Please enter the party size you'd like to count:");
                            input = stdin.readLine().trim();
                            size = Integer.parseInt(input);
                            int total = restaurantQueue.getNumberOfParties(size);
                            System.out.printf("There are %s parties with %d guests waiting currently.\n\n", total, size);
                            break;
                        case 'P':
                            System.out.println("Please enter the reservation name you'd like to know its position:");
                            input = stdin.readLine().trim();
                            int result = restaurantQueue.getPosition(input);
                            if(result == -1)
                                System.out.println("Reservation not found, please try again.\n");
                            else
                                System.out.printf("The %s reservation is currently at position %d in line.\n\n", input,
                                        result + 1);
                            break;
                        case 'R':
                            System.out.println("Please enter the reservation name you'd like to remove from queue:");
                            input = stdin.readLine().trim();
                            Table guest = restaurantQueue.removeGuest(input);
                            if(guest.guests == -1)
                                System.out.println("Reservation not found, please try again.\n\n");
                            else
                                System.out.printf("Reservation for %s for %d guest(s) has been cancelled.\n\n", guest.name,
                                        guest.guests);

                            break;
                        case 'D':
                            //get next guest in line
                            Table nextInLine = restaurantQueue.removeFirst();
                            if(nextInLine.guests == -1)
                                System.out.println("No reservations in line currently.\n");
                            else
                                System.out.printf("Next party in line is %s, for %d guests, please come to host area.\n\n",
                                        nextInLine.name, nextInLine.guests);
                            break;
                        case 'L':
                            String reservations = restaurantQueue.listReservations();
                            System.out.println(reservations);
                            break;
                        case 'Q':   //Quit
                            break;
                        case '?':   //Display Menu
                            printMenu();
                            break;
                        default:
                            System.out.print("Unknown action\n");
                            break;

                    }
                }
            }while(input1 != 'Q' || line.length() != 1);
        }catch (IOException exception)
        {
            System.out.print("IO Exception\n");
        }
    }

    private static void launch(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public static void printMenu()
    {
        System.out.print("Choice\t\tAction\n" +
                "------\t\t------\n" +
                "A\t\tAdd a new reservation to the queue\n" +
                "C\t\tFind how many parties of a certain size are in the queue\n" +
                "D\t\tGet next reservation in line\n" +
                "L\t\tList all reservations in queue\n" +
                "P\t\tFind the position of a reservation in the queue\n" +
                "R\t\tRemove reservation from queue\n" +
                "Q\t\tQuit\n" +
                "?\t\tDisplay Help\n\n");
    } //end of printMenu()

}