// This class handles the process of adding and removing members.
// It also has a method that allows users to display information about a member.

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {

    final private Scanner reader = new Scanner(System.in);


    // get input from a user

    private int getIntInput() {

        int choice = 0;

        while (choice == 0) {
            try {
                choice = reader.nextInt();

                if (choice == 0) {
                    throw new InputMismatchException();
                    // reader.nextLine();
                }
            }
            catch (InputMismatchException e) {
                System.out.println("\nERROR: INVALID INPUT. Please try again:");
            }
        }
        return choice;
    }

    private void printClubOptions() {

        System.out.println("\n1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
    }

    // get the choice from the user
    public int getChoice() {

        int choice;

        System.out.println("\nWELCOME TO OZONE FITNESS CENTER");
        System.out.println("================================");
        System.out.println("1) Add Member.");
        System.out.println("2) Remove Member.");
        System.out.println("3) Display Member information.");

        System.out.println("\nPlease select an option(or Enter Q to quit): ");
        choice = getIntInput();

        return choice;
    }

    //  method takes in a LinkedList of Member objects and adds a new member to this LinkedList.

    public String addMembers(LinkedList<Member> m) {

        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;

        // getting the member's name
        System.out.print("\nMember's name: ");
        name = reader.nextLine();

        // getting the member's club access
        printClubOptions();
        System.out.print("\nMember's Club ID: ");
        club = getIntInput();

        while (club < 1 || club > 4) {
            System.out.print("\nInvalid Club ID. Please try again: ");
            club = getIntInput();
        }

        if (m.size() > 0)
            memberID = m.getLast().getMemberID() + 1;
        else
            memberID = 1;

        if (club != 4) {
            cal = (n)-> {
                switch (n)
                {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                     default:
                        return -1;

                }
            };

            fees = cal.calculateFees(club);

            mbr = new SingleClassMember('S', memberID, name, fees, club);
            m.add(mbr);
            mem = mbr.toString();

            System.out.println("\nSTATUS: Single club Member added\n");
        }
        else {
            cal = (n)-> {
                if (n == 4)
                    return 1200;
                else
                    return -1;
            };

            fees = cal.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            m.add(mbr);
            mem = mbr.toString();

            System.out.println("\nSTATUS: Multi club Member added\n");
        }

        return mem;
    }

    // class that removes a member

    public void removeMember(LinkedList<Member> m) {

        int memberID;
        System.out.print("\nPlease give the Member ID you wish to remove: ");
        memberID = getIntInput();

        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                m.remove(i);
                System.out.println("\nMember removed.");
                return;
            }
        }
        System.out.println("\nMember ID not found.\n");

    }


}

