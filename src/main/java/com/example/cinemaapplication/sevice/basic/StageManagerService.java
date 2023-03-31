package com.example.cinemaapplication.sevice.basic;

import com.example.cinemaapplication.exception.stageManager.StageManagerNotFound;
import com.example.cinemaapplication.model.StageManager;
import com.example.cinemaapplication.repository.StageManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StageManagerService {

    private final StageManagerRepository stageManagerRepository;

    public StageManager save(StageManager stageManager) {
        return stageManagerRepository.save(stageManager);
    }

    public void remove(StageManager stageManager) {
        stageManagerRepository.delete(stageManager);
    }

    public List<StageManager> findAll() {
        return stageManagerRepository.findAll();
    }

    public StageManager findById(Integer id) throws StageManagerNotFound {
        return stageManagerRepository.findById(id).orElseThrow(StageManagerNotFound::new);
    }
}
