package br.efficient.DiasSemanaPackage.DiasSemanaPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.efficient.HorarioPackage.add_Horario;
import br.efficient.R;

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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_horario_semana);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiasSemanaActivity.this, add_Horario.class);
                startActivity(intent);
            }
        });




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

