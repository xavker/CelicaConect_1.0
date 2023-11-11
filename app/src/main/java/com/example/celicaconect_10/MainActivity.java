package com.example.celicaconect_10;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celicaconect_10.Navegador.Menu.Acercade.Acercade;
import com.example.celicaconect_10.Navegador.Menu.Atencion.Atencion;
import com.example.celicaconect_10.Navegador.Menu.Autenticacion.Login;
import com.example.celicaconect_10.Navegador.Menu.CampeonatoFutbol.CampeonatoFultball;
import com.example.celicaconect_10.Navegador.Menu.ConsultaAgua.Pagoagua;
import com.example.celicaconect_10.Navegador.Menu.ConsultarLuz.Luz;
import com.example.celicaconect_10.Navegador.Menu.ConsultarTelefono.Telefono;
import com.example.celicaconect_10.Navegador.Menu.Conversor.Conversor;
import com.example.celicaconect_10.Navegador.Menu.Noticias.Noticias;
import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.MascotasMain;
import com.example.celicaconect_10.NoConexion.Tramites.MenuTramites;
import com.example.celicaconect_10.Navegador.Menu.Home.HomeFragment;
import com.example.celicaconect_10.Navegador.Menu.MultasTransito.Transito;
import com.example.celicaconect_10.Navegador.Menu.TipodeLicencia.Licencia;
import com.example.celicaconect_10.Navegador.Menu.Turismo.Turismo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.celicaconect_10.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    //public Probar_conexion probar_conexion;
    public ImageView screenshot;
    private String sharePath = "no";
    private FloatingActionButton fab;
    View navegacion;
    FragmentManager fragmentManager = getSupportFragmentManager();
    TextView iduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //probar_conexion=null;



        String user1=getIntent().getStringExtra("key");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Gracias por usar Celica Conected", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //takescreenshot();//Capturar pantalla;
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View v=navigationView.getHeaderView(0);
        iduser=v.findViewById(R.id.usuarioid);
        iduser.setText(user1);
        //screenshot=navegacion.findViewById(R.id.captura);
        //screenshot.setVisibility(View.INVISIBLE);

        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, new HomeFragment()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuprincipal
                , menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if( id==R.id.acercade) {
            Acercade();
        }else{
               return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.noticias) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Noticias()).commit();
        } else if (id == R.id.sugerencia) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, new MenuTramites()).commit();
        } else if (id == R.id.planilla_luz) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Luz()).commit();
        } else if (id == R.id.planilla_telefono) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Telefono()).commit();
        } else if (id == R.id.agua) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Pagoagua()).commit();
        }
        if (id == R.id.multa_transito) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Transito()).commit();
       }
        else if (id == R.id.mascotas) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new MascotasMain()).commit();
        }
        else if (id == R.id.licencia) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Licencia()).commit();
        } else if (id == R.id.ser_bachiller) {
            //fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new SerBachiller()).commit();
        } else if (id == R.id.conversor) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Conversor()).commit();
        } else if (id == R.id.campeonato) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new CampeonatoFultball()).commit();
        } else if (id == R.id.turismo) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Turismo()).commit();
        } else if (id == R.id.turno) {
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Atencion()).commit();
        }else if (id == R.id.salir) {
            Intent i=new Intent(MainActivity.this, Login.class);
            startActivity(i);
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void Acercade() {
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Acercade()).commit();

    }

}

