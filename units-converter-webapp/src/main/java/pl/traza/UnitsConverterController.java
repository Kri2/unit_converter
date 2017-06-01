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
	@RequestMapping(value = "/converter", method = RequestMethod.GET)
	public String showUnitConverterForm(Model model){
		UnitConverter unitConverter = new UnitConverter();
		model.addAttribute("unitConverterForm", unitConverter);
		return "converter";
	}
	
	@RequestMapping(value = "/converter", method = RequestMethod.POST)
	public String updateUnitConverterForm(@ModelAttribute("unitConverterForm") UnitConverter unitConverter, BindingResult result, Model model){
		return "converter";
	}
	
}
