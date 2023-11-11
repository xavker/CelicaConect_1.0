package com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.celicaconect_10.R;
import com.google.android.material.tabs.TabLayout;



public class CampeonatoFultball extends Fragment {

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_campeonato_futbol, container, false);
        MyPagerAdapter myPagerAdapter =new MyPagerAdapter(getActivity().getSupportFragmentManager());

        ViewPager viewPager =  view.findViewById(R.id.pager);
        viewPager.setAdapter(myPagerAdapter);

        TabLayout tabLayout =  view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment;
            switch (i){
                case 0:
                    fragment=new TabladePosiciones();
                    break;
                case 1:
                    fragment=new Partidos();
                    break;
                case 2:
                    fragment=new HistoriaCampeonato();
                    break;
                default:
                    fragment=null;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tabla de Posiones";
                case 1:
                    return "Proximos PartidosColeccions";
                case 2:
                    return "Historia del Campeonato";
            }
            return null;
        }
    }


}
