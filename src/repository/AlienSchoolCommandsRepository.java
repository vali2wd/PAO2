package repository;

import entities.AlienSchool;
import entities.dao.AlienSchoolDao;
import interfaces.ICRUDCommands;
import util.LineReplacer;

import java.io.IOException;

public class AlienSchoolCommandsRepository implements ICRUDCommands<AlienSchool> {
    private AlienSchoolDao _context;

    public AlienSchoolCommandsRepository(AlienSchoolDao _context) {
        this._context = _context;
    }

    @Override
    public void getAll() {
        for (AlienSchool school: _context.fetchAll()){
            System.out.println(school);
        }
    }

    @Override
    public AlienSchool getById(int id) {
        return _context.getById(id);
    }

    @Override
    public void upsert(int id, AlienSchool alienschool) {

    }

    @Override
    public void delete(int id) throws IOException {
        _context.delete(id);
    }
}
