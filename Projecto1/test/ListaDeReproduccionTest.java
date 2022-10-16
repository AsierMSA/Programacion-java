import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class ListaDeReproduccionTest {
	private ListaDeReproduccion lr1;
	private ListaDeReproduccion lr2;
	private final File FIC_TEST1 = new File( "test/res/No del grupo.mp4" );
	
	@Before
	public void setUp() throws Exception {
	lr1 = new ListaDeReproduccion();
	lr2 = new ListaDeReproduccion();
	lr2.add( FIC_TEST1 );
	}
	
	@After
	 public void tearDown() {
	lr2.clear();
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

	// Chequeo de error por getFic(�ndice) por encima de final
	@Test(expected = IndexOutOfBoundsException.class) 
	public void testGet_Exc1() { 
	lr1.getFic(0); // Debe dar error porque a�n no existe la posici�n 0
	} 

	// Chequeo de error por get(�ndice) por debajo de 0
	@Test(expected = IndexOutOfBoundsException.class) 
	public void testGet_Exc2() { 
	lr2.getFic(-1); // Debe dar error porque a�n no existe la posici�n -1
	} 
	// Chequeo de funcionamiento correcto de get(�ndice)
	@Test public void testGet() {
	assertEquals( FIC_TEST1, lr2.getFic(0) ); // El �nico dato es el fic-test1
	}
}
