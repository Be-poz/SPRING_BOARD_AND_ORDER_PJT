package ksy.geshi.repository;

import ksy.geshi.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MemberEntity,String> {
}
