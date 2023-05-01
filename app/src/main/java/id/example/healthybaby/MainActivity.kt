package id.example.healthybaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
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

        if (preferences.getJenisKelamin() == "Laki-Laki"){
            Glide.with(this).load(R.drawable.babyboy).into(binding.civProfilFoto)
        }else{
            Glide.with(this).load(R.drawable.babygirl).into(binding.civProfilFoto)
        }

        binding.btnLogout.setOnClickListener {
            preferences.saveInputStatus(false)
            startActivity(Intent(this,InputNIKActivity::class.java))
            finishAffinity()
        }

//        binding.btnBack.setOnClickListener{
//            finish()
//        }

        binding.tvNama.text = preferences.getNama()
        binding.tvNik.text = preferences.getNIK()
        binding.tvTglLahir.text = preferences.getTanggalLahir()

        binding.btnUkur.setOnClickListener {
            startActivity(Intent(this,PengukuranActivity::class.java))
        }

        binding.btnRiwayat.setOnClickListener {
            startActivity(Intent(this,RiwayatActivity::class.java))
        }
    }

    override fun onBackPressed() {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        if (backStackEntryCount == 0) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Yakin ingin keluar?")
            builder.setCancelable(true)
            builder.setPositiveButton(
                "Ya"
            ) {
                    _, _ -> super@MainActivity.onBackPressed() }
            builder.setNegativeButton(
                "Tidak"
            ) { _, _ ->
            }
            val dialog = builder.create()
            dialog.show()
        } else {
            super.onBackPressed()
        }
    }
}