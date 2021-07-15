package com.example.dialogo;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mostrar,cap_correo,personalizado,mostrar_toast,mostrar_toastp;
    private boolean visto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrar =(Button)findViewById(R.id.ver_mensaje);
        cap_correo =(Button)findViewById(R.id.captura_correo);
        personalizado =(Button)findViewById(R.id.ver_mensaje_personalizado);
        mostrar_toast =(Button)findViewById(R.id.ver_toast);
        mostrar_toastp =(Button)findViewById(R.id.ver_toastp);
        visto = false;
        cap_correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapturarCorreo();
            }
        });
        personalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoPersonalizado();
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!visto)
                {
                    mostrarmensaje();
                }
            }
        });
        mostrar_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verToast();
            }
        });
        mostrar_toastp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarToastpersonalizado();
            }
        });
    }

    private void verToast() {
        Toast.makeText(getApplicationContext(),"Este es un SIMPLE mensaje",Toast.LENGTH_LONG).show();
    }

    private void mostrarDialogoPersonalizado()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        final View customlayout = getLayoutInflater().inflate(R.layout.dialog_layout,null);
        alert.setView(customlayout);
        alert.setCancelable(false);
        EditText nombre= customlayout.findViewById(R.id.name);
        EditText mail = customlayout.findViewById(R.id.email);
        alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nombre.getText().toString();
                String email = mail.getText().toString();
                // Toast.makeText(getApplicationContext(),"Segurado"+name+email,Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    private void mostrarToastpersonalizado()
    {
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,50);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toast_layout,null);
        toast.setView(view);
        toast.show();
    }
    private void CapturarCorreo() {
        // AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        final EditText email = new EditText(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(R.string.dialog_title2);
        alert.setCancelable(false);
        alert.setView(email);
        alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String correo = email.getText().toString();
                Toast.makeText(getApplicationContext(),"El correo que se guardo es:"+correo, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    private void mostrarmensaje()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Mensaje de bienvenida");
        alert.setMessage("Bienvenido a la aplicación de dialogos");
        alert.setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Bienvenido gracias por aceptar",Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Lamentamos que no aceptaras :( bye...",Toast.LENGTH_SHORT).show();
                visto = true;
            }
        });
        String[] opciones = {"Seguir viendo", "Solo una vez mas", "No ver más"};
        alert.setSingleChoiceItems(opciones, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Seleccionaste la opcion:" + which, Toast.LENGTH_SHORT).show();
                switch (which) {
                    case 0:
                        visto = false;
                        break;
                    case 1:
                        break;
                    case 2:
                        visto = true;
                        break;
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}