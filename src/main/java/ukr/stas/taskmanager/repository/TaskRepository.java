package ukr.stas.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ukr.stas.taskmanager.model.Task;
import ukr.stas.taskmanager.model.User;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByOwnerOrderByDateDesc(User user);
}