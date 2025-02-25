package view;

import entity.Dokter;
import service.DokterService;
import util.InputUtil;

import java.util.Scanner;

public class DokterView {

    private static DokterService dokterService;

    public DokterView(DokterService dokterService) {
        DokterView.dokterService = dokterService;
    }

    public static void menuDokter(){
        while (true){
            System.out.println("DATA DOKTER");
            System.out.println("MENU");
            System.out.println("1. Tambah");
            System.out.println("2. Lihat Data Dokter");
            System.out.println("3. Cari Data Dokter");
            System.out.println("X. Keluar");

            var input = InputUtil.inputString("Pilih");

            if (input.equals("1")){
                dokterService.addDokter();
            } else if (input.equals("2")){
                showDokter();
            } else if (input.equals("3")) {
                searchDokter();
            } else if (input.equalsIgnoreCase("X")){
                break;
            } else {
                System.out.println("Pilihan tidak ada !!");
            }
        }
    }

    public static void showDokter(){
        dokterService.showDokter();
    }

    public static void searchDokter(){
        int searchId = 0;
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukan Id: ");
        searchId = sc.nextInt();
        sc.nextLine();

        Dokter searchedDokter = dokterService.findId(searchId);
        if (searchedDokter != null){
            dokterService.showById(searchedDokter);
        } else {
            System.out.println("Data tidak ditemukan!!");
        }
    }

    public static void removeDokter() {

    }
}
