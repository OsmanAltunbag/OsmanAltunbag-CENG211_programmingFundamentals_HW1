package TicketBooking;

public class Query {
    
    // En yüksek gelir elde eden bölümü döndüren metot
    public static Section getHighestRevenueSection(Venue venue) {
        Section[] sections = venue.getSections(); // Venue'deki tüm bölümleri al
        Section highestRevenueSection = null;     // En yüksek gelire sahip bölümü saklayacak değişken
        double highestRevenue = 0.0;

        // Her bir bölüm için geliri hesapla
        for (Section section : sections) {
            double sectionRevenue = calculateSectionRevenue(section);
            //System.out.println("Section " + section.getSectionID() + " Revenue: " + sectionRevenue); // Kontrol için gelir yazdır

            // Eğer bu bölüm en yüksek gelire sahipse, onu güncelle
            if (sectionRevenue > highestRevenue) {
                highestRevenue = sectionRevenue;
                highestRevenueSection = section;
            }
        }

        return highestRevenueSection; // En yüksek gelire sahip bölümü döndür
    }

    // Bir bölümün toplam gelirini hesaplayan yardımcı metot
    private static double calculateSectionRevenue(Section section) {
        Ticket[][] seats = section.getSeats();
        double revenue = 0.0;

        // Her koltuğu kontrol et, rezerve edilmişse fiyatını ekle
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isBooked()) {
                    revenue += seats[i][j].getPrice();
                }
            }
        }

        return revenue; // Toplam geliri döndür
    }
      // Venue'deki toplam geliri döndüren metot
      public static double getTotalRevenue(Venue venue) {
        Section[] sections = venue.getSections();  // Venue'deki tüm bölümleri al
        double totalRevenue = 0.0;

        // Her bölümün gelirini hesaplayıp toplama ekle
        for (Section section : sections) {
            totalRevenue += calculateSectionRevenue(section);
        }

        return totalRevenue;  // Toplam geliri döndür
    }
   
    
        // Diğer metodlar (en yüksek gelirli bölüm ve toplam gelir hesaplama) burada...
    
        // Venue'nun doluluk oranını hesaplayan metot
        public static double getOccupancyRate(Venue venue) {
            Section[] sections = venue.getSections(); // Venue'deki tüm bölümleri al
            double totalOccupiedSeats = 0; // Toplam dolu koltuk sayısı
            double totalSeats = 0; // Toplam koltuk sayısı
    
            // Her bölüm için dolu koltuk sayısını ve toplam koltuk sayısını hesapla
            for (Section section : sections) {
                Ticket[][] seats = section.getSeats();
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[i].length; j++) {
                        totalSeats++; // Toplam koltuk sayısını artır
                        if (seats[i][j].isBooked()) {
                            totalOccupiedSeats++; // Dolu koltuk sayısını artır
                        }
                    }
                }
            }
    
            // Doluluk oranını hesapla
            return (totalSeats == 0) ? 0 : (totalOccupiedSeats / totalSeats) * 100; // Yüzde olarak döndür
        }
      // En yüksek toplam fiyatı ödeyen müşterinin biletlerini döndüren metot
      public static Customer<Ticket> getHighestPayingCustomer(Venue venue) {
        Customer<Ticket>[] customers = venue.getCustomers();  // Venue'deki tüm müşterileri al

        // Eğer customers dizisi null veya boşsa, hata vermemesi için kontrol edelim
        if (customers == null || customers.length == 0) {
            return null;  // Eğer müşteri yoksa null döndür
        }

        Customer<Ticket> highestPayingCustomer = null;  // En yüksek ödemeyi yapan müşteri
        double highestTotalPrice = 0.0;  // En yüksek toplam ödeme

        // Müşteriler arasında dolaşarak toplam ödemeyi hesapla
        for (Customer<Ticket> customer : customers) {
            if (customer != null) {  // Eğer müşteri mevcutsa
                double totalPrice = calculateTotalPrice(customer);  // Müşterinin toplam ödediği fiyatı hesapla

                // Eğer bu müşteri en yüksek toplam ödemeyi yaptıysa, güncelle
                if (totalPrice > highestTotalPrice) {
                    highestTotalPrice = totalPrice;
                    highestPayingCustomer = customer;
                }
            }
        }

        return highestPayingCustomer;  // En yüksek ödemeyi yapan müşteriyi döndür
    }

    // Bir müşterinin toplam ödediği fiyatı hesaplayan yardımcı metot
    private static double calculateTotalPrice(Customer<Ticket> customer) {
        double totalPrice = 0.0;
        Ticket[] bookedTickets = customer.getBookedTickets();

        // Müşterinin biletleri arasında dolaşarak toplam fiyatı hesapla
        for (Ticket ticket : bookedTickets) {
            if (ticket != null && ticket.isBooked()) {
                totalPrice += ticket.getPrice();  // Rezerve edilmişse fiyatı ekle
            }
        }

        return totalPrice;
    }
      // En pahalı bileti döndüren metot
      public static Ticket getMostExpensiveTicket(Venue venue) {
        Section[] sections = venue.getSections();  // Venue'deki tüm bölümleri al
        Ticket mostExpensiveTicket = null;  // En pahalı bileti saklayacak değişken
        double highestPrice = 0.0;  // En yüksek fiyat

        // Her bölümdeki biletleri kontrol et
        for (Section section : sections) {
            Ticket[][] seats = section.getSeats();
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    Ticket currentTicket = seats[i][j];
                    if (currentTicket != null && currentTicket.getPrice() > highestPrice) {
                        highestPrice = currentTicket.getPrice();
                        mostExpensiveTicket = currentTicket;  // Eğer bilet daha pahalıysa güncelle
                    }
                }
            }
        }

        return mostExpensiveTicket;  // En pahalı bileti döndür
    }
   
    }
     

