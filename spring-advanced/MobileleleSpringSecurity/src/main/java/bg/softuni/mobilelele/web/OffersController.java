package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.OfferCreateBindingModel;
import bg.softuni.mobilelele.model.service.OfferCreateServiceModel;
import bg.softuni.mobilelele.model.entities.EngineType;
import bg.softuni.mobilelele.model.binding.OfferUpdateBindingModel;
import bg.softuni.mobilelele.model.service.OfferUpdateServiceModel;
import bg.softuni.mobilelele.model.entities.TransmissionType;
import bg.softuni.mobilelele.model.view.AddOfferModelView;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.repositories.ModelRepository;
import bg.softuni.mobilelele.services.MobileleleUser;
import bg.softuni.mobilelele.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class OffersController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    public OffersController(OfferService offerService, ModelMapper modelMapper, ModelRepository modelRepository) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/offers/{id}/details")
    public String offer(@PathVariable Long id, Model model) {
        OfferDetailsView offer = offerService.findOffer(id);
        model.addAttribute("offer", offer);
        return "details";
    }
    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}/update")
    public String getUpdateOffer(@PathVariable Long id, Model model) {
        OfferDetailsView offerView = offerService.findOffer(id);
        OfferUpdateBindingModel offer = modelMapper.map(offerView, OfferUpdateBindingModel.class);
        offer.setId(id);
        model.addAttribute("offer", offer);
        model.addAttribute("engines", EngineType.values());
        model.addAttribute("transmissions", TransmissionType.values());
        return "update";
    }
    @GetMapping("/offers/{id}/update/errors")
    public String getUpdateOfferErrors(@PathVariable Long id, Model model) {
        model.addAttribute("engines", EngineType.values());
        model.addAttribute("transmissions", TransmissionType.values());
        return "update";
    }

    @PatchMapping("/offers/{id}/update")
    public String updateOffer(@PathVariable Long id, @Valid OfferUpdateBindingModel offerUpdateBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offer", offerUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", bindingResult);
            return "redirect:/offers/"+id+"/update/errors";
        }
        OfferUpdateServiceModel serviceModel = modelMapper.map(offerUpdateBindingModel, OfferUpdateServiceModel.class);
        serviceModel.setId(id);
        offerService.updateOffer(serviceModel);
        return "redirect:/offers/all";
    }

    @ModelAttribute("offerModel")
    public OfferCreateBindingModel createModel() {
        return new OfferCreateBindingModel();
    }
    @GetMapping("offers/add")
    public String getAddOffer(Model model) {
        model.addAttribute("models", modelRepository.findAll().stream().map(b -> modelMapper.map(b, AddOfferModelView.class)).collect(Collectors.toSet()));
        model.addAttribute("engines", EngineType.values()) ;
        model.addAttribute("transmissions", TransmissionType.values()) ;
        return "offer-add";
    }

    @PostMapping("offers/add")
    public String postAddOffer(@Valid OfferCreateBindingModel offerCreateBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, @AuthenticationPrincipal MobileleleUser user) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
            return "redirect:/offers/add";
        }

        OfferCreateServiceModel serviceModel = modelMapper.map(offerCreateBindingModel, OfferCreateServiceModel.class);
        offerService.addOffer(serviceModel, user);
        return  "redirect:/offers/all";
    }
}
