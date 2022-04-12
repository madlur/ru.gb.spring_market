package persist;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    List<User> findByLogin(String login);

    List<User> findByLoginLike(String loginPattern);
}