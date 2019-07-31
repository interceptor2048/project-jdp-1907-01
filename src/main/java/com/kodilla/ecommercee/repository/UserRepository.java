package com.kodilla.ecommercee.repository;
import com.kodilla.ecommercee.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
<<<<<<< 1b54d12d9e6c01f114e5178ee191c92e5e1c06ed
import org.springframework.stereotype.Repository;
=======
import org.springframework.data.repository.query.Param;

>>>>>>> Refactoring, added relations beetwen user and order , add new method to user reposotory,add unit test for this new function
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    User save(User user);

    @Override
    void deleteById(Long id);

    @Query
    User returnUserById(@Param("param")  long id);
}
