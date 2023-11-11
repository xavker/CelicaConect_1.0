package com.example.celicaconect_10.NoConexion.Tramites;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.celicaconect_10.R;


public class Sugerencias extends Fragment {

private EditText correo,mensaje;
private Button enviar;
public LottieAnimationView lottieAnimationView;
private LinearLayout grupocorreo;
public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_sugerencias, container, false);
        correo=view.findViewById(R.id.correo);
        mensaje=view.findViewById(R.id.mensaje);
        enviar=view.findViewById(R.id.enviar);
        lottieAnimationView=view.findViewById(R.id.intro_lottie_animation_view);
        grupocorreo=view.findViewById(R.id.grupocorreo);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!correo.getText().toString().equals("")) {
                    if(!mensaje.getText().toString().equals("")) {
                        enviar_email();

                    }else{
                        Toast.makeText(getContext(),"Ingrese un valor en el campo mensaje",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(),"Ingrese un valor en el campo correo",Toast.LENGTH_SHORT).show();
                }
            }
        });
         return view;
    }

    private void enviar_email() {
        EnviarCorreo objCorreo = new EnviarCorreo(getActivity(), correo.getText().toString(), "Tienes una Sugerencia en Celica Conect", mensaje.getText().toString(),lottieAnimationView,grupocorreo);
        objCorreo.execute();
        correo.setText("");
        mensaje.setText("");

    }


}
