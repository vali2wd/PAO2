package interfaces;

import java.io.IOException;

// interface used for implementing in the Repositories. Final functionalities are stored there.
public interface ICRUDCommands<T> {
    void getAll();
    T getById(int id);
    void upsert(int id, T item) throws IOException;
    void delete(int id) throws IOException;

}
