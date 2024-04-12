package lt.viko.eif.m.trojanovskis.taksi.endpoint;

import lt.viko.eif.mantas.springsoap.gen.GetClientOrdersRequest;
import lt.viko.eif.mantas.springsoap.gen.GetClientOrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class OrdersEndpoint {
    private static final String NAMESPACE_URI = "http://eif.viko.lt/mantas/springsoap/gen";
    private OrderService orderService;

    @Autowired
    public OrdersEndpoint(OrderService countryRepository) {
        this.OrderService = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
    @ResponsePayload
    public GetClientOrdersResponse getOrder(@RequestPayload GetClientOrdersRequest request) {
        GetClientOrdersResponse response = new GetClientOrdersResponse();
        response.setOrderList(OrderService.findOrders(request.getFirstName(),request.getLastName()));

        return response;
    }

}