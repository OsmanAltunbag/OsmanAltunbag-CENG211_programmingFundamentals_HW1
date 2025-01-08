package TicketBooking;
import java.util.Random;

public class Venue {
    private Section[] sections;
    private Customer<Ticket>[] customers; // Müşterileri saklamak için eklenen dizi
    private int customerCount = 0; // Müşteri sayısını takip eden değişken
    public Venue() {
        sections = new Section[4];
        customers = new Customer[150]; 

        // Bölümleri rastgele fiyatlarla oluştur
        for (int i = 0; i < sections.length; i++) {
            sections[i] = createSection(i);
        }
    }

    // Bölümü oluşturma ve fiyatları ayarlama metodu
    private Section createSection(int sectionID) {
        double maxPrice;
        double minPrice;
        Random random = new Random();

        // Fiyat sınırlarını belirleme
        switch (sectionID) {
            case 0:
                maxPrice = random.nextDouble() * (5000 - 4000) + 4000; // [4000, 5000)
                minPrice = random.nextDouble() * (4000 - 3000) + 3000; // [3000, 4000)
                break;
            case 1:
                maxPrice = random.nextDouble() * (4500 - 3500) + 3500; // [3500, 4500)
                minPrice = random.nextDouble() * (3500 - 2500) + 2500; // [2500, 3500)
                break;
            case 2:
                maxPrice = random.nextDouble() * (4000 - 3000) + 3000; // [3000, 4000)
                minPrice = random.nextDouble() * (3000 - 2000) + 2000; // [2000, 3000)
                break;
            case 3:
                maxPrice = random.nextDouble() * (3500 - 2500) + 2500; // [2500, 3500)
                minPrice = random.nextDouble() * (2500 - 1500) + 1500; // [1500, 2500)
                break;
            default:
                maxPrice = 0; // Hatalı ID için
                minPrice = 0;
                break;
        }

        return new Section(sectionID); // Yeni Section nesnesini oluştur
    }

    public void bookRandomTicketsForCustomer(Customer<Ticket> customer) {
        Random random = new Random();
      

        int ticketsBooked = 0;
        while (ticketsBooked < customer.getNumberOfTickets()) {
            int sectionIndex = random.nextInt(sections.length);  // Rastgele bir section seç

            Section selectedSection = sections[sectionIndex];  // Seçilen section
            Ticket[][] seats = selectedSection.getSeats();
    
            int row = random.nextInt(10);  // Rastgele satır seç
            int seat = random.nextInt(60); // Rastgele koltuk seç
            if (!seats[row][seat].isBooked()) {  // Eğer koltuk rezerve edilmemişse
                seats[row][seat].bookTicket();   // Koltuğu rezerve et
                customer.addTicket(seats[row][seat]);;  // Müşteri biletlerine ekle
                ticketsBooked++;
            }
        }
    }
      // Müşteri ekleme metodu
      public void addCustomer(Customer<Ticket> customer) {
        if (customerCount < customers.length) {
            customers[customerCount] = customer; // Müşteriyi customers dizisine ekle
            customerCount++; // Müşteri sayısını artır
        } else {
            System.out.println("Müşteri kapasitesi dolu, müşteri eklenemedi.");
        }
    }
      // Her bölümün koltuk doluluk durumunu gösteren metot
      public void displayAllSectionOccupancies() {
        for (Section section : sections) {
            section.displaySeatOccupancies(); // Her bölüm için koltuk durumunu göster
        }
    }


    public Customer<Ticket>[] getCustomers() {
        return customers; // Müşteri dizisini döndür
    }
    
    public Section[] getSections() {
        return sections;
    }
}
