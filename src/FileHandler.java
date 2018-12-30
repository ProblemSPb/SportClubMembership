// handling the membership files

import java.io.*;
import java.util.LinkedList;

public class FileHandler {

    // method readFile -> reading from the csv files
    public LinkedList<Member> readFile() {

        LinkedList<Member> m = new LinkedList();
        String lineRead;
        String[] splitLine;
        Member mem;

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            lineRead = reader.readLine();
            while (lineRead != null) {
                splitLine = lineRead.split(", "); // splitting the line into an array

                if (splitLine[0].equals("S")) {         // if its a Single member, instantiate a SinleClassMember
                    mem = new SingleClassMember('S', Integer.parseInt(splitLine[1]),
                            splitLine[2], Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                } else {      // Multi Club member Object
                    mem = new MultiClubMember('M', Integer.parseInt(splitLine[1]), splitLine[2],
                            Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                }
                m.add(mem);     // adding a newly created member to the List
                lineRead = reader.readLine();
            }
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return m;
    }


    // method => appending new members to the file
    public void appendFile(String mem) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))) {
            writer.write(mem + "\n"); // \n will move the cursor to the next line
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // over writing the file
    // this method is called when we want to remove a member from the club
    // Every time we want to remove a member from our club, we’ll remove it from the LinkedList first.
    // Next, we’ll pass this LinkedList as an argument to the overwriteFile() method.
    // Hence, we need to create a temporary file.

    public void overwriteFile(LinkedList<Member> m) {

        String s;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false))) {
            // to loop through the elements in the LinkedList that is passed in
            for (int i = 0; i < m.size(); i++) {
                s = m.get(i).toString();
                writer.write(s + "\n");
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // deleting the original file and renaming the temp file into members.csv

        try {
            File f = new File("members.csv");
            File tf = new File("members.temp");

            f.delete();
            tf.renameTo(f);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
