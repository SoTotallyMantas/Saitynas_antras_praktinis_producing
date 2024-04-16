package lt.viko.eif.m.trojanovskis.taksi.endpoint;

import lt.viko.eif.m.trojanovskis.taksi.Service.OrderService;
import lt.viko.eif.mantas.springsoap.gen.GetClientOrdersRequest;
import lt.viko.eif.mantas.springsoap.gen.GetClientOrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class OrdersEndpoint {
    private static final String NAMESPACE_URI = "http://lt.viko.eif/mantas/springsoap/gen";
    private OrderService orderService;

    @Autowired
    public OrdersEndpoint(OrderService ordersRepository) {
        this.orderService = ordersRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientOrdersRequest")
    @ResponsePayload
    public GetClientOrdersResponse getOrder(@RequestPayload GetClientOrdersRequest request) {
        GetClientOrdersResponse response = new GetClientOrdersResponse();
        response.setOrderList(orderService.findOrders(request.getFirstName(),request.getLastName()));
        return response;
    }

}
