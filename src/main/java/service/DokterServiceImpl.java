package service;

import entity.Dokter;
import repository.DokterRepository;

import java.util.List;

public class DokterServiceImpl implements DokterService{

    private final DokterRepository dokterRepository;

    public DokterServiceImpl(DokterRepository dokterRepository) {
        this.dokterRepository = dokterRepository;
    }


    @Override
    public List<Dokter> getAllDokter() {
        return dokterRepository.findAll();
    }

    @Override
    public Dokter getDokterById(int id) {
        return dokterRepository.findById(id);
    }

    @Override
    public void addDokter(Dokter dokter) {
        dokterRepository.create(dokter);
    }

    @Override
    public void removeDokter(int id) {
        dokterRepository.delete(id);
    }
}
