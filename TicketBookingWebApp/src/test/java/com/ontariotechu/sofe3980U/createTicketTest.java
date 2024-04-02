package com.ontariotechu.sofe3980U;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class createTicketTest {
   
    private Ticket newTicket;

    @BeforeEach
    public void setup() {
        newTicket = new Ticket();

    }
   
   @Test
   public void testCreateTicketRoundDirectTrip() {
       String fname = "Javier";
       String lname = "oofer";


       String departureDate = "8:24 PM";
       boolean isRoundTrip = true;
       boolean isDirect = true;

      //newTicket.createTicket("fname", "lname", "departureDate", roundTrip,  true);

       String ticket = newTicket.createTicket(fname, lname, departureDate, isRoundTrip, isDirect);

       assertTrue(ticket.equals("Round and Direct trip"));
    }


    @Test
    public void testCreateTicketRoundIndirectTrip() {
        String fname = "Javier";
        String lname = "oofer";


        String departureDate = "8:24 PM";
        boolean isRoundTrip = true;
        boolean isDirect = false;


       String ticket = newTicket.createTicket(fname, lname, departureDate, isRoundTrip, isDirect);


       assertTrue(ticket.equals("Round and Indirect trip"));
    }

    @Test
    public void testCreateTicketOneWayDirectTrip() {
        String fname = "Javier";
        String lname = "oofer";


        String departureDate = "8:24 PM";
        boolean isRoundTrip = false;
        boolean isDirect = true;


        String ticket = newTicket.createTicket(fname, lname, departureDate, isRoundTrip, isDirect);


       assertTrue(ticket.equals("One-Way and Direct trip"));
    }


    @Test
    public void testCreateTicketOneWayInirectTrip() {
        String fname = "Javier";
        String lname = "oofer";


        String departureDate = "8:24 PM";
        boolean isRoundTrip = false;
        boolean isDirect = false;


        String ticket = newTicket.createTicket(fname, lname, departureDate, isRoundTrip, isDirect);


       assertTrue(ticket.equals("One-Way and Indirect trip"));
    }

//     this test will test if the the user tries to book a flight
//    to and from the same airport or different airports
    @ParameterizedTest
    @CsvFileSource(resources = "/AirportRouting.csv")
    public void testBookTrip(String airName1, String airName2, String expectedValue){

        //creating the airport objects
        Airport air1 = new Airport(airName1);
        Airport air2 = new Airport(airName2);

        Ticket newTicket = new Ticket();
        String trip = newTicket.bookTrip(air1, air2);


        assertTrue(trip.equals(expectedValue));
    }

    /*
    These are to test the Time Conversion Functionalities.
    First one tests for when military time is true, checks conversion is correct
    Second test checks if military time is true, checks if 12 converts to 0
    Third test checks if the value is correct if military time is false
     */
    @Test
    public void TimeConversion1(){
        assertTrue(Ticket.TimeConversion(true,"11:00 PM").equals("23:00"));
    }
    @Test
    public void TimeConversion2(){
     assertTrue(Ticket.TimeConversion(true,"12:48 AM").equals("00:48"));
    }

    @Test
    public void TimeConversion3(){
        assertTrue(Ticket.TimeConversion(false,"12:48 PM").equals("12:48 PM"));
    }



}

