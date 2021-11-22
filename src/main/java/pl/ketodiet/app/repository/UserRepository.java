package pl.ketodiet.app.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ketodiet.app.model.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity getUserByName(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.name = :name")
    List<UserEntity> isUserDuplicated(@Param("name") String name);

    @Query("SELECT u FROM UserEntity u WHERE u.name =  :name AND u.password = :password")
    List<UserEntity> findUserByNameAndPassword(@Param("name") String name, @Param("password") String password);


    //    public boolean isLoginCorrect(UserEntity userEntity) {
//        javax.persistence.Query query = entityManager.createQuery("SELECT u.name, u.password FROM UserEntity u WHERE  u.name = ?1 and  u.password= ?2");
//        query.setParameter(1, userEntity.getName());
//        query.setParameter(2, userEntity.getPassword());
//        List<UserEntity> checkList = query.getResultList();
//        if (checkList.isEmpty()) {
//            return false;
//        } else {
//            return true;
//        }
//    }


//    public int getIdByUserName(String userName) {
//        javax.persistence.Query query = entityManager.createQuery("SELECT u.id from UserEntity u WHERE u.name = ?1");
//        query.setParameter(1, userName);
//        return (int) query.getSingleResult();
//    }
//
//    public boolean isUserDuplicated(UserEntity userEntity) {
//        javax.persistence.Query query = entityManager.createQuery("SELECT u.name FROM UserEntity u WHERE u.name = ?1");
//        query.setParameter(1, userEntity.getName());
//        List<UserEntity> userList = query.getResultList();
//        if (userList.isEmpty()) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//


//    public UserEntity findUserByName(UserEntity userEntity) {
//        javax.persistence.Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.name = ?1 ");
//        query.setParameter(1, userEntity.getName());
//        UserEntity userTemp = (UserEntity) query.getSingleResult();
//        return userTemp;
//    }

    }
