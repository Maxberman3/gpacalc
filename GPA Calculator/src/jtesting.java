/* Max Berman mb3ax
 * used tons of sources, mostly stack overflow,stack exchange,api,google
 *  Creates a course class which holds all fo the information about each entered course as well
 * as includes some useful methods
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
//some junit tests I set up early on. i had one for required gpa as well but I took it out after I edited the method
//to accomodate for something i changed in the gui.
public class jtesting {
	Summary classes;
	Course math,chem,next_semester;
	@Before
	public void setUp(){
		math=new Course(4,4.0,"math",'p');
		chem=new Course(4,2.0,"chem",'p');
		classes=new Summary();
		classes.AddCourse(math);
		classes.AddCourse(chem);
		classes.AddCourse(new Course());
		}
	@Test
	public void CurrentGpa() {
		assertTrue(3.0==classes.CurrentGpa());
	}
	@Test
	public void ToString() {
		System.out.println(math.toString());
	}

}
