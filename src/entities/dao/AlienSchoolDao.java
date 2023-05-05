package entities.dao;

import entities.AlienSchool;
import interfaces.IDBOperations;
import util.DatabaseManager;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlienSchoolDao implements IDBOperations<AlienSchool> {
    private Connection conn;

    public AlienSchoolDao(String jdbcUrl) throws SQLException {
        this.conn = DatabaseManager.getConnection(jdbcUrl);
    }

    public AlienSchool parseResultSetToReturnSchool(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new AlienSchool(id, name);
    }

    @Override
    public List<AlienSchool> fetchAll()  {
        List<AlienSchool> alienSchools = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM AlienSchools");
            while (rs.next()) {
                AlienSchool school = parseResultSetToReturnSchool(rs);
                alienSchools.add(school);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alienSchools;
    }

    @Override
    public AlienSchool getById(int id) {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM AlienSchools WHERE id = " + id);
            if (rs.next()) {
                return parseResultSetToReturnSchool(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void upsert(int id, AlienSchool item)  {
        String sqlSelect = "SELECT * FROM AlienSchools WHERE id = ?";
        String sqlInsert = "INSERT INTO AlienSchools (id, name) VALUES (?,?)";
        String sqlUpdate = "UPDATE AlienSchools SET name = ? WHERE id = ?";
        try {

            // mod de lucru:
            // se prepara query SELECT pt a verifica prezenta PRIMARY KEUY
            // daca EXISTA se executa UPDATE
            // daca NU EXISTA se executa INSERT
            // QUERY urile se prepara oricum, dar se executa doar unul din ele
            PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);

            psInsert.setInt(1, item.getId());
            psInsert.setString(2, item.getName());
            psUpdate.setString(1, item.getName());
            psUpdate.setInt(2, item.getId());

            psSelect.setInt(1, item.getId());
            ResultSet rs = psSelect.executeQuery();

            // verificarea existentei PRIMARY KEY
            if(rs.next()){
                psUpdate.executeUpdate();
            }else{
                psInsert.executeUpdate();
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws IOException {
        String sql = "DELETE FROM AlienSchools WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
