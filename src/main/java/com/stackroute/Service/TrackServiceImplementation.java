package com.stackroute.Service;

import com.stackroute.Muzix.Track;
import com.stackroute.exceptions.TrackAlreadyExists;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.Muzix.Track;
import com.stackroute.exceptions.TrackAlreadyExists;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.Repository.TrackRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImplementation implements TrackService {
    @Autowired
    private TrackRepository trackRepository;

    public TrackServiceImplementation(TrackRepository trackRepository) {

        this.trackRepository = trackRepository;
    }

    //implement all the methods
    @Override
    public boolean saveTrack(Track track) throws TrackAlreadyExists {
        if(trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExists("already exists");
        }
        Track tracksave = trackRepository.save(track);
        if(tracksave==null)
            throw new TrackAlreadyExists("already exists");
        return true;


    }

    @Override
    public boolean deleteTrack(int id) throws TrackNotFound {
        if (getTrackById(id) == null) {
            throw new TrackNotFound("the track is not found");

        } else {

            trackRepository.delete(getTrackById(id));
            return true;
        }
    }

    @Override
    public List<Track> getAllTracks() throws TrackNotFound {
        if(trackRepository.findAll()==null)
            throw new TrackNotFound("this track is not found");
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFound {

        Optional<Track> trackbyid = trackRepository.findById(id);

        if (trackbyid.isPresent()) {
            return trackbyid.get();
        }
        else
            throw new TrackNotFound("the track is not found");
    }

    @Override
    public boolean updateTrack(Track track) throws TrackAlreadyExists {
        Track updatetrack = trackRepository.findById(track.getId()).get();

        if (updatetrack != null) {
            //Track newEntity = track.get();

            updatetrack.setId(track.getId());
            updatetrack.setName(track.getMovie());
            updatetrack.setSong(track.getSong());

            trackRepository.save(updatetrack);

            return true;
        } else {
            throw new TrackAlreadyExists("already exists");
        }
    }
    @Override
    public List<Track> getTrackByName(String name)
    {
        List<Track> found=trackRepository.getTrackByName(name);
        return found;
    }

}





