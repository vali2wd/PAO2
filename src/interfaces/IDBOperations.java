package interfaces;

import java.io.IOException;
import java.util.List;

// interface used for implementing DAOs.
public interface IDBOperations<T> {
    List<T> fetchAll() throws IOException;

    T getById(int id);

}
