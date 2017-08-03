package com.example.pablo.efficient.DiasSemanaPackage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.pablo.efficient.R;

public class DiasSemanaActivity extends AppCompatActivity {
    private static final String TAB = "FragmentActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_semana);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);




    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SegundaFragment(), "SEG");
        adapter.addFragment(new TercaFragment(), "TER");
        adapter.addFragment(new QuartaFragment(), "QUA");
        adapter.addFragment(new QuintaFragment(), "QUI");
        adapter.addFragment(new SextaFragment(), "SEX");
        adapter.addFragment(new SabadoFragment(), "SAB");
        viewPager.setAdapter(adapter);
    }



}

