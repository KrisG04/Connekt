package com.hash.android.connekt;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by aditi on 03-Sep-17.
 */

public class ChatFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.chat_list, container, false);

        final ArrayList<User> users = new ArrayList<>();
        users.add(new User("Sumitabha",null,null,null,null,null,"https://scontent.fpat1-1.fna.fbcdn.net/v/t1.0-1/c0.0.240.240/p240x240/17884380_329793304090424_6049208603333473476_n.jpg?oh=769d3e4d06f9bec93b87786eb80aab20&oe=5A57A69F",null,null,null,"a",false,null,null,null,false,null));
        users.add(new User("Saptarshi",null,null,null,null,null,"https://scontent.fpat1-1.fna.fbcdn.net/v/t1.0-1/c1.0.240.240/p240x240/20525838_867210680098204_7731175762265688506_n.jpg?oh=374690e91890d411862a42b29c7ddd05&oe=5A5F6E23",null,null,null,"b",false,null,null,null,false,null));
        users.add(new User("Krishnachur",null,null,null,null,null,"https://scontent.fpat1-1.fna.fbcdn.net/v/t1.0-1/p240x240/21034294_337267450028635_6115580575153583952_n.jpg?oh=4a2fb63e7fbf3bd4a4f2e962710b13d4&oe=5A1E1F84",null,null,null,"c",false,null,null,null,false,null));
        users.add(new User("Prakhar",null,null,null,null,null,"https://scontent.fpat1-1.fna.fbcdn.net/v/t1.0-1/p240x240/16865228_295905140825110_1600140466052766582_n.jpg?oh=d0eba003b245a108bd291b51c0db8e89&oe=5A595C6B",null,null,null,"d",false,null,null,null,false,null));

        ChatAdapter adapter = new ChatAdapter(getActivity(),users);
        ListView listView = (ListView)rootView.findViewById(R.id.chat_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),Chat.class);
                intent.putExtra("User",(User)adapterView.getItemAtPosition(i));
            }
        });

        return rootView;
    }
}







