package lt.viko.eif.m.trojanovskis.taksi.Database;


import lt.viko.eif.m.trojanovskis.taksi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Represents OrdersRepository Object that extends JpaRepository
 * This class is designated to communicate with database
 * and perform CRUD operations
 * on Order objects
 */
@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {


}
