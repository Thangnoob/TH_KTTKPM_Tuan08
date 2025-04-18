@Entity
public class Customer { @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; private String name; private String email; private String phone; private String address; }