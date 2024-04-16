package lt.viko.eif.m.trojanovskis.taksi.Database;


import lt.viko.eif.m.trojanovskis.taksi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {


}
