package ca.bullseye.ims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Order;
import ca.bullseye.ims.repositories.EmployeeRepository;
import ca.bullseye.ims.repositories.OrderRepository;
import ca.bullseye.ims.repositories.ProductRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	
	//display all orders
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	//sort list of orders
	public List<Order> getAllOrders(Sort sort){
		return orderRepository.findAll(sort);
	}
	
	// pagination
	public Page<Order> getAllOrders(Pageable pageable){
		return orderRepository.findAll(pageable);
	}
	
	/*
	public Page<Order> getOrdersBySearchValue(String value, Pageable pageable){
		Order order = new Order();
		order.setOrderId(value);
	}
			*/

	
	
}
