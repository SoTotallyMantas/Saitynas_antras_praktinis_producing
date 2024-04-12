package lt.viko.eif.m.trojanovskis.taksi.Service;

import lt.viko.eif.m.trojanovskis.taksi.Database.OrdersRepository;
import lt.viko.eif.mantas.springsoap.gen.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

   // @Autowired
            private ModelMapper modelMapper;

   // public List<Order> list() {
   //     return OrdersRepository.findAll();
    //}
    private lt.viko.eif.mantas.springsoap.gen.Order convertToDto(Order order) {
        lt.viko.eif.mantas.springsoap.gen.Order orderDto = modelMapper.map(order, lt.viko.eif.mantas.springsoap.gen.Order.class);
        return orderDto;
    }
//    public lt.viko.eif.pv.springsoap.gen.Student findStudent(String name) {
//        Student student = this.list().stream()
//                .filter(st -> st.getFirstName().equals(name)).findFirst().get();
//        return convertToDto(student);
//    }

}
