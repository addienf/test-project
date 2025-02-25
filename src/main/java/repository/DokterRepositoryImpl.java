package repository;

import entity.Dokter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DokterRepositoryImpl implements DokterRepository{
    private DataSource dataSource;

    public DokterRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Dokter> findAll() {
        List<Dokter> listDokter = new ArrayList<>();
        String sql = "SELECT * FROM dokter";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                Dokter dokter = new Dokter();
                dokter.setId(resultSet.getInt("id"));
                dokter.setNama(resultSet.getString("nama"));
                listDokter.add(dokter);
            }

            return listDokter;
        } catch (SQLException e){
            throw new RuntimeException("Error saat mengambil data dokter", e);
        }
    }

    @Override
    public Integer create(Dokter object) {
        String sql = """
                INSERT INTO dokter (nama, umur, jenis_kelamin, departemen, nomor_telpon, alamat)
                VALUES (?, ?, ?, ?, ?, ?);
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, object.getNama());
            statement.setInt(2, object.getUmur());
            statement.setString(3, object.getJenisKelamin());
            statement.setString(4, object.getDepartemen());
            statement.setString(5, object.getNomorTelpon());
            statement.setString(6, object.getAlamat());

            int effectedRows = statement.executeUpdate();

            if (effectedRows == 0){
                throw new SQLException("Gagal menyimpan data dokter, tidak ada baris yang terpengaruh.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Gagal mendapatkan ID dokter yang baru dibuat.");
                }
            }
        } catch (SQLException e){
            throw new RuntimeException("Error saat menyimpan data dokter", e);
        }
    }

    @Override
    public Integer update(Dokter object) {
        String sql = """
                UPDATE dokter 
                set nama = ?, umur = ?, jenis_kelamin = ?, departemen = ?, nomor_telpon = ?, alamat = ? 
                WHERE id = ?;
                """;

        try (Connection con = dataSource.getConnection();
        PreparedStatement statement = con.prepareStatement(sql)){

            statement.setString(1, object.getNama());
            statement.setInt(2, object.getUmur());
            statement.setString(3, object.getJenisKelamin());
            statement.setString(4, object.getDepartemen());
            statement.setString(5, object.getNomorTelpon());
            statement.setString(6, object.getAlamat());
            statement.setInt(7, object.getId());

            return statement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Error saat memperbarui data dokter", e);
        }
    }

    @Override
    public Dokter findById(int id) {
        String sql = "SELECT * FROM dokter WHERE id = ? ";
        Dokter dokter = null;

        try(Connection con = dataSource.getConnection();
        PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    dokter = new Dokter();
                    dokter.setId(resultSet.getInt("id"));
                    dokter.setNama(resultSet.getString("nama"));
                }
            }
        } catch (SQLException e){
            throw new RuntimeException("Error saat mencari dokter dengan ID " + id, e);
        }

        return dokter;
    }

    private boolean isExist(int id){
        String sql = "SELECT id FROM dokter where id = ?";

        try (Connection con = dataSource.getConnection();
         PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e){
            throw new RuntimeException("Error saat mengecek keberadaan dokter dengan ID " + id, e);
        }
    }

    @Override
    public Integer delete(int id) {
        int result = 0;
        String sql = "DELETE FROM dokter WHERE id = ?";

        if (isExist(id)){
            try (Connection con = dataSource.getConnection();
                 PreparedStatement statement = con.prepareStatement(sql)){

                statement.setInt(1, id);
                result = statement.executeUpdate();

            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            return result;
        } else {
            return 0;
        }
    }
}
