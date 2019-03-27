package org.styleru.the6hands.presentation.mainscreen;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import org.styleru.the6hands.R;

public class MainActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
