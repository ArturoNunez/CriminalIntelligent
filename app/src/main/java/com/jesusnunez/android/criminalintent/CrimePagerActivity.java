package com.jesusnunez.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jesusnunez.android.criminalintent.modelo.Crime;
import com.jesusnunez.android.criminalintent.modelo.CrimeLab;

import java.util.List;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class CrimePagerActivity
        extends FragmentActivity {

    private static final String EXTRA_CRIME_ID =
            "com.jesusnunez.android.criminalintent.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(
            Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext,
                CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = findViewById(
                R.id.activity_crime_pager_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager =
                getSupportFragmentManager();
        mViewPager.setAdapter(
                new FragmentStatePagerAdapter(
                        fragmentManager) {

                    @Override
                    public int getCount() {
                        return mCrimes.size();
                    }

                    @Override
                    public Fragment getItem(
                            int position) {
                        Crime crime = mCrimes
                                .get(position);
                        return CrimeFragment
                                .newInstance(
                                        crime.getId());
                    }
                });
        for (int i=0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
