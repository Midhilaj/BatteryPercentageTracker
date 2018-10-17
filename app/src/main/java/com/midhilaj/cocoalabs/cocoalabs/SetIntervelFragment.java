package com.midhilaj.cocoalabs.cocoalabs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetIntervelFragment extends Fragment {


    EditText input;
    Button submit;

    View view;
    public SetIntervelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_set_intervel, container, false);
        input=(EditText)view.findViewById(R.id.input_interval_);
        if( new Milla().getDefaults("intervel__",getContext())!=null){
            input.setText( new Milla().getDefaults("intervel__",getContext())+"");
        }else{
            input.setText("1");
        }
        submit=(Button)view.findViewById(R.id.submit_intervel_);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input.getText().toString().trim().length()>0){
                    new Milla().setDefaults("intervel__",input.getText().toString().trim(),getContext());
                    if(!new Milla().isMyServiceRunning(getContext(),ReadDetailsService.class)){
                        Log.i("info_bird","isMyServiceRunning  is false");
                        Intent readtime = new Intent(getContext(), ReadDetailsService.class);
                       getActivity(). startService(readtime);
                    }else{
                        Intent readtime = new Intent(getContext(), ReadDetailsService.class);
                       getActivity(). stopService(readtime);
                       getActivity(). startService(readtime);
                        Log.i("info_bird","isMyServiceRunning  is true");
                    }
                }
            }
        });
        return view;
    }

}
