/**
 * Info 5101 - Section 8
 * Assignment 6
 * Author: Runlin Liu
 *
 */
public class WeightedGrade {

    //Attributes
    private int pointTotal;

    private int earnedPoints;

    private double assignmentPercentage;


    //Setters and getters
    public int getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(int pointTotal) {
        this.pointTotal = pointTotal;
    }

    public int getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(int earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public double getAssignmentPercentage() {
        return assignmentPercentage;
    }

    public void setAssignmentPercentage(double assignmentPercentage) {
        this.assignmentPercentage = assignmentPercentage;
    }


    //Methods
    public double calculate(){
        //Algorithm
        double rev = (double) getEarnedPoints() / getPointTotal() *  getAssignmentPercentage() *100;
        //Previous version got a mistake, quotient became 0
        //double rev = getEarnedPoints() / getPointTotal() *  getAssignmentPercentage() *100;
        return rev;
    }




}
