package castor.no.c.programar.practicaretrofit

import android.app.Person
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.rvPersonas)
    }

    override fun onResume() {
        super.onResume()
        //Voy a retrofit
        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion:Call<List<Persona>> = retrofitService.getListaPersona()
        peticion.enqueue(object:Callback<List<Persona>>{
            override fun onResponse(call: Call<List<Persona>>, response: Response<List<Persona>>) {
                val lista = response.body()
                llenarRecycler(lista!!)
            }

            override fun onFailure(call: Call<List<Persona>>, t: Throwable) {
                Log.e("Retrofit", "Ocurrio un error ${t.message}")
            }

        })
    }

    fun llenarRecycler(datos:List<Persona>){
        val adapter = AdaptadorPersona(this, datos)
        recycler.adapter = adapter
    }

    fun cliclBoton(v: View){
        val intent = Intent(this, AgregarPersona::class.java)
        startActivity(intent)
    }
}