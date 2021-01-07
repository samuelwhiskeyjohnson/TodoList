package main.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {



    //Constructors-------------------------------------------------------------------------------
    //Public Routines-------------------------------------------------------------------------------


    //Filereader reads characters
    // FileInputStream reads raw bytes
    //there is a text file
    //Id, title, duedate, mark
    //1,buy milk,2020-11-28,false
    public void loadTask() {

        //read the second line in the txt file as String
        // How to fix
        try {
            BufferedReader taskLoader = new BufferedReader( new FileReader( fileName ) );

            String savedTask = "";

            while ( ( savedTask = taskLoader.readLine() ) != null ) {
                //split the String with deliminator and store in a string array
                String[] loadingTaskInString = savedTask.split("," );
                //if loadingTaskInString has length of 4,
                if ( loadingTaskInString.length == 4 ) {
                    //from the string array convert id to int type, date to Date type, mark to boolean type first.
                    String loadingIdBeforeConvert = loadingTaskInString[0];
                    String loadingTitle = loadingTaskInString[1];
                    String loadingDueDateBeforeConvert = loadingTaskInString[2];
                    String loadingMarkBeforeConvert = loadingTaskInString[3];

                    int loadingIdAfterConvert = Integer.parseInt( loadingIdBeforeConvert );
                    Date LoadingDueDateAfterConvert = new Date( loadingDueDateBeforeConvert );
                    boolean LoadingMarkAfterConvert = Boolean.parseBoolean( loadingMarkBeforeConvert );

                    //create a task
                    Task loadingTask = new Task( loadingIdAfterConvert, loadingTitle, LoadingDueDateAfterConvert, LoadingMarkAfterConvert );

                    //and store in the taskList
                    taskList.add( loadingTask );

                    //do this for rest of the lines in the txt files
                }



            }

            //close loader
            taskLoader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }




    public void saveTask() {

        //overwrite from second line in the txt file
        try {
            BufferedWriter taskSaver = new BufferedWriter( new FileWriter(fileName, false) );

            for ( int taskIndex = 0; taskIndex < taskList.size(); taskIndex++ ) {
                //get a task from the taskList
                Task taskToBeSaved = taskList.get( taskIndex );

                //split into id, title, due date, mark
                int savingIdBeforeConvert = taskToBeSaved.getId();
                String savingTitle = taskToBeSaved.getTitle();
                Date savingDueDateBeforeConvert = taskToBeSaved.getDueDate();
                boolean savingMarkBeforeConvert = taskToBeSaved.getMark();

                //and convert task's id, title, dueDate, mark to String
                String savingIdAfterConvert = String.valueOf( savingIdBeforeConvert );
                String savingDueDateAfterConvert = String.valueOf( savingDueDateBeforeConvert ); //on the file save date as undated?
                String savingMarkAfterConvert = String.valueOf( savingMarkBeforeConvert );


                //write id, title, dueDate, mark
                taskSaver.write( savingIdAfterConvert +
                        "," +
                        savingTitle +
                        "," +
                        savingDueDateAfterConvert +
                        "," +
                        savingMarkAfterConvert );

                taskSaver.newLine();

                //do this for the rest of the task in taskList
            }


            //close saver
            taskSaver.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }





    public void addTask( Task inputTask ) {
        taskList.add( inputTask );
    }





    //modify existing task's title
    public void modifyTask( int inputId, String inputTitle ) {
        //find the task with input id
        Task taskToModify = findTask( inputId );
        //modify only task's title
        modifyTask( inputId, inputTitle, taskToModify.getDueDate(), taskToModify.getMark() );

    }



    //modify existing task's date
    public void modifyTask( int inputId, Date inputDate ) {
        //find the task with input id
        Task taskToModify = findTask( inputId );
        //modify only task's date
        modifyTask( inputId, taskToModify.getTitle(), inputDate, taskToModify.getMark() );


    }




    //modify existing task's mark
    public void modifyTask( int inputId, boolean inputMark ) {
        //find the task with input id
        Task taskToModify = findTask( inputId );
        //modify only task's mark
        modifyTask( inputId, taskToModify.getTitle(), taskToModify.getDueDate(), inputMark );


    }


    //modify existing task's title, date, mark
    public void modifyTask( int inputId,
                            String inputTitle,
                            Date inputDueDate,
                            boolean inputMark ) {
        //find the task with input id
        Task taskToModify = findTask( inputId );

        //modify only task's title, date, mark
        taskToModify.setTitle( inputTitle );
        taskToModify.setDueDate( inputDueDate );
        taskToModify.setMark( inputMark );


    }



    public void removeTask( int inputId ) {
        for ( int index = 0; index < taskList.size(); index++ ) {
            //if a task in taskList has same id as inputId
            if ( taskList.get(index).getId() == inputId ) {
                //remove the task in the taskList
            }
            //if there is no task that has the same id as inputId
            else {
                //print on console that no such task is found
                System.out.println( "task to remove not found");
            }
            //do this for every task in the taskList (for loop)
        }

    }




    public void showTasks() {

        for ( int index = 0; index < taskList.size(); index++ ){
            //print a task in a taskList
            Task taskAtIndex = taskList.get( index );
            System.out.println( taskAtIndex );
            //do this for every task in the taskList (for loop)
        }


    }






    //Protected Routines-------------------------------------------------------------------------------
    //Private Routines-------------------------------------------------------------------------------

    //find task given id
    private Task findTask( int inputId ) {
        for ( int index = 0; index < taskList.size(); index++ ) {
            //if the task found in the taskList given inputId
            Task taskAtIndex = taskList.get( index );
            if ( taskAtIndex.getId() == inputId ) {
                return taskAtIndex;
            }
            //do this for every task in the taskList (for loop)
        }

        //print on console that no such task is found
        System.out.println( "task to modify not found" );
        //if task is not found
        return null;
    }


    //Fields-------------------------------------------------------------------------------
    private List<Task> taskList = new ArrayList<>();

    private File file = new File( ".\\data\\savedTasks.txt" );
    private String fileName = file.getPath();




    //public setters and getters-------------------------------------------------------------------------------

    public List<Task> getTaskList() {
        return taskList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }










}
