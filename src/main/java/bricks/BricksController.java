package bricks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BricksController {

    private final AtomicLong counter = new AtomicLong();
    private static final Map<Long, Order> orders = new HashMap<Long, Order>();

    /**
     * List detail of a single order
     * @param id Unique order reference
     * @return Returns the order details (id, amount, status)
     */
    @RequestMapping(value="/order", method=RequestMethod.GET)
    public Order getOrder(@RequestParam(value="id", required=false) Long id) {
        return orders.get(id);
    }

    /**
     * List detail of all orders
     * @return Returns a list containing the details of all orders [ (id, amount, status) ]
     */
    @RequestMapping(value="/orders", method=RequestMethod.GET)
    public List<Order> getOrders() {
        List<Order> result = new ArrayList<Order>();
        for (Map.Entry<Long, Order> entry : orders.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * Create a new order
     * @param amount The number of bricks ordered
     * @return Returns the order details (id, amount, status)
     */
    @RequestMapping(value="/order", method=RequestMethod.POST)
    public Order createOrder(@RequestParam(value="amount", required=true) Long amount) {
        Order result = new Order(counter.incrementAndGet(), amount, OrderStatus.NEW);
        orders.put(result.getId(), result);
        return result;
    }

    /**
     * Update an existing order
     * @param id     The unique order reference
     * @param amount The updated number of bricks
     * @return Returns the updated order details (id, amount, status)
     */
    @RequestMapping(value="/order", method=RequestMethod.PUT)
    public Order updateOrder(@RequestParam(value="id", required=true) Long id,
                             @RequestParam(value="amount", required=true) Long amount)
                 throws BadRequestException {
        Order order = orders.get(id);
        if (order != null) {
            order.setAmount(amount);
        }
        return order;
    }

    /**
     * Cancel an existing order
     * @param id The unique order reference
     * @return Returns the cancelled order details (id, amount, status)
     */
    @RequestMapping(value="/order", method=RequestMethod.DELETE)
    public Order cancelOrder(@RequestParam(value="id", required=true) Long id)
                 throws BadRequestException {
        Order order = orders.get(id);
        if (order != null) {
            order.setStatus(OrderStatus.CANCELLED);
        }
        return order;
    }

    /**
     * Fulfill an existing order
     * @param id The unique order reference
     * @return Returns the fulfilled order details (id, amount, status)
     */
    @RequestMapping(value="/order/fulfill", method=RequestMethod.GET)
    public Order fulfillOrder(@RequestParam(value="id", required=false) Long id)
                 throws BadRequestException {
        Order order = orders.get(id);
        if (order == null) {
            throw new BadRequestException("Invalid order reference: " + id.toString());
        }
        order.setStatus(OrderStatus.SHIPPED);
        return order;
    }
}

