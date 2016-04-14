
/**
 * Problem G - Untied Airlines
 * This is an OO solution, quite different to that of other people
 * Probably took longer as well!
 *
 * @author Phil Robbins
 *
 */
//package untiedjava;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author probbins
 */
public class UntiedJava {
    //-----------------------------------------------------
    // Private class to store data about a seat/passenger

    private class Seat implements Comparable {

        char letter; // A to J
        int row, // 01 to 99
                points; // The demerit points

        /**
         * Constructor
         *
         * @param letter of seat
         * @param row number of seat
         */
        public Seat(char lttr, int rw) {
            letter = lttr;
            row = rw;
            // Initialise demerit points to 0
            points = 0;
        }
        // Accessor methods

        /**
         * @return seat letter
         */
        public char getLetter() {
            return letter;
        }

        /**
         * @return row number
         */
        public int getRow() {
            return row;
        }

        /**
         * @return demerit points
         */
        public int getPoints() {
            return points;
        }
        // Mutator method

        /**
         * @param new demerit points to add to existing ones
         */
        public void newPoints(int newPts) {
            points += newPts;
        }
        // Methods

        /**
         * Have to implement compareTo
         *
         * @param other Seat object for comparison
         * @return 0 if equal, otherwise +1
         */
        public int compareTo(Object other) {
            int iRet = 1;
            Seat oSeat = (Seat) other;
            // Equal if row and letter are the same.
            if ((oSeat.getLetter() == letter)
                    && (oSeat.getRow() == row)) {
                iRet = 0;
            }
            return iRet;
        }

        /**
         * Array list uses equal - define using compareTo
         *
         * @param other Seat object for comparison
         * @return true if values are equal
         */
        public boolean equals(Object other) {
            return compareTo(other) == 0;
        }
    }
    //-----------------------------------------------------
    // Instance variables
    private InputStreamReader strIn;
    private BufferedReader in;
    private ArrayList<Seat> lstSeats;
    private static final String TERMINATOR = "#"; // Main input
    private static final String TERMINATOR2 = "00A"; // Flight data
    //------------------------------------------------------------------------------

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UntiedJava oE = new UntiedJava();
        oE.solveProblem();
    }
    //------------------------------------------------------------------------------

    /**
     * Constructor for objects of class Untied
     */
    public UntiedJava() {
        try {
            // Set up objects to get input from STDIN
            strIn = new InputStreamReader(System.in);
            // For debugging, comment out above line and replace with this:
            //strIn = new InputStreamReader(new FileInputStream("P:\\NZ Contest\\2009\\Problems\\sampleG.in"));
            in = new BufferedReader(strIn);
            lstSeats = new ArrayList();
        } catch (Exception e) {
            System.out.println("Error in constructor");
            System.exit(1);
        }
    }
    //------------------------------------------------------------------------------

    /**
     * The program expects data to be passed from a file by redirection.
     */
    private String readLine() throws IOException {
        return in.readLine();
    }
    //------------------------------------------------------------------------------

    /*
 * Write code here to solve 1 scenario
     */
    private void solveSet(String sLine) {
        try {
            int count = 0;
            lstSeats.clear();
            // Output flight number then forget it
            System.out.print(sLine + " ");
            // Read the seat data
            sLine = readLine();
            while (!sLine.equals(TERMINATOR2)) {
                String[] sData = sLine.split(" ");
                // Construct a seat object
                char letter = sData[0].charAt(2);
                int row = Integer.parseInt(sData[0].substring(0, 2));
                Seat newSeat = new Seat(letter, row), seat;
                // Is the seat already stored?
                int index = lstSeats.indexOf(newSeat);
                if (index == -1) {
                    // No, so use and store it
                    seat = newSeat;
                    lstSeats.add(newSeat);
                } else {
                    // Yes, so get the stored object
                    seat = lstSeats.get(index);
                }
                // How many points to apply?
                switch (sData[1].charAt(0)) {
                    case 'L':
                        seat.newPoints(120);
                        break;
                    case 'N':
                        seat.newPoints(40);
                        break;
                    case 'C':
                        seat.newPoints(160);
                        break;
                    case 'S':
                    case 'B':
                        seat.newPoints(150);
                        break;
                    default:
                        seat.newPoints(100);
                }
                sLine = readLine();
            }
            // Now count those who reach 200.
            for (Seat seat : lstSeats) {
                if (seat.getPoints() >= 200) {
                    count++;
                }
            }
            // Output the count
            System.out.println(count + "");
        } catch (Exception e) {
            System.out.println("Error in solveSet()\n");
        }
    }
    //------------------------------------------------------------------------------

    /**
     * This code solves the problem
     */
    private void solveProblem() {
        try {
            // Read first line of file
            String sLine = readLine();
            // Keep going until terminator found
            while (!sLine.equals(TERMINATOR)) {
                // Now solve this data set
                solveSet(sLine);
                // Any more?
                sLine = readLine();
            }
        } catch (Exception e) {
            System.out.println("Error in solveProblem()\n");
        }
    }
}
