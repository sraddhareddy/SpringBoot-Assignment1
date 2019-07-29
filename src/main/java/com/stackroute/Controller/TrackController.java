package com.stackroute.Controller;

import com.stackroute.Muzix.Track;
import com.stackroute.exceptions.TrackAlreadyExists;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.Service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1")
public class TrackController {
    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }



    @PostMapping("track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track) throws TrackAlreadyExists {
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("created", HttpStatus.CREATED);
        } catch (TrackAlreadyExists e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() throws TrackNotFound {
        ResponseEntity responseEntity;
        try {
            return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
        } catch (TrackNotFound e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping({"id"})
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id) throws TrackNotFound {
        ResponseEntity responseEntity;
        try {
            trackService.getTrackById(id);
            return new ResponseEntity<String>("tracked", HttpStatus.OK);
        } catch (TrackNotFound e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<Track> deleteTrack(@PathVariable("id") int id) throws TrackNotFound {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity<String>("deleted", HttpStatus.FORBIDDEN);
        } catch (TrackNotFound e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Track> UpdateTrack(@RequestBody Track track) throws TrackAlreadyExists {
        ResponseEntity responseEntity;

        try {

            trackService.updateTrack(track);
            responseEntity= new ResponseEntity<String>("updated", HttpStatus.OK);
        } catch (TrackAlreadyExists e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("track/{name}")
    @Query("from Track where name =?1")
    public ResponseEntity<Track> getTrackByName(@PathVariable("name") String name)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.getTrackByName(name);
            responseEntity=new ResponseEntity<String>("found",HttpStatus.OK);
        }catch(Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
