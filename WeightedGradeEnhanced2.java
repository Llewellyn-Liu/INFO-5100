import java.util.LinkedList;

/**
 * Info 5101 - Section 8
 * Assignment 4 Based on Assignment 2, 3 code
 * Author: Runlin Liu
 *
 */
public class WeightedGradeEnhanced2 {

    private LinkedList<Integer> pointTotalForEach;

    private LinkedList<Integer> gradeSheet;

    private LinkedList<Double> percentageForEach;


    //Constructor
    public WeightedGradeEnhanced2(){

        pointTotalForEach = new LinkedList();

        gradeSheet = new LinkedList();

        percentageForEach = new LinkedList();
    }


    //Getters and setters


    public LinkedList<Integer> getPointTotalForEach() {
        return pointTotalForEach;
    }

    public void setPointTotalForEach(LinkedList<Integer> pointTotalForEach) {
        this.pointTotalForEach = pointTotalForEach;
    }

    public LinkedList<Integer> getGradeSheet() {
        return gradeSheet;
    }

    public void setGradeSheet(LinkedList<Integer> gradeSheet) {
        this.gradeSheet = gradeSheet;
    }

    public LinkedList<Double> getPercentageForEach() {
        return percentageForEach;
    }

    public void setPercentageForEach(LinkedList<Double> percentageForEach) {
        this.percentageForEach = percentageForEach;
    }

    public int getAssignmentAmount() {
        return pointTotalForEach.size();
    }


    //Methods
    //calculate the total final score(Assignment 3)
    public double calculateFinal(){
        if(pointTotalForEach.size()==gradeSheet.size()&&gradeSheet.size()==percentageForEach.size()){
            double sum = 0;
            for(int i = 0; i < pointTotalForEach.size(); i++){
                WeightedGrade weightedGrade = new WeightedGrade();
                weightedGrade.setPointTotal(pointTotalForEach.get(i));
                weightedGrade.setEarnedPoints(gradeSheet.get(i));
                weightedGrade.setAssignmentPercentage(percentageForEach.get(i));

                sum += weightedGrade.calculate();
            }
            return sum;
        }
        else{
            System.out.printf("Length don't match in 3 sets:\nTotal points:%d inputs, Earned Points: %d inputs, Percentage: %d inputs.",pointTotalForEach.size(),gradeSheet.size(),percentageForEach.size());
            return 0;
        }

    }

    //Updated method for Assignment 4
    public void setPointTotalForEachAt(int value, int offset){
        this.pointTotalForEach.set(offset, value);
    }

    public void setGradeSheetAt(int value, int offset){
        this.gradeSheet.set(offset, value);
    }

    public void setPercentageForEachAt(double value, int offset){
        this.percentageForEach.set(offset, value);
        //Verification part deleted, for total tasks numbers never know.
    }

    //New methods in Assignment 4
    public void insertMultiplePointTotalAt(LinkedList<Integer> values, int index){
        pointTotalForEach.addAll(index, values);
    }
    public void insertMultipleGradeAt(LinkedList<Integer> values, int index){
        gradeSheet.addAll(index, values);
    }
    public void insertMultiplePercentageAt(LinkedList<Double> values, int index){
        percentageForEach.addAll(index, values);
    }
    public void insertMultiplePointTotal(LinkedList<Integer> values){
        pointTotalForEach.addAll(values);
    }
    public void insertMultipleGrade(LinkedList<Integer> values){
        gradeSheet.addAll(values);
    }
    public void insertMultiplePercentage(LinkedList<Double> values){
        percentageForEach.addAll(values);
    }
    public void insertMultiplePointTotal(int value){
        pointTotalForEach.add(value);
    }
    public void insertMultipleGrade(int value){
        gradeSheet.add(value);
    }
    public void insertMultiplePercentage(double value){
        percentageForEach.add(value);
    }

    public boolean checkTheInput(){
        boolean rev = true;
        if(this.gradeSheet.size() == this.percentageForEach.size() && this.percentageForEach.size() == this.pointTotalForEach.size());
        else {
            System.out.println("Size don't match in 3 sets.");
            rev = false;
        }
        double sum = 0;
        for(double d: this.percentageForEach){
            sum+=d;
        }
        if(sum != 1.0){
            rev = false;
            System.out.println("Sum of percentage doesn't equals to 1. ");
        }
        return rev;
    }

    public void result() {
        double sum = this.calculateFinal();;
        String grade = "";

        if(sum >= 90 && sum <= 100){
            grade = "A";
        } else if (sum >= 80) {
            grade = "B";
        } else if (sum >= 70) {
            grade = "C";
        } else if (sum >= 60) {
            grade = "D";
        }
        else
            grade = "F";
        System.out.printf("The student earned: %.3f in total, grade %s", sum, grade);
    }
}
