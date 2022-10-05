/*
 * package ca.bullseye.ims.controllers;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import ca.bullseye.ims.repositories.EmployeeRepository;
 * 
 * @Controller public class EmployeeController {
 * 
 * @Autowired private EmployeeRepository empRepository;
 * 
 * //method to go to login page
 * 
 * @GetMapping(path="/login") public String getLoginPage() { return "login"; }
 * 
 * //method to post login
 * 
 * @PostMapping(path="/login") public String submitLogin(LoginPage loginPage) {
 * Employee emp = null; if(null != loginPage) { emp = new
 * Employee(loginPage.get) } } }
 */