package com.stackroute.Service;
import com.stackroute.Muzix.Track;
import com.stackroute.exceptions.TrackAlreadyExists;
import com.stackroute.exceptions.TrackNotFound;

import java.util.List;

public interface TrackService {
    public boolean saveTrack(Track track) throws TrackAlreadyExists;

    public boolean deleteTrack(int id) throws TrackNotFound;

    public List<Track> getAllTracks() throws TrackNotFound;

    public Track getTrackById(int id) throws TrackNotFound;

    public boolean updateTrack(Track track) throws TrackAlreadyExists;
    public List<Track> getTrackByName(String name);
}
