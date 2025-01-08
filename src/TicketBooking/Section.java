package TicketBooking;
import java.util.Random;
    public class Section {
        private int sectionID;
        private Ticket[][] seats;
        private double maxPrice;
        private double minPrice;
    
        public Section(int sectionID) {
            this.sectionID = sectionID;
            
            // Maximum ve minimum fiyatları belirle
            setPriceBoundaries(sectionID);
    
            this.seats = new Ticket[10][60]; // 10 rows, 60 seats
            initializeTickets(); // Biletleri başlat
        }
    
        private void setPriceBoundaries(int sectionID) {
            Random random = new Random();
            int baseMaxPrice = 4000; // Maksimum fiyat için başlangıç değeri
            int baseMinPrice = 3000; // Minimum fiyat için başlangıç değeri
            int decrementStep = 500; // Her bölümde fiyat sınırları 500 azaltılacak
    
            // Döngü ile her bölüm için fiyat sınırlarını hesapla
            for (int i = 0; i <= sectionID; i++) {
                int sectionMaxUpperBound = baseMaxPrice + 1001 - (i * decrementStep); // [MaxAlt, MaxÜst)
                int sectionMaxLowerBound = baseMaxPrice - (i * decrementStep);
    
                int sectionMinUpperBound = baseMinPrice + 1000 - (i * decrementStep); // [MinAlt, MinÜst)
                int sectionMinLowerBound = baseMinPrice - (i * decrementStep);
    
                if (i == sectionID) {
                    // İlgili bölüm için rastgele fiyat belirleme
                    maxPrice = random.nextDouble() * (sectionMaxUpperBound - sectionMaxLowerBound) + sectionMaxLowerBound;
                    minPrice = random.nextDouble() * (sectionMinUpperBound - sectionMinLowerBound) + sectionMinLowerBound;
                }
            }
        }
    
        private void initializeTickets() {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 60; j++) {
                    double price;
                    if (i == 0) {
                        price = maxPrice; // İlk satır
                    } else if (i == 1) {
                        price = maxPrice * 0.8; // İkinci satır
                    } else {
                        price = randomPrice(minPrice, maxPrice); // Diğer satırlar
                    }
                    seats[i][j] = new Ticket(sectionID, i, j, price);
                }
            }
        }
    
        private double randomPrice(double min, double max) {
            return min + (Math.random() * (max - min));
        }

     // Her bölümdeki koltukların doluluk durumunu ekrana yazdıran metot
     public void displaySeatOccupancies() {
        System.out.println("Section " + sectionID + " Seat Occupancies:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isBooked()) {
                    System.out.print("X "); // Rezerve edilmiş koltuk
                } else {
                    System.out.print("O "); // Boş koltuk
                }
            }
            System.out.println(); // Satırın sonuna geldiğinde alt satıra geç
        }
        System.out.println(); // Bölüm sonuna boş satır ekle
    }
    public void printSectionDetails() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 60; j++) {
                System.out.println(seats[i][j].getTicketDetails());
            }
}

    }
    public Ticket[][] getSeats() {
        return seats;
    }
    public int getSectionID() {
        return sectionID;}
    }
