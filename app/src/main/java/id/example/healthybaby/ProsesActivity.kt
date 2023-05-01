package id.example.healthybaby

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.jaredrummler.materialspinner.MaterialSpinner
import id.example.healthybaby.databinding.ActivityProsesBinding


class ProsesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProsesBinding
    var frekuensi = ""
    var jenis1 = ""
    var jenis2 = ""
    var jenis3 = ""
    var jenis4 = ""
    val preferences = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProsesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(this)

//        if (getAge(preferences.getTanggalLahir().toString()).toInt() < 9 ){
//
//        }

        binding.btnSelanjutnya.setOnClickListener {
            if (frekuensi==""){
                Snackbar.make(binding.root,"Anda belum memilih frekuensi",Snackbar.LENGTH_LONG).show()
            }else if(jenis1 == "" || jenis2 == "" || jenis3 == ""){
                Snackbar.make(binding.root,"Anda belum jenis MP-ASI",Snackbar.LENGTH_LONG).show()
            }else{
                preferences.saveFrekuensi(frekuensi)
                preferences.saveJenis1(jenis1)
                preferences.saveJenis2(jenis2)
                preferences.saveJenis3(jenis3)
                preferences.saveJenis4(jenis4)
                if (frekuensi == "3-4x"){
                    if (jenis1 == jenis2 && jenis2 == jenis3 && jenis3 == jenis4){
                        preferences.saveHasilJenis("Kurang")
                    }else if (jenis1 == jenis2 || jenis2 == jenis3 || jenis3 == jenis4 || jenis2==jenis4 || jenis1== jenis4 || jenis1==jenis3){
                        preferences.saveHasilJenis("Cukup")
                    }else{
                        preferences.saveHasilJenis("Baik")
                    }
                }else{
                    if (jenis1 == jenis2 && jenis2 == jenis3){
                        preferences.saveHasilJenis("Kurang")
                    }else if (jenis1 == jenis2 || jenis2 == jenis3 || jenis1==jenis3){
                        preferences.saveHasilJenis("Cukup")
                    }else{
                        preferences.saveHasilJenis("Baik")
                    }
                }
                startActivity(Intent(this,ResultActivity::class.java))
            }
        }

        val spinner = binding.spinner
        spinner.setItems("2-3x","3-4x")
        spinner.setOnItemSelectedListener { view, position, id, item ->
            frekuensi = item.toString()
            binding.linearLayout3.visibility = View.VISIBLE
            if (frekuensi=="3-4x"){
                binding.mcJenis4.visibility=View.VISIBLE
            }else{
                binding.mcJenis4.visibility=View.GONE
            }
        }


        val spinner1 = binding.etJenis1
        spinner1.setItems("Bubur Halus, Pure Food/Buah Halus, Finger Food", "Bubur Kasar", "Nasi Tim", "Buah Potong", "Snack (Kue/Biskuit)","Sayur","Protein", "Nasi", "Roti","Mie")
        spinner1.setOnItemSelectedListener { view, position, id, item ->
            jenis1 = item.toString()
        }

        val spinner2 = binding.etJenis2
        spinner2.setItems("Bubur Halus, Pure Food/Buah Halus, Finger Food", "Bubur Kasar", "Nasi Tim", "Buah Potong", "Snack (Kue/Biskuit)","Sayur","Protein", "Nasi", "Roti","Mie")
        spinner2.setOnItemSelectedListener { view, position, id, item ->
            jenis2 = item.toString()
        }

        val spinner3 = binding.etJenis3
        spinner3.setItems("Bubur Halus, Pure Food/Buah Halus, Finger Food", "Bubur Kasar", "Nasi Tim", "Buah Potong", "Snack (Kue/Biskuit)","Sayur","Protein", "Nasi", "Roti","Mie")
        spinner3.setOnItemSelectedListener { view, position, id, item ->
            jenis3 = item.toString()
        }

        val spinner4 = binding.etJenis4
        spinner4.setItems("Bubur Halus, Pure Food/Buah Halus, Finger Food", "Bubur Kasar", "Nasi Tim", "Buah Potong", "Snack (Kue/Biskuit)","Sayur","Protein", "Nasi", "Roti","Mie")
        spinner4.setOnItemSelectedListener { view, position, id, item ->
            jenis4 = item.toString()
        }
    }
}