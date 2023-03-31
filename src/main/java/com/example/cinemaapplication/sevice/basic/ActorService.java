package com.example.cinemaapplication.sevice.basic;

import com.example.cinemaapplication.exception.actor.ActorNotFound;
import com.example.cinemaapplication.model.Actor;
import com.example.cinemaapplication.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    public void remove(Actor actor) {
        actorRepository.delete(actor);
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Actor findById(Integer id) throws ActorNotFound {
        return actorRepository.findById(id).orElseThrow(ActorNotFound::new);
    }
}
