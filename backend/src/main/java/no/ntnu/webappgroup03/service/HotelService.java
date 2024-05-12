package no.ntnu.webappgroup03.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import no.ntnu.webappgroup03.model.Hotel;
import no.ntnu.webappgroup03.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic related to Hotels.
 */
@Service
public class HotelService {

  @Autowired
  private HotelRepository hotelRepository;

  /**
   * Constructor class for hotel service class
   *
   * @param hotelRepository hotelRepository
   */
  public HotelService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  /**
   * Find all the hotels and returns it in a list
   *
   * @return a list of all hotels
   */
  public List<Hotel> findAllHotels() {

    return (List<Hotel>) hotelRepository.findAll();
  }

  /**
   * Return all hotels.
   *
   * @return all hotels.
   */
  public Iterable<Hotel> getAll() {
    return hotelRepository.findAll();
  }

  /**
   * Filters the hotels based on location
   *
   * @param location the location of the hotel
   * @return hotelsByLocation the hotels with desired location.
   */
  public Iterable<Hotel> getAllHotelsByLocation(String location) {
    //Retrieve all hotels from the repository
    Iterable<Hotel> allHotels = getAll();

    //Filter hotels by location
    List<Hotel> hotelsByLocation = new ArrayList<>();
    for (Hotel hotel : allHotels) {
      if (hotel.getLocation().equalsIgnoreCase(location)) {
        hotelsByLocation.add(hotel);
      }
    }
    return hotelsByLocation;
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
   * Add a Hotel to the application state (persist in the database).
   *
   * @param hotel Hotel to persist
   * @return true when Hotel added, false on error
   */
  public boolean add(Hotel hotel) {
    boolean added = false;
    if (hotel != null && hotel.isValid()) {
      Hotel existingHotel = findById(hotel.getId());
      if (existingHotel == null) {
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
   * Update a hotel in the application state (persist in the database).
   *
   * @param hotel Hotel to update
   * @return null on success, error message on error
   */
  public String update(Integer id, Hotel hotel) {
    // Error checking first
    String errorMessage = null;
    Hotel existingHotel = findById(id);
    if (existingHotel == null) {
      errorMessage = "No hotel with id " + id + " found";
    } else if (hotel == null || !hotel.isValid()) {
      errorMessage = "Wrong data in request body";
    } else if (hotel.getId() != id) {
      errorMessage = "Hotel ID in the URL does not match the ID in JSON data (response body)";
    }

    if (errorMessage == null) {
      hotelRepository.save(hotel);
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
