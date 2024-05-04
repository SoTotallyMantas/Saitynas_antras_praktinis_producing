package lt.viko.eif.m.trojanovskis.taksi.Service;

import lt.viko.eif.m.trojanovskis.taksi.Database.OrdersRepository;
import lt.viko.eif.m.trojanovskis.taksi.model.Order;
import lt.viko.eif.mantas.springsoap.gen.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

             @Autowired
            private ModelMapper modelMapper;


   public List<Order> list() {
        return ordersRepository.findAll();
    }
    private OrderList convertToDto(List<Order> order) {
        OrderList orderList = new OrderList();
        for (Order order1 : order) {
            lt.viko.eif.mantas.springsoap.gen.Order orderDto= (modelMapper.map(order1, lt.viko.eif.mantas.springsoap.gen.Order.class));
            orderList.getOrder().add(orderDto);
        }



            return orderList;
    }

    public OrderList findClientOrders(String firstName, String lastName) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getClient().getFirstname().equals(firstName) &&
                        order.getClient().getLastname().equals(lastName))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }
    public OrderList findDispatchOrders(String firstName, String lastName) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getDispatch().getFirstname().equals(firstName) &&
                        order.getDispatch().getLastname().equals(lastName))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }
    public OrderList findDriverOrders(String firstName, String lastName) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getDriver().getFirstname().equals(firstName) &&
                        order.getDriver().getLastname().equals(lastName))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }
    public OrderList findDriverPlateOrders(String LicensePlate) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getDriver().getLicenseplate().equals(LicensePlate))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }
    public OrderList findDispatchNumberOrders(String WorkNumber) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getDispatch().getWorknumber().equals(WorkNumber))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }

}
