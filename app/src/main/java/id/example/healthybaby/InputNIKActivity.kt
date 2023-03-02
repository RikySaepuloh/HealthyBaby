package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.example.healthybaby.databinding.ActivityInputNikactivityBinding
import id.example.healthybaby.databinding.ActivityLoginBinding

class InputNIKActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputNikactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputNikactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegistrasi.setOnClickListener {
            startActivity(Intent(this,RegistrasiActivity::class.java))
        }

        binding.btnMasuk.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}