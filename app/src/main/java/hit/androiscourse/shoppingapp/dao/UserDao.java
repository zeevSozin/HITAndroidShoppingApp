package hit.androiscourse.shoppingapp.dao;

import java.util.List;

import hit.androiscourse.shoppingapp.model.UserEntity;

public interface UserDao {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(long id);
    void createUser(UserEntity user);
    void update(long id, UserEntity user);
    void deleteById(long id);
}
