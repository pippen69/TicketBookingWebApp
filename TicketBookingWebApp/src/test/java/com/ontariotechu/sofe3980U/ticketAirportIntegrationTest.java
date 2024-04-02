package com.ontariotechu.sofe3980U;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Before;
import java.util.*;

public class ticketIntegrationTest {
    private Ticket newTicket;
    private Airport airport1;
    private Airport airport2;
    private Airport airport3;
    private Airport airport4;
    Airport[] airports;

    //The setup, initializes ticket and airport object
    @BeforeEach
    public void setup() {
        newTicket = new Ticket();
        airport1 = new Airport("JFK");
        airport2 = new Airport("YYZ");
        airport3 = new Airport("LAX");
        airport4 = new Airport("DEN");


        airports = new Airport[]{ airport1, airport2, airport3, airport4 }; // Initialization here

        ArrayList<String> existingAirports = new ArrayList<>();
        existingAirports.add("YYZ");
        existingAirports.add("JFK");
        existingAirports.add("LAX");

        //Mock data for flight distance
        airport1.setAirportDistance("YYZ", 115 ); //fight time in minutes
        airport1.setAirportDistance("DEN", 280 ); //fight time in minutes
        airport1.setAirportDistance("LAX", 385 ); //fight time in minutes
        airport1.setAirportDistance("JFK", 0 ); //fight time in minutes

        airport4.setAirportDistance("YYZ", 190 ); //fight time in minutes
        airport4.setAirportDistance("JFK", 225 ); //fight time in minutes
        airport4.setAirportDistance("LAX", 155 ); //fight time in minutes
        airport4.setAirportDistance("DEN", 0 ); //fight time in minutes

        airport2.setAirportDistance("JFK", 105 ); //fight time in minutes
        airport2.setAirportDistance("DEN", 235 ); //fight time in minutes
        airport2.setAirportDistance("LAX", 325 ); //fight time in minutes
        airport2.setAirportDistance("YYZ", 0 ); //fight time in minutes

        airport3.setAirportDistance("YYZ", 280 ); //fight time in minutes
        airport3.setAirportDistance("DEN", 140 ); //fight time in minutes
        airport3.setAirportDistance("JFK", 325 ); //fight time in minutes
        airport3.setAirportDistance("LAX", 0 ); //fight time in minutes

        //setting the direct flights
        airport1.addDirFlight("YYZ");//jfk
        airport1.addDirFlight("DEN");

        airport2.addDirFlight("LAX");//yyz
        airport2.addDirFlight("JFK");//yyz
        airport2.addDirFlight("DEN");//yyz

        airport3.addDirFlight("JFK");//LAX
        airport3.addDirFlight("YYZ");
        airport3.addDirFlight("DEN");

        airport4.addDirFlight("JFK");//DEN
        airport4.addDirFlight("YYZ");
        airport4.addDirFlight("LAX");


        //generating multi stop flights
        airport1.generateMultiFlights(airport2, airport3, airport4, existingAirports);
        airport2.generateMultiFlights( airport1, airport3, airport4, existingAirports);
        airport3.generateMultiFlights(airport1, airport2, airport4, existingAirports);
        airport4.generateMultiFlights(airport1, airport2, airport3, existingAirports);

    }
    
    //This test will test if the ticket class will successfully use the airport class to generate a flight time
    //To generate a flight time from the selected origin and destination requires a lot of integration between 
    // the two classes
    @Test
    public void testCreateTicketAndFlight() {
        //initializing variables
        String fname = "Javier";
        String lname = "oofer";
        String departureDate = "8:25 PM";
        String returnDate = "9:21 PM";
        boolean isRoundTrip = false;
        Airport origin = airport1;
        Airport destination = airport2;

        //Creates airport path that user will be taking
        newTicket.bookTrip(origin, destination);

        //find out if there is only direct or multistop
        boolean dirPathExists = origin.checkDirectFlight(destination);

        String ticket;

        //initializing the arraylist to create the ticket path
        ArrayList<String> airportSelected = new ArrayList<>();
        airportSelected.add(origin.airportName);
    
        if(!dirPathExists){ //there are no direct flights
            ticket = newTicket.createTicket(fname, lname, departureDate, isRoundTrip,  false);
            System.out.println("There are no direct paths to your destination.");
            
            ArrayList<String> originMulti = origin.returnMultiFlight();

            //finding which option the user selected
            for(int i = 0; i < originMulti.size(); i++){
                System.out.println( (i+1) + " - " + origin.airportName + "-->" + originMulti.get(i));
            }

            int multiFlightChoice = 1; //default select first path

            String flightString = originMulti.get(multiFlightChoice);

            //spliting the string
            String[] tmpPath = flightString.split("-->");

            //adding to the arraylist
            for (String airport : tmpPath) {
                airportSelected.add(airport);
            }

            newTicket.createAirportPath(airportSelected);

        }else{ //this is if there is a direct flight
            ticket = newTicket.createTicket("fname", "lname", "departureDate", isRoundTrip,  true);

            airportSelected.add(destination.airportName);

            newTicket.createAirportPath(airportSelected);
        }

        newTicket.setFlightTime(origin.getFlightTime(newTicket.getFlightPath(), airports));

        int flightTime = origin.getFlightTime(newTicket.getFlightPath(), airports);

        airportSelected.clear(); //freeing up space

        // Tests if ticket has info
        assertNotNull(ticket);

        //Test if flight time is working with the ticket
        assertNotNull(flightTime);
        assertEquals(flightTime, 115);
    }
}
