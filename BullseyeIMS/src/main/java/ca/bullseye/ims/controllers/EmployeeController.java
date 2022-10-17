package ca.bullseye.ims.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import ca.bullseye.ims.model.Employee;

import ca.bullseye.ims.services.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private static List<String> empDepartment;
	static {
		empDepartment = new ArrayList<>();
		empDepartment.add("Regional");
		empDepartment.add("Finance");
		empDepartment.add("Warehouse");
		empDepartment.add("Store");
		empDepartment.add("Transportation");
	}

	private static List<String> empJobRole;
	static {
		empJobRole = new ArrayList<>();
		empJobRole.add("Manager");
		empJobRole.add("Asst. Manager");
		empJobRole.add("Staff");
		empJobRole.add("Delivery Driver");
	}

	@GetMapping("/employee")
	public String getEmployeePage(@RequestParam(value = "empId", required = false) Long empId,
								  @RequestParam(value = "search", required = false) String search,
								  @RequestParam(defaultValue = "empFirstName") String sort, @RequestParam(defaultValue = "1") int page,
			                      @RequestParam(defaultValue = "15") int size, 
			                      @RequestParam(defaultValue = "asc") String direction,
			                      Model model) {
		if (empId != null) {
			Employee employee = employeeService.getEmployeeById(empId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
			model.addAttribute("employee", employee);
			return "employee-info";
		}

		Sort.Order sortOrder = new Sort.Order(Sort.Direction.fromString(direction), sort).ignoreCase();
		PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortOrder));

		Page<Employee> employeePage;
		if (search != null && !search.isBlank()) {
			employeePage = employeeService.getEmployeesBySearchValue(search, pageRequest);
		} else {
			employeePage = employeeService.getAllEmployees(pageRequest);
		}
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);
		model.addAttribute("totalPages", employeePage.getTotalPages());
		model.addAttribute("employee", employeePage.getContent());
		model.addAttribute("empDepartment", empDepartment);
		model.addAttribute("empJobRole", empJobRole);
		return "employee";
	}

	@GetMapping("/addEmployee")
	public String getAddEmployeePage(Model model) {
		model.addAttribute("pageTitle", "Create Employee");
		model.addAttribute("employee", new Employee());
		model.addAttribute("empDepartment", empDepartment);
		model.addAttribute("empJobRole", empJobRole);
		return "employee-edit";
	}

	@GetMapping("/updateEmployee")
	public String getUpdateEmployeePage(@RequestParam Long empId, Model model) {
		Employee existingEmployee = employeeService.getEmployeeById(empId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found."));
		model.addAttribute("pageTitle", "Edit Employee");
		model.addAttribute("employee", existingEmployee);
		model.addAttribute("empDepartment", empDepartment);
		model.addAttribute("empJobRole", empJobRole);
		return "employee-edit";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute Employee employee, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("pageTitle", (employee.getEmpId() != null ? "Update" : "Create") + " Employee");
			model.addAttribute("empDepartment", empDepartment);
			model.addAttribute("empJobRole", empJobRole);
			return "employee-edit";
		} else {
			employeeService.saveEmployee(employee);
			return "redirect:/employee";
		}
	}

	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long empId, Model model) {
		if (!employeeService.isEmployeeExists(empId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found.");
		}

		employeeService.deleteEmployeeById(empId);
		return "redirect:/employee";
	}

	/*
	 * // display list of employee
	 * 
	 * @RequestMapping(value = "/employee") public String viewEmployeeList(Model
	 * model) { List<Employee> employeeList = employeeService.getAllEmployees();
	 * model.addAttribute("employeeList", employeeList);
	 * 
	 * return "employeelist"; }
	 * 
	 * // show new or create employee page
	 * 
	 * @RequestMapping(value = "/employee/create", method = RequestMethod.GET)
	 * public String newEmployee(Model model) {
	 * 
	 * // create model attribute to bind form data Employee employee = new
	 * Employee(); model.addAttribute(employee);
	 * 
	 * model.addAttribute("empDepartment", empDepartment);
	 * model.addAttribute("empJobRole", empJobRole);
	 * 
	 * return "employee_new"; }
	 * 
	 * // save employee to database with error validation
	 * 
	 * @RequestMapping(value = "/employee/add", method = RequestMethod.POST) public
	 * String addNewEmployee(@ModelAttribute @Valid Employee employee, BindingResult
	 * result) { if (result.hasErrors()) { return "employee_new"; }
	 * employeeService.addNewEmployee(employee); return "redirect:/employee"; }
	 * 
	 * // creating a get mapping that retrieves the detail of a specific employee
	 * 
	 * @GetMapping(path = "/employee/edit/{empId}") public String
	 * editEmployee(@PathVariable(name = "empId") Long empId, Model model) { // get
	 * employee from the service Employee employee =
	 * employeeService.getEmployeeById(empId);
	 * 
	 * // set employee as a model attribute to pre-populate the form
	 * model.addAttribute("employee", employee); model.addAttribute("empDepartment",
	 * empDepartment); model.addAttribute("empJobRole", empJobRole); return
	 * "/employee_edit"; }
	 * 
	 * // save updated employee record
	 * 
	 * @RequestMapping(value="/employee/update", method = RequestMethod.POST) public
	 * String updateEmployee(@ModelAttribute @Valid Employee employee, BindingResult
	 * result) { if (result.hasErrors()) { return "employee_edit"; }
	 * employeeService.updateEmployee(employee); return "redirect:/employee"; }
	 * 
	 * // creating a delete mapping that deletes a specific employee
	 * 
	 * @RequestMapping(value = "/employee/delete/{empId}", method = {
	 * RequestMethod.DELETE, RequestMethod.GET }) private String
	 * deleteEmployee(@PathVariable(name = "empId") Long empId) { // call delete
	 * employee method employeeService.deleteEmployee(empId); return
	 * "redirect:/employee"; }
	 * 
	 * 
	 * // pagination and sorting
	 * 
	 * @GetMapping(path = "/page/{pageNo}") public String
	 * findPaginated(@PathVariable(value = "pageNo") int
	 * pageNo, @RequestParam("sortFieldEmp") String sortFieldEmp,
	 * 
	 * @RequestParam("sortDirectionEmp") String sortDirectionEmp, Model model) { int
	 * pageSize = 6;
	 * 
	 * Page<Employee> pageEmp = employeeService.findPaginated(pageNo, pageSize,
	 * sortFieldEmp, sortDirectionEmp); List<Employee> employeeList =
	 * pageEmp.getContent();
	 * 
	 * model.addAttribute("currentPage", pageNo); model.addAttribute("totalPages",
	 * pageEmp.getTotalPages()); model.addAttribute("totalItems",
	 * pageEmp.getTotalElements());
	 * 
	 * model.addAttribute("sortFieldEmp", sortFieldEmp);
	 * model.addAttribute("sortDirection", sortDirectionEmp);
	 * model.addAttribute("reverseSortDirection", sortDirectionEmp.equals("asc") ?
	 * "desc" : "asc");
	 * 
	 * model.addAttribute("employeeList", employeeList);
	 * 
	 * return "employeelist"; }
	 */

}
