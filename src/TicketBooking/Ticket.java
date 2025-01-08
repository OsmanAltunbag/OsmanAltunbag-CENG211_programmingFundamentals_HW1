package TicketBooking;

public class Ticket {
    private int sectionNumber;
    private int rowNumber;
    private int seatNumber;
    private double price;
    private boolean isBooked;

    public Ticket(int sectionNumber, int rowNumber, int seatNumber, double price) {
        this.sectionNumber = sectionNumber;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.price = price;
        this.isBooked = false;
    }

    public void bookTicket() {
        this.isBooked = true;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return isBooked ? "X" : "O";
    }
    public String getTicketDetails() {
        return "Section: " + sectionNumber + ", Row: " + rowNumber + 
               ", Seat: " + seatNumber + ", Price: " + price + " TL" +
               ", Booked: " + (isBooked ? "Yes" : "No");
    }
}

