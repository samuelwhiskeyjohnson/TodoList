package main.model;

public class Task {



    //Constructors-------------------------------------------------------------------------------
    //create task with no input


    //create task with input of title
    public Task( String inputTitle ) {
        this( inputTitle, null );

    }


    //create task with input of title and date
    public Task( String inputTitle, Date inputDueDate ) {
        title = inputTitle;
        if ( inputDueDate == null ) {
            dueDate = new Date();
        }
        else {
            dueDate = inputDueDate;
        }

        //every time task is created
        //id is incremented by 1
        id = idCounter;
        idCounter++;

    }

    public Task( String inputTitle, Date inputDueDate, boolean inputMark ) {
        title = inputTitle;
        if ( inputDueDate == null ) {
            dueDate = new Date();
        }
        else {
            dueDate = inputDueDate;
        }

        mark = inputMark;

        //every time task is created
        //id is incremented by 1
        id = idCounter;
        idCounter++;
    }

    //for loading tasks
    //create task with input of Id, title, duedate, mark
    public Task( int inputId, String inputTitle, Date inputDueDate, boolean inputMark ) {
        id = inputId;
        title = inputTitle;
        dueDate = inputDueDate;
        mark = inputMark;
    }




    //Public Routines-------------------------------------------------------------------------------









    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", mark=" + mark +
                '}';
    }







    //Protected Routines-------------------------------------------------------------------------------
    //Private Routines-------------------------------------------------------------------------------
    //Fields-------------------------------------------------------------------------------
    private static int idCounter = 0;
    private int id = 0;
    private String title = "";
    private Date dueDate = null;
    private boolean mark = false;


    //public setters and getters-------------------------------------------------------------------------------

    public void setDueDate( Date inputDueDate ) {
        dueDate = inputDueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }




    public void setId( int inputId ) {
        id = inputId;
    }

    public int getId() {
        return id;
    }


    public void setTitle(String inputTitle) {
        title = inputTitle;
    }

    public String getTitle() {
        return title;
    }



    public void setMark( boolean inputMark ) {
        mark = inputMark;
    }

    public boolean getMark() {
        return mark;
    }




}
