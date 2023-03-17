package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.example.healthybaby.databinding.ActivityProsesBinding
import id.example.healthybaby.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    var preferences  = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)

        binding.btnSimpan.setOnClickListener {
            startActivity(Intent(this,SaveActivity::class.java))
            finishAffinity()
        }
    }


    fun checkGizi(){
        val day = preferences.getTanggalLahir()?.substringBefore("/")?.toInt()
        val month = preferences.getTanggalLahir()?.substringAfter("/")?.substringBefore("/")?.toInt()
        val year = preferences.getTanggalLahir()?.substringAfterLast("/")?.toInt()
        val usia = day?.let { month?.let { it1 -> year?.let { it2 -> getAge(it, it1, it2) } } }

        preferences.getTB()
        preferences.getBB()
    }
}