package tpi.dgrv4.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpi.dgrv4.entity.entity.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, String> {

}
