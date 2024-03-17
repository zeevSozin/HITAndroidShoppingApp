package hit.androiscourse.shoppingapp.data;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.ArraySortedMap;
import com.google.firebase.database.snapshot.ChildKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedMap;
import java.util.stream.Collectors;

import hit.androiscourse.shoppingapp.activities.MainActivity;
import hit.androiscourse.shoppingapp.model.Product;
import hit.androiscourse.shoppingapp.model.UidEntity;
import hit.androiscourse.shoppingapp.model.UidObject;

public class FireBaseCollection<T> {

    private HashMap<String,T> objects;
    private String collectionName;
    private DatabaseReference mDatabase;
    private T obj;

    public FireBaseCollection(String collectionName){
        this.collectionName = collectionName;
        objects = new HashMap<>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(collectionName);
    }

    // CRUD

    public void createObject(T obj){
        mDatabase.push().setValue(obj);
    }

    public List<T>  getAll(){
        getAll(new FirebaseCallback<ArrayList<T>>() {
            @Override
            public void onSuccess(ArrayList<T> data) {
                Log.d("Info", objects.toString());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        return (ArrayList<T>)objects.values().stream().collect(Collectors.toList());
    }


    public void getAll(final FirebaseCallback<ArrayList<T>> callback){
        objects.clear();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    objects.put(snapshot.getKey(), (T)snapshot.getValue());


                }
                callback.onSuccess((ArrayList<T>) objects.values().stream().collect(Collectors.toList()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }

    public T getObjectById(String id){
        mDatabase.child(String.valueOf(id)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                }
                else {
                    obj = (T) task.getResult().getValue();

                }

            }
        });
        return obj;
    }
    public void updateObject(String id, T obj){
        mDatabase.child(id.toString()).setValue(obj);
        this.objects.remove(id, obj);

    }
    public void deleteObject(String id){
        mDatabase.child(id.toString()).removeValue();
        this.objects.remove(id);
    }

    public interface FirebaseCallback<T>{
        void onSuccess(T data);
        void onFailure(Exception e);
    }


}
