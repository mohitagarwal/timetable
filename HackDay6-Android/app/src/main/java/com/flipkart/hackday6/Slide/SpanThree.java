package com.flipkart.hackday6.Slide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.flipkart.hackday6.Activities.Configurations;

import com.flipkart.hackday6.R;

public class SpanThree extends Fragment {

    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fa = super.getActivity();
        View three = inflater.inflate(R.layout.span_three, container, false);

        Button validerInscription = (Button) three.findViewById(R.id.valider);
        validerInscription.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fa, Configurations.class);
                startActivity(intent);
            }
        });

        return three;
    }

}
