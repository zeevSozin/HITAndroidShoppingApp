package hit.androiscourse.shoppingapp.services;

import static com.google.android.gms.tasks.Tasks.await;
import static com.google.android.gms.tasks.Tasks.whenAllComplete;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import hit.androiscourse.shoppingapp.activities.MainActivity;

public final class AuthenticationService {

    private final MainActivity activityContext;
    private boolean isSuccess;




    private static AuthenticationService instance;

    private AuthenticationService(MainActivity activityContext) {
        this.activityContext = activityContext;

    }
    public static AuthenticationService getInstance(MainActivity activity){
        if(instance == null){
            instance = new AuthenticationService(activity);
        }
        return instance;
    }

    public  boolean authenticateUser(String username, String password)  {
        isSuccess = false;
        //activityContext.isLoggedIn= false;


        firebaseAuth(username, password);


        if (isSuccess){
            return true;
        }
        else {
            return false;
        }

    }


    private void firebaseAuth(String username, String password)  {
        new Thread(
                () -> {
                    FirebaseAuth mAuth = activityContext.getmAuth();
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(activityContext, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        activityContext.user = user;
                                        isSuccess = true;
                                        Toast.makeText(activityContext, "Authentication success.",
                                                Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(activityContext, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });

                }
        ).start();

    }

    public boolean addUser(String userName, String password) throws ExecutionException, InterruptedException {
        boolean result = false;
        FirebaseAuth mAuth = this.activityContext.getmAuth();
         await( mAuth.createUserWithEmailAndPassword(userName, password)
                .addOnCompleteListener(activityContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            activityContext.user = user;
                            Toast.makeText(activityContext,"Register success", Toast.LENGTH_LONG).show();



                        } else {
                            Toast.makeText(activityContext,"Register Failed ", Toast.LENGTH_LONG).show();

                        }

                    }
                }));
        if(activityContext.user != null){

            result = true;
        }
        return result;

    }

}
