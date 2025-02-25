package view;

import entity.Dokter;
import service.DokterService;
import util.InputUtil;

import java.util.List;
import java.util.Scanner;

public class DokterView {

    private final DokterService dokterService;
    private final Scanner scanner;

    public DokterView(DokterService dokterService, Scanner scanner) {
        this.dokterService = dokterService;
        this.scanner = new Scanner(System.in);
    }

    public void addDokter(){
        System.out.print("Nama: ");
        String nama = scanner.nextLine();

        System.out.print("Umur: ");
        int umur = scanner.nextInt();
        scanner.nextLine();  // clear buffer

        System.out.print("Jenis Kelamin: ");
        String jenisKelamin = scanner.nextLine();

        System.out.print("Departemen: ");
        String departemen = scanner.nextLine();

        System.out.print("Nomor Telpon: ");
        String nomorTelpon = scanner.nextLine();

        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();

        Dokter dokter = new Dokter(null, nama, umur, jenisKelamin, departemen, nomorTelpon, alamat);
        dokterService.addDokter(dokter);

        System.out.println("Data dokter berhasil ditambahkan!");
    }

    public void showDokter(){
        List<Dokter> dokterList = dokterService.getAllDokter();
        for (Dokter dokter : dokterList) {
            printDokterInfo(dokter);
        }
    }

    public void showDokterById() {
        System.out.print("Masukkan ID Dokter: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Dokter dokter = dokterService.getDokterById(id);
        if (dokter != null) {
            printDokterInfo(dokter);
        } else {
            System.out.println("Dokter tidak ditemukan.");
        }
    }

    public void removeDokter() {
        System.out.print("Masukkan ID Dokter yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        dokterService.removeDokter(id);
        System.out.println("Dokter berhasil dihapus.");
    }

    private void printDokterInfo(Dokter dokter) {
        System.out.println("ID: " + dokter.getId());
        System.out.println("Nama: " + dokter.getNama());
        System.out.println();
    }

    public void menuDokter(){
        while (true){
            System.out.println("MENU");
            System.out.println("1. Tambah Data Dokter");
            System.out.println("2. Lihat Data Dokter");
            System.out.println("3. Cari Data Dokter");
            System.out.println("4. Hapus Data Dokter");
            System.out.println("X. Keluar");

            var input = InputUtil.inputString("Pilih");

            if (input.equals("1")){
                addDokter();
            } else if (input.equals("2")){
                showDokter();
            } else if (input.equals("3")) {
                showDokterById();
            } else if (input.equals("4")) {
                removeDokter();
            } else if (input.equalsIgnoreCase("X")) {
                break;
            } else {
                System.out.println("Pilihan tidak ada!");
            }
        }
    }
}
