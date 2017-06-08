package pl.traza;

import pl.traza.domain.UnitConverter;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UnitsConverterController {
	@Autowired
	UnitsConverterDAO unitsConverterDAO;  //czy to wystarczy? czy gdzieś indziej to się jeszcze inicializuje?
	
	@RequestMapping(value = "/converter", method = RequestMethod.GET)
	public String showUnitConverterForm(Model model){
		UnitConverter unitConverter = new UnitConverter();
		model.addAttribute("unitConverterForm", unitConverter);
		return "converter";
	}
	
	@RequestMapping(value = "/converter", method = RequestMethod.POST)
	public String updateUnitConverterForm(@ModelAttribute("unitConverterForm") UnitConverter unitConverter, BindingResult result, Model model){
		//trochę dziwne ze moge tu uzywac ten unitConverter przeciez on jest lokalny w funkcji wyzej, moze mozna go przeniesc tu?
		//dalem tutaj i co się okazało, on jest przekazany przecież w argumencie, pojawiła sie info o zdublowanej zmiennej, czyli juz tu jest
		//tylko skad jest w argumencie? nie ważne na razie
		
		unitsConverterDAO.setTemperatureInCelsius( unitConverter.getTemperatureC() );
		//return "converter";
		//System.out.println(unitsConverterDAO.getTemperatureInCelsius());
		return "redirect:/resultpage";
	}
	
	@RequestMapping(value = "/resultpage")
	public String showResult(Model model){
		UnitConverter unitConverter = new UnitConverter();
		unitConverter.setTemperatureC(unitsConverterDAO.getTemperatureInCelsius());
		
		model.addAttribute("inputTemperature", unitConverter.getTemperatureF());
		model.addAttribute("conversionResult", unitConverter.getTemperatureC());

		return "resultpage";
	}
}
