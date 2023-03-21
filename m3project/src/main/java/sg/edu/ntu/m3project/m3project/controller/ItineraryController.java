package sg.edu.ntu.m3project.m3project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import sg.edu.ntu.m3project.m3project.entity.Destination;
import sg.edu.ntu.m3project.m3project.entity.Itinerary;
import sg.edu.ntu.m3project.m3project.entity.User;
import sg.edu.ntu.m3project.m3project.repo.DestinationRepository;
import sg.edu.ntu.m3project.m3project.repo.ItineraryRepository;
import sg.edu.ntu.m3project.m3project.repo.UserRepository;

import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
    @Autowired
    ItineraryRepository itineraryRepo;

    @Autowired
    DestinationRepository destinationRepo;

    @Autowired
    UserRepository userRepo;

    @GetMapping
    public ResponseEntity<List<Itinerary>> getAllItineraries() {
        List<Itinerary> itineraryRecords = (List<Itinerary>) itineraryRepo.findAll();
        return ResponseEntity.ok().body(itineraryRecords);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<Itinerary>> getUserItinerary(@PathVariable int userId) {
        List<Itinerary> userItinerary = (List<Itinerary>) itineraryRepo.findAllByUserId(userId);
        if (userItinerary.size()>0) {
            return ResponseEntity.ok().body(userItinerary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity addDestination(@PathVariable int userId, @RequestBody Itinerary itinerary ) {

        Optional<User> user = userRepo.findById(userId);
        Optional<Destination> destination = destinationRepo.findById(itinerary.getDestination().getId());

        if (!(user.isPresent() && destination.isPresent())){
            return ResponseEntity.badRequest().build();
        }

        itinerary.setUser(user.get());
        itinerary.setDestination(destination.get());
        itineraryRepo.save(itinerary);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itinerary.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity updateDestination(@PathVariable int userId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/deleteAllDestinations/{userId}")
    public ResponseEntity deleteAllDestinations(@PathVariable int userId) {
        List<Itinerary> userItineraryList = (List<Itinerary>) itineraryRepo.findAllByUserId(userId);
        if (userItineraryList.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        for (Itinerary itinerary : userItineraryList) {
            if(itinerary.getDestination() == null) {
                return ResponseEntity.notFound().build();
            }
            itinerary.setDestination(null);
            itineraryRepo.save(itinerary);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/deleteDestination/{userId}/{destinationId}")
    public ResponseEntity deleteDestination(@PathVariable int userId, @PathVariable int destinationId) {
        Optional<Itinerary> itineraryOptional = itineraryRepo.findByUserIdAndDestinationId(userId, destinationId);
        if(!itineraryOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Itinerary itineraryToUpdate = itineraryOptional.get();
        itineraryToUpdate.setDestination(null);
        itineraryRepo.save(itineraryToUpdate);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{userId}/{destName}/accommodation")
    public ResponseEntity addAccommodation() {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{userId}/{destName}/transport")
    public ResponseEntity addTransport() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{userId}/{destName}/accommodation")
    public ResponseEntity deleteAccommodation() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{userId}/{destName}/transport")
    public ResponseEntity deleteTransport() {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{userId}/budget")
    public ResponseEntity setBudget(@RequestParam float budget) {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{userId}/{destName}/duration")
    public ResponseEntity setDuration(@RequestParam Date startDate, @RequestParam Date endDate) {
        return ResponseEntity.ok().build();
    }

}
