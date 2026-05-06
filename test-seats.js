// Test script to verify seat configuration
const { MovieProvider, generateSeatMap } = require('./src/contexts/MovieContext.tsx');

// Get the seat map
const seatMap = generateSeatMap();

// Find Interstellar showtimes (st1 and st2)
const interstellarShowtimes = ['st1', 'st2'];

interstellarShowtimes.forEach(showtimeId => {
  console.log(`\nChecking seats for Interstellar showtime ${showtimeId}:`);
  const seats = seatMap[showtimeId];
  
  // Filter for row A seats
  const rowASeats = seats.filter(seat => seat.row === 'A');
  
  // Display status of each seat in row A
  rowASeats.forEach(seat => {
    console.log(`Seat ${seat.row}${seat.number}: ${seat.status}`);
  });
}); 