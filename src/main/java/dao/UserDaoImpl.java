package dao;

import service.model.Student;
import service.model.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Stateless
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
    public User save(User user) throws Exception {
        entityManager.getTransaction().begin();
        user.setPassword(MD5(user.getPassword()));
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> usersByFullname(String fullname) {
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
        return entityManager.find(User.class, id);
    }

    @Override
    public User auth(User user) throws Exception {
        Query query = entityManager.createQuery("SELECT * FROM USER WHERE EMAIL LIKE ? '" + user.getEmail() + "' AND PASSWORD LIKE ? '"+MD5(user.getPassword())+"'");
        return (User) query.getSingleResult();
    }
    @Override
    public void delete(Long id) {

    }
}
