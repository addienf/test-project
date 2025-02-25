package service;

import entity.Dokter;
import repository.DokterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DokterServiceImpl implements DokterService{
    private static DokterRepository dokterRepository;
    List<Dokter> dokterList = new ArrayList<>();
    static Dokter dokter = new Dokter();

    public DokterServiceImpl(DokterRepository dokterRepository) {
        this.dokterRepository = dokterRepository;
    }

    @Override
    public void showDokter() {
        dokterList = dokterRepository.findAll();
        findAllDokter(dokterList);
    }

    @Override
    public void showById(Dokter search) {
        System.out.println("PENCARIAN");
        System.out.println("Id : " +search.getId());
        System.out.println("Nama : " +search.getNama());
    }

    @Override
    public Dokter findId(int id) {
        return dokterRepository.findById(id);
    }


    @Override
    public void addDokter() {
        insertDokter(dokter);
    }

    @Override
    public void removeDokter(Integer id) {
        dokterRepository.delete(id);

    }

    private static void insertDokter(Dokter datas){
        var sc = new Scanner(System.in);
        String nama, jenisKelamin, departemen, nomorTelpon, alamat;
        int umur;

        System.out.println("Nama : ");
        nama = sc.nextLine();

        System.out.println("Umur : ");
        umur = sc.nextInt();
        sc.nextLine();

        System.out.println("Jenis Kelamin : ");
        jenisKelamin = sc.nextLine();

        System.out.println("Departemen : ");
        departemen = sc.nextLine();

        System.out.println("Nomor Telpon : ");
        nomorTelpon = sc.nextLine();

        System.out.println("Alamat : ");
        alamat = sc.nextLine();

        dokter.setNama(nama);
        dokter.setUmur(umur);
        dokter.setJenisKelamin(jenisKelamin);
        dokter.setDepartemen(departemen);
        dokter.setNomorTelpon(nomorTelpon);
        dokter.setAlamat(alamat);

        dokterRepository.create(dokter);

        System.out.println("Data berhasil disimpan!");
    }

    private static void findAllDokter(List<Dokter> dokterList){
        for (var dokter : dokterList){
            System.out.println("ID : " +dokter.getId());
            System.out.println("Nama : " +dokter.getNama());
//            System.out.println("Umur : " +dokter.getUmur());
//            System.out.println("Jenis Kelamin : " +dokter.getJenisKelamin());
//            System.out.println("Departemen : " +dokter.getDepartemen());
//            System.out.println("Telepon : " +dokter.getNomorTelpon());
//            System.out.println("Alamat : " +dokter.getAlamat());
            System.out.println(" ");
        }
    }
}
