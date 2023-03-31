package com.example.cinemaapplication.mapper;

import com.example.cinemaapplication.dto.actor.ActorAddDto;
import com.example.cinemaapplication.dto.actor.ActorDto;
import com.example.cinemaapplication.dto.actor.ActorEditDto;
import com.example.cinemaapplication.model.Actor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ActorMapper {

    public ActorDto toDto(Actor actor) {
        return new ActorDto(
                actor.getId(),
                actor.getFirstName(),
                actor.getLastName()
        );
    }

    public Actor toNewModel(ActorAddDto addDto) {
        return new Actor(
                null,
                addDto.getFirstName(),
                addDto.getLastName(),
                Collections.emptyList()
        );
    }

    public ActorEditDto toEditDto(Actor actor) {
        return new ActorEditDto(
                actor.getId(),
                actor.getFirstName(),
                actor.getLastName()
        );
    }

    public Actor toEditModel(Actor actor, ActorEditDto editDto) {
        actor.setFirstName(editDto.getFirstName());
        actor.setLastName(editDto.getLastName());
        return actor;
    }
}
