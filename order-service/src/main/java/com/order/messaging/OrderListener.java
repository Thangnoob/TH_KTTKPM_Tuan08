@Component
public class OrderListener {
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleOrderCreated(String message) {
        System.out.println("🟢 Nhận đơn hàng mới: " + message);
    }
}