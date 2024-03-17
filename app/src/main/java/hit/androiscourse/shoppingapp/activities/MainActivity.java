package hit.androiscourse.shoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hit.androiscourse.shoppingapp.R;
import hit.androiscourse.shoppingapp.data.FireBaseDB;
import hit.androiscourse.shoppingapp.data.MockDb;
import hit.androiscourse.shoppingapp.services.AuthenticationService;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public FirebaseUser user;
    public boolean isLoggedIn;
    private AuthenticationService authenticationService;



    public FirebaseAuth getmAuth() {
        return mAuth;
    }


    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FireBaseDB db = new FireBaseDB();
        mAuth = FirebaseAuth.getInstance();
        authenticationService = AuthenticationService.getInstance(this);

    }
}