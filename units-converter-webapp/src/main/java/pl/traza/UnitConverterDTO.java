package pl.traza;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class UnitConverterDTO {
	@NotNull
	@Min(0)
	//@Max(100)
	@Digits(message="less than 1000",integer = 3,fraction = 2)
	//@Pattern(regexp="[0-9]?") //@Pattern only for strings
	private Float temperatureC;
	
	public Float getTemperatureC(){
		return this.temperatureC;
	}
	public void setTemperatureC(Float temperatureC){
		this.temperatureC = temperatureC;
	}
}
