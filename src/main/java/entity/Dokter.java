package entity;

public class Dokter {

    private Integer id;
    private String nama;
    private Integer umur;
    private String jenisKelamin;
    private String departemen;
    private String nomorTelpon;
    private String alamat;

    public Dokter() {
    }

    public Dokter(String nama) {
        this.nama = nama;
    }

    public Dokter(String nama, Integer umur, String jenisKelamin, String departemen, String nomorTelpon, String alamat) {
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.departemen = departemen;
        this.nomorTelpon = nomorTelpon;
        this.alamat = alamat;
    }

    public Dokter(Integer id, String nama, Integer umur, String jenisKelamin, String departemen, String nomorTelpon, String alamat) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.departemen = departemen;
        this.nomorTelpon = nomorTelpon;
        this.alamat = alamat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public String getNomorTelpon() {
        return nomorTelpon;
    }

    public void setNomorTelpon(String nomorTelpon) {
        this.nomorTelpon = nomorTelpon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
