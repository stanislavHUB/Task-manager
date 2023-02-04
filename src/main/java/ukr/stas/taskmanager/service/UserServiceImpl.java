package ukr.stas.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ukr.stas.taskmanager.dto.UserDto;
import ukr.stas.taskmanager.mapper.UserMapper;
import ukr.stas.taskmanager.model.Role;
import ukr.stas.taskmanager.model.User;
import ukr.stas.taskmanager.repository.RoleRepository;
import ukr.stas.taskmanager.repository.UserRepository;

import java.io.IOException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static final String ADMIN="ADMIN";
    private static final String USER="USER";

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(USER);
        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        user.setPhoto(user.getPhoto());
        return userRepository.save(user);
    }

    @Override
    public User changeRoleToAdmin(User user) {
        Role adminRole = roleRepository.findByRole(ADMIN);
        user.setRoles(new ArrayList<>(Collections.singletonList(adminRole)));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isUserEmailPresent(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.getOne(id);
        user.getTasksOwned().forEach(task -> task.setOwner(null));
        userRepository.delete(user);
    }

    @Override
    public UserDto addPhoto(User user) {
        User addPhotoToUser = this.userRepository.save(user);
        return userMapper.toUserDto(addPhotoToUser);
    }

//    public String addPhoto(String title, MultipartFile file) throws IOException {
//        Photo photo = new Photo(title);
//        photo.setImage(
//                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
//        photo = photoRepo.insert(photo); return photo.getId();
//    }
//
//    public Photo getPhoto(String id) {
//        return photoRepo.findById(id).get();
//    }
}

