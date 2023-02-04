package ukr.stas.taskmanager.service;

import ukr.stas.taskmanager.model.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    List<Role> findAll();
}
