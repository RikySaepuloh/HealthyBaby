package id.example.healthybaby

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
    var jenis5 = ""
    var jenis6 = ""
    val preferences = Preferences()
    val karbohidrat = arrayListOf("Bihun", "Bubur Beras", "Kentang", "Makaroni", "Mie Basah", "Mie Kering", "Nasi", "Nasi Tim", "Roti Putih","Singkong","Talas","Tepung Beras","Tepung Terigu","Tepung Hunkwee","Ubi")
    val protein_hewani = arrayListOf("Ayam Tanpa Kulit","Babat" ,"Cumi-cumi","Daging Kerbau","Ikan asin","Ikan kakap","Ikan kembung","Ikan lele","Ikan mas","Ikan mujair","Ikan peda","Ikan pindang","Ikan segar","Kerang","Udang basah","Bakso","Daging kambing","Daging sapi","Hati ayam","Hati sapi","Otak","Telur ayam","Telur bebek","Ayam dengan kulit","Bebek","Kuning telur")
    val protein_nabati = arrayListOf("Kacang hijau","Kacang kedelai","Kacang merah segar","Kacang tanah","Tahu","Tempe")
    val lemak = arrayListOf("Mentega","Minyak bunga matahari","Minyak jagung","Minyak kedele","Minyak kelapa","Minyak kelapa sawit","Minyak zaitun","Santan")
    val sayuran = arrayListOf("Gambas","Jamur kuping","Tomat sayur","Oyong" ,"Ketimun"	,"Labu air","Selada air","Selada","Lobak","Daun bawang","Bayam","Bit","Labu waluh","Genjer", "Kapri muda","Kol", "Daun talas","Jagung muda", "Brokoli","Daun kecipir","Pepaya muda","Sawi","Kembang kol","Buncis","Labu siam","Rebung","Kemangi","Daun kacang panjang","Pare","Toge","Kangkung","Terong","Kacang panjang","Wortel","Bayam merah","Mangkokan","Nangka muda","Daun pepaya", "Daun katuk","Kacang kapri","Melinjo","Toge kedelai", "Daun melinjo","Daun talas","Kluwih","Daun singkong")
    val buah = arrayListOf("Avokado","Anggur","Apel merah","Apel malang","Belimbing","Blewah","Duku","Durian","Jambu air","Jambu bol","Jeruk balik","Jerut garut","Jeruk manis","Kedondong","Kurma","Leci","Mangga","Manggis","Markisa","Melon","Nangka masak","Nanas","Pear","Pepaya","Pisang ambon","Pisang kepok","Pisan mas","Pisang raja","Rambutan","Sawo","Salak","Semangka","Sirsak","Srikaya","Stroberi")
    val frek1 = arrayListOf("<2x per hari","2x per hari", "3x per hari")
    val frek2 = arrayListOf("2x per hari", "3x per hari","4x per hari")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProsesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences.setPreferences(this)

        val layoutKarbo=binding.linearkarbo
        val spinner = MaterialSpinner(this,null)
        spinner.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        spinner.hint = "Pilih MP-ASI"
        spinner.setItems(karbohidrat)
        spinner.setOnItemSelectedListener { view, position, id, item ->
            jenis1+= ",$item"
            layoutKarbo.addView(spinner)
        }
        if (layoutKarbo != null){
            layoutKarbo.addView(spinner)
        }

        val layoutprohen=binding.linearprohen
        val spinner2 = MaterialSpinner(this,null)
        spinner2.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        spinner2.hint = "Pilih MP-ASI"
        spinner2.setItems(protein_hewani)
        spinner2.setOnItemSelectedListener { view, position, id, item ->
            jenis2+= ",$item"
            layoutprohen.addView(spinner2)
        }
        if (layoutprohen != null){
            layoutprohen.addView(spinner2)
        }

        val layoutprobi=binding.linearprobi
        val spinner3 = MaterialSpinner(this,null)
        spinner3.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        spinner3.hint = "Pilih MP-ASI"
        spinner3.setItems(protein_nabati)
        spinner3.setOnItemSelectedListener { view, position, id, item ->
            jenis3+= ",$item"
            layoutprobi.addView(spinner3)
        }
        if (layoutprobi != null){
            layoutprobi.addView(spinner3)
        }

        val layoutLemak=binding.linearlemak
        val spinner4 = MaterialSpinner(this,null)
        spinner4.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        spinner4.hint = "Pilih MP-ASI"
        spinner4.setItems(lemak)
        spinner4.setOnItemSelectedListener { view, position, id, item ->
            jenis4+= ",$item"
            layoutLemak.addView(spinner4)
        }
        if (layoutLemak != null){
            layoutLemak.addView(spinner4)
        }

        val layoutSayur=binding.linearsayur
        val spinner5 = MaterialSpinner(this,null)
        spinner5.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        spinner5.hint = "Pilih MP-ASI"
        spinner5.setItems(sayuran)
        spinner5.setOnItemSelectedListener { view, position, id, item ->
            jenis5+= ",$item"
            layoutSayur.addView(spinner5)
        }
        if (layoutSayur != null){
            layoutSayur.addView(spinner5)
        }

        val layoutBuah=binding.linearbuah
        val spinner6 = MaterialSpinner(this,null)
        spinner6.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        spinner6.hint = "Pilih MP-ASI"
        spinner6.setItems(buah)
        spinner6.setOnItemSelectedListener { view, position, id, item ->
            jenis6+= ",$item"
            layoutBuah.addView(spinner6)
        }
        if (layoutBuah != null){
            layoutBuah.addView(spinner6)
        }


        if (getAge(preferences.getTanggalLahir().toString()) < 9 ){
            binding.etFrekuensi.setItems(frek1)
        }else{
            binding.etFrekuensi.setItems(frek2)
        }
        binding.etFrekuensi.setOnItemSelectedListener { view, position, id, item ->
            frekuensi=item.toString()
        }

        binding.btnSelanjutnya.setOnClickListener {
            if (frekuensi==""){
                Snackbar.make(binding.root,"Anda belum memilih frekuensi",Snackbar.LENGTH_LONG).show()
            }else if(jenis1 == "" || jenis2 == "" || jenis3 == ""){
                Snackbar.make(binding.root,"Anda belum jenis MP-ASI",Snackbar.LENGTH_LONG).show()
            }else{
                preferences.saveFrekuensi(frekuensi)
                try {
                    preferences.saveJenis1(jenis1.replaceFirst(",",""))
                } catch (e: Exception) {
                    preferences.saveJenis1(jenis1)
                }
                try {
                    preferences.saveJenis2(jenis2.replaceFirst(",",""))
                } catch (e: Exception) {
                    preferences.saveJenis2(jenis2)
                }
                try {
                    preferences.saveJenis3(jenis3.replaceFirst(",",""))
                } catch (e: Exception) {
                    preferences.saveJenis3(jenis3)
                }
                try {
                    preferences.saveJenis4(jenis4.replaceFirst(",",""))
                } catch (e: Exception) {
                    preferences.saveJenis4(jenis4)
                }
                try {
                    preferences.saveJenis5(jenis5.replaceFirst(",",""))
                } catch (e: Exception) {
                    preferences.saveJenis5(jenis5)
                }
                try {
                    preferences.saveJenis6(jenis6.replaceFirst(",",""))
                } catch (e: Exception) {
                    preferences.saveJenis6(jenis6)
                }
                if (getAge(preferences.getTanggalLahir().toString()) < 9 ){
                    when (frekuensi) {
                        "<2x per hari" -> {
                            preferences.saveHasilJenis("Kurang")
                        }
                        "2x per hari" -> {
                            preferences.saveHasilJenis("Cukup")
                        }
                        else -> {
                            preferences.saveHasilJenis("Baik")
                        }
                    }
                }else{
                    when (frekuensi) {
                        "2x per hari" -> {
                            preferences.saveHasilJenis("Kurang")
                        }
                        "3x per hari" -> {
                            preferences.saveHasilJenis("Cukup")
                        }
                        else -> {
                            preferences.saveHasilJenis("Baik")
                        }
                    }
                }
                startActivity(Intent(this,ResultActivity::class.java))
            }
        }
