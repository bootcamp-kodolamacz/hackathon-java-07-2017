package pl.kodolamacz.hack.controller;

import com.sun.scenario.effect.Effect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.kodolamacz.hack.forms.RegisterEmployerForm;
import pl.kodolamacz.hack.model.Employer;
import pl.kodolamacz.hack.model.User;
import pl.kodolamacz.hack.security.SecurityContext;
import pl.kodolamacz.hack.service.EmployerService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/employer")
public class EmployerController {

    @Autowired
    EmployerService employerService;

    @RequestMapping(value = "registerEmployer.html", method = RequestMethod.GET)
    public ModelAndView showAddEmployerForm() {

        ModelAndView modelAndView = new ModelAndView("employerViews/addEmployerForm", "employerform", new RegisterEmployerForm());
        return modelAndView;
    }

    @RequestMapping(value = "registerEmployer.html", method = RequestMethod.POST)
    public ModelAndView addNewEmployer(@Valid RegisterEmployerForm employerForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("employerViews/addEmployerForm");
        }
        employerService.addNewEmployerProfile(employerForm);
        ModelAndView modelAndView = new ModelAndView("employerViews/addEmployerConfirmation");
        modelAndView.addObject("employerForm");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView showEditEmployer(){
        return new ModelAndView(
                "/employerViews/editEmployer","employer", employerService.findByUser(SecurityContext.getCurrentlyLoggedUser()));
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ModelAndView editEmployer(@Valid Employer employer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("/employerViews/editEmployer");
        }
        employerService.updateEmployerProfile(employer);
        return new ModelAndView("/employerViews/editEmployerConfirmation");
    }

    //REMOVE Employer
    @RequestMapping(value="removeEmployer.html", method= RequestMethod.GET)
    public ModelAndView removeCandidate(@RequestParam(name="id") Long id){
        ModelAndView modelAndView = new ModelAndView("employerViews/removeEmployerConfirmation");
        employerService.deleteEmployerProfileById(id);
        return modelAndView;
    }

    //show list of employers
    @RequestMapping(value = "/list")
    public ModelAndView showListOfEmployers(){
        Iterable<Employer> allEmployers = employerService.findAllEmployers();
        ModelAndView modelAndView = new ModelAndView("employerViews/listOfEmployers");
        modelAndView.addObject("allEmployers", allEmployers);
        return modelAndView;
    }

    //

    @RequestMapping(value = "/show")
    public ModelAndView findEmployer () {
        ModelAndView modelAndView = new ModelAndView("employerViews/displayEmployer");

        Employer employer = employerService.findByUser(SecurityContext.getCurrentlyLoggedUser());
        modelAndView.addObject("employer", employer);
        return modelAndView;
    }

    @RequestMapping(value = "displayEmployerPage.html", method = RequestMethod.GET)
    public ModelAndView displayEmployerPage(@RequestParam(name="id") Long id){
        ModelAndView modelAndView = new ModelAndView("employerViews/displayEmployer");
        Employer employer = employerService.findEmployerById(id);
        modelAndView.addObject(employer);
        return modelAndView;
    }

}
