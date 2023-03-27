package budget.control.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import budget.control.api.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	UserDetails findByLogin(String username);

}
