package hit.androiscourse.shoppingapp.fragments;

import static com.google.android.gms.tasks.Tasks.await;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import hit.androiscourse.shoppingapp.R;
import hit.androiscourse.shoppingapp.activities.MainActivity;
import hit.androiscourse.shoppingapp.dao.UserDaoImpl;
import hit.androiscourse.shoppingapp.services.AuthenticationService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSignUp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSignUp extends Fragment implements TextWatcher {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String userName;
    private String password;
    private String repeatPassword;

    private EditText userNameInput;
    private EditText passwordInput;
    private EditText repeatPasswordInput;

    private Button registerButton;

    private FirebaseAuth mAuth;

    public FragmentSignUp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSignUp.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSignUp newInstance(String param1, String param2) {
        FragmentSignUp fragment = new FragmentSignUp();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        userNameInput = view.findViewById(R.id.editTextUserNameReg);
        passwordInput = view.findViewById(R.id.editTextPasswordReg);
        repeatPasswordInput = view.findViewById(R.id.editTextPasswordRepeate);
        registerButton = view.findViewById(R.id.buttonRegisterReg);


        userNameInput.addTextChangedListener(this);
        passwordInput.addTextChangedListener(this);
        repeatPasswordInput.addTextChangedListener(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) requireActivity();
                FirebaseAuth mAuth = activity.getmAuth();
                mAuth.createUserWithEmailAndPassword(userName, password)
                        .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    activity.user = user;
                                    Toast.makeText(activity, "Register success", Toast.LENGTH_LONG).show();
                                    Navigation.findNavController(view).navigate(R.id.action_fragmentSignUp_to_fragmentShop);


                                } else {
                                    Toast.makeText(activity, "Register Failed ", Toast.LENGTH_LONG).show();

                                }

                            }
                        });
            }
            });


        registerButton.setEnabled(false);

        return view;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        userName = userNameInput.getText().toString();
        password = passwordInput.getText().toString();
        repeatPassword = repeatPasswordInput.getText().toString();

        if(!userName.isEmpty() && !password.isEmpty() && !repeatPassword.isEmpty()){
            if(password.equals(repeatPassword)) {
                registerButton.setEnabled(true);
            }
            else{
                registerButton.setEnabled(false);
            }
        }

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        userName = userNameInput.getText().toString();
        password = passwordInput.getText().toString();
        repeatPassword = repeatPasswordInput.getText().toString();

        if(!userName.isEmpty() && !password.isEmpty() && !repeatPassword.isEmpty()){
            if(password.equals(repeatPassword)) {
                registerButton.setEnabled(true);
            }
            else{
                registerButton.setEnabled(false);
            }
        }


    }

    @Override
    public void afterTextChanged(Editable s) {
        userName = userNameInput.getText().toString();
        password = passwordInput.getText().toString();
        repeatPassword = repeatPasswordInput.getText().toString();

        if(!userName.isEmpty() && !password.isEmpty() && !repeatPassword.isEmpty()){
            if(password.equals(repeatPassword)) {
                registerButton.setEnabled(true);
            }
            else{
                registerButton.setEnabled(false);
            }
        }


    }
}