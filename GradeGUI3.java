import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Info 5101 - Section 8
 * Assignment 7
 * Author: Runlin Liu
 *
 */
public class GradeGUI3 {
    /**
     * Comments and Restrictions:
     * The reader only read at most 100 lines from file.
     */
    private static int count = 0;

    private static ArrayList<JTextField> inputTextFieldList = new ArrayList<>();

    private static JButton fileChooserInReader = null;

    private static JButton fileChooserInWriter = null;

    private static JFrame fileDialog = null;

    private static JTable tableForReader = null;

    private static JTable tableForWriter = null;

    private static JLabel labelForReader = null;

    private static JLabel labelForWriter = null;

    //Click to copy the content to the writer tab
    private static JButton copyToWriterBt = null;

    //Save button in writer tab
    private static JButton saveBt = null;

    private static File currentWorkloadForReader = null;

    private static File currentWorkloadForWriter = null;

    private static TextField delimiterReader = null;

    private static TextField newFileNameReader = null;

    public static void prepareGUI(){

        /**
         * Prepare the structure(JFrame and JPanels)
         * Reader and Saver have their own section(divided by tabbedPane)
         */

        JFrame frame = new JFrame();
        frame.setTitle("File Reader and Writer GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();

        //Creating TabbedPane, each tab given a graphic panel
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        JPanel readerPanel = new JPanel(new BorderLayout());
        JPanel writerPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("File Reader", readerPanel);
        tabbedPane.addTab("File Writer", writerPanel);
        contentPane.add(tabbedPane);

        /**
         * Stuff the two sections
         */
        //Stuffing each panel
        readerPanel = stuffReaderPanel(readerPanel);
        writerPanel = stuffWriterPanel(writerPanel);


        /**
         * Functions registered here
         */
        //Register functions of file choose in the reader tab
        fileChooserInReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int rev = chooser.showDialog(fileDialog, "Hutao");

                if(rev == JFileChooser.APPROVE_OPTION){
                    System.out.println(chooser.getSelectedFile().getName());
                    File f = chooser.getSelectedFile();
                    Scanner input = null;
                    try{
                        input = new Scanner(f);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Show the path when successfully read
                    labelForReader.setText(String.format("Current File: %s",f.getAbsolutePath()));
                    currentWorkloadForReader = f;

                    //First row is defined to have an attribute for each column
                    //Reader panel is not editable
                    DefaultTableModel dtm = new DefaultTableModel(){
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    //Read the headers of each row and set the col count automatically
                    String[] colNames = input.nextLine().split(",");
                    dtm.setColumnCount(colNames.length);
                    dtm.addRow(colNames);

                    ArrayList<String[]> data = new ArrayList<>();
                    while(input.hasNext()){
                        String[] line = input.nextLine().split(",");
                        data.add(line);
                        dtm.addRow(line);

                        for(int i = 0; i< line.length; i++){
                            System.out.print(line[i]+" ");
                        }
                        System.out.println();
                    }


                    System.out.println(dtm.getColumnCount());
                    System.out.println(dtm.getRowCount());
                    tableForReader.setModel(dtm);
                    for(int i = 0; i<colNames.length; i++){
                        tableForReader.getColumnModel().getColumn(i).setPreferredWidth(100);
                    }


                }

            }
        });

        //Register functions of file choose in the writer tab
        fileChooserInWriter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int rev = chooser.showDialog(fileDialog, "Hutao");

                if(rev == JFileChooser.APPROVE_OPTION){
                    System.out.println(chooser.getSelectedFile().getName());
                    File f = chooser.getSelectedFile();
                    Scanner input = null;
                    try{
                        input = new Scanner(f);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    //Show the path when successfully read
                    currentWorkloadForWriter = f;
                    labelForWriter.setText(String.format("Current File: %s",f.getAbsolutePath()));
                    newFileNameReader.setText(currentWorkloadForWriter.getName());

                    //First row by definition should have an attribute for each column
                    DefaultTableModel dtm = new DefaultTableModel();

                    //Read the headers of each column and set the column count automatically
                    String[] colNames = input.nextLine().split(",");
                    dtm.setColumnCount(colNames.length);
                    dtm.addRow(colNames);

                    ArrayList<String[]> data = new ArrayList<>();
                    while(input.hasNext()){
                        String[] line = input.nextLine().split(",");
                        data.add(line);
                        dtm.addRow(line);

                        for(int i = 0; i< line.length; i++){
                            System.out.print(line[i]+" ");
                        }
                        System.out.println();
                    }


                    System.out.println(dtm.getColumnCount());
                    System.out.println(dtm.getRowCount());
                    tableForWriter.setModel(dtm);
                    for(int i = 0; i<colNames.length; i++){
                        tableForWriter.getColumnModel().getColumn(i).setPreferredWidth(100);
                    }


                }

            }
        });

        saveBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Clicked");

                if(null == newFileNameReader.getText()){
                    if(currentWorkloadForWriter.exists()){
                        System.out.println("Delimitor: "+ delimiterReader.getText());
                        writeFile(currentWorkloadForWriter, tableForWriter.getModel());
                    }
                    else{
                        //If occasionally the file is removed before saving.
                        try {
                            currentWorkloadForWriter.createNewFile();
                            writeFile(currentWorkloadForWriter, tableForWriter.getModel());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                else{
                    File f = new File(currentWorkloadForWriter.getParent() + File.separator + newFileNameReader.getText());
                    System.out.println(f.getAbsolutePath());
                    try {
                        f.createNewFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    writeFile(f, tableForWriter.getModel());

                }
            }
        });

        copyToWriterBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                currentWorkloadForWriter = currentWorkloadForReader;

                Scanner input = null;
                try {
                    input = new Scanner(currentWorkloadForWriter);
                } catch (FileNotFoundException | NullPointerException ex) {
                    labelForReader.setText("Please check if the file exists.");
                }

                //First row by definition should have an attribute for each column
                DefaultTableModel dtm = new DefaultTableModel();

                //Read the headers of each column and set the column count automatically
                String[] colNames = input.nextLine().split(",");
                dtm.setColumnCount(colNames.length);
                dtm.addRow(colNames);

                ArrayList<String[]> data = new ArrayList<>();
                while(input.hasNext()){
                    String[] line = input.nextLine().split(",");
                    data.add(line);
                    dtm.addRow(line);

                    for(int i = 0; i< line.length; i++){
                        System.out.print(line[i]+" ");
                    }
                    System.out.println();
                }


                System.out.println(dtm.getColumnCount());
                System.out.println(dtm.getRowCount());
                tableForWriter.setModel(dtm);
                for(int i = 0; i<colNames.length; i++){
                    tableForWriter.getColumnModel().getColumn(i).setPreferredWidth(100);
                }
            }
        });

        /**
         * Final steps
         */
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Separate the writing document process independently
     * @param currentWorkloadForWriter File with a confirmed available
     * @param tm TableModel from which to fetch data
     */
    private static void writeFile(File currentWorkloadForWriter, TableModel tm) {
        int rowCount = tm.getRowCount();
        int columnCount = tm.getColumnCount();

        FileWriter output = null;
        try {
            output = new FileWriter(currentWorkloadForWriter);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String delimiter = delimiterReader.getText();
        for(int i = 0; i< rowCount; i++){
            for(int j = 0; j< columnCount; j++){
                String s = (String)tm.getValueAt(i,j);
                if(j+1<columnCount) s+= delimiter;
                try {
                    output.write(s);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.print(s);
            }
            //New line after a line finished
            try {
                output.write("\n");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println();
        }
        //Close the output stream
        try {
            output.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Generate the inner components inside the readerPanel
     * Necessary components are set static for other operations in actionListeners
     * @param readerPanel
     * @return
     */
    private static JPanel stuffReaderPanel(JPanel readerPanel) {
        /**
         * Structure of the reader section
         */
        JPanel workPanel = new JPanel();
        JPanel controllerPanel = new JPanel();
        JPanel profilePanel = new JPanel();

        controllerPanel.setBorder(BorderFactory.createTitledBorder("Controllers"));
        workPanel.setBorder(BorderFactory.createTitledBorder("Table"));
        profilePanel.setBorder(BorderFactory.createTitledBorder("Author"));

        //Setting the preferred size of profilePanel and the controllerPanel
        profilePanel.setPreferredSize(new Dimension(200,200));
        controllerPanel.setPreferredSize(new Dimension(200,200));
        profilePanel = generateProfilePanel(profilePanel);


        readerPanel.add(workPanel, BorderLayout.WEST);
        readerPanel.add(profilePanel, BorderLayout.EAST);


        /**
         * Preparation for the table inside
         */
        //Default view of the table
        String[] colNames = {"Col1","Col2","Col3","Col4","Col5"};
        String[][] data = new String[20][5];

        int count = 0;
        for(int i = 0; i< 20; i++){
            for(int j = 0; j<5; j++){
                data[i][j] = "";
            }
        }

        tableForReader = new JTable(data, colNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableForReader.setFillsViewportHeight(true);//To fit the scrollPane, learned from another tutorial
        tableForReader.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(tableForReader);
        scrollPane.setPreferredSize(new Dimension(400,200));
        workPanel.add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Add components into the controllerPanel
        JButton chooseFileBt = new JButton("Choose File");
        copyToWriterBt = new JButton("Copy to File Writer");
        JLabel delimiterNoticeLabel = new JLabel("Default delimiter: ','");
        JLabel editableNoticeLabel = new JLabel("File Reader Not Editable.");
        JLabel filePathLabel = new JLabel("Current Path: Default.");
        chooseFileBt.setHorizontalAlignment(SwingConstants.CENTER);
        copyToWriterBt.setHorizontalAlignment(SwingConstants.CENTER);
        controllerPanel.add(chooseFileBt);
        controllerPanel.add(copyToWriterBt);
        controllerPanel.add(delimiterNoticeLabel);
        controllerPanel.add(editableNoticeLabel);

        workPanel.add(controllerPanel);
        readerPanel.add(filePathLabel, BorderLayout.SOUTH);

        labelForReader = filePathLabel;
        fileChooserInReader = chooseFileBt;

        return readerPanel;
    }

    /**
     * Generate the inner components inside the writerPanel
     * Necessary components are set static for other operations in actionListeners
     * Note for using the writer tab:
     * - Save: directly save the sheet to the previous path, delimiter change is available, and former delimiter by default.
     * - Create...: Create a empty sheet with no path attached. Need to be saved.
     * The program is not responsible for format disorders if the delimiter can be found in the text.
     *  @param writerPanel
     * @return
     */
    private static JPanel stuffWriterPanel(JPanel writerPanel) {
        /**
         * Structure of the reader section
         */
        JPanel workPanel = new JPanel();
        JPanel controllerPanel = new JPanel();
        JPanel profilePanel = new JPanel();

        controllerPanel.setBorder(BorderFactory.createTitledBorder("Controllers"));
        workPanel.setBorder(BorderFactory.createTitledBorder("Table"));
        profilePanel.setBorder(BorderFactory.createTitledBorder("Author"));

        //Setting the preferred size of profilePanel and the controllerPanel
        profilePanel.setPreferredSize(new Dimension(200,200));
        controllerPanel.setPreferredSize(new Dimension(200,200));
        profilePanel = generateProfilePanel(profilePanel);

        //Add the 2 section into the writerPanel
        writerPanel.add(workPanel, BorderLayout.WEST);
        writerPanel.add(profilePanel, BorderLayout.EAST);


        /**
         * Preparation for the table inside
         */
        //Default view of the table
        String[] colNames = {"Col1","Col2","Col3","Col4","Col5"};
        String[][] data = new String[20][5];

        for(int i = 0; i< 20; i++){
            for(int j = 0; j<5; j++){
                data[i][j] = "";
            }
        }

        //Register the table and settings
        tableForWriter = new JTable(data, colNames);
        tableForWriter.setFillsViewportHeight(true);//To fit the scrollPane, learned from another tutorial
        tableForWriter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(tableForWriter);
        scrollPane.setPreferredSize(new Dimension(400,200));
        workPanel.add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        /**
         * Prepare the controllers
         */
        //Components
        fileChooserInWriter = new JButton("Choose File");
        saveBt = new JButton("Save");
        labelForWriter = new JLabel("Current Path: Default."); //The label to show the path
        delimiterReader = new TextField(",");
        JLabel delimiterLabel = new JLabel("Delimiter:");
        newFileNameReader = new TextField();
        JLabel labelForNewFileNameReader = new JLabel("Save as(using new file name):");
        newFileNameReader.setColumns(15);
        controllerPanel.add(fileChooserInWriter);
        controllerPanel.add(saveBt);
        controllerPanel.add(delimiterLabel);
        controllerPanel.add(delimiterReader);
        controllerPanel.add(labelForNewFileNameReader);
        controllerPanel.add(newFileNameReader);

        //Layout
        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.WEST, fileChooserInWriter, 5, SpringLayout.WEST, controllerPanel);
        layout.putConstraint(SpringLayout.NORTH, fileChooserInReader, 5, SpringLayout.NORTH, controllerPanel);
        layout.putConstraint(SpringLayout.WEST, delimiterLabel, 0, SpringLayout.WEST, newFileNameReader);
        layout.putConstraint(SpringLayout.NORTH, delimiterLabel, 5, SpringLayout.SOUTH, newFileNameReader);
        layout.putConstraint(SpringLayout.WEST, delimiterReader, 5, SpringLayout.EAST, delimiterLabel);
        layout.putConstraint(SpringLayout.NORTH, delimiterReader, 0, SpringLayout.NORTH, delimiterLabel);
        layout.putConstraint(SpringLayout.WEST, labelForNewFileNameReader, 0, SpringLayout.WEST, fileChooserInWriter);
        layout.putConstraint(SpringLayout.NORTH, labelForNewFileNameReader, 5, SpringLayout.SOUTH, fileChooserInWriter);
        layout.putConstraint(SpringLayout.WEST, newFileNameReader, 0, SpringLayout.WEST, labelForNewFileNameReader);
        layout.putConstraint(SpringLayout.NORTH, newFileNameReader, 5, SpringLayout.SOUTH, labelForNewFileNameReader);
        layout.putConstraint(SpringLayout.WEST, saveBt, 0, SpringLayout.WEST, delimiterLabel);
        layout.putConstraint(SpringLayout.NORTH, saveBt, 5, SpringLayout.SOUTH, delimiterLabel);

        controllerPanel.setLayout(layout);

        workPanel.add(controllerPanel);
        writerPanel.add(labelForWriter, BorderLayout.SOUTH);

        return writerPanel;
    }


    private static JPanel generateProfilePanel(JPanel profilePanel) {
        String[] attrList = {"Runlin Liu", "INFO 5100","Section 8",
                String.format("%s", DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US).format(new Date()))};

        SpringLayout layout = new SpringLayout();

        JLabel emptyLabel = new JLabel();
        profilePanel.add(emptyLabel);
        emptyLabel.setPreferredSize(new Dimension(100,20));

        for(int i = 0; i< attrList.length; i++){
            //Components and relationships
            JLabel itemi = new JLabel(attrList[i]);

            itemi.setPreferredSize(new Dimension(100,20));

            profilePanel.add(itemi);

            itemi.setHorizontalAlignment(SwingConstants.CENTER);
        }

        return profilePanel;
    }

    private static int count() {
        return count++;
    }

    public static ArrayList<JTextField> getInputTextFieldList() {
        return inputTextFieldList;
    }

}
