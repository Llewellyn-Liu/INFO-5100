/**
 * Info 5101 - Section 8
 * Assignment 3 Based on Assignment 2 code
 * Author: Runlin Liu
 *
 */
public class WeightedGradeEnhanced{

    private int[] pointTotalForEach;

    private int[] gradeSheet;

    private double[] percentageForEach;

    private int assignmentAmount = 8;


    //Constructor
    public WeightedGradeEnhanced(){

        pointTotalForEach = new int[assignmentAmount];

        gradeSheet = new int[assignmentAmount];

        percentageForEach = new double[assignmentAmount];
    }

    public WeightedGradeEnhanced(int assignmentAmount){
        this.assignmentAmount = assignmentAmount;

        pointTotalForEach = new int[assignmentAmount];

        gradeSheet = new int[assignmentAmount];

        percentageForEach = new double[assignmentAmount];
    }

    //Getters and setters
    public int[] getPointTotalForEach() {
        return pointTotalForEach;
    }

    public void setPointTotalForEach(int[] pointTotalForEach) {
        this.pointTotalForEach = pointTotalForEach;
    }

    public int[] getGradeSheet() {
        return gradeSheet;
    }

    public void setGradeSheet(int[] gradeSheet) {
        this.gradeSheet = gradeSheet;
    }

    public double[] getPercentageForEach() {
        return percentageForEach;
    }

    public void setPercentageForEach(double[] percentageForEach) {
        this.percentageForEach = percentageForEach;
    }

    public int getAssignmentAmount() {
        return assignmentAmount;
    }

    public void setAssignmentAmount(int assignmentAmount) {
        this.assignmentAmount = assignmentAmount;
    }

    //Methods
    public double calculateFinal(){
        double sum = 0;
        for(int i = 0; i < assignmentAmount; i++){
            WeightedGrade weightedGrade = new WeightedGrade();
            weightedGrade.setPointTotal(pointTotalForEach[i]);
            weightedGrade.setEarnedPoints(gradeSheet[i]);
            weightedGrade.setAssignmentPercentage(percentageForEach[i]);

            sum += weightedGrade.calculate();
        }
        return sum;
    }

    public void setPointTotalForEachAt(int value, int offset){
        this.pointTotalForEach[offset] = value;
    }

    public void setGradeSheetAt(int value, int offset){
        this.gradeSheet[offset] = value;
    }

    public void setPercentageForEachAt(double value, int offset){
        this.percentageForEach[offset] = value;

        double sum = 0;
        if(offset == assignmentAmount){
            for(int i = 0; i<assignmentAmount; i++){
                sum+= this.percentageForEach[i];
            }
        }
        if(sum>1){
            System.out.println("Total percentage greater than 1");
        }
        else if(sum< 1){
            System.out.println("Total percentage smaller than 1");
        }
    }

}
