package com.example.celicaconect_10.NoConexion.Tramites;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.celicaconect_10.R;
import com.google.android.material.textfield.TextInputLayout;


public class Enviardenuncia extends Fragment {

   Button s_acepta,s_cancelar;
    Uri fullPhotoUri;
    ImageView imgView;
    TextInputLayout s_nombre,s_comentario;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fullPhotoUri=null;
        Fragment nuevofragmento=new MenuTramites();
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        View v=inflater.inflate(R.layout.fragment_enviardenuncia, container, false);
        imgView = v.findViewById(R.id.foto1);
        s_acepta=v.findViewById(R.id.s_aceptar);
        s_cancelar=v.findViewById(R.id.s_cancelar1);
        s_comentario=v.findViewById(R.id.comentario_denuncia);
        s_nombre=v.findViewById(R.id.nombre_denuncia);

        s_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.replace(R.id.nav_host_fragment_content_main,nuevofragmento);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        s_acepta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String asunto="Denuncias de mascota";
                String correo= "xavker@gmail.com";
                if(s_comentario.getEditText().getText().toString().isEmpty()
                        || s_nombre.getEditText().getText().toString().isEmpty() ) {
                    Toast.makeText(getContext(), "Ingrese un nombre o un comentario.", Toast.LENGTH_SHORT).show();
                    s_nombre.setError("Nombre vavio");
                    s_nombre.setError("Sin comentario");
                } else {
                        composeEmail(correo,asunto,fullPhotoUri );
                    }
                }
        });
        abrircamara();
        EditText etPlannedDate = (EditText) v.findViewById(R.id.etPlannedDate);


        return v;
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }

    private void abrircamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getContext().getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            fullPhotoUri = data.getData();
            imgView.setImageBitmap(imgBitmap);
        }
    }
    public void composeEmail(String addresses, String subject, Uri attachment) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}