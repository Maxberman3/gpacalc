/* Max Berman mb3ax
 * used tons of sources, mostly stack overflow,stack exchange,api,google
 *  Creates a course class which holds all fo the information about each entered course as well
 * as includes some useful methods
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class CalculatorFrame extends JFrame {
	private JFrame calcframe;
	private JPanel panel1,p1sub1,p1sub2,p1sub3,radiobuttonpanel,panel2,p2sub1,panel3;
	private JTextField coursename,gradebox,creditbox,targetinput;
	private JLabel coursenamelabel,gradeboxlabel,creditboxlabel,targetinlabel;
	private JRadioButton past,current,future;
	private ButtonGroup statebuttons;
	private JButton p1button,p2removebutton,p2clearcourses,p2add15blank,p3enter,p3calccurrent,p3calcrequired;
	private JList<Course> courselist;
	private DefaultListModel<Course> listmodel;
	private JScrollPane coursepane;
	private Summary summary;
	public double tgtgpa;
	
	public CalculatorFrame() {
		calcframe=new JFrame();
		calcframe.setLayout(new BorderLayout());
		calcframe.setTitle("GPA Calculator");
		calcframe.setBounds(500, 200,1000,800);
		calcframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel1=new JPanel();
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
		coursename=new JTextField();
		coursename.setPreferredSize(new Dimension(150,40));
		coursenamelabel=new JLabel();
		coursenamelabel.setText("Course Name");
		coursenamelabel.setLabelFor(coursename);
		gradebox=new JTextField();
		gradebox.setPreferredSize(new Dimension(150,40));
		gradeboxlabel=new JLabel();
		gradeboxlabel.setText("Grade");
		gradeboxlabel.setLabelFor(gradebox);
		creditbox=new JTextField();
		creditbox.setPreferredSize(new Dimension(150,40));
		creditboxlabel=new JLabel();
		creditboxlabel.setText("Credits");
		creditboxlabel.setLabelFor(creditbox);
		radiobuttonpanel=new JPanel(new FlowLayout());
		past=new JRadioButton("Past");
		current=new JRadioButton("Current",true);
		future=new JRadioButton("Future");
		statebuttons=new ButtonGroup();
		statebuttons.add(past);
		statebuttons.add(current);
		statebuttons.add(future);
		radiobuttonpanel.add(past);
		radiobuttonpanel.add(current);
		radiobuttonpanel.add(future);
		p1button=new JButton("Enter");
		P1EnterListener p1listener=new P1EnterListener();
		p1button.addActionListener(p1listener);
		p1sub1=new JPanel();
		p1sub2=new JPanel();
		p1sub3=new JPanel();
		p1sub1.setLayout(new FlowLayout());
		p1sub2.setLayout(new FlowLayout());
		p1sub3.setLayout(new FlowLayout());
		p1sub1.add(coursenamelabel);
		p1sub1.add(coursename);
		p1sub2.add(gradeboxlabel);
		Dimension fillbox1= new Dimension(39,0);
		p1sub2.add(new Box.Filler(fillbox1,fillbox1,fillbox1) );
		p1sub2.add(gradebox);
		p1sub3.add(creditboxlabel);
		Dimension fillbox2= new Dimension(38,0);
		p1sub3.add(new Box.Filler(fillbox2, fillbox2, fillbox2));
		p1sub3.add(creditbox);
		panel1.add(p1sub1);
		panel1.add(p1sub2);
		panel1.add(p1sub3);
		panel1.add(radiobuttonpanel);
		panel1.add(p1button);
		Dimension fillbox3=new Dimension(0,60);
		panel1.add(new Box.Filler(fillbox3, fillbox3, fillbox3));
		calcframe.add(panel1,BorderLayout.WEST);
		panel2=new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		summary=new Summary();
		listmodel=new DefaultListModel<Course>(); 
		courselist=new JList<Course>(listmodel);
		courselist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		courselist.setLayoutOrientation(JList.VERTICAL);
		courselist.setPreferredSize((new Dimension(300,400)));
		coursepane=new JScrollPane(courselist);
		p2removebutton=new JButton("Remove Course");
		P2RemoveListener removelistener=new P2RemoveListener();
		p2removebutton.addActionListener(removelistener);
		p2clearcourses=new JButton("Clear Courses");
		P2ClearListener clearlistener=new P2ClearListener();
		p2clearcourses.addActionListener(clearlistener);
		p2add15blank=new JButton("Add 15 Blank Credits");
		Add15BlankListener blanklistener=new Add15BlankListener();
		p2add15blank.addActionListener(blanklistener);
		p2sub1=new JPanel(new FlowLayout());
		p2sub1.add(p2removebutton);
		p2sub1.add(p2clearcourses);
		p2sub1.add(p2add15blank);
		panel2.add(coursepane);
		panel2.add(p2sub1);
		calcframe.add(panel2, BorderLayout.EAST);
		panel3=new JPanel(new FlowLayout());
		targetinlabel=new JLabel("Enter Target GPA");
		targetinput=new JTextField();
		targetinput.setPreferredSize(new Dimension(149,50));
		p3enter=new JButton("Enter");
		P3EnterListener tgtenterlistener=new P3EnterListener();
		p3enter.addActionListener(tgtenterlistener);
		p3calccurrent=new JButton("Calculate Current GPA");
		CalculateCurrentListener currentlistener=new CalculateCurrentListener();
		p3calccurrent.addActionListener(currentlistener);
		p3calcrequired=new JButton("Calculate Required GPA");
		CalculateRequiredListener requiredlistener=new CalculateRequiredListener();
		p3calcrequired.addActionListener(requiredlistener);
		panel3.add(targetinlabel);
		panel3.add(targetinput);
		panel3.add(p3enter);
		panel3.add(p3calccurrent);
		panel3.add(p3calcrequired);
		calcframe.add(panel3, BorderLayout.SOUTH);
		
		
		calcframe.pack();
		calcframe.setVisible(true);
		
	}
	private class P1EnterListener implements ActionListener{
//gets the input from panel1, the course entry area. checks to see if all the input is okay,if not the it prompts
		//the user. adds it to both the summary object aand the list mod
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			char time=' ';
			int credits;
			double grade;
			String name;
			if(past.isSelected()) {
				time='p';
			}
			else if(current.isSelected()) {
				time='c';
			}
			else if(future.isSelected()) {
				time='f';
			}
			if(!gradebox.getText().trim().isEmpty()) {
				try {
				grade=Integer.parseInt(gradebox.getText());
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null,"Please input a integer on the 4 point scale","Incorrect Input",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(grade>4) {
					JOptionPane.showMessageDialog(null,"Please input a integer on the 4 point scale","Incorrect Input",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else {
				grade=5.0;
			}
			if(coursename.getText().trim().isEmpty()) {
				name="Unnamed Course";
			}
			else {
				name=coursename.getText();
			}
			if(creditbox.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "The Credit input was empty, please input credit hours");
				return;
			}
			else {
				try {
					credits=Integer.parseInt(creditbox.getText());
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,"Please input a integer","Incorrect Input",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			summary.AddCourse(new Course(credits,grade,name,time));
			listmodel.addElement(new Course(credits,grade,name,time));
		}
		
	}
	private class P2RemoveListener implements ActionListener {
//the remove course button for panel 2
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			listmodel.removeElement(courselist.getSelectedValue());
			summary.RemoveCourse(courselist.getSelectedValue());
		}
		
	}
	private class P2ClearListener implements ActionListener{
//the clear button for panel 2
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			summary.RemoveAllCourses();
			listmodel.clear();
		}
		
	}
	private class Add15BlankListener implements ActionListener{
		//the add 15 blank button. I set up my course object's default constructor to fit this
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			listmodel.addElement(new Course());
			summary.AddCourse(new Course());
		}
		
	}
	private class P3EnterListener implements ActionListener{
//checks that the input is good, and if so enters it as the summary's tgt gpa value
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(targetinput.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null,"The input was empty","Incorrect Input",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				try {
					summary.setTgtgpa(Double.parseDouble(targetinput.getText()));
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,"Please input a double","Incorrect Input",JOptionPane.ERROR_MESSAGE);
					return;
				}
			if(summary.getTgtgpa()>4) {
				JOptionPane.showMessageDialog(null,"You entered a GPA above 4.0","Incorrect Input",JOptionPane.ERROR_MESSAGE);
				return;
			}
			}
		}
		
	}
	private class CalculateCurrentListener implements ActionListener{
//calculates the current gpa excluding future courses
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			double currentgpa=summary.CurrentGpa();
			String output="Your current gpa is: "+currentgpa;
			JOptionPane.showMessageDialog(null, output);
			return;
		}
		
	}
	private class CalculateRequiredListener implements ActionListener{
//calculates the required gpa in "blank" courses. I purposely let the user enter future classes with a grade
		//so they could have the functionality of "assuming" they are going to get a certain grade
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			double requiredgpa=summary.RequiredGpa();
			for(int i=0;i<listmodel.size();i++) {
				if(listmodel.get(i).CheckIfBlank()) {
					listmodel.get(i).setGrade(requiredgpa);
				}
			}
			if(requiredgpa>4) {
				String output1="With a target gpa of "+summary.getTgtgpa()+" you would need a gpa of "+requiredgpa+" Consider adding more classes ";
				JOptionPane.showMessageDialog(null,output1);
				courselist.repaint();
				return;
			}
			if(requiredgpa<2) {
				String output2="With a target gpa of "+summary.getTgtgpa()+" you would only need a gpa "+requiredgpa+", you could consider taking it a little easy";
				JOptionPane.showMessageDialog(null, output2);
				courselist.repaint();
				return;
			}
			String output3="With a target gpa of "+summary.getTgtgpa()+" you would need a gpa of "+requiredgpa;
			JOptionPane.showMessageDialog(null,output3);
			courselist.repaint();
			return;
			
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		new CalculatorFrame();

	}

}
