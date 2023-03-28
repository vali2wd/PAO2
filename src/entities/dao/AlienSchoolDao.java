package entities.dao;

import entities.AlienSchool;
import interfaces.IDBOperations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlienSchoolDao implements IDBOperations<AlienSchool> {
    private final String txtpath;

    public AlienSchoolDao(String txtpath) {
        this.txtpath = txtpath;
    }

    private AlienSchool parseLine(String line){
        String[] words = line.split(" ");
        String name = words[1];
        int id = Integer.parseInt(words[0]);

        return new AlienSchool(id, name);
    }
    @Override
    public List<AlienSchool> fetchAll()  {
        List<AlienSchool> alienschool = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(txtpath))){
            String line;
            while ((line = reader.readLine()) != null){
                alienschool.add(parseLine(line));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return alienschool;
    }

    @Override
    public AlienSchool getById(int id) {
        return null;
    }
}
