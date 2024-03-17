package hit.androiscourse.shoppingapp.model;

public class UserEntity extends UidEntity<User>{
    private User user;

    public UserEntity(long id, User obj) {
        super(id, obj);
    }
}
