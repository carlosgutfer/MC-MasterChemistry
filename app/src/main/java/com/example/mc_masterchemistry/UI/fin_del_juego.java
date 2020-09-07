package com.example.mc_masterchemistry.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.db.Entities.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

public class fin_del_juego extends AppCompatActivity implements View.OnClickListener {
    private EditText nick;
    private TextView gemas;
    private Button guardar, salir, volverjugar;
    private FirebaseFirestore db;
    private String nicks;
    private int gemasGanadas=0;

    public fin_del_juego() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_del_juego);
        db = FirebaseFirestore.getInstance();
        findViewById();
        Intent();
        onClick();



    }

    private void onClick() {
        guardar.setOnClickListener(this);
        salir.setOnClickListener(this);
        volverjugar.setOnClickListener(this);
    }

    private void Intent() {
        gemasGanadas=getIntent().getIntExtra("GemasGanadas",-1);
        gemas.setText("¡Felicidades! Has conseguido: "+gemasGanadas);
    }


    private void findViewById() {
        nick=findViewById(R.id.ET_Nick);
        gemas=findViewById(R.id.TV_RegsitroGemas);
        guardar=findViewById(R.id.BT_guardar);
        salir=findViewById(R.id.BT_Salir);
        volverjugar=findViewById(R.id.BT_VolverAJugar);


    }


    @Override
    public void onClick(View v) {
        int view = v.getId();
        nicks=nick.getText().toString();
        if(view==R.id.BT_guardar){
            if(nicks.length()>3) {
                db.collection("users").whereEqualTo("nick", nicks)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (queryDocumentSnapshots.size() > 0) {
                                    nick.setError("El nick no esta disponible");
                                } else {
                                    Users nuevoUsuario = new Users(nicks, gemasGanadas);
                                    db.collection("users")
                                            .add(nuevoUsuario)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Toast.makeText(fin_del_juego.this, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(fin_del_juego.this, EleccionBinarioTerciarios.class);
                                                    startActivity(i);
                                                    finish();

                                                }
                                            });
                                }

                            }
                        });
            }else{
                nick.setError("El nick tiene que tener al menos 4 caracteres");
            }
        }
        if(view==R.id.BT_Salir){
        Intent i = new Intent( fin_del_juego.this, MainActivity.class);
        startActivity(i);
        finish();
        }
        if(view==R.id.BT_VolverAJugar){
            Intent i = new Intent(fin_del_juego.this, EleccionBinarioTerciarios.class);
            startActivity(i);
            finish();

        }
    }
}