import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    	String str = readFile(inputFilePath);
    	char[] arr = str.toCharArray();
    	for(int i = 0; i < arr.length; i++) {
    		if(arr[i] >= 65 && arr[i] <= 90) {
    			arr[i] += shift;
    			if(arr[i] > 90) {
    				arr[i] = (char) (64 + (arr[i]-90));
    			}
    		}
    		else if(arr[i] >= 97 && arr[i] <= 122) {
    			arr[i] += shift;
    			if(arr[i] > 122) {
    				arr[i] = (char) (96 + (arr[i]-122));
    			}
    		}
    		encrypted = String.valueOf(arr);
    		writeFile(encrypted,encryptedFilePath);
    	}
    	
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    	String str = readFile(messageFilePath);
    	char[] arr = str.toCharArray();
    	for(int i = 0; i < arr.length; i++) {
    		if(arr[i] >= 65 && arr[i] <= 90) {
    			arr[i] -= shift;
    			if(arr[i] < 65) {
    				arr[i] = (char) (91 - (65 - arr[i]));
    			}
    		}
    		else if(arr[i] >= 97 && arr[i] <= 122) {
    			arr[i] -= shift;
    			if(arr[i] < 97) {
    				arr[i] = (char) (123 - (97 - arr[i]));
    			}
    		}
    	}
    	writeFile(String.valueOf(arr),decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        //TODO: Read file from filePath
        try(Scanner fileScanner = new Scanner(Paths.get(filePath))){
    		while(fileScanner.hasNextLine()) {
    			message += fileScanner.nextLine() + "\n";
    		}
    	}
    	catch(Exception e) {
    		System.out.println("Error: " + e.toString());
    	}
        return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
    	try(PrintWriter output = new PrintWriter(filePath)){
    		output.println(data);
    		output.close();
    	}
    	catch(Exception e) {
    		System.out.println("Error: " + e.toString());
    	}
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
