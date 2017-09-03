package com.hash.android.connekt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class FacebookLogin extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 12344;
    private static final String TAG = FacebookLogin.class.getSimpleName();

    private static AccessToken token;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    Button loginWithFacebook;
    private ProgressDialog pd;
    private PreferenceManager mPrefs;
    private EditText userNameET;
    private TextView urlTextView;
    private String university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        final TextView text, urlDomain;
        text = (TextView) findViewById(R.id.connektTextView);
        mPrefs = new PreferenceManager(this);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "pacifico.ttf");
        loginWithFacebook = (Button) findViewById(R.id.loginWithFacebookButton);
        loginWithFacebook.setOnClickListener(this);
        text.setTypeface(custom_font);
        pd = new ProgressDialog(FacebookLogin.this);
        Spinner mSpinner = (Spinner) findViewById(R.id.spinner);
        userNameET = (EditText) findViewById(R.id.usernameTextView);
        urlTextView = (TextView) findViewById(R.id.urlUserNameTextView);

        urlDomain = (TextView) findViewById(R.id.urlDomainTV);
        mSpinner.setAdapter(getArtsDepartmentAdapter());

        userNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                urlTextView.setText(charSequence + ".");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                university = adapterView.getItemAtPosition(i).toString();
                switch (university) {
                    case "Jadavpur University":
                        urlDomain.setText("jaduniv.connekt");
                        break;

                    case "IIEST Shibpur":
                        urlDomain.setText("iiest.connekt");
                        break;

                    case "IIT Kharagpur":
                        urlDomain.setText("iitkgp.connekt");
                        break;

                    case "NIT Durgapur":
                        urlDomain.setText("nitdgp.connekt");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginWithFacebookButton) {
            //Start login flow
            signIn();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        if (user != null && new PreferenceManager(this).isFlowCompleted()) {
            Intent i = new Intent(FacebookLogin.this, DashboardActivity.class);
            FacebookLogin.this.overridePendingTransition(0, 0);
            startActivity(i);
            finish();
        }
    }

    private void fetchGraphData(AccessToken token) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && token != null) {

                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String link = object.getString("link");
                                    String gender = object.getString("gender");
                                    JSONObject profilePhoto = object.getJSONObject("picture");
                                    JSONObject data = profilePhoto.getJSONObject("data");
                                    String photoURL = data.getString("url");
                                    JSONObject cover = object.getJSONObject("cover");
                                    String coverURL = cover.getString("source");
                                    String name = object.getString("name");
                                    mPrefs.setLink(link);
                                    mPrefs.setGender(gender);
                                    mPrefs.setCoverURL(coverURL);
                                    mPrefs.setPhotoURL(photoURL);
                                    mPrefs.setName(name);
                                    mPrefs.saveUser();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name, gender, email, link, cover, picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();
            }

    }


    private SpinnerAdapter getArtsDepartmentAdapter() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Jadavpur University");
        list.add("IIEST Shibpur");
        list.add("IIT Kharagpur");
        list.add("NIT Durgapur");


        return new ArrayAdapter<>(this, R.layout.spinner_item, list);
    }


    private void signIn() {

        //Create an IDPConfig for facebook
        AuthUI.IdpConfig facebookIdp = new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER)
                .setPermissions(Arrays.asList("public_profile", "email"))
                .build();

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(
                                        facebookIdp
                                )
                        )
                        .build()
                , RC_SIGN_IN
        );
        if (!pd.isShowing()) {
            pd.setMessage("Creating your brand new account!");
            pd.show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            startActivity(new Intent(FacebookLogin.this, DashboardActivity.class));
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//
//            // Successfully signed in
//            if (resultCode == ResultCodes.OK) {
//                token = AccessToken.getCurrentAccessToken();
//                new PreferenceManager(FacebookLogin.this).setFacebookToken(token.toString());
//                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                if (user != null) {
//
//                    fetchGraphData(token);
//                    mPrefs.setUID(user.getUid());
//                    mPrefs.setEmail(user.getEmail());
//                    if (user.getPhotoUrl() != null)
//                        mPrefs.setPhotoURL(user.getPhotoUrl().toString());
////                    FirebaseAuth.getInstance().signOut();
//                    Toast.makeText(this, "Signed in", Toast.LENGTH_SHORT).show();
//
//                    //TODO: Start an intent to go to login activity
//
//                } else {
//                    Toast.makeText(this, "Failed to sign in", Toast.LENGTH_SHORT).show();
//                }
//                return;
//
//            } else {
//                // Sign in failed
//                if (response == null) {
//                    // User pressed back button
//                    Toast.makeText(this, "Sign in cancelled!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
//                    Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
//                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//
//
//            Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show();
        }

        pd.hide();
        pd.dismiss();
    }
}
