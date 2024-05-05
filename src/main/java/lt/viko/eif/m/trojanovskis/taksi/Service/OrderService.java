package lt.viko.eif.m.trojanovskis.taksi.Service;

import lt.viko.eif.m.trojanovskis.taksi.Database.OrdersRepository;
import lt.viko.eif.m.trojanovskis.taksi.model.Order;
import lt.viko.eif.mantas.springsoap.gen.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.stream.Collectors;

/**
 *  OrderService class that is designated to
 *   perform operations on Order objects
 *    and communicate with OrdersRepository
 *    to perform CRUD operations
 *    on Order objects
 *    and convert Order objects to OrderList objects
 *
 *
 */
@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

             @Autowired
            private ModelMapper modelMapper;

    /**
     *  Method that returns all orders
     *  from the database
     *  and converts them to OrderList objects
     *
     * @return List of Order objects
     */
   public List<Order> list() {
        return ordersRepository.findAll();
    }

    /**
     *  Method that converts Order objects
     *  to OrderList objects
     *
     * @param order List of Order objects
     * @return OrderList object
     */
    private OrderList convertToDto(List<Order> order) {
        OrderList orderList = new OrderList();
        for (Order order1 : order) {
            lt.viko.eif.mantas.springsoap.gen.Order orderDto= (modelMapper.map(order1, lt.viko.eif.mantas.springsoap.gen.Order.class));
            orderList.getOrder().add(orderDto);
        }



            return orderList;
    }

    /**
     *  Method that returns client orders
     *  from the database
     *  and converts them to OrderList objects
     *
     * @param firstName String object
     * @param lastName String object
     * @return OrderList object
     */
    public OrderList findClientOrders(String firstName, String lastName) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getClient().getFirstname().equals(firstName) &&
                        order.getClient().getLastname().equals(lastName))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }

    /**
     *  Method that returns dispatch orders
     *  from the database
     *  and converts them to OrderList objects
     *
     * @param firstName String object
     * @param lastName String object
     * @return OrderList object
     */
    public OrderList findDispatchOrders(String firstName, String lastName) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getDispatch().getFirstname().equals(firstName) &&
                        order.getDispatch().getLastname().equals(lastName))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }

    /**
     *  Method that returns driver orders
     *  from the database
     *  and converts them to OrderList objects
     *
     * @param firstName String object
     * @param lastName String object
     * @return OrderList object
     */
    public OrderList findDriverOrders(String firstName, String lastName) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getDriver().getFirstname().equals(firstName) &&
                        order.getDriver().getLastname().equals(lastName))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }

    /**
     *  Method that returns driver plate orders
     *  from the database
     *  and converts them to OrderList objects
     *
     * @param LicensePlate String object
     * @return OrderList object
     */
    public OrderList findDriverPlateOrders(String LicensePlate) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getDriver().getLicenseplate().equals(LicensePlate))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }

    /**
     *  Method that returns dispatch number orders
     *  from the database
     *  and converts them to OrderList objects
     *
     * @param WorkNumber String object
     * @return OrderList object
     */
    public OrderList findDispatchNumberOrders(String WorkNumber) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getDispatch().getWorknumber().equals(WorkNumber))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }

}
