import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class hangman{
	static String[] words = getWords();
	static String targetWord = words[(int) (Math.random() * words.length)]; // pick random word
	static String dash = new String(new char[targetWord.length()]).replace("\0", "-"); // replace letters with -
	static int count = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (count < 7 && dash.contains("-")) {
			System.out.println("Guess any letter in the word");
			System.out.println(dash);
			String guess = sc.next(); // user's guess
			hang(guess);
		}
		sc.close();
}
	private static String[] getWords() {
		// Load the file and read it

		String[] lines = null; // Array we'll return holding all the words

		try {
			String path = "Words.txt";

			// Creating an array of strings, one for each line in the file
			lines = new String(Files.readAllBytes(Paths.get(path))).split("\\r?\\n");

		}
		catch (IOException ex){
			ex.printStackTrace();
		}

		return lines; 
	} 
	public static void hang(String guess) {
		String newDash = "";
		for (int i = 0; i < targetWord.length(); i++) {
			if (targetWord.charAt(i) == guess.charAt(0)) { // if user guesses a letter correctly
				newDash += guess.charAt(0); // replace dash with letter
			} else if (dash.charAt(i) != '-') { // show letters in the word that the user guessed correctly
				newDash += targetWord.charAt(i);
			} else {
				newDash += "-";
			}
		}

		if (dash.equals(newDash)) {
			count++;
			
		} else {
			dash = newDash;
		}
		if (dash.equals(targetWord)) {
			System.out.println("Correct! You win! The word was " + targetWord);
		}
		if (count == 7){
			System.out.println("GAME OVER! The word was " + targetWord);
		}
	}
}
