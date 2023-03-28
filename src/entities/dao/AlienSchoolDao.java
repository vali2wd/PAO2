package entities.dao;

import entities.AlienChild;
import entities.AlienSchool;
import interfaces.IDBOperations;
import util.LineReplacer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlienSchoolDao implements IDBOperations<AlienSchool> {
    public String getTxtpath() {
        return txtpath;
    }

    private final String txtpath;

    public AlienSchoolDao(String txtpath) {
        this.txtpath = txtpath;
    }

    private AlienSchool parseLineToReturnSchool(String line){
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
                alienschool.add(parseLineToReturnSchool(line));
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
        try (BufferedReader reader = new BufferedReader(new FileReader(txtpath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (Integer.parseInt(line.split(" ")[0]) == id) {
                    AlienSchool a = parseLineToReturnSchool(line);
                    reader.close();
                    return a;

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

        @Override
    public void upsert(int id, AlienSchool item) throws IOException{

    }

    @Override
    public void delete(int id) throws IOException {
        LineReplacer linereplacer =  new LineReplacer();
        try {
            linereplacer.removeLine(txtpath, id);
        }catch (IOException e){
            throw new IOException(e);
        }
    }
}
