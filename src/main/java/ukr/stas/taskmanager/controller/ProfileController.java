package ukr.stas.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ukr.stas.taskmanager.dto.UserDto;
import ukr.stas.taskmanager.model.User;
import ukr.stas.taskmanager.service.TaskService;
import ukr.stas.taskmanager.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping
@BasePathAwareController
@RequiredArgsConstructor
public class ProfileController {

    private UserService userService;
    private TaskService taskService;

    @Autowired
    public ProfileController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("tasksOwned", taskService.findByOwnerOrderByDateDesc(user));
        return "views/profile";
    }

    @GetMapping("/profile/mark-done/{taskId}")
    public String setTaskCompleted(@PathVariable Long taskId) {
        taskService.setTaskCompleted(taskId);
        return "redirect:/profile";
    }

    @GetMapping("/profile/unmark-done/{taskId}")
    public String setTaskNotCompleted(@PathVariable Long taskId) {
        taskService.setTaskNotCompleted(taskId);
        return "redirect:/profile";
    }

    @PostMapping("/profile/addPhoto")
    public ResponseEntity<UserDto> addPhoto(@RequestParam("name") String name, @RequestParam("photo") MultipartFile file) throws IOException {
        User addNewPhoto = User.builder().name(name)

    }

//    @PostMapping("/profile/addPhoto")
//    public String addPhoto(@RequestParam("title") String title,
//                           @RequestParam("photo") MultipartFile image, Model model)
//            throws IOException {
//        String id = userService.addPhoto(title, image);
//        return "redirect:/profile/" + id;
//    }
}
