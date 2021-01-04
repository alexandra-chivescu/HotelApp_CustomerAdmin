package com.proiectBD.hotel.controller;

import com.proiectBD.hotel.dao.*;
import com.proiectBD.hotel.model.Camera;
import com.proiectBD.hotel.model.Client;
import com.proiectBD.hotel.model.Rezervare;
import com.proiectBD.hotel.model.Tip_camera;
import com.proiectBD.hotel.security.ClientSession;
import com.proiectBD.hotel.security.Encryption;
import com.proiectBD.hotel.service.CameraService;
import com.proiectBD.hotel.service.ClientService;
import com.proiectBD.hotel.service.RezervareService;
import com.proiectBD.hotel.service.Tip_cameraService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    ClientSession clientSession;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientDao clientDao;

    @Autowired
    CameraDao cameraDao;

    @Autowired
    CameraService cameraService;

    @Autowired
    RezervareDao rezervareDao;

    @Autowired
    RezervareService rezervareService;

    @Autowired
    Tip_cameraDao tip_cameraDao;

    @Autowired
    Tip_cameraService tip_cameraService;

    @Autowired
    AngajatDao angajatDao;


    @GetMapping("/menu")
    public ModelAndView menu() {
        ModelAndView registerMV = new ModelAndView("index.html");
        return registerMV;
    }

    @GetMapping("/login-form")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView("login");

        String loginPass = Encryption.SHA1(Encryption.MD5(password));
        //cum fac loginul? cum verific daca utilizatorul este inregistrat?
        List<Client> clientList = clientService.getUsersByEmail(email);
        if(clientList.size() == 0){
            modelAndView.addObject("message", "Credentialele nu sunt corecte");
        }
        if(clientList.size() > 1){
            modelAndView.addObject("message", "Credentialele nu sunt corecte");
        }

        if(clientList.size() == 1 && email.equals("adminhotelalexa@gmail.com") && password.equals("333")) {
            Client clientFromDatabase = clientList.get(0);
            clientSession.setId(clientFromDatabase.getId_client());
            modelAndView = new ModelAndView("redirect:/adminhomepage");
        }
        if(clientList.size() == 1 &&  !email.equals("adminhotelalexa@gmail.com")) {
            Client clientFromDatabase = clientList.get(0);
            if(!clientFromDatabase.getParola().equals(loginPass)){
                modelAndView.addObject("message", "Credentialele nu sunt corecte");
            } else {
                clientSession.setId(clientFromDatabase.getId_client());
                modelAndView = new ModelAndView("redirect:/reservations");
            }
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {

        int id = clientSession.getId();
        if (id != 0){
            return new ModelAndView("redirect:/reservations");
        }
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @GetMapping("/register-form")
    public ModelAndView registerPost(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("nume") String nume,
                                     @RequestParam("prenume") String prenume,
                                     @RequestParam("password-again") String password2) {
        ModelAndView modelAndView = new ModelAndView("register");

        List<Client> clients = clientService.getUsersByEmail(email);

        if(clients.size() > 0) {
            modelAndView.addObject("message", "Acest email este deja folosit");
            return modelAndView;
        }
        if(!password.equals(password2)) {
            modelAndView.addObject("message", "Parolele nu sunt identice");
        } else {
            clientService.save(email, Encryption.SHA1(Encryption.MD5(password)), nume, prenume);
        }

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("reservations")
    public ModelAndView reservations(){
        int id = clientSession.getId();
        if (id == 0){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView("reservations");
        List<Tip_camera> tipuri_camera = tip_cameraDao.findAll();
        modelAndView.addObject("tip_camera", tipuri_camera);
        int cartSize = clientSession.getSizeOfCart();
        modelAndView.addObject("items", cartSize);

        return modelAndView;
    }

    @GetMapping("adminhomepage")
    public ModelAndView adminhomepage() {
        ModelAndView modelAndView = new ModelAndView("adminhomepage.html");

        List<Client> clienti = clientDao.findAll();
        modelAndView.addObject("client", clienti);
        return modelAndView;
    }

    @GetMapping("c/delete/{id}")
    public ModelAndView deleteClient(@PathVariable(value="id") int id, Model model) {
        Client client = clientDao.findById(id);
        clientDao.delete(client);
        model.addAttribute("client",clientDao.findAll());
        return new ModelAndView("redirect:/adminhomepage");
    }

    @GetMapping("c/edit/{id}")
    public ModelAndView editClientData(@PathVariable(value="id") int id, Model model) {
        Client client = clientDao.findById(id);
    }

    @PostMapping("/add-to-cart")
    public ModelAndView addToCart(@RequestParam("checkInDate") String checkInDate,
                                  @RequestParam("checkOutDate") String checkOutDate,
                                  @RequestParam("productId") Integer id_tip,
                                  @RequestParam("nradulti") Integer nradulti,
                                  @RequestParam("nrcopii") Integer nrcopii,
                                  @RequestParam("nrpatsuplimentar") Integer nrpatsuplimentar){

        ModelAndView modelAndView = new ModelAndView("reservations");


        List<Tip_camera> tipuri_camera = tip_cameraDao.findAll();
        modelAndView.addObject("tip_camera", tipuri_camera);

        //2.Check in and check out dates + Reservation
        String[] wholeCheckInDate = checkInDate.split("/");
        String dbCheckInDate = wholeCheckInDate[2] + "-" + wholeCheckInDate[0] + "-" + wholeCheckInDate[1];

        String[] wholeCheckOutDate = checkOutDate.split("/");
        String dbCheckOutDate = wholeCheckOutDate[2] + "-" + wholeCheckOutDate[0] + "-" + wholeCheckOutDate[1];
        int idUser = clientSession.getId();
        if(cameraService.listaCamereTipDorit(id_tip) != null) {
            //vreau sa verific daca pot face rezervarea
            //CE INSEAMNA SA POT FACE REZERVAREA?
            //deci am o lista cu rezervarile existente deja care au tipul de camera dorit de mine
            List<Rezervare> listaRezervariDeTipDorit = rezervareService.findRezervariTipDorit(id_tip);

            //in cazul in care exista destule camere si pot face rezervarea, vreau sa salvez rezervarea in baza de date -> se vor completa:
            //tabelele rezervare, cazare si factura
        }
        //daca nu pot face rezervarea, se va afisa mesajul "INDISPONIBIL"

        //eu vreau sa adaug in cos exact numarul camerei.
            clientSession.addIntoCart(id_tip, 1);
        int cartSize = clientSession.getSizeOfCart();
        modelAndView.addObject("items", cartSize);

        //rezervareService.save(dbCheckInDate, dbCheckOutDate, id, idUser, nradulti, nrcopii, nrpatsuplimentar);
        return modelAndView;
    }

    @GetMapping("/cart")
    public ModelAndView getCartItems(){
        int id = clientSession.getId();
        if (id == 0){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView("cart");
        List<Camera> cameraList = cameraService.getListRooms();
        List<Rezervare> rezervareList = rezervareService.getListReservations();
        modelAndView.addObject("userId", clientSession.getId());
        modelAndView.addObject("rooms", cameraList);
        modelAndView.addObject("reservations", rezervareList);
        double sum = 0;
        for(int i = 0; i< rezervareList.size(); i++) {
            //am luat data din tabelul sql
            String dayCheckin = rezervareList.get(i).getZi_sosire();
            String dayCheckout = rezervareList.get(i).getZi_plecare();
            LocalDate checkIn = LocalDate.parse(dayCheckin);
            LocalDate checkOut = LocalDate.parse(dayCheckout);
            long numberDays = ChronoUnit.DAYS.between(checkIn, checkOut);

            if(clientSession.getId() == rezervareList.get(i).getId_client()) {

//                for (int j = 0; j < cameraList.size(); j++) {
//                    if (cameraService.getListRooms().get(j).getId_camera() == rezervareList.get(i).getIdCa()) {
//                        sum = sum + numberDays * cameraService.getListRooms().get(j).getPret();
//                    }
//                }
            }
        }
        modelAndView.addObject("totalSum", sum);
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        clientSession.setId(0);
        return new ModelAndView("redirect:/menu");
    }

}
