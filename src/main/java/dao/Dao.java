package dao;

import java.util.List;

/**
 * Data Access Object generico
 */
public interface Dao<T> {
	List<T> getAll();

	void addData(T t);

	void saveAll(List<T> tList);

}
