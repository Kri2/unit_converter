package pl.traza;
import org.springframework.stereotype.Repository;

@Repository
public class UnitsConverterDAO {
	private Float temperatureInCelsius = 0.0F;
	public void setTemperatureInCelsius(Float temperatureInCelsius){
		this.temperatureInCelsius=temperatureInCelsius;
	}
	public Float getTemperatureInCelsius(){
		return this.temperatureInCelsius;
	}
}
