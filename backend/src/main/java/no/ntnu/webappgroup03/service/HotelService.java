package no.ntnu.webappgroup03.service;

import java.util.Optional;
import no.ntnu.webappgroup03.model.Hotel;
import no.ntnu.webappgroup03.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Buisness logic related to Hotels.
 */
@Service
public class HotelService {

  @Autowired
  private HotelRepository hotelRepository;

  /**
   * Return all hotels.
   *
   * @return all hotels.
   */
  public Iterable<Hotel> getAll() {
    return hotelRepository.findAll();
  }

  /**
   * Look up Hotel in the application state.
   *
   * @param id ID of the Hotel to look up
   * @return The hotel or null if none found by that ID
   */
  public Hotel findById(int id) {
    Optional<Hotel> hotel= hotelRepository.findById(id);
    return hotel.orElse(null);
  }

  /**
   * Add an author to the application state (persist in the database).
   *
   * @param hotel Author to persist
   * @return true when author added, false on error
   */
  public boolean add(Hotel hotel) {
    boolean added = false;
    if (hotel != null && hotel.isValid()) {
      Hotel existingAuthor = findById(hotel.getId());
      if (existingAuthor == null) {
        hotelRepository.save(hotel);
        added = true;
      }
    }
    return added;
  }

  /**
   * Remove a hotel from application state (database).
   *
   * @param id ID of the hotel to delete
   * @return true when hotel deleted, false when hotel was not found in the database
   */
  public boolean delete(int id) {
    Optional<Hotel> hotel = hotelRepository.findById(id);
    if (hotel.isPresent()) {
      hotelRepository.deleteById(id);
    }
    return hotel.isPresent();
  }

  /**
   * Update an author in the application state (persist in the database).
   *
   * @param author Author to update
   * @return null on success, error message on error
   */
  public String update(Integer id, Hotel author) {
    // Error checking first
    String errorMessage = null;
    Hotel existingHotel = findById(id);
    if (existingHotel == null) {
      errorMessage = "No hotel with id " + id + " found";
    } else if (author == null || !author.isValid()) {
      errorMessage = "Wrong data in request body";
    } else if (author.getId() != id) {
      errorMessage = "Hotel ID in the URL does not match the ID in JSON data (response body)";
    }

    if (errorMessage == null) {
      hotelRepository.save(author);
    }
    return errorMessage;
  }

  /**
   * Get the number of hotels in the database.
   *
   * @return The total number of hotels stored in the database.
   */
  public long GetCount() {
    return hotelRepository.count();
  }
}
