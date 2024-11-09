package com.example.Ems;

import com.example.Ems.entity.Employee;
import com.example.Ems.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers // Annotation to enable Testcontainers support in JUnit
public class EmsBackendApplicationTests {
	@BeforeEach
	void resetDatabase() {
		employeeRepository.deleteAll(); // 清空所有员工数据
	}


	// Define a MySQLContainer to be shared and reused between test methods
	@Container
	public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.33")
			.withDatabaseName("testdb")
			.withUsername("testuser")
			.withPassword("testpass");

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
		assertThat(employeeRepository).isNotNull();
	}

	@Test
	void testCreateEmployee() throws InterruptedException {
		// Given
		Employee employee = new Employee();
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setEmail("8john.doe@example.com");
		employee.setCardId("123456789");

		// When
		Employee savedEmployee = employeeRepository.save(employee);

		// Then
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);
		assertThat(savedEmployee.getFirstName()).isEqualTo("John");
		assertThat(savedEmployee.getLastName()).isEqualTo("Doe");
		assertThat(savedEmployee.getEmail()).isEqualTo("8john.doe@example.com");

		// Add a delay to check if MySQL container still exists
		System.out.println("Sleeping for 30 seconds, check Docker containers...");
		Thread.sleep(120000); // Pause for 30 seconds

		// Check if MySQL container is still running
		System.out.println("MySQL container is running: " + mysqlContainer.isRunning());
	}
}
