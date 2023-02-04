package ukr.stas.taskmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ukr.stas.taskmanager.dto.UserDto;
import ukr.stas.taskmanager.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toUserDto(User user);
}
