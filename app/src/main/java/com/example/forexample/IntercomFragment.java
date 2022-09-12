package com.example.forexample;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Objects;

public class IntercomFragment extends Fragment {

    Button open_door;
    ImageView back_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intercom, container, false);
        open_door = view.findViewById(R.id.open_door_button);
        back_button = view.findViewById(R.id.back_button);
        open_door.setOnClickListener(v -> Toast.makeText(getActivity(), "Дверь открыта", Toast.LENGTH_SHORT).show());
        back_button.setOnClickListener(v -> {
            FragmentManager manager = getFragmentManager();
            assert manager != null;
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(Objects.requireNonNull(manager.findFragmentByTag("FragmentTransaction")));
            transaction.commit();
        });
        return view;
    }
}