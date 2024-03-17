package hit.androiscourse.shoppingapp.model;

public  class UidEntity<T> {
    private long id;
    private T obj;

    public UidEntity() {
    }

    public UidEntity(long id, T obj) {
        this.id = id;
        this.obj = obj;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
