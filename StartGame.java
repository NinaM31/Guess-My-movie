import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class StartGame {
	static Scanner read = new Scanner(System.in);
	static final String fileName = "GuessMyMovie.sev";
	public static void main(String[] args) {
		clearScreen();	
		start();
	}
//--------------------------- Prompting the user --------------------------------	
	public static void prompt() {
		System.out.println("		          (0)   )");
		System.out.println("		    (     (OOO)    )");
		System.out.println("		           |");
		System.out.println("		    _______|___________");
		System.out.println("		    |  ||O|     ||O|   |		");
		System.out.println("		    | |o||||   |o||||  |");
		System.out.println("		    |  ||o|     ||o|   |");
		System.out.println("		    |________U_________|");
		System.out.println("		       ____|--|____          ");
		System.out.println("		       | Hi World! |");
		System.out.println("		____GUESS MY MOVIE GAME____");
		System.out.println();
		System.out.println();
		
		System.out.println("Press X to Exit");
		System.out.println("Press P to Play");
		System.out.println("Press L to Load");
		System.out.println("Press D to View Memory");
		System.out.println("Press S to Save ");
	}
//---------------Using the memories ability to learn------------------------------
	public static void learn(Memory brain , char ans) {
		System.out.println("we learn much from defeat!...\nWhat is your movie's name? ");
		String nameMovie = read.next();
		nameMovie += read.nextLine();
		System.out.println("What Question would you ask about this movie?");
		String question = read.nextLine();
		String answer ="";
		while(true) {
			System.out.println("How would you reply to that?\nY: yes	N:no");
			answer = read.next();
			if(answer.toLowerCase().charAt(0) == 'y' || answer.toLowerCase().charAt(0)  =='n')
				break;
			else
				System.out.println("Please choose provided character");
		}
		String category  = "";
		if(!brain.currentQuestion.category.equals("") && (ans != 'y')) {
			System.out.println("What is the category of the movie?");
			category = read.next() + read.nextLine();
		}	
		brain.learn(nameMovie, question, answer.charAt(0), category, ans);
		System.out.println("Learnt!");		
	}
//----------------------- GAME ON !! ----------------------------------------------------
	public static void letsPlay(Memory brain) {
		String ans= "";		
		while(true) {
			brain.ask();
			ans = read.next();
			ans += read.nextLine();
			char answer = ans.toLowerCase().charAt(0);
			if(answer == 'x') {
				System.out.println("Exiting the game");
				System.exit(1);
			}			
			if(brain.ifWon(answer) == 'y') {
				System.out.println("I Know Everything ^_^");
				break;
			}
			else if ( brain.ifWon(answer) == 'c') {
				switch(answer) {
				case 'y':
					brain.nextQuestion(true);
					break;
				case 'n':
					brain.nextQuestion(false);
					break;
				}
			}else {
				learn(brain, answer);
				break;
			}
		}
	}
//---------------This is where the Features of the game will be chosen--------------------------	
	public static void start() {
		int end = 0;
		Memory brain = new Memory();
		brain.beggining("Is it an animation movie?", null, null, "animation");
		
		while(end == 0) {
			prompt();
			switch(read.next().toLowerCase().charAt(0)) {
			case 'x':
					end = 1;
				break;
			case 'p': 
					clearScreen();
					letsPlay(brain);
					brain.currentQuestion = brain.firstQuestion;	
				break;
			case 'd':
					brain.printBinaryTree(brain.firstQuestion, 0);
				break;
			case 's':
				save(brain);
				System.out.println("Saved");
				
				break;
			case 'l':
				brain = load(brain);
				System.out.println("Loaded");
				break;
			default :
				System.out.println("Please Choose provided character");
				break;
			}
		}		
	}
//------------------------------- Save and load -----------------------------------------------------
	public static void save(Serializable object) {
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	public static Memory load(Memory brain) {
		if(checkFile()) {			
			FileInputStream fis = null;			
			try {
				fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Memory memory = (Memory) ois.readObject();
				brain = memory;
				ois.close();
				return brain;
			} catch (ClassNotFoundException  | IOException e) {
				e.printStackTrace();
			}	
		}
		return brain;
	}
	public static boolean checkFile() {
		return new File(fileName).isFile();
	}
//-----------------------Cleaning the screen-----------------------------------
	   public static void clearScreen() {
	        try {
	            new ProcessBuilder(new String[] { "cmd", "/c", "cls" }).inheritIO().start().waitFor();
	        }
	        catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        catch (IOException e2) {
	            e2.printStackTrace();
	        }
	    }
}
