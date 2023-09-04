package sn.ept.git.seminaire.cicd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class TodoApplicationTests {

	@Autowired
	ApplicationContext ctx;

	@Test
	void contextLoads() {
		assertThat(ctx).isNotNull();
	}

	@Test
	public void mainMethodStartsApplication(CapturedOutput output) {
		// Capture la sortie standard lors de l'exécution de la méthode main
		TodoApplication.main(new String[]{});

		assertTrue(output.toString().contains("Started TodoApplication"));
	}

}
