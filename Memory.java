import java.io.Serializable;

public class Memory implements Serializable {
	private static final long serialVersionUID = 1L;
	Question firstQuestion;
	Question currentQuestion;
	static String path = "";
//------------------Methods used to move around the memory-----------------------------
	public void nextQuestion(boolean q) {
		if(currentQuestion.yes != null && q) 
			currentQuestion = currentQuestion.yes;
		else if(currentQuestion.no != null)
			currentQuestion = currentQuestion.no;
	}
	public char ifWon(char yes) {
		if(((currentQuestion.yes == null && yes == 'y' ) || (currentQuestion.no == null  && yes == 'n')) && currentQuestion.isQuestion)
			return 'n';		
		if(!currentQuestion.isQuestion && yes == 'y')
			return 'y';
		else if (!currentQuestion.isQuestion && yes == 'n')
			return 'n';		
		return 'c';
	}	
//-------------------------------YOU LOSE BUT YOU LEARN ---------------------------------------------
	public void learn(String movie, String question, char answer, String category, char ans) {
		question += question.charAt(question.length()-1) == '?' ? "":"?" ; 
		char firstL = ' ';
		String categoryQ = "";
		if(!category.equals("")) {
			firstL = (category.toLowerCase().charAt(0));
			categoryQ = "Is it " +( firstL == 'a' || firstL  == 'u' || firstL == 'i' || firstL == 'e' ? "an " : "a ") + category + " movie ?";
		}
		if(currentQuestion == firstQuestion ) {
			if(ans == 'y') {
				currentQuestion.yes =  new Question(question, true);
				currentQuestion = currentQuestion.yes;
				memorize(answer, null, movie);	
			}else {
				currentQuestion.no= new Question(categoryQ, true);
				currentQuestion = currentQuestion.no;
				currentQuestion.yes = new Question(question, true);
				currentQuestion.category = category;
				currentQuestion = currentQuestion.yes;
				if(answer == 'y') {
					currentQuestion.yes = new Question(movie, false);
				}else {
					currentQuestion.yes = new Question(movie, false);
				}
			}		
		}
		else {
		if(!category.equals("")) {
			Question tmp = currentQuestion.copy(currentQuestion);
			currentQuestion.question = categoryQ;
			currentQuestion.isQuestion = true;
			currentQuestion.yes = new Question(question, true);
			currentQuestion.category = category;
			Question currentP = currentQuestion;
			currentQuestion = currentQuestion.yes;
			if(answer == 'y') {
				currentQuestion.yes = new Question(movie, false);
			}else {
				currentQuestion.no = new Question(movie, false);
			}
			currentQuestion = currentP;
			currentQuestion.no = tmp;		
		}else {	
			Question tmp = currentQuestion.copy(currentQuestion);
			currentQuestion.question = question;
			currentQuestion.isQuestion = true;
			memorize(answer, tmp, movie);
			}	
		}
	}
	public void memor(char ans, String question, String category) {
		if(ans == 'y') {
			currentQuestion.yes = new Question(question, true);
			currentQuestion = currentQuestion.yes;
			currentQuestion.category = category;	
		}else {
			currentQuestion.no= new Question(question, true);
			currentQuestion = currentQuestion.no;	
			
		}
	}
	public Question memorize(String question) {
		Question tmp = currentQuestion.copy(currentQuestion);
		currentQuestion.question = question;
		currentQuestion.isQuestion = true;
		return tmp;
	}
	public void memorize(char answer, String movie) {
		if(answer == 'y') {
			currentQuestion.yes = new Question(movie, false);
		}else {
			currentQuestion.no = new Question(movie, false);
		}
	}
	public void memorize(char answer, Question temp, String movie) {
		if(answer == 'y') {
			currentQuestion.yes = new Question(movie, false);
			currentQuestion.no = temp;
		}else {
			currentQuestion.no = new Question ( movie, false);
			currentQuestion.yes = temp;	
		}	
	}	
//-----------------------necessary Methods--------------------------------------------------------
	public void beggining(String firstQ, String yes, String no, String category) {
		firstQuestion = new Question(firstQ, true);
		firstQuestion.category = category;
		currentQuestion = firstQuestion;
	}
	public void ask() {
		if(!currentQuestion.isQuestion) 
			System.out.println("Is the name of the movie " + currentQuestion.question + "?");	
		else
			System.out.println(currentQuestion.question);
		System.out.println("N:no	 Y:yes	  X:exit");
	}	
//-------------------------Printing the tree-------------------------------------
	public void printBinaryTree(Question root, int level) {
	     if (root == null) 
	          return;
	     printBinaryTree(root.yes, level + 1);
	     if (level != 0) {
	    	 for (int i = 0; i < level - 1; ++i) 
	    		 System.out.print("|\t");
	         System.out.println("|-------" + root.question);
	        }
	      else 
	    	  System.out.println(root.question);
	      printBinaryTree(root.no, level + 1);
	    }
}

