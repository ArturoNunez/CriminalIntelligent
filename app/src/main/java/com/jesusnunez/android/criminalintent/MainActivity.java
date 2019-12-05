package com.jesusnunez.android.criminalintent;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    @Override
    public Fragment createFragment() {
        return new CrimeListFragment();
    }
}
