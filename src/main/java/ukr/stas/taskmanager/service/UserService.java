package ukr.stas.taskmanager.service;

import org.springframework.web.multipart.MultipartFile;
import ukr.stas.taskmanager.dto.UserDto;
import ukr.stas.taskmanager.model.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User changeRoleToAdmin(User user);
    List<User> findAll();
    User getUserByEmail(String email);
    boolean isUserEmailPresent(String email);
    User getUserById(Long userId);
    void deleteUser(Long id);
    UserDto addPhoto(User user) throws IOException;
}
