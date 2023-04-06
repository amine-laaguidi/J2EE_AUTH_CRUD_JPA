package dao;

import service.model.Student;
import service.model.User;

import javax.ejb.Local;
import javax.ejb.Remote;
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
@Local(UserDao.class)
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
       this.entityManager = emf.createEntityManager();
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
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email LIKE ?1 ",User.class);
        query.setParameter(1,email);
        try{
            return query.getSingleResult() !=null;
        }catch(NoResultException e){
            return false;
        }
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User auth(User user) throws Exception {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email LIKE  '" + user.getEmail() + "' AND u.password LIKE  '"+MD5(user.getPassword())+"'");
        return (User) query.getSingleResult();
    }
    @Override
    public void delete(Long id) {

    }
}
