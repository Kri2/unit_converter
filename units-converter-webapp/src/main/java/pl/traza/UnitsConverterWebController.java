package pl.traza;

import pl.traza.domain.UnitConverter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UnitsConverterWebController {
	@Autowired
	UnitsConverterDAO unitsConverterDAO;  //czy to wystarczy? czy gdzieś indziej to się jeszcze inicializuje?
	
	//inny sposób na controller, to jest wersja z tutoriala kdk, jest elegancki z użycieem DTO, ale 
	//niezbyt łatwy do zapamiętania i nauki. Dane do modelu przesyłane są przez DTO, w którym też następuje
	//walidacja formularza
	/*
	@RequestMapping("/converter")
	public String showOrProcessForm(HttpServletRequest request, @ModelAttribute("unitConverterForm") @Valid UnitConverterDTO unitConverterDTO, BindingResult result){
		if(request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()){
			UnitConverter unitConverter = new UnitConverter();
			unitConverter.setTemperatureC(unitConverterDTO.getTemperature());
			unitsConverterDAO.setTemperatureInCelsius(unitConverter.getTemperatureC());
			return "redirect:/resultpage";//when POST
		}
		return "converter";//when GET
	}*/
	
	// to jest wersja z innych tutoriali, może być też walidacja ale bezpośrednio w unitConverter, nie da się przesłać poprzez DTO, 
	// bo obiekt stworzony w GET i dodany do modelu musi być przekazany do POST
		
	// when post controller tworzy nowy domainObject (tutaj unitController) fills with data from model (dto),
	@RequestMapping(value = "/converter", method = RequestMethod.GET)
	public String setupForm(Model model){
		// ciekawe czy to jest konieczne, czy musimy mieć nazwę, wygląda na to, że nie
		// UnitConverter unitConverter = new UnitConverter(); 
		// model.addAttribute("unitConverterForm", unitsConverterDAO);
		// myślałem, żeby tu podstawić dto, ale dto nie powinno być tworzone, a tu wygląda, że obiekt musi być utworzony
		model.addAttribute("unitConverterForm", new UnitConverter());
		return "converter";
	}
	
	@RequestMapping(value = "/converter", method = RequestMethod.POST)
	public String processSubmit(	@ModelAttribute("unitConverterForm")  @Valid UnitConverterDTO unitConverterDTO, 
									BindingResult result, 
									Model model ){
		// -------------------------------------------------------------------------
		// przekazujemy de facto ModelAttribute unitConverterForm i typ obiektu, do 
		// którego ma być przyporządkowany, mozemy tu dac DTO, ale musi miec te same 
		// elementy (atrybuty i geterry/settery)
		// -------------------------------------------------------------------------
		//trochę dziwne ze moge tu uzywac ten unitConverter przeciez on jest lokalny w funkcji wyzej, moze mozna go przeniesc tu?
		//dalem tutaj i co się okazało, on jest przekazany przecież w argumencie, pojawiła sie info o zdublowanej zmiennej, czyli juz tu jest
		//tylko skad jest w argumencie? nie ważne na razie
		//ten obiekt (tutaj DTO) to tylko model, nie jest nigdzie tworzony
		//UnitConverter unitConverter = new UnitConverter();
		//unitConverter.setTemperatureC();
		if(!result.hasErrors()){
			unitsConverterDAO.setTemperatureInCelsius( unitConverterDTO.getTemperatureC() );
			unitsConverterDAO.setTemperatureInCelsius( unitConverterDTO.getTemperatureC(), "do bazy");
			return "redirect:/resultpage";
		}
		else
			return "converter";
	}
	//przy wypisaniu listy, bezposrednio z dao
	@RequestMapping(value = "/resultpage")
	public String showResult(Model model){
		UnitConverter unitConverter = new UnitConverter();
		unitConverter.setTemperatureC(unitsConverterDAO.getTemperatureInCelsius());
		
		model.addAttribute("inputTemperature", unitConverter.getTemperatureF());
		model.addAttribute("conversionResult", unitConverter.getTemperatureC());
		
		//previous values from database
		model.addAttribute("previousValues", unitsConverterDAO.getTemperatureInCelsius(1) );
		return "resultpage";
	}
}
