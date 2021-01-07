package main.model;

public class Date {


    //Constructors-------------------------------------------------------------------------------

    public Date() {
        this( 0, 0, 0 );

    }

    public Date( int inputYear ) {
        this( inputYear, 0, 0 );
    }

    public Date( int inputYear, int inputMonth ) {
        this( inputYear, inputMonth, 0 );
    }

    public Date( int inputYear, int inputMonth, int inputDay )   {
        year = inputYear;
        month = inputMonth;
        day = inputDay;

    }

    //for loading date
    public Date( String inputDate ) {
        if ( inputDate.equals( "undated" ) ) {
            year = 0;
            month = 0;
            day = 0;
        }
        else {
            int[] convertedInputDate = convertDate( inputDate );
            year = convertedInputDate[ 0 ];
            month = convertedInputDate[ 1 ];
            day = convertedInputDate[ 2 ];
        }

    }





    //Public Routines-------------------------------------------------------------------------------
    public int[] convertDate( String inputDate ) {
        //inputDate is in the format of "YYYY-MM-DD"
        //split YYYY, MM, DD to their own separate strings
        String[] separatedDate = inputDate.split( "-" );

        //convert the three strings into int type
        int year = Integer.parseInt( separatedDate[ 0 ] );
        int month = Integer.parseInt( separatedDate[ 1 ] );
        int day = Integer.parseInt( separatedDate[ 2 ] );


        int[] dateInInt = { year, month, day };
        return dateInInt;

    }



    @Override
    public String toString() {

        if ( year == 0 || month == 0 || day == 0 ) {
            return "undated";
        }

        return year + "-" + month + "-" +day;
    }


    //Protected Routines-------------------------------------------------------------------------------
    //Private Routines-------------------------------------------------------------------------------

    //Fields-------------------------------------------------------------------------------

    private int year = 0;
    private int month = 0;
    private int day = 0;

    //public setters and getters-------------------------------------------------------------------------------
    public void setYear( int inputYear ){
        year = inputYear;
    }

    public int getYear() {
        return year;
    }


    public void setMonth( int inputMonth ) {
        month = inputMonth;
    }

    public int getMonth() {
        return month;
    }

    public void setDay( int inputDay ) {
        day = inputDay;
    }

    public int getDay() {
        return day;
    }


    public void setDate(int[] dateArray) {
        //if date array size is 3 elements big
        if( dateArray.length == 3 )   {
            //assign the first element of the array as year, second element as month, third element as day
            year = dateArray[0];
            month = dateArray[1];
            day = dateArray[2];
        }
        // if dateArray is over or less than 3
        else {
            //print out error
            System.out.println( "date Array is over or less than 3." );
        }
    }

    public int[] getDate() {

        int[] outputDate = { year, month, day };

        return outputDate;
    }

}
