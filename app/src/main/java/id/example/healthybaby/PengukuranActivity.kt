package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.example.healthybaby.databinding.ActivityPengukuranBinding
import id.example.healthybaby.databinding.ActivityRegistrasiBinding

class PengukuranActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengukuranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengukuranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            startActivity(Intent(this,ProsesActivity::class.java))
        }

        binding.btnUlangi.setOnClickListener {
            binding.etTb.setText("")
            binding.etBb.setText("")
        }
    }
}