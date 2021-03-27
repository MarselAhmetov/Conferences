package team404.conference.general.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface SimpleDao {
    <T> Optional<T> findById(Class<T> entityClass, Serializable id);

    <T> Serializable save(T entity);

    <T> void saveOrUpdate(T entity);

    <T> void update(T entity);

    <T> void delete(T entity);

    void flush();

    <T> List<T> findAll(Class<T> entityClass);
}
