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
        String sql = """
                SELECT * FROM dokter;
                """;
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            List<Dokter> listDokter = new ArrayList<>();

            while (resultSet.next()){
                Dokter dokter = new Dokter();
                dokter.setId(resultSet.getInt("id"));
                dokter.setNama(resultSet.getString("nama"));
                listDokter.add(dokter);
            }

            return listDokter;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer create(Dokter object) {
        int result = 0;
        String sql = """
                INSERT INTO dokter (nama, umur, jenis_kelamin, departemen, nomor_telpon, alamat)
                VALUES (?, ?, ?, ?, ?, ?);
                """;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)){

            statement.setString(1, object.getNama());
            statement.setInt(2, object.getUmur());
            statement.setString(3, object.getJenisKelamin());
            statement.setString(4, object.getDepartemen());
            statement.setString(5, object.getNomorTelpon());
            statement.setString(6, object.getAlamat());

            result = statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Integer update(Dokter object) {
        int result = 0;

        String sql = """
                UPDATE dokter set nama = ?, umur = ?, jenis_kelamin = ?,
                departemen = ?, nomor_telpon = ?, alamat = ? WHERE id = ?;
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

            result = statement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Dokter findById(int id) {
        Dokter dokter = null;
        String sql = "SELECT * FROM dokter WHERE id = " +id;

        try(Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                dokter = new Dokter();
                dokter.setId(resultSet.getInt("id"));
                dokter.setNama(resultSet.getString("nama"));
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return dokter;
    }

    private boolean isExist(Integer num){
        String sql = """
                SELECT id FROM dokter where id = ?
                """;

        try (Connection con = dataSource.getConnection();
         PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, num);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
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
