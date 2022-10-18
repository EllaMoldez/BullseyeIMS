/*
 * package ca.bullseye.ims.services;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.data.domain.Example; import
 * org.springframework.data.domain.ExampleMatcher; import
 * org.springframework.data.domain.Page; import
 * org.springframework.data.domain.Pageable; import
 * org.springframework.data.domain.Sort; import
 * org.springframework.stereotype.Service;
 * 
 * import ca.bullseye.ims.model.Employee; import ca.bullseye.ims.model.Order;
 * import ca.bullseye.ims.model.Product; import
 * ca.bullseye.ims.repositories.EmployeeRepository; import
 * ca.bullseye.ims.repositories.OrderRepository; import
 * ca.bullseye.ims.repositories.ProductRepository;
 * 
 * @Service public class OrderService {
 * 
 * @Autowired OrderRepository orderRepository;
 * 
 * @Autowired private ProductRepository productRepository;
 * 
 * @Autowired private EmployeeRepository employeeRepository;
 * 
 * 
 * Display all supplier records public List<Order> getAllOrders() { return
 * orderRepository.findAll(); }
 * 
 * public List<Product> getAllProducts(){ return productRepository.findAll(); }
 * 
 * public List<Employee> getAllEmployees(){ return employeeRepository.findAll();
 * }
 * 
 * Sort supplier list public List<Order> getAllOrders(Sort sort) { return
 * orderRepository.findAll(sort); }
 * 
 * Pagination public Page<Order> getAllOrders(Pageable pageable) { return
 * orderRepository.findAll(pageable); }
 * 
 * public Page<Order> getOrdersBySearchValue(String value, Pageable pageable) {
 * Order order = new Order(); order.setoId(value); Employee employee = new
 * Employee(); employee.setEmpFirstName(value); employee.setEmpLastName(value);
 * employee.setEmpDepartment(value); order.setEmployee(employee);
 * 
 * ExampleMatcher.GenericPropertyMatcher propertyMatcher =
 * ExampleMatcher.GenericPropertyMatchers.contains() .ignoreCase();
 * ExampleMatcher exampleMatcher =
 * ExampleMatcher.matchingAny().withMatcher("oId", propertyMatcher)
 * .withMatcher("employee.setEmpFirstName",
 * propertyMatcher).withMatcher("employee.setEmpLastName", propertyMatcher)
 * .withMatcher("employee.setEmpDepartment", propertyMatcher); Example<Order>
 * orderExample = Example.of(order, exampleMatcher); return
 * this.orderRepository.findAll(orderExample, pageable); }
 * 
 * public Optional<Order> getOrderById(Long orderId) { return
 * orderRepository.findById(orderId); }
 * 
 * public Order saveOrder(Order order) { return orderRepository.save(order); }
 * 
 * public void deleteOrderById(Long orderId) {
 * orderRepository.deleteById(orderId); }
 * 
 * public boolean isOrderExists(Long orderId) { return
 * orderRepository.existsById(orderId); }
 * 
 * public long getTotalCount() { return orderRepository.count(); } }
 */