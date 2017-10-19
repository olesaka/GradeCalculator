
/**
 * Calculates weighted grades.
 * 
 * @author Andrew Olesak 
 * @version December 5, 2015
 */
public class Grade
{
    private double totalWeight;
    private double grade;
    private int percent;

    /**
     * Constructor initializes instance variables
     */
    public Grade(){
        this.totalWeight = 0.0;
        this.grade = 0.0;
        this.percent = 0;
    }

    /**
     * Calculates an individual weighted grade
     * @param w the weight of the grade
     * @param g the grade to be associated with the weight
     */
    public void calcIndividualGrade(double w, double g){
        this.percent += w;
        w = w/100;
        this.totalWeight += w * g;
    }

    /**
     * @return the finalGrade
     */
    public double getFinalGrade(){
        if(this.percent==100){
            return this.totalWeight;
        }else{
            return -1;
        }
    }

    /**
     * Calculates the lowest grade
     * @param w the weight
     * @param finalGrade the lowest grade allowed to recieve
     * @return the lowest grade allowed to get if allowed
     */
    public double calcLowestGrade(double w, int finalGrade){
        this.percent += w;
        w /= 100;
        if(this.percent==100){
            this.grade = finalGrade - this.totalWeight;
            this.grade /= w;
            return this.grade;
        }else{
            return -1;
        }
    }
}
