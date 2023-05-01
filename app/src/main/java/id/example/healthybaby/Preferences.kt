package id.example.healthybaby

import android.content.Context
import android.content.SharedPreferences

class Preferences {

    var APP_NAME = R.string.app_name

    private var log_status = "log_status"
    private var input_status = "input_status"
    private var token_type = "token_type"
    private var kelas = "kelas"
    private var userid = "userid"
    private var idDevice = "id_device"
    private var email = "email"
    private var frekuensi = "frekuensi"
    private var namaSekolah = "nama_sekolah"
    private var jenis_kelamin = "jenis_kelamin"
    private var nama = "nama"
    private var tb = "tb"
    private var bb = "bb"
    private var jenis1 = "jenis1"
    private var jenis2 = "jenis2"
    private var jenis3 = "jenis3"
    private var jenis4 = "jenis4"
    private var hasiljenis = "hasiljenis"
    private var nama_nakes = "nama_nakes"
    private var tanggal_lahir = "tanggal_lahir"
    private var tempat_lahir = "tempat_lahir"
    private var nik = "nik"
    private var expires = "expires"
    private var token = "token"
    private var logged_as = "logged_as"
    private var noHp = "no_hp"
    private var kodePP = "kodepp"
    private var saveData = "save_data"
    private var password = "password"
    private var fingerprint = "fingerprint"
    private var kodeRumah = "koderumah"
    private var darkMode = "darkMode"

    var sp: SharedPreferences? = null
    var spEditor: SharedPreferences.Editor? = null

    fun setPreferences(context: Context) {
        sp = context.getSharedPreferences(APP_NAME.toString(), Context.MODE_PRIVATE)
        spEditor = sp?.edit()
    }

    fun preferencesLogout() {
        spEditor!!.clear()
        spEditor!!.commit()
    }


    fun saveFingerprintState(value: Boolean) {
        spEditor!!.putBoolean(fingerprint, value)
        spEditor!!.commit()
    }

    fun saveDarkMode(value: Boolean) {
        spEditor!!.putBoolean(darkMode, value)
        spEditor!!.commit()
    }

    fun saveLogStatus(value: Boolean) {
        spEditor!!.putBoolean(log_status, value)
        spEditor!!.commit()
    }

    fun saveInputStatus(value: Boolean) {
        spEditor!!.putBoolean(input_status, value)
        spEditor!!.commit()
    }

    fun saveSaveData(value: Boolean) {
        spEditor!!.putBoolean(saveData, value)
        spEditor!!.commit()
    }

    fun saveToken(value: String?) {
        spEditor!!.putString(token, value)
        spEditor!!.commit()
    }

    fun saveFrekuensi(value: String?) {
        spEditor!!.putString(frekuensi, value)
        spEditor!!.commit()
    }

    fun saveHasilJenis(value: String?) {
        spEditor!!.putString(hasiljenis, value)
        spEditor!!.commit()
    }

    fun saveJenis1(value: String?) {
        spEditor!!.putString(jenis1, value)
        spEditor!!.commit()
    }

    fun saveJenis2(value: String?) {
        spEditor!!.putString(jenis2, value)
        spEditor!!.commit()
    }

    fun saveJenis3(value: String?) {
        spEditor!!.putString(jenis3, value)
        spEditor!!.commit()
    }

    fun saveJenis4(value: String?) {
        spEditor!!.putString(jenis4, value)
        spEditor!!.commit()
    }

    fun saveUserID(value: String?) {
        spEditor!!.putString(userid, value)
        spEditor!!.commit()
    }

    fun saveIdDevice(value: String?) {
        spEditor!!.putString(idDevice, value)
        spEditor!!.commit()
    }

    fun saveNamaNakes(value: String?) {
        spEditor!!.putString(nama_nakes, value)
        spEditor!!.commit()
    }

    fun saveNama(value: String?) {
        spEditor!!.putString(nama, value)
        spEditor!!.commit()
    }

    fun saveKelas(value: String?) {
        spEditor!!.putString(kelas, value)
        spEditor!!.commit()
    }

    fun saveTokenType(value: String?) {
        spEditor!!.putString(token_type, value)
        spEditor!!.commit()
    }

    fun saveExpires(value: String?) {
        spEditor!!.putString(expires, value)
        spEditor!!.commit()
    }

    fun saveNoHp(value: String?) {
        spEditor!!.putString(noHp, value)
        spEditor!!.commit()
    }

    fun saveNIK(value: String?) {
        spEditor!!.putString(nik, value)
        spEditor!!.commit()
    }

