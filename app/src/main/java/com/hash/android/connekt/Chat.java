package com.hash.android.connekt;

/**
 * Created by aditi on 02-Sep-17.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.bumptech.glide.Glide;




public class Chat extends AppCompatActivity {

    LinearLayout layout;

    ImageView imagePicker;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    String photoMessage;
    ImageView profile;
    TextView name;

    final static int RC_PHOTO_PICKER = 123;

    private FirebaseDatabase database;
    private DatabaseReference mFirebaseReference1;
    private DatabaseReference mFirebaseReference2;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosReference;

    private  PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent =getIntent();
        User chatter = intent.getParcelableExtra("user");
        final String chatWith = chatter.getUID();
        String chatName = chatter.getName();
        String profilePic = chatter.getPhotoURL();


        layout = (LinearLayout) findViewById(R.id.layout1);
        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        imagePicker = (ImageView)findViewById(R.id.imageSend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chatbar);
        name = toolbar.findViewById(R.id.nameView);
        profile = toolbar.findViewById(R.id.profileView);
        preferenceManager = new PreferenceManager(this);
        /*database = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mFirebaseReference1 = database.getReference().child(preferenceManager.getUID()+"_"+chatWith);
        mFirebaseReference2 = database.getReference().child(chatWith+"_"+preferenceManager.getUID());
        mChatPhotosReference = mFirebaseStorage.getReference().child("chat_photos");*/

        name.setText(chatName);
        Glide.with(profile.getContext())
                .load(profilePic)
                .into(profile);

        imagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Complete action using"), RC_PHOTO_PICKER);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message= new Message(messageArea.getText().toString(),null,preferenceManager.getUID());

                if(!message.getText().equals("")){
                    /*mFirebaseReference1.push().setValue(message);
                    mFirebaseReference2.push().setValue(message);*/
                    addMessageBox(message,1);
                    messageArea.setText("");
                }
            }
        });

        /*mFirebaseReference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Message newMessage = dataSnapshot.getValue(Message.class);

                if(newMessage.getUserID().equals(preferenceManager.getUID())){
                    addMessageBox(newMessage, 1);
                }
                else{
                    addMessageBox(newMessage, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_PHOTO_PICKER && resultCode==RESULT_OK )
        {
            Uri imageUri = data.getData();

            //get the reference to stored file at database
            StorageReference photoReference = mChatPhotosReference.child(imageUri.getLastPathSegment());

            //upload file to firebase
            photoReference.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    photoMessage = downloadUrl.toString();
                    Message message = new Message(null,photoMessage,preferenceManager.getUID());
                    /*mFirebaseReference1.push().setValue(message);
                    mFirebaseReference2.push().setValue(message);*/
                    addMessageBox(message,1);

                }
            });
        }
    }


    public void addMessageBox(Message msg, int type){

        TextView textView = new TextView(Chat.this);
        ImageView imageView = new ImageView(Chat.this);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if(type == 1) {
            lp2.gravity = Gravity.LEFT;
            if(msg.getPhotoUrl()!=null)
            {
                textView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(imageView.getContext())
                        .load(photoMessage)
                        .into(imageView);
                imageView.setBackgroundResource(R.drawable.bubble_out);
            }
            else{
                textView.setText(msg.getText());
                textView.setBackgroundResource(R.drawable.bubble_out);
            }
        }
        else{
            lp2.gravity = Gravity.RIGHT;
            if(msg.getPhotoUrl()!=null)
            {
                textView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(imageView.getContext())
                        .load(photoMessage)
                        .into(imageView);
                imageView.setBackgroundResource(R.drawable.bubble_in);
            }
            else{
                textView.setText(msg.getText());
                textView.setBackgroundResource(R.drawable.bubble_in);
            }
        }
        textView.setLayoutParams(lp2);
        imageView.setLayoutParams(lp2);
        layout.addView(textView);
        layout.addView(imageView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}
