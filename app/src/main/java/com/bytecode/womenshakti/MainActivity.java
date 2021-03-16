package com.bytecode.womenshakti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CALL = 1;
    public static final int REQUEST_SMS = 0;
    private static final int GALLERY_INTENT_CODE = 1023;
    DrawerLayout drawerLayout = null;
    //  TextView fullName,email,phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    // Button resendCode;
    // Button resetPassLocal;
    FirebaseUser user;
    // ImageView profileImage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        // MenuItem logout=findViewById(R.id.logout);
       /*phone = findViewById(R.id.profilePhone);
        fullName = findViewById(R.id.profileName);
        email    = findViewById(R.id.profileEmail);*/
        //  resetPassLocal = findViewById(R.id.resetPasswordLocal);

        // profileImage = findViewById(R.id.profileImage);
        /*changeProfileImage = findViewById(R.id.changeProfile);*/
        /*   gaurdinaDetails= findViewById(R.id.guardian_details);*/

        Button btncall = (Button) findViewById(R.id.BtnCall);
        btncall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendmessage();
                makephonecall();

            }
        });
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

      /*  StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
              //  Picasso.get().load(uri).into(profileImage);
            }
        });
*/
        //resendCode = findViewById(R.id.resendCode);
        // verifyMsg = findViewById(R.id.verifyMsg);


        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

       /* if(!user.isEmailVerified()){
            verifyMsg.setVisibility(View.VISIBLE);
            resendCode.setVisibility(View.VISIBLE);

            resendCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(), "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("tag", "onFailure: Email not sent " + e.getMessage());
                        }
                    });
                }
            });
        }
*/



       /* DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    phone.setText(documentSnapshot.getString("phone"));
                    fullName.setText(documentSnapshot.getString("fName"));
                    email.setText(documentSnapshot.getString("email"));

                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });*/

       /* resetPassLocal = findViewById(R.id.resetPasswordLocal);
        resetPassLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetPassword = new EditText(v.getContext());

                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter New Password > 6 Characters long.");
                passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String newPassword = resetPassword.getText().toString();
                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Password Reset Successfully.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Password Reset Failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // close
                    }
                });

                passwordResetDialog.create().show();

            }
        });
*/
        /*changeProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open gallery
                Intent i = new Intent(v.getContext(),EditProfile.class);
                i.putExtra("fullName",fullName.getText().toString());
                i.putExtra("email",email.getText().toString());
                i.putExtra("phone",phone.getText().toString());
                startActivity(i);
//

            }
        });*/
       /* gaurdinaDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open gallery
                Intent i = new Intent(v.getContext(),GuardianApp.class);

                startActivity(i);
//

            }
        });*/






   /* public void logout(MenuItem menu) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
*/

    }

    public void makephonecall() {
        SharedPreferences sharedPreferences = getSharedPreferences("myspf", Context.MODE_PRIVATE);
        String mno = sharedPreferences.getString("phone1", "no value");
        Toast.makeText(this, "Mobile no. is" + mno, Toast.LENGTH_SHORT).show();
        if (mno.trim().length() > 0) {
            //checking for request
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //if request not granted then request for permission
                ActivityCompat.requestPermissions(MainActivity.this, new
                        String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + sharedPreferences.getString("phone1", "no value")));
                Toast.makeText(this, "Mobile no. is" + mno, Toast.LENGTH_SHORT).show();

                //write the logic to call
                startActivity(intent);
            }
        } else {
            Toast.makeText(MainActivity.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            makephonecall();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == REQUEST_SMS) {
            myMessage();
            Toast.makeText(this, "testing only on request permission", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendmessage() {
        int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            myMessage();
            Toast.makeText(this, "send button" + checkPermission, Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
        }
    }

    public void myMessage() {
        int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            SharedPreferences sharedPreferences = getSharedPreferences("myspf", Context.MODE_PRIVATE);
            String mno1 = sharedPreferences.getString("phone1", "no value");
            String mno2 = sharedPreferences.getString("phone2", "no value");
            String mno3 = sharedPreferences.getString("phone3", "no value");
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mno1, null, "Help I am in danger", null, null);
            smsManager.sendTextMessage(mno2, null, "Help I am in danger", null, null);
            smsManager.sendTextMessage(mno3, null, "Help I am in danger", null, null);
            Toast.makeText(this, "Message sent" + "Help I am in danger" + " " + mno1, Toast.LENGTH_SHORT).show();
            //smsManager.sendTextMessage(phoneNumber, null, message, null, null, 0); } else { ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    public void gaurdianDetails(MenuItem menuItem){
        startActivity(new Intent(getApplicationContext(),GuardianApp.class));
    }

    public void logout(MenuItem item) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}

