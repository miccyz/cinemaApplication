package com.example.cinemaapplication.view.admin;

import com.example.cinemaapplication.dto.stageManager.StageManagerAddDto;
import com.example.cinemaapplication.dto.stageManager.StageManagerEditDto;
import com.example.cinemaapplication.exception.stageManager.StageManagerNotFound;
import com.example.cinemaapplication.sevice.admin.StageManagerSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminStageManagerCOntroller")
@RequiredArgsConstructor
@RequestMapping("admin/stageManager")
public class StageManagerController {

    private final StageManagerSFC stageManagerSFC;

    @GetMapping("list")
    public ModelAndView displayList(Model model) {
        return stageManagerSFC.displayList(model);
    }

    @GetMapping("add")
    public ModelAndView displayAddForm(Model model) {
        return stageManagerSFC.displayAddForm(model);
    }

    @PostMapping("add")
    public ModelAndView add(StageManagerAddDto addDto, Model model) {
        return stageManagerSFC.add(addDto, model);
    }

    @GetMapping("edit")
    public ModelAndView displayEditForm(Integer id, Model model) throws StageManagerNotFound {
        return stageManagerSFC.displayEditForm(id, model);
    }

    @PostMapping("edit")
    public ModelAndView edit(StageManagerEditDto editDto, Model model) throws StageManagerNotFound {
        return stageManagerSFC.edit(editDto, model);
    }

    @PostMapping("remove")
    public ModelAndView remove(Integer id) throws StageManagerNotFound {
        return stageManagerSFC.remove(id);
    }
}
