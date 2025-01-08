package TicketBookingApp;
import FileIO.FileIO;
import TicketBooking.Customer;
import TicketBooking.Query;
import TicketBooking.Section;
import TicketBooking.Ticket;
import TicketBooking.Venue;
import java.io.IOException;

public class TicketBookingApplication {
    public static void main(String[] args) {
        String filePath = "resources/customers.csv"; 
        Venue venue = new Venue();

        try {
            String[][] customersData = FileIO.readCSV(filePath);

            //System.out.println("CSV Dosyasından Okunan Veriler:");
            for (String[] customerData : customersData) {
                if (customerData[0] != null) {  // Eğer veri varsa işle
                    String customerName = customerData[0];
                    int numberOfTickets = Integer.parseInt(customerData[1]);
                    Customer<Ticket> customer = new Customer<>(customerName, numberOfTickets);
                    
                    // Rastgele biletleri müşteriye ata
                    venue.bookRandomTicketsForCustomer(customer);
                    venue.addCustomer(customer); // Müşteriyi venue'ya ekl
                    
                    // Müşteri bilet bilgilerini yazdır
                    //System.out.println(customer);
                }
            }
           
            // En yüksek gelirli bölümü bul
            Section highestRevenueSection = Query.getHighestRevenueSection(venue);
            System.out.println("The section with the highest revenue is Section " + highestRevenueSection.getSectionID());

            // Venue'nun toplam gelirini hesapla
            double totalRevenue = Query.getTotalRevenue(venue);
            System.out.println("The total revenue of the venue is: " + totalRevenue + " TL");

             // Venue'nun doluluk oranını hesapla
             double occupancyRate = Query.getOccupancyRate(venue);
             System.out.printf("The occupancy rate of the venue is: %.2f%%\n", occupancyRate); // Yüzde formatında yazdır

            // En yüksek ödemeyi yapan müşteriyi bul
            Customer<Ticket> highestPayingCustomer = Query.getHighestPayingCustomer(venue);
            if (highestPayingCustomer != null) {
            System.out.println("The customer who pays the highest price is: " + highestPayingCustomer.getName());
            System.out.println("Tickets of the highest paying customer:");
            for (Ticket ticket : highestPayingCustomer.getBookedTickets()) {
            if (ticket != null) {
               System.out.println(ticket.getTicketDetails() );
           }
       }
            } else {
            System.out.println("No customer has booked tickets.");
        }
              // En pahalı bileti bul
              Ticket mostExpensiveTicket = Query.getMostExpensiveTicket(venue);
              if (mostExpensiveTicket != null) {
                  System.out.println("The most expensive ticket is:");
                  System.out.println(mostExpensiveTicket.getTicketDetails());
              } else {
                  System.out.println("No tickets found.");
              }

                    // Tüm bölümlerin koltuk doluluk durumlarını göster
                    venue.displayAllSectionOccupancies();
   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
