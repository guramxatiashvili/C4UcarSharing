package c4uCarSharing.gurami.controllers;

import c4uCarSharing.gurami.domain.Trip;
import c4uCarSharing.gurami.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TripController {

    private TripService tripService;

    @Autowired
    public void setProductService(TripService tripService) {
        this.tripService = tripService;
    }

    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("trips", tripService.listAllTrips());
        return "trips";
    }

    @RequestMapping("trip/show/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("trip", tripService.getTripById(id));
        return "tripshow";
    }

    @RequestMapping("trip/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("trip", tripService.getTripById(id));
        return "tripform";
    }

    @RequestMapping("trip/new")
    public String newProduct(Model model){
        model.addAttribute("trip", new Trip());
        return "tripform";
    }

    @RequestMapping(value = "trip", method = RequestMethod.POST)
    public String saveTrip(Trip trip){
        tripService.saveTrip(trip);
        return "redirect:/trip/show/" + trip.getId();
    }

    @RequestMapping("trip/delete/{id}")
    public String delete(@PathVariable Integer id){
        tripService.deleteTrip(id);
        return "redirect:/trips";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
         return "login";
    }

}