package com.example.celicaconect_10.Navegador.Menu.Atencion;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.celicaconect_10.R;
//import com.google.api.Distribution;

import java.util.Calendar;


public class Atencion extends Fragment {
    EditText etPlannedDate,h_nombre,h_cedula,h_motivo;
    private Spinner spinner;
    private  String h_hora;
    private LinearLayout horario,horario2,formulario;
    private Button buscar_h, h_aceptar,siguiente_h, b_200,b_220,b_240,b_300,b_320,b_340,b_400,b_420,b_440;;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_atencion, container, false);
        spinner=v.findViewById(R.id.spinner_oficina);
        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.oficinas, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);
        buscar_h=v.findViewById(R.id.buscar_horario);
        h_hora=null;
        //lineal layout
        horario=v.findViewById(R.id.horario);
        formulario=v.findViewById(R.id.h_formulario);
        horario2=v.findViewById(R.id.h_confirrmar);
        //botones
        b_200=v.findViewById(R.id.pm200);
        b_220=v.findViewById(R.id.pm220);
        b_240=v.findViewById(R.id.pm240);
        b_300=v.findViewById(R.id.pm300);
        b_320=v.findViewById(R.id.pm320);
        b_340=v.findViewById(R.id.pm340);
        b_400=v.findViewById(R.id.pm400);
        b_420=v.findViewById(R.id.pm420);
        b_440=v.findViewById(R.id.pm440);

        h_aceptar=v.findViewById(R.id.h_aceptar);

        //edittext
        etPlannedDate =  v.findViewById(R.id.etPlannedDate);
        h_nombre=v.findViewById(R.id.h_nombre);
        h_cedula=v.findViewById(R.id.h_cedula);
        h_motivo=v.findViewById(R.id.h_motivo);

        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        buscar_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar_horario();
            }


        });

        b_200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b=b_200.getText().toString();
                Toast.makeText(getContext(),"Usted selecciono "+b,Toast.LENGTH_SHORT).show();
                establecer_horario(b);
            }
        });
        b_220.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b=b_220.getText().toString();
                Toast.makeText(getContext(),"Usted selecciono "+b,Toast.LENGTH_SHORT).show();

                establecer_horario(b);
            }
        });
        b_240.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b=b_240.getText().toString();
                Toast.makeText(getContext(),"Usted selecciono "+b,Toast.LENGTH_SHORT).show();
                establecer_horario(b);
            }
        });

        b_300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b=b_300.getText().toString();
                Toast.makeText(getContext(),"Usted selecciono "+b,Toast.LENGTH_SHORT).show();
                establecer_horario(b);
            }
        });
        b_320.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b=b_320.getText().toString();
                Toast.makeText(getContext(),"Usted selecciono "+b,Toast.LENGTH_SHORT).show();
                establecer_horario(b);
            }
        });
        b_340.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b=b_340.getText().toString();
                Toast.makeText(getContext(),"Usted selecciono "+b,Toast.LENGTH_SHORT).show();
                establecer_horario(b);
            }
        });
        b_400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b=b_400.getText().toString();
                Toast.makeText(getContext(),"Usted selecciono "+b,Toast.LENGTH_SHORT).show();
                establecer_horario(b);
            }
        });
        b_420.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b=b_420.getText().toString();
                Toast.makeText(getContext(),"Usted selecciono "+b,Toast.LENGTH_SHORT).show();
                establecer_horario(b);
            }
        });
        b_440.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b=b_440.getText().toString();
                Toast.makeText(getContext(),"Usted selecciono "+b,Toast.LENGTH_SHORT).show();
                establecer_horario(b);
            }
        });

        h_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitar_cita();
            }


        });
        return v;
    }
    private void solicitar_cita() {
        if(h_nombre.getText().toString().isEmpty()){
            h_nombre.setFocusable(true);
            h_nombre.setError("Ingrese un nombre.");
        }else if(h_cedula.getText().toString().isEmpty()){
            h_cedula.setFocusable(true);
            h_cedula.setError("Ingrese un numero de cedula valido.");
        }else if(h_motivo.getText().toString().isEmpty()){
            h_motivo.setFocusable(true);
            h_motivo.setError("Ingrese un motivo de la cita.");
        }else{
            String h_nombre1=h_nombre.getText().toString();
            String h_cedula1=h_nombre.getText().toString();
            String h_motivo1=h_nombre.getText().toString();
            String h_fecha1=etPlannedDate.getText().toString();
            String h_funcionario1=spinner.getSelectedItem().toString();
            enviarsolicitud(h_nombre1,h_cedula1,h_motivo1,h_fecha1,h_funcionario1,h_hora);

        }
    }

    private void enviarsolicitud(String h_nombre1, String h_cedula1, String h_motivo1, String h_fecha1, String h_funcionario1, String h_hora) {
        Toast.makeText(getContext(),"Estimado " + h_nombre1+" su cita a sido agendada el dia "+h_fecha1+
                                         " a las " + h_hora + " con el funcionario "+h_funcionario1+", por favor llegar 15 minutos antes. Gracias",Toast.LENGTH_SHORT).show();
        String datos="Estimado " + h_nombre1+" su cita a sido agendada el dia "+h_fecha1+
                " a las " + h_hora + " con el funcionario "+h_funcionario1+", por favor llegar 15 minutos antes. Gracias";
        Bundle bundle=new Bundle();
        bundle.putString("datos",datos);
        getParentFragmentManager().setFragmentResult("key",bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,new Pantalla_confirmacion()).commit();
    }

    private void establecer_horario(String b) {
        formulario.setVisibility(View.VISIBLE);
        horario2.setVisibility(View.VISIBLE);
        h_hora=b;
        if(b.equals("2:00pm")){
            b_200.setBackgroundColor(getResources().getColor(R.color.disponible));
            b_220.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_240.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_300.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_320.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_340.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_400.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_420.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_440.setBackgroundColor(getResources().getColor(R.color.nodisponible));
        }else if(b.equals("2:20pm")){
            b_200.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_220.setBackgroundColor(getResources().getColor(R.color.disponible));
            b_240.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_300.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_320.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_340.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_400.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_420.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_440.setBackgroundColor(getResources().getColor(R.color.nodisponible));
        }else if(b.equals("2:40pm")){
            b_200.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_220.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_240.setBackgroundColor(getResources().getColor(R.color.disponible));
            b_300.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_320.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_340.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_400.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_420.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_440.setBackgroundColor(getResources().getColor(R.color.nodisponible));
        }else if(b.equals("3:00pm")){
            b_200.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_220.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_240.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_300.setBackgroundColor(getResources().getColor(R.color.disponible));
            b_320.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_340.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_400.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_420.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_440.setBackgroundColor(getResources().getColor(R.color.nodisponible));
        }else if(b.equals("3:20pm")){
            b_200.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_220.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_240.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_300.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_320.setBackgroundColor(getResources().getColor(R.color.disponible));
            b_340.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_400.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_420.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_440.setBackgroundColor(getResources().getColor(R.color.nodisponible));
        }else if(b.equals("3:40pm")){
            b_200.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_220.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_240.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_300.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_320.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_340.setBackgroundColor(getResources().getColor(R.color.disponible));
            b_400.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_420.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_440.setBackgroundColor(getResources().getColor(R.color.nodisponible));
        }
        else if(b.equals("4:00pm")){
            b_200.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_220.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_240.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_300.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_320.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_340.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_400.setBackgroundColor(getResources().getColor(R.color.disponible));
            b_420.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_440.setBackgroundColor(getResources().getColor(R.color.nodisponible));
        }else if(b.equals("4:20pm")){
            b_200.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_220.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_240.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_300.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_320.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_340.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_400.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_420.setBackgroundColor(getResources().getColor(R.color.disponible));
            b_440.setBackgroundColor(getResources().getColor(R.color.nodisponible));
        }
        else if(b.equals("4:40pm")){
            b_200.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_220.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_240.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_300.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_320.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_340.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_400.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_420.setBackgroundColor(getResources().getColor(R.color.nodisponible));
            b_440.setBackgroundColor(getResources().getColor(R.color.disponible));
        }
    }

    private void buscar_horario() {
        if(!etPlannedDate.getText().toString().isEmpty()){
            horario.setVisibility(View.VISIBLE);
            buscar_h.setVisibility(View.INVISIBLE);
        }else{
            horario.setVisibility(View.INVISIBLE);
            buscar_h.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(),"Ingrese una fecha para buscar",Toast.LENGTH_SHORT).show();
        }
    }

    private void showDatePickerDialog() {

        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our text view.
                        etPlannedDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();


    }


}