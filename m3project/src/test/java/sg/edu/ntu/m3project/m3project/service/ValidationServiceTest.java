package sg.edu.ntu.m3project.m3project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidationServiceTest {
  
  @Autowired ValidationService service;

  @Test
	public void givenNameIsEmpty_whenMethodIsCalled_thenThrowException() throws Exception {
    Exception exception = assertThrows(Exception.class, () -> {
			service.validateName("");
	});
		String expectedMessage = "Name cannot be empty";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void givenNameHasNumbers_whenMethodIsCalled_thenThrowException() throws Exception {
    Exception exception = assertThrows(Exception.class, () -> {
			service.validateName("123");
	});
		String expectedMessage = "Name can only contain letters and spaces";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
}
