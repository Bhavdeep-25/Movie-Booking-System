package com.ticketscape.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {
    // Model classes
    public static class Booking {
        private String id;
        private String userId;
        private String movieId;
        private String showtimeId;
        private List<String> seats;
        private double totalAmount;
        private LocalDateTime bookingDate;
        private String status;

        // Constructor
        public Booking(String userId, String movieId, String showtimeId, List<String> seats, double totalAmount) {
            this.id = UUID.randomUUID().toString();
            this.userId = userId;
            this.movieId = movieId;
            this.showtimeId = showtimeId;
            this.seats = seats;
            this.totalAmount = totalAmount;
            this.bookingDate = LocalDateTime.now();
            this.status = "confirmed";
        }

        // Getters and setters
        public String getId() { return id; }
        public String getUserId() { return userId; }
        public String getMovieId() { return movieId; }
        public String getShowtimeId() { return showtimeId; }
        public List<String> getSeats() { return seats; }
        public double getTotalAmount() { return totalAmount; }
        public LocalDateTime getBookingDate() { return bookingDate; }
        public String getStatus() { return status; }
    }

    // In-memory storage (similar to frontend state)
    private List<Booking> bookings = new ArrayList<>();

    // Service methods
    public Booking createBooking(String userId, String movieId, String showtimeId, List<String> seats, double totalAmount) {
        Booking booking = new Booking(userId, movieId, showtimeId, seats, totalAmount);
        bookings.add(booking);
        return booking;
    }

    public List<Booking> getUserBookings(String userId) {
        return bookings.stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .toList();
    }

    public Booking getBooking(String bookingId) {
        return bookings.stream()
                .filter(booking -> booking.getId().equals(bookingId))
                .findFirst()
                .orElse(null);
    }

    // Main method for testing
    public static void main(String[] args) {
        BookingService service = new BookingService();
        
        // Test creating a booking
        List<String> seats = List.of("A1", "A2");
        Booking booking = service.createBooking(
            "user123",
            "movie456",
            "showtime789",
            seats,
            25.99
        );
        
        System.out.println("Created booking: " + booking.getId());
        System.out.println("User bookings: " + service.getUserBookings("user123").size());
    }
} 