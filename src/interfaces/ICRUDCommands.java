package interfaces;

// interface used for implementing in the Repositories. Final functionalities are stored there.
public interface ICRUDCommands<T> {
    void getAll();
    T getById(int id);
    void upsert(int id, T data);
    void delete(int id);

}
