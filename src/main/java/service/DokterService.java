package service;

import entity.Dokter;

import java.util.List;

public interface DokterService {
    List<Dokter> getAllDokter();
    Dokter getDokterById(int id);
    void addDokter(Dokter dokter);
    void removeDokter(int id);
}
