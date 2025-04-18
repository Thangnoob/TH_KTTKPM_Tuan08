@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private String status;
    private LocalDateTime createdAt = LocalDateTime.now();
}