package hit.androiscourse.shoppingapp.dao;

import java.util.List;

import hit.androiscourse.shoppingapp.data.MockDb;
import hit.androiscourse.shoppingapp.model.UserEntity;

public class UserDaoImpl implements UserDao{
    @Override
    public List<UserEntity> getAllUsers() {
        return MockDb.users.getAll();
    }

    @Override
    public UserEntity getUserById(long id) {
        return MockDb.users.getObjectById(id);
    }

    @Override
    public void createUser(UserEntity user) {
        MockDb.users.createObject(user);
    }

    @Override
    public void update(long id, UserEntity user) {
        MockDb.users.updateObject(id,user);
    }

    @Override
    public void deleteById(long id) {
        MockDb.users.deleteObject(id);

    }
}
