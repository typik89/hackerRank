package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PrimalityTest {
	
	@Test
	public void test() {
		assertFalse( Primality.isPrimality( 12 ) );
		assertTrue ( Primality.isPrimality( 5  ) );
		assertTrue ( Primality.isPrimality( 7  ) );
	}

}
