import javax.swing.*;

public class GradeGUI3Runner {

    public static void main(String[] args) {
        //WeightedGrade.java is NEEDED for operation
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GradeGUI3.prepareGUI();
            }
        });
    }
}
