package entities.dao;

import entities.AlienChild;
import filters.BetterFilter;
import interfaces.IDBOperations;
import specifications.FinalYearSpecification;
import util.DatabaseManager;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AlienChildDao implements IDBOperations<AlienChild> {

    private Connection conn;
    public AlienChildDao(String jdbcUrl) throws SQLException {
        this.conn = DatabaseManager.getConnection(jdbcUrl);
    }

    private AlienChild parseResultSetToReturnChild(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int yearOfStudy = rs.getInt("yearOfStudy");
        char qwerty = rs.getString("qwerty").charAt(0);
        char qwertz = rs.getString("qwertz").charAt(0);
        char qzerty = rs.getString("qzerty").charAt(0);
        char azerty = rs.getString("azerty").charAt(0);
        char dvorak = rs.getString("dvorak").charAt(0);
        int schoolID = rs.getInt("schoolID");

        AlienChild child = new AlienChild(id, name, yearOfStudy, qwerty, qwertz, qzerty, azerty, dvorak, schoolID);

        return child;
    }

    @Override
    public List<AlienChild> fetchAll() {
        List<AlienChild> alienchildren = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM AlienChildren");
            while (rs.next()) {
                AlienChild child = parseResultSetToReturnChild(rs);
                alienchildren.add(child);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alienchildren;
    }
    public Stream<AlienChild> getFinalYear(){
        Stream<AlienChild> childrenAsStream = fetchAll().stream();
        BetterFilter bf = new BetterFilter();

        return bf.filter(childrenAsStream, new FinalYearSpecification());
    }

    @Override
    public AlienChild getById(int id) {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM AlienChildren WHERE id = " + id);
            if (rs.next()) {
                return parseResultSetToReturnChild(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void upsert(int id, AlienChild item) throws IOException{
        //insert or update at id
        // EXPLICATIA se afla in AlienSchoolDao
        String sqlInsert = "INSERT INTO AlienChildren (id, name, yearOfStudy, qwerty, qwertz, qzerty, azerty, dvorak, schoolID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlUpdate = "UPDATE AlienChildren SET name = ?, yearOfStudy = ?, qwerty = ?, qwertz = ?, qzerty = ?, azerty = ?, dvorak = ?, schoolID = ? WHERE id = ?";
        String sqlSelect = "SELECT * FROM AlienChildren WHERE id = ?";
        try {
            PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psSelect.setInt(1, id);

            ResultSet rs = psSelect.executeQuery();
            // daca EXISTA un ITEM CU ID-ul respectiv, atunci facem UPDATE
            if (rs.next()) {
                psUpdate.setString(1, item.getName());
                psUpdate.setInt(2, item.getYearOfStudy());
                psUpdate.setString(3, String.valueOf(item.getQwerty()));
                psUpdate.setString(4, String.valueOf(item.getQwertz()));
                psUpdate.setString(5, String.valueOf(item.getQzerty()));
                psUpdate.setString(6, String.valueOf(item.getAzerty()));
                psUpdate.setString(7, String.valueOf(item.getDvorak()));
                psUpdate.setInt(8, item.getschoolID());
                psUpdate.setInt(9, id);
                psUpdate.executeUpdate();
            }else {
                psInsert.setInt(1, id);
                psInsert.setString(2, item.getName());
                psInsert.setInt(3, item.getYearOfStudy());
                psInsert.setString(4, String.valueOf(item.getQwerty()));
                psInsert.setString(5, String.valueOf(item.getQwertz()));
                psInsert.setString(6, String.valueOf(item.getQzerty()));
                psInsert.setString(7, String.valueOf(item.getAzerty()));
                psInsert.setString(8, String.valueOf(item.getDvorak()));
                psInsert.setInt(9, item.getschoolID());
                psInsert.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void delete(int id) throws IOException {
        String sql = "DELETE FROM AlienChildren WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
