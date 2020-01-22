import java.io.Serializable;

public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	String question;
	String category;
	Question yes;
	Question no;
	boolean isQuestion;
	
	public Question(String q, boolean iQ) {
		question = q;
		isQuestion = iQ;
		yes = no = null;
		category ="";
	}
	public Question copy(Question q) {
		Question copy = new Question(q.question, q.isQuestion);
		copy.category = q.category;
		copy.yes = q.yes;
		copy.no = q.no;
		return copy;
	}
}
