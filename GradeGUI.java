import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * Creates a GUI to calculate grade.
 * 
 * @author Andrew Olesak
 * @version December 5, 2015
 */
public class GradeGUI extends JFrame implements ActionListener
{

    private Grade grade;

    // define buttons
    private JButton calcGradeButton;
    private JButton setWeightButton;
    private JButton lowestGradeButton;
    private JButton addWeightButton;

    // define text fields
    private JTextField weightField;
    private JTextField gradeField;
    private JTextField finalGradeField;

    // define labels
    private JLabel weightLabel;
    private JLabel gradeLabel;
    private JLabel finalGradeLabel;

    // define results area
    private JTextArea results;

    /**
     * Main method
     */
    public static void main(String args[]){
        GradeGUI g = new GradeGUI();
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.setTitle("Weighted Grade Calculator");
        g.pack();
        g.setVisible(true);
    }

    /**
     * constructor initializes GUI components
     */
    public GradeGUI(){

        grade = new Grade();

        // set the gridbaglayout
        setLayout(new GridBagLayout());
        GridBagConstraints spot = new GridBagConstraints();

        // create and place the results area
        this.results = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(this.results);
        spot.gridx = 0;
        spot.gridy = 1;
        spot.gridheight = 8;
        spot.insets.left = 20;
        spot.insets.bottom = 20;
        add(scrollPane, spot);
        spot = new GridBagConstraints();

        // create and place results label
        spot.gridx = 0;
        spot.gridy = 0;
        spot.insets.bottom = 10;
        add(new JLabel("Results"), spot);

        // create and place the weight label
        this.weightLabel = new JLabel("Weight");
        spot.gridx = 1;
        spot.gridy = 1;
        spot.anchor = GridBagConstraints.WEST;
        spot.insets.bottom = 10;
        spot.insets.left = 20;
        add(this.weightLabel, spot);

        // create and place the grade label
        this.gradeLabel = new JLabel("Grade");
        spot.gridx = 1;
        spot.gridy = 2;
        add(this.gradeLabel, spot);

        // create and place the final grade label
        this.finalGradeLabel = new JLabel("Final Grade");
        spot.gridx = 1;
        spot.gridy = 3;
        add(this.finalGradeLabel, spot);

        // create and place the weight text field
        this.weightField = new JTextField(5);
        spot.gridx = 1;
        spot.gridy = 1;
        spot.insets.left = 90;
        add(this.weightField, spot);

        // create and place the grade text field
        this.gradeField = new JTextField(5);
        spot.gridx = 1;
        spot.gridy = 2;
        add(this.gradeField, spot);

        // create and place the final grade text field
        this.finalGradeField = new JTextField(5);
        spot.gridx = 1;
        spot.gridy = 3;
        add(this.finalGradeField, spot);

        // create and place the set weight button
        this.setWeightButton = new JButton("Set Weight");
        spot.gridx = 1;
        spot.gridy = 4;
        spot.anchor = GridBagConstraints.CENTER;
        add(this.setWeightButton, spot);

        // create and place the add individual weight button
        this.addWeightButton = new JButton("Set individual Weight");
        spot.gridx = 1;
        spot.gridy = 6;
        add(this.addWeightButton, spot);

        // create and place the grade button
        this.calcGradeButton = new JButton("Calculate Final Grade");
        spot.gridx = 1;
        spot.gridy = 5;
        add(this.calcGradeButton, spot);

        // create and place the lowest grade button
        this.lowestGradeButton = new JButton("Calculate Lowest Grade");
        spot.gridx = 1;
        spot.gridy = 7;
        add(this.lowestGradeButton, spot);

        // add action listener
        this.calcGradeButton.addActionListener(this);
        this.setWeightButton.addActionListener(this);
        this.lowestGradeButton.addActionListener(this);
        this.addWeightButton.addActionListener(this);
    }

    /**
     * 
     */
    public void setWeight(){

        // check to make sure text fields aren't blank
        String str1 = this.weightField.getText();
        String str2 = this.gradeField.getText();
        if(str1.length()==0 || str2.length()==0){
            JOptionPane.showMessageDialog(null, "Make sure the weight and grade fields aren't " +
                "blank");
            return;
        }

        // check for valid numbers
        if(!this.checkValidNumber(this.weightField.getText(), "weightfield")){
            return;
        }if(!this.checkValidNumber(this.gradeField.getText(), "gradefield")){
            return;
        }

        // set the weight for an individual grade
        double w = Double.parseDouble(this.weightField.getText());
        double g = Double.parseDouble(this.gradeField.getText());

        this.results.append("Weight: " + w/100 + "  Grade: " + g + "\n");
        grade.calcIndividualGrade(w, g);

        // set the textfields to blank
        this.weightField.setText("");
        this.gradeField.setText("");

    }

    public void setIndividualWeight(){

        // check that the weight field isn't blank
        String str = this.weightField.getText();
        if(str.length()==0){
            JOptionPane.showMessageDialog(null, "make sure the weight field isn't blank");
            return;
        }

        // check that the weight field is a valid number
        if(!this.checkValidNumber(this.weightField.getText(), "weightfield")){
            return;
        }

        // set the individual weight

    }

    public void calcLowestGrade(){

        // check that the weight and final grade fields aren't blank
        String str1 = this.weightField.getText();
        String str2 = this.finalGradeField.getText();
        if(str1.length()==0 || str2.length()==0){
            JOptionPane.showMessageDialog(null, "Make sure the weight and final grade " +
                "fields aren't blank");
            return;
        }

        // check that the weight and final grade are valid numbers
        if(!this.checkValidNumber(this.weightField.getText(), "weightfield")){
            return;
        }if(!this.checkValidNumber(this.finalGradeField.getText(), "finalgradfield")){
            return;
        }

        // check that the percent is equal to 100
        int finalGrade = Integer.parseInt(this.finalGradeField.getText());
        double w = Double.parseDouble(this.weightField.getText());
        double g = grade.calcLowestGrade(w, finalGrade);
        if(g==-1){
            JOptionPane.showMessageDialog(null, "your weights don't add to 100%");
        }else{
            // calculate the lowest grade
            this.results.append("Lowest Grade: " + g);
        }
    }

    /**
     * 
     */
    public void calcFinalGrade(){
        double g = grade.getFinalGrade();
        if(g==-1){
            JOptionPane.showMessageDialog(null, "Your weights don't add to 100%");
        }else{
            this.results.append("\nFinal Grade: " + grade.getFinalGrade());
        }
    }

    /**
     * Method is called when a button is pressed
     */
    public void actionPerformed(ActionEvent e){

        // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        // react to the set weight button pressed
        if(e.getSource() == this.setWeightButton){
            this.setWeight();
        }

        // react to the add individual weight button pressed
        if(e.getSource() == this.addWeightButton){
            this.setIndividualWeight();
        }

        // react to the calc final grade button pressed
        if(e.getSource() == this.calcGradeButton){
            this.calcFinalGrade();
        }

        // react to the calc lowest grade button pressed
        if(e.getSource() == this.lowestGradeButton){
            this.calcLowestGrade();
        }
    }

    /**
     * Check for a valid number
     * 
     * @param numStr the string to be checked
     * @param label the texfield name
     * @return true if valid number
     */
    public boolean checkValidNumber(String numStr, String label){
        boolean isValid = true;
        try{
            double val = Double.parseDouble(numStr);

            // display error message if not a valid integer  
        }catch(NumberFormatException e){
            isValid = false;
            JOptionPane.showMessageDialog(this, "Enter a number in " + label);

        }    
        return isValid;
    }
}
