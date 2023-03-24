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
                if (jenis1 == jenis2 && jenis2 == jenis3){
                    preferences.saveHasilJenis("Kurang")
                }else if (jenis1 == jenis2 || jenis2 == jenis3 || jenis1==jenis3){
                    preferences.saveHasilJenis("Cukup")
                }else{
                    preferences.saveHasilJenis("Baik")
                }
                startActivity(Intent(this,ResultActivity::class.java))
            }
        }

        val spinner = binding.spinner
        spinner.setItems("2-3x","3-4x")
        spinner.setOnItemSelectedListener { view, position, id, item ->
            frekuensi = item.toString()
            binding.linearLayout3.visibility = View.VISIBLE
        }


        val spinner1 = binding.etJenis1
        spinner1.setItems("Bubur Kental, saring kasar, finger food", "Nasi tim, cincang halus/kasar", "Makanan keluarga")
        spinner1.setOnItemSelectedListener { view, position, id, item ->
            jenis1 = item.toString()
        }

        val spinner2 = binding.etJenis2
        spinner2.setItems("Bubur Kental, saring kasar, finger food", "Nasi tim, cincang halus/kasar", "Makanan keluarga")
        spinner2.setOnItemSelectedListener { view, position, id, item ->
            jenis2 = item.toString()
        }

        val spinner3 = binding.etJenis3
        spinner3.setItems("Bubur Kental, saring kasar, finger food", "Nasi tim, cincang halus/kasar", "Makanan keluarga")
        spinner3.setOnItemSelectedListener { view, position, id, item ->
            jenis3 = item.toString()
        }
    }
}