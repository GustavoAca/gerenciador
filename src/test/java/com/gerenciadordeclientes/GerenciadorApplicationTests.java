package com.gerenciadordeclientes;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = GerenciadorApplication.class)
class GerenciadorApplicationTests {

	@Test
	void contextLoads() {
	}

}
