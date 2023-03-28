package repository;

import entities.AlienChild;
import entities.dao.AlienChildDao;
import interfaces.ICRUDCommands;

public class AlienChildCommandsRepository implements ICRUDCommands<AlienChild> {

    private AlienChildDao _context;

    public AlienChildCommandsRepository(AlienChildDao _context) {
        this._context = _context;
    }

    @Override
    public void getAll() {
        for (AlienChild child: _context.fetchAll()) {
            System.out.println(child);

        }
    }

    @Override
    public AlienChild getById(int id) {
        return _context.getById(id);
    }

    @Override
    public void upsert(int id, AlienChild data) {
        System.out.println("!");
    }

    @Override
    public void delete(int id) {
        System.out.println("!");
    }

}
