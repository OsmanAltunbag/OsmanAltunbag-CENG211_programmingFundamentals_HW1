package TicketBooking;

public class Customer<T>{
    private String name;
    private int numberOfTickets;
    private T[] tickets;  
    private Ticket[] bookedTickets; // Biletler bu dizide tutulacak
    private int ticketsBooked = 0;  // Şu ana kadar kaç bilet rezerve edildiğini tutar
    
    public Customer(String name, int numberOfTickets) {
        this.name = name;
        this.numberOfTickets = numberOfTickets;
        this.bookedTickets = new Ticket[numberOfTickets];
    }
    
    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for numberOfTickets
    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    // Getter and Setter for tickets
    public T[] getTickets() {
        return tickets;
    }

    public void setTickets(T[] tickets) {
        this.tickets = tickets;
    }
  
    
    public Ticket[] getBookedTickets() {
    return bookedTickets;
}
    
    public void addTicket(Ticket ticket) {
        if (ticketsBooked < numberOfTickets) {
            bookedTickets[ticketsBooked] = ticket;
            ticketsBooked++;
        }
    }   

    @Override
    public String toString() {
        StringBuilder ticketInfo = new StringBuilder("Customer " + name + " has booked the following tickets:\n");
        for (Ticket ticket : bookedTickets) {
            if (ticket != null) {
                ticketInfo.append(ticket.getTicketDetails()).append("\n");
            }
        }
        return ticketInfo.toString();
    }}

  



