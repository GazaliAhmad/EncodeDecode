import java.util.*;

import static java.lang.System.*;

public class Encrypt {
	
	private final Scanner scanner;
	private final Random random;
	private final ArrayList<Character> list;
	private ArrayList<Character> shuffledList;
	private char[] letters;
	
	Encrypt() {
		scanner = new Scanner(System.in);
		random = new Random();
		list = new ArrayList<>();
		shuffledList = new ArrayList<>();
		
		newKey();
		getChoice();
	}
	
	@SuppressWarnings("InfiniteLoopStatement")
	private void getChoice() {
		try {
			while (true) {
				out.print("\nWhat would you like to do? (e)ncrypt, (d)ecrypt, (n)ew key, (g)et Key (q)uit:\n");
				char response = Character.toLowerCase(scanner.nextLine().charAt(0));
				switch (response) {
					case 'e' -> encrypt();
					case 'd' -> decrypt();
					case 'n' -> newKey();
					case 'g' -> getKey();
					case 'q' -> quit();
					default -> out.println("Invalid choice.");
				}
			}
		} catch (Exception e) {
			getChoice();
		}
	}
	@SuppressWarnings("ReassignedVariable")
	private void newKey () {
		var character = ' ';
		list.clear();
		shuffledList.clear();
		
		for (var i = 32; i < 127; i++) {
			list.add(character);
			character++;
		}
		
		shuffledList = new ArrayList<>(list);
		Collections.shuffle(shuffledList, random);
	}
			
	private void getKey () {
		out.println("Key: ");
		for (Character x : list){
			out.print(x);
		}
		out.println();
		for (Character x : shuffledList){
			out.print(x);
		}
		out.println();
	}
			
	private void encrypt () {
		out.println("Enter a message to encrypt: ");
		var message = scanner.nextLine();
		
		letters = message.toCharArray();
		
		for(var i = 0; i < letters.length; i++) {
			
			for(var j =0; j < list.size(); j++) {
				if (letters[i] == list.get(j)) {
					letters[i] = shuffledList.get(j);
					break;
				}
			}
		}
		err.println("Encrypted message: ");
		for (var x : letters) {
			out.print(x);
		}
		out.println();
	}
	
	private void decrypt () {
		out.println("Enter a message to decrypt: ");
		var message = scanner.nextLine();
		
		letters = message.toCharArray();
		
		for(var i = 0; i < letters.length; i++) {
			
			for(var j =0; j < shuffledList.size(); j++) {
				if (letters[i] == shuffledList.get(j)) {
					letters[i] = list.get(j);
					break;
				}
			}
		}
		err.println("Decrypted message: ");
		for (var x : letters) {
			out.print(x);
		}
		out.println();
	}
	
	private void quit () {
		out.println("Encrypt quit");
		exit(0);
	}
}