    fun saveTanggalLahir(value: String?) {
        spEditor!!.putString(tanggal_lahir, value)
        spEditor!!.commit()
    }

    fun saveTB(value: String?) {
        spEditor!!.putString(tb, value)
        spEditor!!.commit()
    }

    fun saveBB(value: String?) {
        spEditor!!.putString(bb, value)
        spEditor!!.commit()
    }

    fun saveJenisKelamin(value: String?) {
        spEditor!!.putString(jenis_kelamin, value)
        spEditor!!.commit()
    }

    fun saveTempatLahir(value: String?) {
        spEditor!!.putString(tempat_lahir, value)
        spEditor!!.commit()
    }

    fun saveEmail(value: String?) {
        spEditor!!.putString(email, value)
        spEditor!!.commit()
    }

    fun savePassword(value: String?) {
        spEditor!!.putString(password, value)
        spEditor!!.commit()
    }

    fun saveKodePP(value: String?) {
        spEditor!!.putString(kodePP, value)
        spEditor!!.commit()
    }

    fun saveLoggedAs(value: String?) {
        spEditor!!.putString(logged_as, value)
        spEditor!!.commit()
    }

    fun saveNamaSekolah(value: String?) {
        spEditor!!.putString(namaSekolah, value)
        spEditor!!.commit()
    }

    fun saveKodeRumah(value: String?) {
        spEditor!!.putString(kodeRumah, value)
        spEditor!!.commit()
    }

    fun getLogStatus(): Boolean {
        return sp!!.getBoolean(log_status, false)
    }

    fun getInputStatus(): Boolean {
        return sp!!.getBoolean(input_status, false)
    }

    fun getFingerprintState(): Boolean {
        return sp!!.getBoolean(fingerprint, false)
    }

    fun getDarkMode(): Boolean {
        return sp!!.getBoolean(darkMode, false)
    }

    fun getSaveData(): Boolean {
        return sp!!.getBoolean(saveData, false)
    }

    fun getExpires(): String? {
        return sp!!.getString(expires, "N/A")
    }

    fun getFrekuensi(): String? {
        return sp!!.getString(frekuensi, "N/A")
    }

    fun getTB(): String? {
        return sp!!.getString(tb, "N/A")
    }

    fun getBB(): String? {
        return sp!!.getString(bb, "N/A")
    }

    fun getNamaNakes(): String? {
        return sp!!.getString(nama_nakes, "N/A")
    }

    fun getLoggedAs(): String? {
        return sp!!.getString("logged_as", "N/A")
    }


    fun getTanggalLahir(): String? {
        return sp!!.getString(tanggal_lahir, "N/A")
    }

    fun getJenis1(): String? {
        return sp!!.getString(jenis1, "N/A")
    }

    fun getHasilJenis(): String? {
        return sp!!.getString(hasiljenis, "N/A")
    }

    fun getJenis2(): String? {
        return sp!!.getString(jenis2, "N/A")
    }

    fun getJenis3(): String? {
        return sp!!.getString(jenis3, "N/A")
    }

    fun getJenis4(): String? {
        return sp!!.getString(jenis4, "N/A")
    }

    fun getTempatLahir(): String? {
        return sp!!.getString(tempat_lahir, "N/A")
    }

    fun getJenisKelamin(): String? {
        return sp!!.getString(jenis_kelamin, "N/A")
    }

    fun getNama(): String? {
        return sp!!.getString(nama, "N/A")
    }

    fun getIdDevice(): String? {
        return sp!!.getString(idDevice, "N/A")
    }

    fun getPassword(): String? {
        return sp!!.getString(password, "N/A")
    }

    fun getUserID(): String? {
        return sp!!.getString(userid, "N/A")
    }

    fun getEmail(): String? {
        return sp!!.getString(email, "N/A")
    }

    fun getNamaSekolah(): String? {
        return sp!!.getString(namaSekolah, "N/A")
    }

    fun getNIK(): String? {
        return sp!!.getString(nik, "N/A")
    }

    fun getTokenType(): String? {
        return sp!!.getString(token_type, "N/A")
    }



    fun getKelas(): String? {
        return sp!!.getString(kelas, "N/A")
    }

    fun getToken(): String? {
        return sp!!.getString(token, "N/A")
    }

    fun getNoHp(): String? {
        return sp!!.getString(noHp, "N/A")
    }

    fun getKodePP(): String? {
        return sp!!.getString(kodePP, "N/A")
    }

    fun getKodeRumah(): String? {
        return sp!!.getString(kodeRumah, "N/A")
    }
}