package bricks;

/**
 * Order class corresponding to a simple order of bricks
 */
public class Order {
    // Unique order reference
    private final long id;
    // Number of bricks ordered
    private long amount;
    // Current order status
    private OrderStatus status;

    /**
     * Order class constructor
     * @param id     Unique order reference
     * @param amount Number of bricks ordered
     * @param status Current order status
     */
    public Order(long id, long amount, OrderStatus status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }

    /**
     * Get the current order id
     * @return The current order id
     */
    public long getId() {
        return id;
    }

    /**
     * Get the current order amount
     * @return The current order amount
     */
    public long getAmount() {
        return amount;
    }

    /**
     * Get the current order status
     * @return The current order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Update the order amount
     * @param amount The new order amount
     * @exception BadRequestException If the current order status is CANCELLED or SHIPPED
     */ 
    public void setAmount(long amount) throws BadRequestException {
        // Only allow amount to be changed for NEW orders
        if (status == OrderStatus.NEW) {
            this.amount = amount;
        }
        else {
            throw new BadRequestException("Unable to update amount on " + status + " order");
        }
    }

    /**
     * Update the order status
     * @param status The new order status
     * @exception BadRequestException If the current order status is CANCELLED or SHIPPED
     */ 
    public void setStatus(OrderStatus status) throws BadRequestException {
        // Only allow status changes for NEW orders
        if (this.status == OrderStatus.NEW) {
            this.status = status;
        }
        else {
            throw new BadRequestException("Unable to update status of " + this.status + " order");
        }
    }
}

