package repository;

import entities.AlienSchool;
import entities.dao.AlienSchoolDao;
import interfaces.ICRUDCommands;

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
        System.out.println("!");
        return null;
    }

    @Override
    public void upsert(int id, AlienSchool data) {
        System.out.println("!");
    }

    @Override
    public void delete(int id) {
        System.out.println("!");
    }
}
