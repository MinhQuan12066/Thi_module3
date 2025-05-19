package com.example.tcomplex;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/floor-spaces")
public class FloorSpaceController {

    @Autowired
    private FloorSpaceRepository repository;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("floorSpace", new FloorSpace());
        model.addAttribute("statuses", FloorSpace.Status.values());
        model.addAttribute("types", FloorSpace.Type.values());
        return "add-floor-space";
    }

    @PostMapping("/add")
    public String addFloorSpace(@Valid FloorSpace floorSpace, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", FloorSpace.Status.values());
            model.addAttribute("types", FloorSpace.Type.values());
            return "add-floor-space";
        }
        repository.save(floorSpace);
        return "redirect:/floor-spaces/list";
    }

    @GetMapping("/list")
    public String listFloorSpaces(Model model) {
        model.addAttribute("floorSpaces", repository.findAll());
        model.addAttribute("types", FloorSpace.Type.values());
        model.addAttribute("floors", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        return "list-floor-spaces";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        FloorSpace floorSpace = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid floor space ID: " + id));
        model.addAttribute("floorSpace", floorSpace);
        model.addAttribute("statuses", FloorSpace.Status.values());
        model.addAttribute("types", FloorSpace.Type.values());
        return "edit-floor-space";
    }

    @PostMapping("/edit/{id}")
    public String updateFloorSpace(@PathVariable("id") Long id, @Valid FloorSpace floorSpace, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", FloorSpace.Status.values());
            model.addAttribute("types", FloorSpace.Type.values());
            return "edit-floor-space";
        }
        floorSpace.setId(id);
        repository.save(floorSpace);
        return "redirect:/floor-spaces/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteFloorSpace(@PathVariable("id") Long id) {
        FloorSpace floorSpace = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid floor space ID: " + id));
        repository.delete(floorSpace);
        return "redirect:/floor-spaces/list";
    }
}