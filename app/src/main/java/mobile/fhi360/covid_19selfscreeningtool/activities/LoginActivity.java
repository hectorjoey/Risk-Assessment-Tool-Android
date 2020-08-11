//package mobile.fhi360.covid_19selfscreeningtool.activities;
//
//import android.annotation.SuppressLint;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Patterns;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.HashMap;
//
//import mobile.fhi360.covid_19selfscreeningtool.Api.RetrofitClient;
//import mobile.fhi360.covid_19selfscreeningtool.R;
//import mobile.fhi360.covid_19selfscreeningtool.SupLoginActivity;
//import mobile.fhi360.covid_19selfscreeningtool.model.Supervisor;
//import mobile.fhi360.covid_19selfscreeningtool.model.Users;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class LoginActivity extends AppCompatActivity {
//    private static final String TAG = "LoginActivity";
//    EditText mEmail, mPassword;
//    Button mLogin, mSupLogin;
//    ProgressDialog loadingBar;
//    Button mGenLogin;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        mEmail = findViewById(R.id.et_email);
//        mPassword = findViewById(R.id.et_password);
//        mLogin = findViewById(R.id.btn_login);
//        mGenLogin = findViewById(R.id.genLogin);
//        mSupLogin = findViewById(R.id.supLogin);
//        loadingBar = new ProgressDialog(this);
//        HashMap<String, String> status = status();
//        String statuss = status.get("status");
//        if (statuss != null) {
//
//        } else {
//            showCustomDialog();
//        }
//
//        mGenLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final String email = mEmail.getText().toString().trim();
//                String password = mPassword.getText().toString().trim();
//
//                if (email.isEmpty()) {
//                    mEmail.setError("Email is required");
//                    mEmail.requestFocus();
//                    return;
//                }
//
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    mEmail.setError("Enter a valid email");
//                    mEmail.requestFocus();
//                    return;
//                }
//
//                if (password.isEmpty()) {
//                    mPassword.setError("Password required");
//                    mPassword.requestFocus();
//                    return;
//                }
//
//                if (password.length() < 6) {
//                    mPassword.setError("Password should be at least 6 character long");
//                    mPassword.requestFocus();
//                }
//
//            }
//        });
//
////        supLink.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent superviseIntent = new Intent(LoginActivity.this, SupLoginActivity.class);
////                startActivity(superviseIntent);
////                finish();
////            }
////        });
//
//
//        mSupLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String email = mEmail.getText().toString().trim();
//                String password = mPassword.getText().toString().trim();
//
//                if (email.isEmpty()) {
//                    mEmail.setError("Email is required");
//                    mEmail.requestFocus();
//                    return;
//                }
//
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    mEmail.setError("Enter a valid email");
//                    mEmail.requestFocus();
//                    return;
//                }
//
//                if (password.isEmpty()) {
//                    mPassword.setError("Password required");
//                    mPassword.requestFocus();
//                    return;
//                }
//
//                if (password.length() < 6) {
//                    mPassword.setError("Password should be at least 6 character long");
//                    mPassword.requestFocus();
//                }
//                loginSup(email, password);
//            }
//        });
//
//
//        mLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String email = mEmail.getText().toString().trim();
//                String password = mPassword.getText().toString().trim();
//
//                if (email.isEmpty()) {
//                    mEmail.setError("Email is required");
//                    mEmail.requestFocus();
//                    return;
//                }
//
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    mEmail.setError("Enter a valid email");
//                    mEmail.requestFocus();
//                    return;
//                }
//
//                if (password.isEmpty()) {
//                    mPassword.setError("Password required");
//                    mPassword.requestFocus();
//                    return;
//                }
//
//                if (password.length() < 6) {
//                    mPassword.setError("Password should be at least 6 character long");
//                    mPassword.requestFocus();
//                }
////
////                if (users.getUserType().equals("Users") || users.getUserType().equals("Admin")) {
//                    loginUser(email, password);
////                }
////                else {
////                    loginSup(email, password);
////                }
//            }
//        });
//    }
//
//    private void loginSup(String email, String password) {
//        Call<Supervisor> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .supLogin(email, password);
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Authenticating please wait...");
//        progressDialog.show();
//        call.enqueue(new Callback<Supervisor>() {
//            @Override
//            public void onResponse(Call<Supervisor> call, Response<Supervisor> response) {
//                if (response.isSuccessful()) {
//                    assert response.body() != null;
//                    Long supervisorId = response.body().getId();
//                    System.out.println("SUPPERVISORID " + supervisorId);
//                    suppervisorId(supervisorId);
//                    Intent supIntent = new Intent(LoginActivity.this, SupervisorActivity.class);
//                    startActivity(supIntent);
//                    finish();
//                    progressDialog.dismiss();
//                    Toast.makeText(LoginActivity.this, "Welcome Sup!", Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Supervisor> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "failed!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    public void loginUser(String email, String password) {
//        //do registration API call
//        Call<Users> call = RetrofitClient
//                .getInstance()
//                .getApi().login(email, password);
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Authenticating please wait...");
//        progressDialog.show();
//
//        call.enqueue(new Callback<Users>() {
//            @Override
//            public void onResponse(Call<Users> call, Response<Users> response) {
//                if (response.isSuccessful()) {
//                    Users users = response.body();
//                    System.out.println("users ..... " + users);
//                    System.out.println("users XXXXXX " + response);
//                    assert response.body() != null;
////                    userID = response.body().getId();
//                    assert users != null;
//                    //save userId to shared pref
//                    if (users.getUserType().equalsIgnoreCase("admin")) {
//
//                        Intent adminIntent = new Intent(LoginActivity.this, MainActivity.class);
//                        assert response.body() != null;
//                        adminIntent.putExtra("Admin", response.body().getUserType());
//                        startActivity(adminIntent);
//                        finish();
//                        Toast.makeText(LoginActivity.this, "Welcome Admin!", Toast.LENGTH_LONG).show();
//
//                    } else
////                        (users.getUserType().equalsIgnoreCase("user"))
//                    {
//                        Intent intent = new Intent(LoginActivity.this, UserHealthDataActivity.class);
//                        intent.putExtra("userId ", users.getId());
//                        saveUserId(users.getId());
//                        System.out.println("users ,,,," + users.getId());
////                        assert response.body() != null;
//                        intent.putExtra("User", response.body().getUserType());
////                        intent.putExtra("email",response.body().getEmail());
////                        intent.putExtra("password", response.body().getPassword());
//                        startActivity(intent);
//                        Toast.makeText(LoginActivity.this, "Welcome User!", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//
//                    }
//                } else {
//                    System.out.println("RESPONSE ==>> " + response);
//                    Toast.makeText(LoginActivity.this, "Cannot login! Check Credentials.!", Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Users> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                System.out.println("Threowing >>>>" + t);
//
//            }
//        });
//    }
//
//
//    public void saveUserId(Long id) {
//        SharedPreferences sharedPreferences = this.getSharedPreferences("userId", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sharedPreferences.edit();
//        edit.clear();
//        edit.putString("userId", id + "");
//        edit.apply();
//    }
//
//    public void suppervisorId(Long id) {
//        SharedPreferences sharedPreferences = this.getSharedPreferences("suppervisorId", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sharedPreferences.edit();
//        edit.clear();
//        edit.putString("suppervisorId", id + "");
//        edit.apply();
//    }
//
//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(false);
//        builder.setMessage("Do you want to Exit?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user pressed "yes", then he is allowed to exit from application
//                finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user select "No", just cancel this dialog and continue with app
//                dialog.cancel();
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
//
//
//    private void showCustomDialog() {
//        //before inflating the custom alert dialog layout, we will get the current activity viewGroup
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//        //then we will inflate the custom alert dialog xml that we created
//        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
//        Button ok = dialogView.findViewById(R.id.buttonOk);
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//
//        //finally creating the alert dialog and displaying it
//        AlertDialog alertDialog = builder.create();
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveStatus();
//                alertDialog.dismiss();
//            }
//        });
//        alertDialog.show();
//    }
//
//    public HashMap<String, String> status() {
//        HashMap<String, String> user = new HashMap<>();
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("status", Context.MODE_PRIVATE);
//        user.put("status", sharedPreferences.getString("status", null));
//        return user;
//    }
//
//    @SuppressLint("ApplySharedPref")
//    public void saveStatus() {
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("status", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("status", "1");
//        editor.commit();
//    }
//}
