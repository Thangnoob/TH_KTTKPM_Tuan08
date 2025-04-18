@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired private OrderRepository repo;
    @Autowired private AmqpTemplate amqpTemplate;
    @PostMapping
    public Order create(@RequestBody Order o) throws JsonProcessingException {
        Order saved = repo.save(o);
        amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, new ObjectMapper().writeValueAsString(saved));
        return saved;
    }
    @GetMapping public List<Order> getAll() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Order> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}