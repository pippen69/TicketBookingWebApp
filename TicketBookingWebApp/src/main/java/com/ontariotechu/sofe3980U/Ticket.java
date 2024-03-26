package com.ontariotechu.sofe3980U;
import java.lang.*;
import java.util.*;

public class Ticket {
    private String fname; 
    private String lname; 
    String[] airportPath; //this has to be dynamic so we have to use a type of data structure
    private boolean isDirect;
    private boolean isRoundTrip;
    private String returnDate; //it cna be string but we have to incorperate the 24hr and 12 hr clock
    private String departureDate; //same thing as above
    private boolean isMilitaryTime;
    private String destination;
    private String origin;



    // This method has 2 paramaters: Origin, Destination
    // bookTrip will book a trip from the users selected origin
    // to the users selected destination and allow the use to proceed 
    String bookTrip(String origin, String destination){
        
        //check if the origin and desitnation are the same
        if(origin.equals(destination)){
            return "invalid";
        }

        this.origin = origin;
        this.destination = destination;

        return "valid";
    }

    //this method has the paramaters 
    String createTicket(String fname, String lname, String departureDate, boolean isRoundTrip, boolean isDirect){

            this.fname = fname;
            this.lname = lname;
            this.departureDate = departureDate;
            this.isRoundTrip = isRoundTrip;
            this.isDirect = isDirect;

            return "";
    }

    //createAirportPath(airportsSelected[])
    
    // this method will take isMilitaryTime as a paramater
    // It will set the ticket to have a 24 hour or 12 hour display
    void is24hr(boolean isMilitaryTime){
        this.isMilitaryTime = isMilitaryTime;
    }



}
