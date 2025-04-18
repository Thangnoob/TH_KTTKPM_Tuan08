@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired private ProductRepository repo;
    @PostMapping public Product create(@RequestBody Product p) { return repo.save(p); }
    @GetMapping public List<Product> getAll() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Product> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}") public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product p) {
        return repo.findById(id).map(product -> {
            product.setName(p.getName());
            product.setPrice(p.getPrice());
            product.setDescription(p.getDescription());
            product.setStock(p.getStock());
            return ResponseEntity.ok(repo.save(product));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}