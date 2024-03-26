package com.ontariotechu.sofe3980U;
public class ticketAirportIntegrationTest {
    private Ticket ticket;
    private Airport airport;

    //The setup, initializes ticket and airport object
    @Before
    public void setup() {
        newTicket() = new ticket();
        newAirport() = new airport();

        Map<String, Integer> airportDist = new HashMap<>();

        //Mock data for flight distance
        airportDist.put("JFK-BJS", 6423);

        //Creates the array holding all flight distance
        airport.setAirportDistAssocArr(airportDist);

        //Creates array holding all direct flight paths
        boolean[] directFlightsArr = new boolean[] {true, false};
        airport.setDirectFlight(directFlightsArr);
    }
    
    @Test
    public void testCreateTicketAndFlight() {
        //initializing variables
        String fname = "Javier";
        String lname = "oofer";
        LocalDate departureDate = LocalDate.of(2024, 4, 4);
        LocalDate returnDate = LocalDate.of(2024, 4, 18);
        boolean militaryTime = true;
        boolean isRoundTrip = true;
        boolean isDirect = true;
        String origin = "JFK";
        String destination = "BJS";

        //Creates airport path that user will be taking
        airport[] airportPath = new airport[] {new airport("JFK"), new airport("BJS")};

        //Grabs the flight time between the flight
        int flightTime = airport.getFlightTime("JFK-BJS");

        //Creates a new ticket object with the variables we have
        String ticket = newTicket.createTicket(fname, lname, departureDate, isRoundTrip, isDirect, departureDate, returnDate, militaryTime, destination, origin, airportPath);

        // Tests if ticket has correct info
        assertNotNull(ticket);
        assertTrue(ticket.contains("round"));
        assertTrue(ticket.contains("Direct"));

        //Test if flight time is working
        assertNotNull(flightTime);
        assertEquals(flightTime, 6423);

        // Test if direct flight list is showing
        assertNotNull(airport.directFlightsArr);
    }
}
