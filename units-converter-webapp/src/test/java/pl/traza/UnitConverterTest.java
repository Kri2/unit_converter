package pl.traza;
import org.junit.*;
//import junit.framework.Assert;
import pl.traza.domain.UnitConverter;

public class UnitConverterTest {
	@Test
	public void wstawienieZeroZwracaTrzydziesciDwa(){
		UnitConverter unitConverter = new UnitConverter();
		unitConverter.setTemperatureC(0.0F);
		Assert.assertEquals("0 C to 32 F",0.0F, unitConverter.getTemperatureC().floatValue(),0.01);
	}
}
