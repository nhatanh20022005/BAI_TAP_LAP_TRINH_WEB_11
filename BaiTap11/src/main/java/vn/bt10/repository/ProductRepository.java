package vn.bt10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bt10.entity.Product;

import java.util.List;

/**
 * Repository for {@link Product} entities.  Includes finder methods to
 * retrieve products by category or user.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByUserId(Long userId);
}