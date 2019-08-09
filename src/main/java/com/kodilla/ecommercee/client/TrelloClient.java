package com.kodilla.ecommercee.client;


import com.kodilla.ecommercee.client.clientDto.TrelloOrder;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
public class TrelloClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderRepository orderRepository;

    public void updateOrder(long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        URI url = UriComponentsBuilder.fromHttpUrl("/cards/id")
                .queryParam("key", "8d1820034df65ac861bb93f0c68f555e")
                .queryParam("token", "b6281a584be1cf03743e3cbefee6719fabde07304d91f8a96e40c58578ae85b3")
                .queryParam("id", order.getTrelloCardId() + "")
                .queryParam("name", "Order ID:" + order.getId())
                .queryParam("desc", "Date: " + order.getDate() + "\n" +
                        "For User: " + order.getUser().getId() + "\n" +
                        "Status: " + order.getStatus()).encode().build().toUri();
        restTemplate.put(url, null);
        TrelloOrder resultTrelloOrder = getOrderFromTrello(order.getId());

        if(order.getStatus().equals(Order.IN_PROGRESS) && (resultTrelloOrder.getListId().equals("5d4d3da71a092c6120273bfc")
         || resultTrelloOrder.getListId().equals("5d4d4dd18fdfdd70d375c0ff"))) {
            deleteOrder(order.getId());
            addOrderToInProgressList(order.getId());
        } else if (order.getStatus().equals(Order.SEND) && (resultTrelloOrder.getListId().equals("5d4d3da71a092c6120273bfc")
        || resultTrelloOrder.getListId().equals("5d4d4dc2b7c993299d4fb9b9"))) {
            deleteOrder(order.getId());
            addOrderToSendList(order.getId());
        }else if (order.getStatus().equals(Order.AWAITING) && (resultTrelloOrder.getListId().equals("5d4d4dd18fdfdd70d375c0ff")
         || resultTrelloOrder.getListId().equals("5d4d4dc2b7c993299d4fb9b9"))) {
            deleteOrder(order.getId());
            addOrderToNewOrderList(order.getId());
        }
    }

    public void deleteOrder(long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        URI url = getEndPointForDeleteAndGetFromTrello(order);
        restTemplate.delete(url);
    }

    public TrelloOrder getOrderFromTrello(long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        URI url = getEndPointForDeleteAndGetFromTrello(order);
        return restTemplate.getForObject(url,TrelloOrder.class);
    }

    public URI getEndPointForDeleteAndGetFromTrello(Order order) {
       return UriComponentsBuilder.fromHttpUrl("/cards/id")
                .queryParam("key", "8d1820034df65ac861bb93f0c68f555e")
                .queryParam("token", "b6281a584be1cf03743e3cbefee6719fabde07304d91f8a96e40c58578ae85b3")
                .queryParam("id", order.getTrelloCardId() + "").encode().build().toUri();
    }

    public TrelloOrder addOrderToNewOrderList(long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        URI url = UriComponentsBuilder.fromHttpUrl("/cards")
                .queryParam("key", "8d1820034df65ac861bb93f0c68f555e")
                .queryParam("token", "b6281a584be1cf03743e3cbefee6719fabde07304d91f8a96e40c58578ae85b3")
                .queryParam("idList", "5d4d3da71a092c6120273bfc")
                .queryParam("name", "Order ID:" + order.getId())
                .queryParam("desc", "Date: " + order.getDate() + "\n" +
                        "For User: " + order.getUser().getId() + "\n" +
                        "Status: " + order.getStatus()).encode().build().toUri();

        return restTemplate.postForObject(url, null, TrelloOrder.class);
    }

    public TrelloOrder addOrderToInProgressList(long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        URI url = UriComponentsBuilder.fromHttpUrl("/cards")
                .queryParam("key", "8d1820034df65ac861bb93f0c68f555e")
                .queryParam("token", "b6281a584be1cf03743e3cbefee6719fabde07304d91f8a96e40c58578ae85b3")
                .queryParam("idList", "5d4d4dc2b7c993299d4fb9b9")
                .queryParam("name", "Order ID:" + order.getId())
                .queryParam("desc", "Date: " + order.getDate() + "\n" +
                        "For User: " + order.getUser().getId() + "\n" +
                        "Status: " + order.getStatus()).encode().build().toUri();

        return restTemplate.postForObject(url, null, TrelloOrder.class);
    }

    public TrelloOrder addOrderToSendList(long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        URI url = UriComponentsBuilder.fromHttpUrl("/cards")
                .queryParam("key", "8d1820034df65ac861bb93f0c68f555e")
                .queryParam("token", "b6281a584be1cf03743e3cbefee6719fabde07304d91f8a96e40c58578ae85b3")
                .queryParam("idList", "5d4d4dd18fdfdd70d375c0ff")
                .queryParam("name", "Order ID:" + order.getId())
                .queryParam("desc", "Date: " + order.getDate() + "\n" +
                        "For User: " + order.getUser().getId() + "\n" +
                        "Status: " + order.getStatus()).encode().build().toUri();

        return restTemplate.postForObject(url, null, TrelloOrder.class);
    }

}
