package dao;

import service.model.Student;
import service.model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public String MD5(String s) throws Exception {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());
        return new BigInteger(1,m.digest()).toString(16);
    }
    @Override
    public User save(User user) {
        Connection connection = SingletonConnection.getConnection();
        try{
            PreparedStatement ps= connection.prepareStatement
                    ("INSERT INTO USERS (FNAME,EMAIL,PASSWORD) VALUES (?,?,?)");
            ps.setString(1,user.getFname());
            ps.setString(2,user.getEmail());
            ps.setString(3,MD5(user.getPassword()));
            ps.executeUpdate();
            PreparedStatement ps2=connection.prepareStatement
                    ("SELECT MAX(ID) AS MAX_ID FROM USERS");
            ResultSet rs=ps2.executeQuery();
            if(rs.next()){
                user.setId(rs.getLong("MAX_ID")); // définir le id du produit inséré
            }
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> studentsByFullname(String fullname) {

        return null;
    }

    @Override
    public boolean emailExist(String email) {

        Connection connection = SingletonConnection.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT * FROM USERS WHERE EMAIL LIKE ?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User auth(User user) {
        Connection connection = SingletonConnection.getConnection();
        User user1 = null;
        try {
            System.out.println( MD5(user.getPassword()));
            System.out.println(user.getEmail());
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?");
            ps.setString(1,   user.getEmail());
            ps.setString(2,   MD5(user.getPassword()));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user1 = new User();
                user1.setId((long)rs.getInt("ID"));
                user1.setEmail(rs.getString("EMAIL"));
                user1.setFname(rs.getString("FNAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user1;
    }
    @Override
    public void delete(Long id) {

    }
}
