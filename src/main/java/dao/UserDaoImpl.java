package dao;

import service.model.Student;
import service.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;
    private EntityManagerFactory emf;
    public UserDaoImpl(){
        this.emf = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public String MD5(String s) throws Exception {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());
        return new BigInteger(1,m.digest()).toString(16);
    }
    @Override
    public User save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> studentsByFullname(String fullname) {

        return null;
    }

    @Override
    public boolean emailExist(String email) {
        Query query = entityManager.createQuery("SELECT * FROM USER WHERE EMAIL LIKE ? '" + email + "'");
        if( query.getSingleResult() !=null)
            return true;
        return false;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User auth(User user) {
        return null;
    }
    @Override
    public void delete(Long id) {

    }
}
