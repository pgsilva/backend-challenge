package com.itidigital.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServletInitializerTest {

	@Mock
	private SpringApplicationBuilder springApplicationBuilder;

	@Test
	public void main() {
		ServletInitializer servletInitializer = new ServletInitializer();
		when(springApplicationBuilder.sources(ChallengeApplication.class))
				.thenReturn(springApplicationBuilder);

		SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

		verify(springApplicationBuilder).sources(ChallengeApplication.class);
		assertEquals(springApplicationBuilder, result);
	}
}
