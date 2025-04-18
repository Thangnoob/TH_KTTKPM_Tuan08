@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired private CustomerRepository repo;
    @PostMapping public Customer create(@RequestBody Customer c) { return repo.save(c); }
    @GetMapping public List<Customer> getAll() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Customer> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}") public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer c) {
        return repo.findById(id).map(customer -> {
            customer.setName(c.getName());
            customer.setEmail(c.getEmail());
            customer.setPhone(c.getPhone());
            customer.setAddress(c.getAddress());
            return ResponseEntity.ok(repo.save(customer));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}