/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcodefinder.HashCodeFinder;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ryan
 */
public class LaunchPad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        byte[] _mysteryHash = new byte[32];
        _mysteryHash[0] = (byte) 106;
        _mysteryHash[1] = (byte) 31;
        _mysteryHash[2] = (byte) 65;
        _mysteryHash[3] = (byte) 19;
        _mysteryHash[4] = (byte) -26;
        _mysteryHash[5] = (byte) -92;
        _mysteryHash[6] = (byte) -9;
        _mysteryHash[7] = (byte) -64;
        _mysteryHash[8] = (byte) 75;
        _mysteryHash[9] = (byte) 119;
        _mysteryHash[10] = (byte) 40;
        _mysteryHash[11] = (byte) 12;
        _mysteryHash[12] = (byte) 5;
        _mysteryHash[13] = (byte) -125;
        _mysteryHash[14] = (byte) 78;
        _mysteryHash[15] = (byte) 87;
        _mysteryHash[16] = (byte) -63;
        _mysteryHash[17] = (byte) 75;
        _mysteryHash[18] = (byte) 125;
        _mysteryHash[19] = (byte) -22;
        _mysteryHash[20] = (byte) 113;
        _mysteryHash[21] = (byte) 109;
        _mysteryHash[22] = (byte) -75;
        _mysteryHash[23] = (byte) -27;
        _mysteryHash[24] = (byte) -40;
        _mysteryHash[25] = (byte) 56;
        _mysteryHash[26] = (byte) 76;
        _mysteryHash[27] = (byte) 109;
        _mysteryHash[28] = (byte) -43;
        _mysteryHash[29] = (byte) -9;
        _mysteryHash[30] = (byte) 123;
        _mysteryHash[31] = (byte) 126;

        File dictionary = new File("Dictionary");
        Scanner in = new Scanner(dictionary);

        String _word;
        char[] word;
        int length;
        boolean[] binVal;
        int[] iteratorVal;
        int iterator;
        boolean flag = false;
        int counter = 0;
        long start = System.currentTimeMillis();
        while (in.hasNextLine()) {
            _word = in.nextLine();
            if (_word.length() < 15) {
                System.out.print(_word + " ");
                
                System.out.println(System.currentTimeMillis()- start);

                word = _word.toCharArray();

                length = (int) (Math.pow(2, word.length));

                binVal = new boolean[word.length];
                iteratorVal = new int[word.length];
                iterator = length;
                for (int i = 0; i < word.length; i++) {
                    binVal[i] = true;
                    iterator = iterator / 2;
                    iteratorVal[i] = iterator;
                }

                String variation = "";
                for (int i = 1; i <= length; i++) {
                    variation = "";

                    for (int n = 0; n < word.length; n++) {

                        if (binVal[n] == true) {
                            variation = variation + Character.toLowerCase(word[n]);
                            //make uppercase [n]
                        } else {
                            variation = variation + Character.toUpperCase(word[n]);
                            //make lowercase [n]
                        }
                    }
                    for (int n = 0; n < iteratorVal.length; n++) {
                        if (i % iteratorVal[n] == 0) {
                            binVal[n] = !binVal[n];
                        }
                    }

                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    messageDigest.update(variation.getBytes());
                    byte[] hashBytes = messageDigest.digest();
                    if (Arrays.equals(_mysteryHash, hashBytes)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    System.out.println(variation + " IS IT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + variation);
                    return;
                }
            }
            if (flag) {
                return;
            }

        }
    }
}
