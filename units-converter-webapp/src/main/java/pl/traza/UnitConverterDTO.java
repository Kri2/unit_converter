package pl.traza;

import javax.validation.constraints.NotNull;

public class UnitConverterDTO {
	@NotNull
	private Float temperature;
	
	public Float getTemperature(){
		return 10.0F;
		//return this.temperature;
	}
	public void setTemperature(Float temperature){
		this.temperature = temperature;
	}
}
