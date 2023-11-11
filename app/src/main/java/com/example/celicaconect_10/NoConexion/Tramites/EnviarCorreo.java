package com.example.celicaconect_10.NoConexion.Tramites;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EnviarCorreo extends AsyncTask<Void,Void,Void> {
    private Context contexto;
    private Session De_Sesion;
    Configuracion configuracion=new Configuracion();
    public  Sugerencias sugerencias=new Sugerencias();
    private String A_Correo;
    private String A_Asunto;
    private String A_Mensaje;
    private LottieAnimationView lottieAnimationView;

    private ProgressDialog progreso;
    private LinearLayout linearLayout;

    public EnviarCorreo(Context cContexto, String cCorreo, String cAsunto, String cMensaje, LottieAnimationView animationView, LinearLayout linearLayout) {
        this.contexto = cContexto;
        this.A_Correo = cCorreo;
        this.A_Asunto = cAsunto;
        this.A_Mensaje = cMensaje;
        this.lottieAnimationView=animationView;
        this.linearLayout=linearLayout;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        linearLayout.setVisibility(View.INVISIBLE);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();

        //progreso = ProgressDialog.show(contexto, "Enviando mensaje", "Espere...", false, false);
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        linearLayout.setVisibility(View.VISIBLE);
        lottieAnimationView.setVisibility(View.INVISIBLE);
        lottieAnimationView.cancelAnimation();
        //progreso.dismiss();
        //sugerencias.detenerprogreso();
        Toast.makeText(contexto, "Mensaje enviado", Toast.LENGTH_LONG).show();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        De_Sesion = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configuracion.DE_CORREO, configuracion.DE_PASSWORD);
            }
        });

        try {
            MimeMessage mm = new MimeMessage(De_Sesion);
            mm.setFrom(new InternetAddress(configuracion.DE_CORREO));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(A_Correo));
            mm.setSubject(A_Asunto);
            mm.setText(A_Mensaje);
            Transport.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
