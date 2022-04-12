package persist;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Page<Product> findByCostLessThan(BigDecimal maxPrice, Pageable pageable);

    Page<Product> findByCostGreaterThan(BigDecimal minPrice, Pageable pageable);

    Page<Product> findByCostBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

}