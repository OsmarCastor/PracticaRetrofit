package castor.no.c.programar.practicaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgregarPersona : AppCompatActivity() {

    lateinit var txtNombre:EditText
    lateinit var txtApellido:EditText
    lateinit var txtId:EditText
    lateinit var txtEdad:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_persona)

        txtNombre = findViewById(R.id.txtNomP)
        txtApellido = findViewById(R.id.txtApellido)
        txtId = findViewById(R.id.txtId)
        txtEdad = findViewById(R.id.txtEdad)

    }
    fun abreModificar(v: View){
        val nombre = txtNombre.text.toString()
        val apellido = txtApellido.text.toString()
        val id = txtId.text.toString()
        val edad = txtEdad.text.toString()
        val persona = Persona(nombre, apellido, id, edad)
        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion: Call<Resultado> = retrofitService.agregar(persona)
        peticion.enqueue(object: Callback<Resultado> {
            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                response.body()
            }
            override fun onFailure(call: Call<Resultado>, t: Throwable) {
                Log.e("Retrofit", "Error ${t.message}")
            }

        })
        finish()
    }
}


