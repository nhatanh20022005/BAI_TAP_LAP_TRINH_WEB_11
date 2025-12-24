package vn.bt10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bt10.entity.Category;

/**
 * Repository for {@link Category} entities.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}