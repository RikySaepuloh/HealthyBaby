package id.example.healthybaby

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
//
//        val spinner = Spinner(this)
//        val layoutKarbo = findViewById<LinearLayout>(R.id.linearkarbo)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, karbohidrat)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter
//// Atur tindakan yang akan dijalankan saat item dipilih dalam spinner
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                jenis1+= ",${karbohidrat[position]}"
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Tindakan yang dijalankan saat tidak ada item yang dipilih
//            }
//        }
//        if (layoutKarbo != null){
//            layoutKarbo.addView(spinner)
//        }
//
//        val spinner2 = Spinner(this)
//        val layoutprohen = findViewById<LinearLayout>(R.id.linearprohen)
//        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, protein_hewani)
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner2.adapter = adapter2
//// Atur tindakan yang akan dijalankan saat item dipilih dalam spinner
//        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                jenis2+= ",${protein_hewani[position]}"
//
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Tindakan yang dijalankan saat tidak ada item yang dipilih
//            }
//        }
//        if (layoutprohen != null){
//            layoutprohen.addView(spinner2)
//        }
//
//        val spinner3 = Spinner(this)
//        val layoutprobi = findViewById<LinearLayout>(R.id.linearprobi)
//        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, protein_nabati)
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner3.adapter = adapter3
//// Atur tindakan yang akan dijalankan saat item dipilih dalam spinner
//        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                jenis3+= ",${protein_nabati[position]}"
//
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Tindakan yang dijalankan saat tidak ada item yang dipilih
//            }
//        }
//        if (layoutprobi != null){
//            layoutprobi.addView(spinner3)
//        }
//
//        val spinner4 = Spinner(this)
//        val layoutlemak = findViewById<LinearLayout>(R.id.linearlemak)
//        val adapter4 = ArrayAdapter(this, android.R.layout.simple_spinner_item, lemak)
//        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner4.adapter = adapter4
//// Atur tindakan yang akan dijalankan saat item dipilih dalam spinner
//        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                jenis4+= ",${lemak[position]}"
//
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Tindakan yang dijalankan saat tidak ada item yang dipilih
//            }
//        }
//        if (layoutlemak != null){
//            layoutlemak.addView(spinner4)
//        }
//
//        val spinner5 = Spinner(this)
//        val layoutsayur = findViewById<LinearLayout>(R.id.linearsayur)
//        val adapter5 = ArrayAdapter(this, android.R.layout.simple_spinner_item, sayuran)
//        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner5.adapter = adapter5
//// Atur tindakan yang akan dijalankan saat item dipilih dalam spinner
//        spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                jenis5+= ",${protein_nabati[position]}"
//
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Tindakan yang dijalankan saat tidak ada item yang dipilih
//            }
//        }
//        if (layoutsayur != null){
//            layoutsayur.addView(spinner5)
//        }
//
//        val spinner6 = Spinner(this)
//        val layoutbuah = findViewById<LinearLayout>(R.id.linearbuah)
//        val adapter6 = ArrayAdapter(this, android.R.layout.simple_spinner_item, buah)
//        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner6.adapter = adapter6
//// Atur tindakan yang akan dijalankan saat item dipilih dalam spinner
//        spinner6.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                jenis6+= ",${buah[position]}"
//
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Tindakan yang dijalankan saat tidak ada item yang dipilih
//            }
//        }
//        if (layoutbuah != null){
//            layoutbuah.addView(spinner6)
//        }


        if (getMonthFromBirthdate(preferences.getTanggalLahir()) < 9 ){
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

    }
}