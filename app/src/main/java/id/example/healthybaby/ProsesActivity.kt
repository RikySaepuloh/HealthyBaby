package id.example.healthybaby

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import id.example.healthybaby.databinding.ActivityProsesBinding


class ProsesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProsesBinding
    var frekuensi = ""
    var jenis1 = ""
    var jenis2 = ""
    var jenis3 = ""
    var jenis4 = ""
    val preferences = Preferences()
    val karbohidrat = arrayListOf("Bihun", "Bubur Beras", "Kentang", "Makaroni", "Mie Basah", "Mie Kering", "Nasi", "Nasi Tim", "Roti Putih","Singkong","Talas","Tepung Beras","Tepung Terigu","Tepung Hunkwee","Ubi")
    val protein_hewani = arrayListOf("Ayam Tanpa Kulit","Babat" ,"Cumi-cumi","Daging Kerbau","Ikan asin","Ikan kakap","Ikan kembung","Ikan lele","Ikan mas","Ikan mujair","Ikan peda","Ikan pindang","Ikan segar","Kerang","Udang basah","Bakso","Daging kambing","Daging sapi","Hati ayam","Hati sapi","Otak","Telur ayam","Telur bebek","Ayam dengan kulit","Bebek","Kuning telur")
    val protein_nabati = arrayListOf("Kacang hijau","Kacang kedelai","Kacang merah segar","Kacang tanah","Tahu","Tempe")
    val lemak = arrayListOf("Mentega","Minyak bunga matahari","Minyak jagung","Minyak kedele","Minyak kelapa","Minyak kelapa sawit","Minyak zaitun","Santan")

    val sayuran = arrayListOf("Gambas","Jamur kuping","Tomat sayur","Oyong" ,"Ketimun"	,"Labu air","Selada air","Selada","Lobak","Daun bawang","Bayam","Bit","Labu waluh","Genjer", "Kapri muda","Kol", "Daun talas","Jagung muda", "Brokoli","Daun kecipir","Pepaya muda","Sawi","Kembang kol","Buncis","Labu siam","Rebung","Kemangi","Daun kacang panjang","Pare","Toge","Kangkung","Terong","Kacang panjang","Wortel","Bayam merah","Mangkokan","Nangka muda","Daun pepaya", "Daun katuk","Kacang kapri","Melinjo","Toge kedelai", "Daun melinjo","Daun talas","Kluwih","Daun singkong")
    val buah = arrayListOf("Avokado","Anggur","Apel merah","Apel malang","Belimbing","Blewah","Duku","Durian","Jambu air","Jambu bol","Jeruk balik","Jerut garut","Jeruk manis","Kedondong","Kurma","Leci","Mangga","Manggis","Markisa","Melon","Nangka masak","Nanas","Pear","Pepaya","Pisang ambon","Pisang kepok","Pisan mas","Pisang raja","Rambutan","Sawo","Salak","Semangka","Sirsak","Srikaya","Stroberi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProsesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(this)

        if (getAge(preferences.getTanggalLahir().toString()) < 9 ){
            frekuensi="2-3x"
            binding.mcJenis4.visibility=View.GONE
        }else{
            frekuensi="3-4x"
            binding.mcJenis4.visibility=View.VISIBLE
        }

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

        val spinner1 = binding.etJenis1
        spinner1.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
        spinner1.setOnItemSelectedListener { view, position, id, item ->
            if (item.toString()=="Karbohidrat"){
                binding.etSubjenis1.setItems(karbohidrat)
            }else if (item.toString()=="Protein Hewani"){
                binding.etSubjenis1.setItems(protein_hewani)
            }else if (item.toString()=="Protein Nabati"){
                binding.etSubjenis1.setItems(protein_nabati)
            }else if (item.toString()=="Lemak"){
                binding.etSubjenis1.setItems(lemak)
            }else if (item.toString()=="Sayuran"){
                binding.etSubjenis1.setItems(sayuran)
            }else if (item.toString()=="Buah-buahan"){
                binding.etSubjenis1.setItems(buah)
            }else{
                binding.etSubjenis1.setItems(karbohidrat)
            }
            binding.etSubjenis1.setOnItemSelectedListener { view1, position1, id1, item1 ->
                jenis1=item1.toString()
            }
        }

        val spinner2 = binding.etJenis2
        spinner2.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
        spinner2.setOnItemSelectedListener { view, position, id, item ->
            if (item.toString()=="Karbohidrat"){
                binding.etSubjenis2.setItems(karbohidrat)
            }else if (item.toString()=="Protein Hewani"){
                binding.etSubjenis2.setItems(protein_hewani)
            }else if (item.toString()=="Protein Nabati"){
                binding.etSubjenis2.setItems(protein_nabati)
            }else if (item.toString()=="Lemak"){
                binding.etSubjenis2.setItems(lemak)
            }else if (item.toString()=="Sayuran"){
                binding.etSubjenis2.setItems(sayuran)
            }else if (item.toString()=="Buah-buahan"){
                binding.etSubjenis2.setItems(buah)
            }else{
                binding.etSubjenis2.setItems(karbohidrat)
            }
            binding.etSubjenis2.setOnItemSelectedListener { view1, position1, id1, item1 ->
                jenis2=item1.toString()
            }
        }

        val spinner3 = binding.etJenis3
        spinner3.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
        spinner3.setOnItemSelectedListener { view, position, id, item ->
            if (item.toString()=="Karbohidrat"){
                binding.etSubjenis3.setItems(karbohidrat)
            }else if (item.toString()=="Protein Hewani"){
                binding.etSubjenis3.setItems(protein_hewani)
            }else if (item.toString()=="Protein Nabati"){
                binding.etSubjenis3.setItems(protein_nabati)
            }else if (item.toString()=="Lemak"){
                binding.etSubjenis3.setItems(lemak)
            }else if (item.toString()=="Sayuran"){
                binding.etSubjenis3.setItems(sayuran)
            }else if (item.toString()=="Buah-buahan"){
                binding.etSubjenis3.setItems(buah)
            }else{
                binding.etSubjenis3.setItems(karbohidrat)
            }
            binding.etSubjenis3.setOnItemSelectedListener { view1, position1, id1, item1 ->
                jenis3=item1.toString()
            }
        }

        val spinner4 = binding.etJenis4
        spinner4.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
        spinner4.setOnItemSelectedListener { view, position, id, item ->
            if (item.toString()=="Karbohidrat"){
                binding.etSubjenis4.setItems(karbohidrat)
            }else if (item.toString()=="Protein Hewani"){
                binding.etSubjenis4.setItems(protein_hewani)
            }else if (item.toString()=="Protein Nabati"){
                binding.etSubjenis4.setItems(protein_nabati)
            }else if (item.toString()=="Lemak"){
                binding.etSubjenis4.setItems(lemak)
            }else if (item.toString()=="Sayuran"){
                binding.etSubjenis4.setItems(sayuran)
            }else if (item.toString()=="Buah-buahan"){
                binding.etSubjenis4.setItems(buah)
            }else{
                binding.etSubjenis4.setItems(karbohidrat)
            }
            binding.etSubjenis4.setOnItemSelectedListener { view1, position1, id1, item1 ->
                jenis4=item1.toString()
            }
        }
    }
}