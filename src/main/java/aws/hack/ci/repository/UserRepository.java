package aws.hack.ci.repository;

import aws.hack.ci.domain.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface UserRepository extends CrudRepository<AppUser,Long>{

}
