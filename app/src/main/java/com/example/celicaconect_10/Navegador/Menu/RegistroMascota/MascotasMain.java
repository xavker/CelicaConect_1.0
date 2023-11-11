package com.example.celicaconect_10.Navegador.Menu.RegistroMascota;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.celicaconect_10.Navegador.Menu.TipodeLicencia.TipoB;
import com.example.celicaconect_10.R;
import com.google.android.material.tabs.TabLayout;


public class MascotasMain extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mascotas_main, container, false);
        MascotasMain.MyPagerAdapter myPagerAdapter = new MascotasMain.MyPagerAdapter(getActivity().getSupportFragmentManager());

        ViewPager viewPager = view.findViewById(R.id.container);
        viewPager.setAdapter(myPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabs);
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
            switch (i) {
                case 0:
                    fragment = new Mascotas();
                    break;
                case 1:
                    fragment = new RegistroMascotas();
                    break;

                default:
                    fragment = null;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "MASCOTAS";
                case 1:
                    return "REGISTRO";

            }
            return null;
        }
    }
}