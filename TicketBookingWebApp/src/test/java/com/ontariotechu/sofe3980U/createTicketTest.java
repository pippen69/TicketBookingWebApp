package com.ontariotechu.sofe3980U;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;


public class createTicketTest {
   
    private Ticket newTicket;

    @Before
    public void setup() {
        newTicket = new Ticket();
    }
   
   @Test
   public void testCreateTicketRoundDirectTrip() {
       String fname = "Javier";
       String lname = "oofer";


       LocalDate departureDate = LocalDate.of(2024, 4, 4);
       LocalDate returnDate = LocalDate.of(2024, 4, 18);
       boolean militaryTime = true;
       String[] airportPath = {"JFK", "BJS"};


       String ticket = newTicket.createTicket(fname, lname, departureDate, true, true, departureDate, returnDate, militaryTime, airportPath);


       assertTrue(ticket.equals("Round and Direct trip"));
   }


   @Test
   public void testCreateTicketRoundIndirectTrip() {
       String fname = "Johnathan";
       String lname = "ooferr";


       LocalDate departureDate = LocalDate.of(2024, 4, 12);
       LocalDate returnDate = LocalDate.of(2024, 4, 19);
       boolean militaryTime = true;
       String[] airportPath = {"DFW", "JFK", "LHR"};


       String ticket = newTicket.createTicket(fname, lname, departureDate, true, false, departureDate, returnDate, militaryTime, airportPath);


       assertTrue(ticket.equals("Round and Indirect trip"));
   }
   @Test
   public void testCreateTicketOneWayDirectTrip() {
       String fname = "Zainab";
       String lname = "ooferrr";


       LocalDate departureDate = LocalDate.of(2024, 4, 2);
       LocalDate returnDate = null;
       boolean militaryTime = true;
       String[] airportPath = {"YYZ", "LAX"};


       String ticket = newTicket.createTicket(fname, lname, departureDate, false, true, departureDate, returnDate, militaryTime, airportPath);


       assertTrue(ticket.equals("One-Way and Direct trip"));
   }


   @Test
   public void testCreateTicketOneWayInirectTrip() {
       String fname = "Prince";
       String lname = "ooferrrr";


       LocalDate departureDate = LocalDate.of(2024, 5, 3);
       LocalDate returnDate = null;
       boolean militaryTime = true;
       String[] airportPath = {"SIN", "YYZ", "LAX"};


       String ticket = newTicket.createTicket(fname, lname, departureDate, false, false, departureDate, returnDate, militaryTime, airportPath);


       assertTrue(ticket.equals("One-Way and Indirect trip"));
   }

    // this test will test if the the user tries to book a flight
    // to and from the same airport or different airports
    @ParameterizedTest
    @CsvFileSource(resources = "/bookTripInvalid.csv")
    public void testBookTrip(String origin, String destination, String expectedValue){
        Ticket newTicket = new Ticket();
        String trip = newTicket.bookTrip(origin, destination);


        assertTrue(trip.equals(expectedValue));
    }



}

