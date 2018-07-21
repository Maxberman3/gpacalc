/* Max Berman mb3ax
 * used tons of sources, mostly stack overflow,stack exchange,api,google
 *  Creates a course class which holds all fo the information about each entered course as well
 * as includes some useful methods
 */
public class Course {
	private int credits;
	private double grade;
	private String name;
	private char state;
	
	public Course() {
		credits=15;
		grade=5;
		name="Blank Credit Hours";
		state='f';
	}
	public Course (int credits,double grade,String name,char state) {
		this.credits=credits;
		this.grade=grade;
		this.state=state;
		if(state=='f'&&name.equals("Unnamed Course")) {
			this.name="Blank Credit Hours";
		}
		else {
			this.name=name;
		}
	}
	
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}
	//checks to see if it's a "blank course", which I defined as an unnamed class in the future, which is used
	//to calculate required gpa
	public boolean CheckIfBlank(){
		if(state=='f'&&name.equals("Blank Credit Hours")) {
			return true;
		}
		return false;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Course) {
			if(((Course) o).getCredits()==this.credits && ((Course) o).getName().equals(this.name) && ((Course) o).getState()==this.state && ((Course) o).getGrade()==this.grade) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		if(grade>4) {
			return "Name: "+name+" Credits: "+credits+" Grade: "+" Past/Current/Future: "+state;
		}
		return "Name: "+name+" Credits: "+credits+" Grade: "+grade+" Past/Current/Future: "+state; 
	}

}
