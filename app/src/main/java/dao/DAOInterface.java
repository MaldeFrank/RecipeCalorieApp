package dao;

import java.util.List;

public interface DAOInterface<Object> {
    public List<Object> getAll();
    public Object getById(int id);
    public Object create(Object object);
    public void delete(Object object);
}
