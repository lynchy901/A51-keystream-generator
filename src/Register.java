import java.util.ArrayList;

public class Register {
    private int specialIndex;
    private int[] xorPositions;
    private String startingRegister;
    private int[] register;
    private String name;

    public Register(int specialIndex, int[] xorPositions, String startingRegister, String name) {
        this.specialIndex = specialIndex;
        this.xorPositions = xorPositions;
        this.startingRegister = startingRegister;
        this.register = this.stringToIntArr(startingRegister);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //takes a string and turns it into an int array
    public int[] stringToIntArr(String bitString) {
        int[] arr = new int[bitString.length()];

        for (int i = 0; i < bitString.length(); i++) {
            arr[i] = Character.getNumericValue(bitString.charAt(i));
        }

        return arr;
    }

    @Override
    public String toString() {
        String bitString = "";
        for (int i = 0; i < register.length; i++) {
            bitString+=register[i];
        }

        return bitString;
    }

    public int[] getRegisterArray() {
        return register;
    }

    //shift array values the the right and set position 0 to the XOR of the bits at the positions in xorPositions.
    public void shiftRight() {
        for (int i = this.register.length - 2; i >= 0; i--) {
            this.register[i + 1] = this.register[i];
        }
        register[0] = getNewStartingBit();
    }

    //returns the result of the XORed bits at the positions in xorPositions
    public int getNewStartingBit() {
        int newStartingBit;

        newStartingBit = register[xorPositions[0]];
        for (int i = 0; i<xorPositions.length - 1; i++) {
            System.out.println(newStartingBit + ", " + register[xorPositions[i+1]]);
            newStartingBit = newStartingBit^register[xorPositions[i+1]];
        }

        return newStartingBit;
    }

    public int getLastBit() {
        return register[register.length-1];
    }

    public int getCharAtSpecialIndex() {
        return register[specialIndex];
    }
}
