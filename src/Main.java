import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Register x = new Register(8, new int[]{13, 16, 17, 18}, "1010101010101010101", "X");
        Register y = new Register(10, new int[]{20, 21}, "1100110011001100110011", "Y");
        Register z = new Register(10, new int[]{7, 20, 21, 22}, "11100001111000011110000", "Z");

        int iterations = 3;
        String keystream = "";

        for (int i = 0; i < iterations; i++) {
            keystream += generateBit(x, y, z);
            System.out.println("Keystream: " + keystream);
            System.out.println("Register " + x.getName() + ": " + x.toString());
            System.out.println("Register " + y.getName() + ": " + y.toString());
            System.out.println("Register " + z.getName() + ": " + z.toString());

            System.out.println("------------------");
        }

    }

    //Gets the majority bit, shifts the necessary ciphers, then generates the new bit for the keystream.
    public static int generateBit(Register x, Register y, Register z) {
        int xBit = x.getCharAtSpecialIndex();
        int yBit = y.getCharAtSpecialIndex();
        int zBit = z.getCharAtSpecialIndex();

        //shift necessary registers
        int majorityBit = getMajority(xBit, yBit, zBit);
        System.out.println("Majority bit is: " + majorityBit);
        if (xBit == majorityBit) {
            System.out.println("Shifting X: " + x.toString());
            x.shiftRight();
        }
        if (yBit == majorityBit) {
            System.out.println("Shifting Y: " + y.toString());
            y.shiftRight();
        }
        if (zBit == majorityBit) {
            System.out.println("Shifting Z: " + z.toString());
            z.shiftRight();
        }

        return generateNewBit(x, y, z);
    }

    //generate a new bit for the keystream
    public static int generateNewBit(Register x, Register y, Register z) {
        int lastXBit = x.getLastBit();
        int lastYBit = y.getLastBit();
        int lastZBit = z.getLastBit();
        System.out.println("Bits being XORed: " + lastXBit + ", " + lastYBit + ", " + lastZBit);
        return lastXBit ^ lastYBit ^ lastZBit;
    }

    //pass 3 bits, return the majority (1, 1, 0) = 1. (0, 0, 0) = 0;
    public static int getMajority(int b1, int b2, int b3) {
        int count1 = 0;
        int count0 = 0;
        int[] arr = {b1, b2, b3};

        for (int i = 0; i < arr.length; i++) {
            if (b1 == 1) {
                count1++;
            } else {
                count0++;
            }
        }

        return (count1>count0) ? 1:0;
    }
}
