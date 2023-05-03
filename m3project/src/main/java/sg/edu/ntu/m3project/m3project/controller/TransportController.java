package sg.edu.ntu.m3project.m3project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.m3project.m3project.entity.Transport;
import sg.edu.ntu.m3project.m3project.repo.TransportRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/transport")

public class TransportController {

    @Autowired
    TransportRepository transportRepo;

    @GetMapping()
    public ResponseEntity<List<Transport>> getAllTransport() {
        try {
            List<Transport> transports = (List<Transport>) transportRepo.findAll();
            if (transports.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(transports);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Transport> createTransport(@RequestBody Transport transport) {

        try {
            Transport created = transportRepo.save(transport);
            return new ResponseEntity(transportRepo.findById(created.getId()), HttpStatus.CREATED);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{transportId}")
    public ResponseEntity<Transport> updateTransport(@RequestBody Transport transport,
            @PathVariable Integer transportId) {
        Optional<Transport> currentTransport = transportRepo.findById(transportId);
        if (currentTransport.isPresent()) {
            try {
                Transport t = currentTransport.get();

                t.setDescription(transport.getDescription());
                t.setPrice(transport.getPrice());

                transportRepo.save(t);
                return ResponseEntity.ok().body(t);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{transportId}")
    public ResponseEntity deleteTransport(@PathVariable int transportId) {
        boolean exist = transportRepo.existsById(transportId);
        if (exist) {
            try {
                transportRepo.deleteById(transportId);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
