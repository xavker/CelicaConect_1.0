package com.example.celicaconect_10.Navegador.Menu.ConsultaAgua;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celicaconect_10.R;


public class CobroAgua extends Fragment {

    TextView nombre, cedula, cuemta, tipo,valor_cobro;
    Button copy_nombre_pichincha,copy_cedula_pichincha,copy_cuenta_pichincha,copy_tipo_pichincha, subir, cancelar;
    String nombre2,valor2;
    LinearLayout primera_parte;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        View v=inflater.inflate(R.layout.fragment_cobro_agua, container, false);
        valor_cobro=v.findViewById(R.id.valor_cobro);

        fragmentManager.setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                valor2=bundle.getString("valor");
                nombre2=bundle.getString("nombre");
                valor_cobro.setText(valor2);

            }
        });



        nombre=v.findViewById(R.id.pichincha_nombre);
        cedula=v.findViewById(R.id.pichincha_cedula);
        cuemta=v.findViewById(R.id.pichincha_cuenta);
        tipo=v.findViewById(R.id.pichincha_tipo);

        copy_nombre_pichincha=v.findViewById(R.id.pichincha_copy_nombre);
        copy_cedula_pichincha=v.findViewById(R.id.pichincha_copy_cedula);
        copy_cuenta_pichincha=v.findViewById(R.id.pichincha_copy_cuenta);
        copy_tipo_pichincha=v.findViewById(R.id.pichincha_copy_tipo);
        copy_tipo_pichincha=v.findViewById(R.id.pichincha_copy_tipo);
        subir=v.findViewById(R.id.pichincha_subir);
        cancelar=v.findViewById(R.id.pichincha_cancelarr);

        primera_parte=v.findViewById(R.id.primera_parte);

        copy_nombre_pichincha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux=nombre.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text",  aux);
                Toast.makeText(getContext(),"Copiado...",Toast.LENGTH_SHORT).show();
                clipboard.setPrimaryClip(clip);

            }
        });
        copy_cedula_pichincha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux=cedula.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text",  aux);
                Toast.makeText(getContext(),"Copiado...",Toast.LENGTH_SHORT).show();
                clipboard.setPrimaryClip(clip);

            }
        });
        copy_tipo_pichincha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux=tipo.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text",  aux);
                Toast.makeText(getContext(),"Copiado...",Toast.LENGTH_SHORT).show();
                clipboard.setPrimaryClip(clip);

            }
        });
        copy_cuenta_pichincha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux=cuemta.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text",  aux);
                Toast.makeText(getContext(),"Copiado...",Toast.LENGTH_SHORT).show();
                clipboard.setPrimaryClip(clip);

            }
        });

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Envie el comprobante a este Whatsapp",Toast.LENGTH_SHORT).show();
                String message="!!!Nuevo Pago!!!\n"+
                        "A Nombre de: "+nombre2+"\n"+
                        "Por un valor de: "+valor2+"\n";
                Intent intentWhatsAppGroup = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://chat.whatsapp.com/send?phone=0979724195");
                intentWhatsAppGroup.setType("text/plain");
                //intentWhatsAppGroup.setData(uri);
                //intentWhatsAppGroup.setType("text/plain");
                intentWhatsAppGroup.setPackage("com.whatsapp");
                intentWhatsAppGroup.setAction(Intent.ACTION_SEND);
                intentWhatsAppGroup.putExtra(Intent.EXTRA_TEXT,message);
                getActivity().startActivity(Intent.createChooser(intentWhatsAppGroup,"Ingresando a whataspp"));

            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,new Pagoagua()).commit();
            }
        });
        return v;
    }
}