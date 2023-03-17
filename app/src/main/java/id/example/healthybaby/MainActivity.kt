package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.example.healthybaby.databinding.ActivityLoginBinding
import id.example.healthybaby.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var preferences  = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(applicationContext)

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.tvNama.text = preferences.getNama()
        binding.tvNik.text = preferences.getNIK()
        binding.tvTglLahir.text = preferences.getTanggalLahir()

        binding.btnUkur.setOnClickListener {
            startActivity(Intent(this,PengukuranActivity::class.java))
        }
    }
}