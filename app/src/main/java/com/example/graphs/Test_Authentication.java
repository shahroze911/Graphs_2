package com.example.graphs;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.graphs.Classes.Firebase_Class.databaseReference;


public class Test_Authentication extends AppCompatActivity {


    static final int GOOGLE_SIGN = 123;
    Button btnfirebase;
    FirebaseAuth mAuth;
    Button btn_login;
    GoogleSignInClient googleSignInClient;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__authentication);
        btnfirebase=findViewById(R.id.btnFirebase);
        btnfirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode= FirebaseDatabase.getInstance();
                databaseReference=rootNode.getReference("users");
                databaseReference.setValue("Hello");
                //databaseReference.child(userName).setValue(users);
                //databaseReference.setValue(users);

            }
        });

        btn_login = findViewById(R.id.btn_authentication);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken("59813587221-ttk05t2k1p83u6egc85a8nggp4tr0p7t.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        /*btn_login.setOnClickListener(v -> Sign_in_with_google());*/
    }

    public void Sign_in_with_google(View view) {

        Toast.makeText(this, "btn pressed", Toast.LENGTH_SHORT).show();
        Intent it = googleSignInClient.getSignInIntent();
        startActivityForResult(it,GOOGLE_SIGN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN)
        {
            Toast.makeText(this, ""+requestCode, Toast.LENGTH_SHORT).show();
            Task<GoogleSignInAccount> task = GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            try
            {
                GoogleSignInAccount account  = task.getResult(ApiException.class);
                if (account!=null)
                {
                    firebaseauthwithgoogle(account);
                }
            }
            catch (ApiException e)
            {

                Toast.makeText(this, "Exception", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void firebaseauthwithgoogle(GoogleSignInAccount account) {

        AuthCredential  credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Test_Authentication.this, "success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUi(user);
                        }
                        else
                        {
                            Toast.makeText(Test_Authentication.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
                                    
                        }


                    }
                });
    }

    private void updateUi(FirebaseUser user) {

        if (user!=null)
        {
            Toast.makeText(this, ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ""+user.getPhoneNumber(), Toast.LENGTH_SHORT).show();
        }
        else
        {

        }

    }
}
