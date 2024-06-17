package songs.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import songs.models.Song;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class SongService implements ISongService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Song> findAll() {
        String queryStr = "SELECT s FROM Song s";
        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
        return query.getResultList();
    }

    @Override
    public void save(Song song) {
        Transaction transaction = null;
        Song origin;
        if (song.getId() == 0){
            origin = new Song();
        } else {
            origin = findById(song.getId());
        }
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setName(song.getName());
            origin.setArtist(song.getArtist());
            origin.setDescription(song.getDescription());
            origin.setLinkSong(song.getLinkSong());
            session.saveOrUpdate(song);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(int id) {
        Song song = findById(id);
        if (song != null){
            Transaction transaction = null;
            try(Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.delete(song);
                transaction.commit();
            } catch (Exception e){
                e.printStackTrace();
                if (transaction!= null) {
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public Song findById(int id) {
        String queryStr = "select s from Song as s  where s.id = :id";
        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
