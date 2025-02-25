package service;

import entity.Dokter;

public interface DokterService {
    void showDokter();

    void showById(Dokter search);

    Dokter findId(int id);

    void addDokter();

    void removeDokter(Integer id);
}
