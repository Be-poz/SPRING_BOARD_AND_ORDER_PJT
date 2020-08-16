package ksy.geshi.repository;

import ksy.geshi.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long>, BoardRepositoryCustom {
}