//
//        val spinner1 = binding.etJenis1
//        spinner1.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
//        spinner1.setOnItemSelectedListener { view, position, id, item ->
//            if (item.toString()=="Karbohidrat"){
//                binding.etSubjenis1.setItems(karbohidrat)
//            }else if (item.toString()=="Protein Hewani"){
//                binding.etSubjenis1.setItems(protein_hewani)
//            }else if (item.toString()=="Protein Nabati"){
//                binding.etSubjenis1.setItems(protein_nabati)
//            }else if (item.toString()=="Lemak"){
//                binding.etSubjenis1.setItems(lemak)
//            }else if (item.toString()=="Sayuran"){
//                binding.etSubjenis1.setItems(sayuran)
//            }else if (item.toString()=="Buah-buahan"){
//                binding.etSubjenis1.setItems(buah)
//            }else{
//                binding.etSubjenis1.setItems(karbohidrat)
//            }
//            binding.etSubjenis1.setOnItemSelectedListener { view1, position1, id1, item1 ->
//                jenis1=item1.toString()
//            }
//        }
//
//        val spinner2 = binding.etJenis2
//        spinner2.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
//        spinner2.setOnItemSelectedListener { view, position, id, item ->
//            if (item.toString()=="Karbohidrat"){
//                binding.etSubjenis2.setItems(karbohidrat)
//            }else if (item.toString()=="Protein Hewani"){
//                binding.etSubjenis2.setItems(protein_hewani)
//            }else if (item.toString()=="Protein Nabati"){
//                binding.etSubjenis2.setItems(protein_nabati)
//            }else if (item.toString()=="Lemak"){
//                binding.etSubjenis2.setItems(lemak)
//            }else if (item.toString()=="Sayuran"){
//                binding.etSubjenis2.setItems(sayuran)
//            }else if (item.toString()=="Buah-buahan"){
//                binding.etSubjenis2.setItems(buah)
//            }else{
//                binding.etSubjenis2.setItems(karbohidrat)
//            }
//            binding.etSubjenis2.setOnItemSelectedListener { view1, position1, id1, item1 ->
//                jenis2=item1.toString()
//            }
//        }
//
//        val spinner3 = binding.etJenis3
//        spinner3.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
//        spinner3.setOnItemSelectedListener { view, position, id, item ->
//            if (item.toString()=="Karbohidrat"){
//                binding.etSubjenis3.setItems(karbohidrat)
//            }else if (item.toString()=="Protein Hewani"){
//                binding.etSubjenis3.setItems(protein_hewani)
//            }else if (item.toString()=="Protein Nabati"){
//                binding.etSubjenis3.setItems(protein_nabati)
//            }else if (item.toString()=="Lemak"){
//                binding.etSubjenis3.setItems(lemak)
//            }else if (item.toString()=="Sayuran"){
//                binding.etSubjenis3.setItems(sayuran)
//            }else if (item.toString()=="Buah-buahan"){
//                binding.etSubjenis3.setItems(buah)
//            }else{
//                binding.etSubjenis3.setItems(karbohidrat)
//            }
//            binding.etSubjenis3.setOnItemSelectedListener { view1, position1, id1, item1 ->
//                jenis3=item1.toString()
//            }
//        }
//
//        val spinner4 = binding.etJenis4
//        spinner4.setItems("Karbohidrat","Protein Hewani","Protein Nabati","Lemak","Sayuran","Buah-buahan")
//        spinner4.setOnItemSelectedListener { view, position, id, item ->
//            if (item.toString()=="Karbohidrat"){
//                binding.etSubjenis4.setItems(karbohidrat)
//            }else if (item.toString()=="Protein Hewani"){
//                binding.etSubjenis4.setItems(protein_hewani)
//            }else if (item.toString()=="Protein Nabati"){
//                binding.etSubjenis4.setItems(protein_nabati)
//            }else if (item.toString()=="Lemak"){
//                binding.etSubjenis4.setItems(lemak)
//            }else if (item.toString()=="Sayuran"){
//                binding.etSubjenis4.setItems(sayuran)
//            }else if (item.toString()=="Buah-buahan"){
//                binding.etSubjenis4.setItems(buah)
//            }else{
//                binding.etSubjenis4.setItems(karbohidrat)
//            }
//            binding.etSubjenis4.setOnItemSelectedListener { view1, position1, id1, item1 ->
//                jenis4=item1.toString()
//            }
//        }
    }
}