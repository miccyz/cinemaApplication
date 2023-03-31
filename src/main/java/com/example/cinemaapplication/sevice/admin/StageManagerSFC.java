package com.example.cinemaapplication.sevice.admin;

import com.example.cinemaapplication.dto.stageManager.StageManagerAddDto;
import com.example.cinemaapplication.dto.stageManager.StageManagerDto;
import com.example.cinemaapplication.dto.stageManager.StageManagerEditDto;
import com.example.cinemaapplication.exception.stageManager.StageManagerNotFound;
import com.example.cinemaapplication.mapper.StageManagerMapper;
import com.example.cinemaapplication.model.Movie;
import com.example.cinemaapplication.model.StageManager;
import com.example.cinemaapplication.sevice.basic.MessageService;
import com.example.cinemaapplication.sevice.basic.StageManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Service("adminStageManagerSFC")
@RequiredArgsConstructor
public class StageManagerSFC {

    private final StageManagerService stageManagerService;
    private final MessageService messageService;
    private final StageManagerMapper stageManagerMapper;

    public ModelAndView displayList(Model model) {
        List<StageManager> stageManagerList = stageManagerService.findAll();
        List<StageManagerDto> dtoList = stageManagerList.stream().map(stageManagerMapper::toDto).collect(Collectors.toList());
        model.addAttribute("stageManagerList", dtoList);
        return new ModelAndView("admin/stageManager/list");
    }

    public ModelAndView displayAddForm(Model model) {
        model.addAttribute("addDto", new StageManagerAddDto());
        return new ModelAndView("admin/stageManager/add");
    }

    public ModelAndView add(StageManagerAddDto addDto, Model model) {
        StageManager stageManager = stageManagerMapper.toNewModel(addDto);
        stageManagerService.save(stageManager);
        messageService.responseMessage(true, "Zapisano", model);
        return displayAddForm(model);
    }

    public ModelAndView displayEditForm(Integer id, Model model) throws StageManagerNotFound {
        StageManager stageManager = stageManagerService.findById(id);
        StageManagerEditDto editDto = stageManagerMapper.toEditDto(stageManager);
        model.addAttribute("editDto", editDto);
        return new ModelAndView("admin/stageManager/edit");
    }

    public ModelAndView edit(StageManagerEditDto editDto, Model model) throws StageManagerNotFound {
        StageManager stageManager = stageManagerService.findById(editDto.getId());
        StageManager toSave = stageManagerMapper.toEditModel(stageManager, editDto);
        stageManagerService.save(toSave);
        messageService.responseMessage(true, "Zapisano", model);
        return displayEditForm(editDto.getId(), model);
    }

    public ModelAndView remove(Integer id) throws StageManagerNotFound {
        StageManager stageManager = stageManagerService.findById(id);
        for (Movie i : stageManager.getMoveList()) {
            i.setStageManager(null);
        }

        stageManagerService.remove(stageManager);
        return new ModelAndView("redirect:list");
    }
}
