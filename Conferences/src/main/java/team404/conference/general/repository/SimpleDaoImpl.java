package team404.conference.general.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class SimpleDaoImpl extends HibernateDaoSupport implements SimpleDao {

    @Resource(type = SessionFactory.class)
    protected void configure(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public <T> Optional<T> findById(Class<T> entityClass, Serializable id) {
        return Optional.ofNullable(getHibernateTemplate().execute(new HibernateCallback<T>() {
            @Override
            public T doInHibernate(Session session) throws HibernateException {
                return session.find(entityClass, id);
            }
        }));
    }
    @Override
    public <T> Serializable save(T entity) {
        return getHibernateTemplate().save(entity);
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public <T> void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public <T> void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public void flush() {
        getHibernateTemplate().flush();
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        return (List<T>) getHibernateTemplate()
                .findByCriteria(DetachedCriteria.forClass(entityClass));
    }
}
