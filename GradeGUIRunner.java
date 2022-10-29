import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GradeGUIRunner {

    public static void main(String[] args) {
        //WeightedGrade.java is NEEDED for operation
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                GradeGUI.prepareGUI();
//            }
//        });
        WeightedGrade wg = new WeightedGrade();
        wg.setPointTotal(100);
        wg.setEarnedPoints(85);
        wg.setAssignmentPercentage(0.25);

        System.out.println(wg.calculate());
    }
}
