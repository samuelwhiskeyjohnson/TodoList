package main.ui;

import main.model.Date;
import main.model.Task;
import main.model.TaskManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ToDoList extends JFrame {

    public ToDoList() {
        //the name of the window is "To-do List"
        super( "To-do List" );

        //initialize graphics e.g.) set window visible
        initializeGraphics();

        taskManagerObj = new TaskManager();


    }

    private void initializeGraphics() {
        //setting the layout to border layout
        setLayout( new BorderLayout(0,0) ); //vgap doesn't do anything


        //function panel
        createFunctionPanel();


        //task panel
        createTaskManagerPanel();

        //set the size of window when first opens
        setSize(WINDOW_WIDTH_IN_PIXELS, WINDOW_HEIGHT_IN_PIXELS);

        //set the minimum size of window
        setMinimumSize( new Dimension(WINDOW_WIDTH_IN_PIXELS, WINDOW_HEIGHT_IN_PIXELS) );

        //Swing application stop when user exits the window
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        //Open the window in the middle of the computer screen
        setLocationRelativeTo(null);

        //window is visible
        setVisible( true );
    }



    private void createFunctionPanel() {

        //create function panel, set layout, set size, and add to the main frame
        JPanel functionPanel = new JPanel();
        functionPanel.setLayout( null ); // hgap doesn't do anything bc no columns
        functionPanel.setPreferredSize( new Dimension( FUNCTION_PANEL_WIDTH_IN_PIXELS, FUNCTION_PANEL_HEIGHT_IN_PIXELS ) ); //height is dummy value
        add( functionPanel, BorderLayout.WEST );


        //add components inside function panel
        //add button
        JButton addTaskButton = new JButton( "Add" );
        functionPanel.add( addTaskButton );
        Insets insets = functionPanel.getInsets();
        int addTaskButtonXPos = 25 + insets.left;
        int addTaskButtonYPos = 25 + insets.top;
        addTaskButton.setBounds( addTaskButtonXPos, addTaskButtonYPos,
                BUTTON_WIDTH_IN_PIXELS , BUTTON_HEIGHT_IN_PIXELS );


        //remove button
        int removeTaskButtonXPos = 25 + insets.left;
        int removeTaskButtonYPos = addTaskButtonYPos + BUTTON_HEIGHT_IN_PIXELS + 10;
        JButton removeTaskButton = new JButton( "Remove" );
        functionPanel.add( removeTaskButton );
        removeTaskButton.setBounds( removeTaskButtonXPos, removeTaskButtonYPos,
                BUTTON_WIDTH_IN_PIXELS , BUTTON_HEIGHT_IN_PIXELS );

        //save button
        int saveTaskButtonXPos = 25 + insets.left;
        int saveTaskButtonYPos = removeTaskButtonYPos + BUTTON_HEIGHT_IN_PIXELS + 10;
        JButton saveTaskButton = new JButton( "Save" );
        functionPanel.add( saveTaskButton );
        saveTaskButton.setBounds( saveTaskButtonXPos, saveTaskButtonYPos,
                BUTTON_WIDTH_IN_PIXELS , BUTTON_HEIGHT_IN_PIXELS );




        //add button functionality
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e ) {

                Object[] newTask = new Object[] { "enter date here", "enter title here" , false };
                taskManagerTableModel.addRow( newTask );
            }


        });

        //remove button functionality
        removeTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //get selected row
                    int row  = taskManagerTable.getSelectedRow();
                    //remove selected row
                    taskManagerTableModel.removeRow( row );
                }
                catch ( ArrayIndexOutOfBoundsException exception ){
                    System.out.println( "you need to select before removing" );
                }


                //test if the button is pressed
                //JOptionPane.showConfirmDialog( null, row + "pressed" );
            }
        });



        //save button functionality
        saveTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //table -> arraylist -> file
                //get date, title, mark from each task in task manager table
                //save it in the array list
                List<Task> taskList = taskManagerObj.getTaskList();
                taskList.clear();

                for( int taskManagerTableRow = 0; taskManagerTableRow < taskManagerTableModel.getRowCount(); taskManagerTableRow ++ ) {
                    Date taskDate = (Date) taskManagerTableModel.getValueAt( taskManagerTableRow, 0 );
                    String taskTitle = (String) taskManagerTableModel.getValueAt( taskManagerTableRow, 1 );
                    boolean taskMark = (boolean) taskManagerTableModel.getValueAt( taskManagerTableRow, 2 );


                    Task task = new Task( taskTitle, taskDate, taskMark );

                    taskManagerObj.addTask( task );

                    System.out.println( taskDate + taskTitle + taskMark );


                }


                //get array list
                //save it in the file
                taskManagerObj.saveTask();


            }
        });





    }


    private void createTaskManagerPanel() {
        //create task manager panel, set layout, set size, and add to the main frame
        JPanel taskManagerPanel = new JPanel();
        taskManagerPanel.setLayout( new BorderLayout() );
        taskManagerPanel.setBorder( new LineBorder( Color.RED ) );
        this.add( taskManagerPanel, BorderLayout.CENTER );



        taskManagerTableModel = new DefaultTableModel() {

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch ( columnIndex ) {
                    case 0:
                        return Date.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };


        Object[] columnTitle = new Object[] { "date", "title", "checkbox" };
        taskManagerTableModel.setColumnIdentifiers( columnTitle );


        taskManagerTable = new JTable( taskManagerTableModel );





        JScrollPane taskManagerTableScrollable = new JScrollPane( taskManagerTable );

        taskManagerPanel.add( taskManagerTableScrollable, BorderLayout.CENTER );

        //load task manager and initialize taskManagerTable
        initTaskTable();







    }


    private void initTaskTable() {
        //Object[] row1 = new Object[] { "11/12/12", "task1", true };
        //taskManagerTableModel.addRow( row1 );
        taskManagerObj = new TaskManager();
        taskManagerObj.loadTask();
        List<Task> taskList = taskManagerObj.getTaskList();


        for( int counter = 0; counter < taskList.size(); counter++ ) {

            Date taskDueDate = taskList.get( counter ).getDueDate();
            String taskTitle = taskList.get( counter ).getTitle();
            boolean taskMark = taskList.get( counter ).getMark();

            Object[] newTask = new Object[] { taskDueDate, taskTitle, taskMark  };
            taskManagerTableModel.addRow( newTask );
        }

    }





    TaskManager taskManagerObj;
    JTable taskManagerTable;
    DefaultTableModel taskManagerTableModel;

    //Width and Height constant, unit is in pixels
    public static final int WINDOW_WIDTH_IN_PIXELS = 1000;
    public static final int WINDOW_HEIGHT_IN_PIXELS = 700;

    public static final int FUNCTION_PANEL_WIDTH_IN_PIXELS = 250;
    public static final int FUNCTION_PANEL_HEIGHT_IN_PIXELS = 0;

    public static final int BUTTON_WIDTH_IN_PIXELS = 200;
    public static final int BUTTON_HEIGHT_IN_PIXELS = 100;



    public static void main(String[] args) {

    new ToDoList();

    }

}
