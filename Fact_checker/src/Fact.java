/*
 * Java class for fact
 */
public class Fact {
	//the subject of this sentence
	String subject;
	//the statement is either:  born,died,author..
	String statement;
	//the object
	String object;
	
	//Constructor
	public Fact(String subject, String statement, String object) {
		super();
		this.subject = subject;
		this.statement = statement;
		this.object = object;
	}
	/*
	 * the getters and setters for variables
	 */
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	
	

}
