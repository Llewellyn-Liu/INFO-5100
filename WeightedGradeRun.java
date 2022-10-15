/**
 * Info 5101 - Section 8
 * Assignment 4 Based on Assignment 2, 3 code
 * Author: Runlin Liu
 *
 */
import java.util.LinkedList;
import java.util.Scanner;

public class WeightedGradeRun {

    public static void main(String[] args) {

        System.out.println("Assignment 3. Calculating the final score.");
        Scanner input = new Scanner(System.in);

        //Get an instance of process, named "task".
        WeightedGradeEnhanced2 task = new WeightedGradeEnhanced2();

        //Read total scores. Conduct in both multiple inputs and single input method
        System.out.println("Input for total points for each task. " +
                "(Split the numbers with ',' if multiple inputs. For certain position to insert, split the position with ';'.\n" +
                "Eligible formats(Integer): \n2; 10, 20, 30\n10, 20, 30\n10.");
        while (true){
            //Read the user's format
            boolean isMultiple = false;
            boolean certainPosition = false;
            System.out.println("Input: ");
            String getValues = input.nextLine();
            if(getValues.contains(",")) isMultiple = true;
            if(getValues.contains(";")) certainPosition = true;

            //Read total points for each assignment
            //Read by format
            if(isMultiple){
                LinkedList<Integer> multipleInputs = new LinkedList<>();

                if(certainPosition){
                    String[] getPosition = getValues.split(";");
                    int positionToInsert = Integer.parseInt(getPosition[0]);
                    String[] inputsStr = getPosition[1].split(",");
                    for(int i = 0; i<inputsStr.length; i++){
                        multipleInputs.add(Integer.parseInt(inputsStr[i].trim()));
                    }
                    task.insertMultiplePointTotalAt(multipleInputs, positionToInsert);
                }
                else {
                    String[] inputsStr = getValues.split(",");
                    for(int i = 0; i<inputsStr.length; i++){
                        multipleInputs.add(Integer.parseInt(inputsStr[i].trim()));
                    }
                    task.insertMultiplePointTotal(multipleInputs);
                }

            }
            else {
                task.insertMultiplePointTotal(Integer.parseInt(getValues));
            }
            System.out.println("Press Y to finish.");
            String end = input.next();
            if(end.equals("Y")) break;
        }

        System.out.println("Total points input completed.");

        //Read grade sheet(Earned points). Same.
        System.out.println("Input for eared points for each task. " +
                "(Split the numbers with ',' if multiple inputs. For certain position to insert, split the position with ';'.\n" +
                "Eligible formats(Integer): \n2; 10, 20, 30\n10, 20, 30\n10");
        input = new Scanner(System.in);
        while (true){
            //Read the user's format
            boolean isMultiple = false;
            boolean certainPosition = false;
            System.out.println("Input: ");
            String getValues = input.nextLine();
            if(getValues.contains(",")) isMultiple = true;
            if(getValues.contains(";")) certainPosition = true;

            //Read eared points for each assignment
            if(isMultiple){
                LinkedList<Integer> multipleInputs = new LinkedList<>();

                if(certainPosition){
                    String[] getPosition = getValues.split(";");
                    int positionToInsert = Integer.parseInt(getPosition[0]);
                    String[] inputsStr = getPosition[1].split(",");
                    for(int i = 0; i<inputsStr.length; i++){
                        multipleInputs.add(Integer.parseInt(inputsStr[i].trim()));
                    }
                    task.insertMultipleGradeAt(multipleInputs, positionToInsert);
                }
                else {
                    String[] inputsStr = getValues.split(",");
                    for(int i = 0; i<inputsStr.length; i++){
                        multipleInputs.add(Integer.parseInt(inputsStr[i].trim()));
                    }
                    task.insertMultipleGrade(multipleInputs);
                }

            }
            else {
                task.insertMultipleGrade(Integer.parseInt(getValues));
            }
            System.out.println("Press Y to finish.");
            String end = input.next();
            if(end.equals("Y")) break;
        }

        System.out.println("Earned points input completed.");

        //Read percentage for each assignment
        System.out.println("Input for percentage for each task. " +
                "(Split the numbers with ',' if multiple inputs. For certain position to insert, split the position with ';'.\n" +
                "Eligible formats(Double): \n2; 0.25, 0.3, 0.15\n0.25 0.3 0.15\n 0.15");
        input = new Scanner(System.in);
        while (true){

            //Read the user's format
            boolean isMultiple = false;
            boolean certainPosition = false;
            System.out.println("Input: ");
            String getValues = input.nextLine();
            if(getValues.contains(",")) isMultiple = true;
            if(getValues.contains(";")) certainPosition = true;

            //Read percentage for each assignment
            if(isMultiple){
                LinkedList<Double> multipleInputs = new LinkedList<>();

                if(certainPosition){
                    String[] getPosition = getValues.split(";");
                    int positionToInsert = Integer.parseInt(getPosition[0]);
                    String[] inputsStr = getPosition[1].split(",");
                    for(int i = 0; i<inputsStr.length; i++){
                        multipleInputs.add(Double.parseDouble(inputsStr[i].trim()));
                    }
                    task.insertMultiplePercentageAt(multipleInputs, positionToInsert);
                }
                else {
                    String[] inputsStr = getValues.split(",");
                    for(int i = 0; i<inputsStr.length; i++){
                        multipleInputs.add(Double.parseDouble(inputsStr[i].trim()));
                    }
                    task.insertMultiplePercentage(multipleInputs);
                }

            }
            else {
                task.insertMultiplePercentage(Double.parseDouble(getValues));
            }
            System.out.println("Press Y to finish.");
            String end = input.next();
            if(end.equals("Y")) break;
        }

        System.out.println("Total points input completed.");

        //Format output
        //Check the invalid input, print the result if valid
        if(task.checkTheInput()){
            task.result();
        }
        else ;

    }


}
