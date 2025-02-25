package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Dokter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseUtil;

public class DokterRepositoryImplTest {
    private HikariDataSource dataSource;
    private DokterRepository dokterRepository;
    Dokter dokter = new Dokter();

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        dokterRepository = new DokterRepositoryImpl(dataSource);
    }

    @Test
    void testCreate() {
        dokter.setNama("Addien");
        dokter.setUmur(23);
        dokter.setJenisKelamin("Pria");
        dokter.setDepartemen("Tulang");
        dokter.setNomorTelpon("082116674129");
        dokter.setAlamat("Cimahi");
        dokterRepository.create(dokter);
    }

    @Test
    void testUpdate() {
        dokter.setNama("Fauzan");
        dokter.setUmur(24);
        dokter.setJenisKelamin("Pria");
        dokter.setDepartemen("Cancer");
        dokter.setNomorTelpon("082145678412");
        dokter.setAlamat("Bandung");
        dokter.setId(2);
        dokterRepository.update(dokter);
    }

    @Test
    void findByID() {
        if (dokterRepository.findById(1) == null){
            System.out.println("Data Tidak Ada!!");
        } else {
            System.out.println("Data Ditemukan!!");
        }
    }

    @Test
    void testRemove() {
        if (dokterRepository.delete(6).equals(0)){
            System.out.println("Data Tidak Ada!!");
        }
    }

    @Test
    void testGetAll() {
        dokterRepository.findAll();
    }
}
