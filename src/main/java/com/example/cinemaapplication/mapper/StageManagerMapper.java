package com.example.cinemaapplication.mapper;

import com.example.cinemaapplication.dto.stageManager.StageManagerAddDto;
import com.example.cinemaapplication.dto.stageManager.StageManagerDto;
import com.example.cinemaapplication.dto.stageManager.StageManagerEditDto;
import com.example.cinemaapplication.model.StageManager;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class StageManagerMapper {

    public StageManagerDto toDto(StageManager stageManager) {
        return new StageManagerDto(
                stageManager.getId(),
                stageManager.getFirstName(),
                stageManager.getLastName()
        );
    }

    public StageManager toNewModel(StageManagerAddDto addDto) {
        return new StageManager(
                null,
                addDto.getFirstName(),
                addDto.getLastName(),
                Collections.emptyList()
        );
    }

    public StageManagerEditDto toEditDto(StageManager stageManager) {
        return new StageManagerEditDto(
                stageManager.getId(),
                stageManager.getFirstName(),
                stageManager.getLastName()
        );
    }

    public StageManager toEditModel(StageManager stageManager, StageManagerEditDto editDto) {
        stageManager.setFirstName(editDto.getFirstName());
        stageManager.setLastName(editDto.getLastName());
        return stageManager;
    }
}
