package lt.viko.eif.m.trojanovskis.taksi.endpoint;

import lt.viko.eif.m.trojanovskis.taksi.Service.OrderService;

import lt.viko.eif.mantas.springsoap.gen.*;
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
    public GetClientOrdersResponse getClientOrder (@RequestPayload GetClientOrdersRequest request) {
        GetClientOrdersResponse response = new GetClientOrdersResponse();
        response.setOrderList(orderService.findClientOrders(request.getFirstName(),request.getLastName()));
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDispatchOrdersRequest")
    @ResponsePayload
    public GetDispatchOrdersResponse getDispatchOrder(@RequestPayload GetDispatchOrdersRequest request) {

        GetDispatchOrdersResponse response = new GetDispatchOrdersResponse();
        response.setOrderList(orderService.findDispatchOrders(request.getFirstName(),request.getLastName()));
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDispatchNumberOrdersRequest")
    @ResponsePayload
    public GetDispatchNumberOrdersResponse getDispatchNumberOrder(@RequestPayload GetDispatchNumberOrdersRequest request) {
        GetDispatchNumberOrdersResponse response = new GetDispatchNumberOrdersResponse();
        response.setOrderList(orderService.findDispatchNumberOrders(request.getWorkNumber()));
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDriverOrdersRequest")
    @ResponsePayload
    public GetDriverOrdersResponse getDriverOrder(@RequestPayload GetDriverOrdersRequest request) {
        GetDriverOrdersResponse response = new GetDriverOrdersResponse();
        response.setOrderList(orderService.findDriverOrders(request.getFirstName(),request.getLastName()));
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDriverPlateOrdersRequest")
    @ResponsePayload
    public GetDriverPlateOrdersResponse getDriverPlateOrder(@RequestPayload GetDriverPlateOrdersRequest request) {
        GetDriverPlateOrdersResponse response = new GetDriverPlateOrdersResponse();
        response.setOrderList(orderService.findDriverPlateOrders(request.getLicensePlate()));
        return response;
    }

}
