/* Max Berman mb3ax
 * used tons of sources, mostly stack overflow,stack exchange,api,google
 *  Creates a course class which holds all fo the information about each entered course as well
 * as includes some useful methods
 */
import java.util.ArrayList;
/*Creates a summary class which is a summary of all the entered courses as well as holds methods that involves
 * summarizing the courses(gpa,etc)
 */
public class Summary {
	private ArrayList<Course> entered;
	private double tgtgpa;
	public Summary() {
		entered=new ArrayList<Course>();
		tgtgpa=4.0;
	}
	public boolean RemoveCourse(Course c) {
		boolean flag=false;
		for(Course lesson:entered) {
			if(lesson.equals(c)) {
				entered.remove(c);
				flag=true;
			}
		}
		if(flag) {
			return true;
		}
		return false;
	}
	public boolean RemoveAllCourses() {
		entered.clear();
		return true;
	}
	public double getTgtgpa() {
		return tgtgpa;
	}
	public void setTgtgpa(double tgtgpa) {
		this.tgtgpa = tgtgpa;
	}
	public double CurrentGpa() {
		double creditsum=0;
		double gradepoints=0;
		double currentgpa;
		for(Course c:entered) {
			if(c.getGrade()<=4 && c.getState()=='c') {
				creditsum+=c.getCredits();
				gradepoints+=(c.getCredits()*c.getGrade());
			}
		}
		currentgpa=gradepoints/creditsum;
		return currentgpa;
	}
	public double RequiredGpa() {
		double currentcreditsum,blankcreditsum,currentgradepoints,gradepointsneeded,requiredgpa;
		currentcreditsum=0;
		blankcreditsum=0;
		currentgradepoints=0;
		for(Course c :entered) {
			if(c.CheckIfBlank()) {
				blankcreditsum+=c.getCredits();
			}
			else if(c.getGrade()<=4) {
				currentcreditsum+=c.getCredits();
				currentgradepoints+=(c.getCredits()*c.getGrade());
			}

		}
		gradepointsneeded=(((tgtgpa)*(blankcreditsum+currentcreditsum))-currentgradepoints);
		requiredgpa=(gradepointsneeded/blankcreditsum);
		return requiredgpa;
	}
	public boolean AddCourse(Course c) {
		entered.add(c);
		return true;
	}
	/*this is an artifact, at the beginning I though jlist could take an array of courses directly,turns out I need a list model to edit*/
	public Course[] ToArray() {
		Course[] courses= new Course[entered.size()];
		for(int i=0;i<entered.size();i++) {
			courses[i]=entered.get(i);
		}
		return courses;
	}


}
