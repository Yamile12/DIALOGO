package com.example.dialogos

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var mostrar: Button? = null
    var cap_correo: Button? = null
    var personalizado: Button? = null
    var mostrar_toast: Button? = null
    var mostrar_toastp: Button? = null
    private var visto = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mostrar = findViewById<View>(R.id.ver_mensaje) as Button
        cap_correo = findViewById<View>(R.id.captura_correo) as Button
        personalizado = findViewById<View>(R.id.ver_mensaje_personalizado) as Button
        mostrar_toast = findViewById<View>(R.id.ver_toast) as Button
        mostrar_toastp = findViewById<View>(R.id.ver_toastp) as Button
        visto = false
        cap_correo!!.setOnClickListener { CapturarCorreo() }
        personalizado!!.setOnClickListener { mostrarDialogoPersonalizado() }
        mostrar!!.setOnClickListener {
            if (!visto) {
                mostrarmensaje()
            }
        }
        mostrar_toast!!.setOnClickListener { verToast() }
        mostrar_toastp!!.setOnClickListener { mostrarToastpersonalizado() }
    }

    private fun verToast() {
        Toast.makeText(applicationContext, "Este es un SIMPLE mensaje", Toast.LENGTH_LONG).show()
    }

    private fun mostrarDialogoPersonalizado() {
        val alert = AlertDialog.Builder(this@MainActivity)
        val customlayout: View = layoutInflater.inflate(R.layout.dialog_layout, null)
        alert.setView(customlayout)
        alert.setCancelable(false)
        val nombre = customlayout.findViewById<EditText>(R.id.name)
        val mail = customlayout.findViewById<EditText>(R.id.email)
        alert.setPositiveButton("Guardar") { dialog, which ->
            val name = nombre.text.toString()
            val email = mail.text.toString()
            // Toast.makeText(getApplicationContext(),"Segurado"+name+email,Toast.LENGTH_SHORT).show();
        }
        val dialog = alert.create()
        dialog.show()
    }

    private fun mostrarToastpersonalizado() {
        val toast = Toast(this)
        toast.duration = Toast.LENGTH_LONG
        toast.setGravity(Gravity.CENTER, 0, 50)
        val inflater = layoutInflater
        val view: View = inflater.inflate(R.layout.toast_layout, null)
        toast.setView(view)
        toast.show()
    }

    private fun CapturarCorreo() {
        // AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        val email = EditText(this)
        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle(R.string.dialog_title2)
        alert.setCancelable(false)
        alert.setView(email)
        alert.setPositiveButton("Guardar") { dialog, which ->
            val correo = email.text.toString()
            Toast.makeText(applicationContext, "El correo que se guardo es:$correo", Toast.LENGTH_SHORT).show()
        }
        val dialog = alert.create()
        dialog.show()
    }

    private fun mostrarmensaje() {
        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Mensaje de bienvenida")
        alert.setMessage("Bienvenido a la aplicación de dialogos")
        alert.setPositiveButton(R.string.dialog_positive, DialogInterface.OnClickListener { dialog, which -> Toast.makeText(applicationContext, "Bienvenido gracias por aceptar", Toast.LENGTH_SHORT).show() })
        alert.setNegativeButton(R.string.dialog_negative, DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(applicationContext, "Lamentamos que no aceptaras :( bye...", Toast.LENGTH_SHORT).show()
            visto = true
        })
        val opciones = arrayOf("Seguir viendo", "Solo una vez mas", "No ver más")
        alert.setSingleChoiceItems(opciones, 1) { dialog, which ->
            Toast.makeText(applicationContext, "Seleccionaste la opcion:$which", Toast.LENGTH_SHORT).show()
            when (which) {
                0 -> visto = false
                1 -> {
                }
                2 -> visto = true
            }
        }
        val dialog = alert.create()
        dialog.show()
    }
}