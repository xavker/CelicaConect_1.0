package com.example.celicaconect_10.Navegador.Menu.RegistroMascota;

import static android.app.Activity.RESULT_OK;

//import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Adatador.ModeloMascotas;
import com.example.celicaconect_10.Navegador.Menu.RegistroMascota.Provider.FirebaseMascotas;
import com.example.celicaconect_10.R;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

import id.zelory.compressor.Compressor;


public class RegistroMascotas extends Fragment {

    Button m_acepta,m_cancelar;
    Uri fullPhotoUri;
    Bitmap imgBitmap;
    Bitmap imagen=null;
    ImageView imgView;
    final byte[ ] thumb_byte=null;
    TextInputLayout m_nombre,m_propetario,m_direccion, m_raza,m_telefono;
    FirebaseMascotas firebaseMascotas;
    ProgressDialog cargando;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    ModeloMascotas modeloMascotas;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mascotas, container, false);
        fullPhotoUri=null;
        firebaseMascotas=new FirebaseMascotas();

        databaseReference=FirebaseDatabase.getInstance().getReference().child("fotossubidas");
        storageReference = FirebaseStorage.getInstance().getReference().child("imgcomprimidas");
        cargando=new ProgressDialog(getActivity());

        imgView = view.findViewById(R.id.fotopet);

        m_cancelar=view.findViewById(R.id.m_cancelar1);
        m_acepta=view.findViewById(R.id.m_aceptar);

        m_nombre=view.findViewById(R.id.nombre_denuncia);
        m_raza=view.findViewById(R.id.raza_denuncia);
        m_propetario=view.findViewById(R.id.propietario);
        m_telefono=view.findViewById(R.id.denuncia_telefono);
        m_direccion=view.findViewById(R.id.m_direccion);


        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrircamara();

            }
        });

        m_acepta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar_datos();
            }
        });

        return view;
    }

    private void validar_datos() {
        String nombre=m_nombre.getEditText().getText().toString();
        String raza=m_raza.getEditText().getText().toString();
        String propietario=m_propetario.getEditText().getText().toString();
        String telefono=m_telefono.getEditText().getText().toString();
        String direccion=m_direccion.getEditText().getText().toString();
        if(nombre.isEmpty()){
            m_nombre.setError("Nombre inválido");
            m_nombre.setFocusable(true);
        } else if (raza.isEmpty()) {
            m_raza.setError("Valor inválido");
            m_raza.setFocusable(true);
        }else if (propietario.isEmpty()) {
            m_propetario.setError("Valor inválido");
            m_propetario.setFocusable(true);
        }else if (telefono.isEmpty()) {
            m_propetario.setError("Valor inválido");
            m_propetario.setFocusable(true);
        }else{
            modeloMascotas=new ModeloMascotas(1,
                    "1",
                    nombre,
                    raza,
                    propietario,
                    telefono,
                    direccion,
                    "encasa");
            firebaseMascotas.Guardar_mascota(modeloMascotas);
            subir_imagen();
        }

    }

    private void subir_imagen() {

        cargando.setTitle("Subiendo foto...");
        cargando.setMessage("Espere por favor");
        cargando .show();
        StorageReference ref=storageReference.child("nuevo") ;
        UploadTask uploadTask=ref.putBytes(thumb_byte);

        //subir
        Task<Uri>uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
               if(!task.isSuccessful()){
                   throw Objects.requireNonNull(task.getException());
               }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Uri donwload=task.getResult();
                Toast.makeText(getActivity(),"SIBIDA...",Toast.LENGTH_SHORT).show();
            }
        });


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
            imgBitmap = (Bitmap) extras.get("data");
            //fullPhotoUri = data.getData();
            imgView.setImageBitmap(imgBitmap);
            File filesDir = getActivity().getFilesDir();
            File imageFile = new File(filesDir, "name" + ".jpg");

            OutputStream os;
            try {
                os = new FileOutputStream(imageFile);
                imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
            }
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            imgBitmap.compress(Bitmap.CompressFormat.JPEG,90,byteArrayOutputStream);
            final byte[ ] thumb_byte=byteArrayOutputStream.toByteArray();

        }
    }
    private static File convertBitmapToFile(Bitmap bitmap, String name) {
        File filesDir = null;
        File imageFile = new File(filesDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
        }
        return filesDir;
    }
    String  currentPhoto;

    private File createimageFile() throws IOException {
        String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFile="JPEG_"+timeStamp+"_";
        File storeDir=getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image=File.createTempFile(imageFile,
                ".jpg",
                storeDir);
        currentPhoto=image.getAbsolutePath();
        return image;

    }

        private void dispatchTakePictureIntent(){
            Intent takePictureIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if(takePictureIntent.resolveActivity(getContext().getPackageManager())!=null){
                File photofile=null;

            }


    }

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            m_nombre.setError("Nombre inválido");
            return false;
        } else {
            m_nombre.setError(null);
        }

        return true;
    }
}