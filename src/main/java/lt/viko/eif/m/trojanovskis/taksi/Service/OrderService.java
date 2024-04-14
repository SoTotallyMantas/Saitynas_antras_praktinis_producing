package lt.viko.eif.m.trojanovskis.taksi.Service;

import lt.viko.eif.m.trojanovskis.taksi.Database.OrdersRepository;
import lt.viko.eif.mantas.springsoap.gen.Order;
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
        OrderList orderDto = modelMapper.map(order, OrderList.class);
        return orderDto;
    }
    public OrderList findOrders(String firstName, String lastName) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getClient().getFirstName().equals(firstName) &&
                        order.getClient().getLastname().equals(lastName))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }

}
