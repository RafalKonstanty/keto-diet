package pl.ketodiet.app.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ketodiet.app.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User getUserByName(String username);

    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> isUserDuplicated(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.name =  :name AND u.password = :password")
    List<User> findUserByNameAndPassword(@Param("name") String name, @Param("password") String password);


    //    public boolean isLoginCorrect(User user) {
//        javax.persistence.Query query = entityManager.createQuery("SELECT u.name, u.password FROM User u WHERE  u.name = ?1 and  u.password= ?2");
//        query.setParameter(1, user.getName());
//        query.setParameter(2, user.getPassword());
//        List<User> checkList = query.getResultList();
//        if (checkList.isEmpty()) {
//            return false;
//        } else {
//            return true;
//        }
//    }


//    public int getIdByUserName(String userName) {
//        javax.persistence.Query query = entityManager.createQuery("SELECT u.id from User u WHERE u.name = ?1");
//        query.setParameter(1, userName);
//        return (int) query.getSingleResult();
//    }
//
//    public boolean isUserDuplicated(User user) {
//        javax.persistence.Query query = entityManager.createQuery("SELECT u.name FROM User u WHERE u.name = ?1");
//        query.setParameter(1, user.getName());
//        List<User> userList = query.getResultList();
//        if (userList.isEmpty()) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//


//    public User findUserByName(User user) {
//        javax.persistence.Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = ?1 ");
//        query.setParameter(1, user.getName());
//        User userTemp = (User) query.getSingleResult();
//        return userTemp;
//    }

    }
