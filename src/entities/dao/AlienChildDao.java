package entities.dao;

import entities.AlienChild;
import interfaces.IDBOperations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlienChildDao implements IDBOperations<AlienChild> {

    private final String txtpath;

    public AlienChildDao(String txtpath) throws FileNotFoundException {
        this.txtpath = txtpath;
    }

    private AlienChild parseLineToReturnChild(String line){
        String[] words = line.split(" ");

        int id = Integer.parseInt(words[0]);

        String name = words[1];
        int yearOfStudy = Integer.parseInt(words[2]);
        char qwerty = words[3].charAt(0);
        char qwertz = words[4].charAt(0);
        char qzerty = words[5].charAt(0);
        char azerty = words[6].charAt(0);
        char dvorak = words[7].charAt(0);
        int planetId = Integer.parseInt(words[8]);

        AlienChild child = new AlienChild(id, name, yearOfStudy, qwerty, qwertz, qzerty, azerty, dvorak, planetId);



        return child;
    }
    @Override
    public List<AlienChild> fetchAll() {
        List<AlienChild> alienchildren = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(txtpath))){
            String line;
            while ((line = reader.readLine()) != null){
                alienchildren.add(parseLineToReturnChild(line));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return alienchildren;
    }

    @Override
    public AlienChild getById(int id) {

        try (BufferedReader reader = new BufferedReader(new FileReader(txtpath))){
            String line;
            while ((line = reader.readLine()) != null){
                if (Integer.parseInt(line.split(" ")[0]) == id){
                    AlienChild a = parseLineToReturnChild(line);
                    return a;

                }
            }
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return null;
    }

}
