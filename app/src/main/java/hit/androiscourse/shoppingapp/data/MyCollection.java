package hit.androiscourse.shoppingapp.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import hit.androiscourse.shoppingapp.model.UidEntity;

public class MyCollection<T> {
    private HashMap<Long, T> objects;

    public MyCollection() {
        objects = new HashMap<Long, T>();

    }

    public HashMap<Long, T> getObjects() {
        return objects;
    }

    public void setObjects(HashMap<Long, T> objects) {
        this.objects = objects;
    }

    public List<T> getAll(){
        return this.objects.values().stream().collect(Collectors.toList());
    }

    public void createObject(T obj){
        Long key = (long)this.objects.size();
        key ++;
        UidEntity newObj = (UidEntity) obj;
        newObj.setId(key);
        this.objects.put(key, (T) newObj);
    }

    public T getObjectById(long id){
        return this.objects.get(id);
    }
    public void updateObject(long id, T obj){
        this.objects.replace(id, obj);
    }
    public void deleteObject(long id){
        this.objects.remove(id);
    }



}
