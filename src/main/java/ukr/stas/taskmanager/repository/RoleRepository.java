package ukr.stas.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ukr.stas.taskmanager.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(String user);
}
