package com.proiectBD.hotel.controller;

import com.proiectBD.hotel.dao.*;
import com.proiectBD.hotel.model.*;
import com.proiectBD.hotel.security.ClientSession;
import com.proiectBD.hotel.security.Encryption;
import com.proiectBD.hotel.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    RecenzieDao recenzieDao;

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

    @Autowired
    Salarii_deptDao salarii_deptDao;

    @Autowired
    Date_facturaDao date_facturaDao;

    @Autowired
    Cazari_aziDao cazari_aziDao;

    @Autowired
    DepartamentDao departamentDao;

    @Autowired
    DepartamentService departamentService;

    @Autowired
    FunctieDao functieDao;

    @Autowired
    FunctieService functieService;

    @Autowired
    Detalii_cameraDao detalii_cameraDao;

    @Autowired
    Detalii_cameraService detalii_cameraService;

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
        model.addAttribute("id_modific", id);
        return new ModelAndView("redirect:/adminhomepage");
    }

    @GetMapping("/finish-edit-client")
    public ModelAndView FinishEditClient(@RequestParam("id_client") int id_client,
                                   @RequestParam("nume") String nume,
                                   @RequestParam("prenume") String prenume,
                                   @RequestParam("cnp") String cnp,
                                   @RequestParam("telefon") String telefon,
                                   @RequestParam("email") String email) {
        clientService.modifyClientById(id_client, nume, prenume, cnp, telefon, email);
        return new ModelAndView("redirect:/adminhomepage");
    }

    @GetMapping("/add-new-client")
    public ModelAndView AddNewClient(@RequestParam("nume") String nume,
                                     @RequestParam("prenume") String prenume,
                                     @RequestParam("cnp") String cnp,
                                     @RequestParam("telefon") String telefon,
                                     @RequestParam("email") String email) {

    clientService.save_new_client(nume, prenume, cnp, telefon, email);
    return new ModelAndView("redirect:/adminhomepage");
    }

    @GetMapping("/recenzii")
    public ModelAndView tabelRecenzii() {
        ModelAndView modelAndView = new ModelAndView("recenzii.html");
        List<Recenzie> recenzii = recenzieDao.findAll();
        modelAndView.addObject("recenzii", recenzii);
        return modelAndView;
    }

    @GetMapping("recenzie/delete/{id}")
    public ModelAndView deleteRecenzie(@PathVariable(value="id") int id, Model model) {
        Recenzie recenzie = recenzieDao.findById(id);
        recenzieDao.delete(recenzie);
        model.addAttribute("recenzii",recenzieDao.findAll());
        return new ModelAndView("redirect:/recenzii");
    }

    @GetMapping("/tabelrezervari")
    public ModelAndView tabelRezervari() {
        ModelAndView modelAndView = new ModelAndView("tabelrezervari.html");
        List<Rezervare> rezervari = rezervareDao.findAll();
        modelAndView.addObject("rezervari", rezervari);
        return modelAndView;
    }

    @GetMapping("rezervare/delete/{id}")
    public ModelAndView deleteRezervare(@PathVariable(value="id") int id, Model model) {
        Rezervare rezervare = rezervareDao.findById(id);
        rezervareDao.delete(rezervare);
        model.addAttribute("rezervari",rezervareDao.findAll());
        return new ModelAndView("redirect:/tabelrezervari");
    }

    @GetMapping("/add-new-rezervare")
    public ModelAndView AddNewRezervare(@RequestParam("id_client") int id_client,
                                     @RequestParam("zi_sosire") String zi_sosire,
                                     @RequestParam("zi_plecare") String zi_plecare,
                                     @RequestParam("plata_avans") Float plata_avans,
                                     @RequestParam("nr_adulti") int nr_adulti,
                                     @RequestParam("nr_copii") int nr_copii,
                                     @RequestParam("pat_suplimentar") int pat_suplimentar) {

        rezervareService.save_new_rezervare(id_client, zi_sosire, zi_plecare, plata_avans, nr_adulti, nr_copii, pat_suplimentar);
        return new ModelAndView("redirect:/tabelrezervari");
    }

    @GetMapping("/tabelfacturi")
    public ModelAndView tabelFacturi() {
        ModelAndView modelAndView = new ModelAndView("tabelfacturi.html");
        List<Date_factura> date_facturi = date_facturaDao.findAll();
        modelAndView.addObject("facturi", date_facturi);
        return modelAndView;
    }

    @GetMapping("/tabelsalarii")
    public ModelAndView tabelSalarii() {
        ModelAndView modelAndView = new ModelAndView("tabelsalarii.html");
        List<Salarii_departamente> salarii = salarii_deptDao.findAll();
        modelAndView.addObject("salarii", salarii);
        return modelAndView;
    }

    @GetMapping("/tabelcazari")
    public ModelAndView tabelCazari() {
        ModelAndView modelAndView = new ModelAndView("tabelcazari.html");
        List<Cazari_astazi> cazari = cazari_aziDao.findAll();
        modelAndView.addObject("cazari", cazari);
        return modelAndView;
    }

    @GetMapping("/tabeldepartamente")
    public ModelAndView tabelDepartamente() {
        ModelAndView modelAndView = new ModelAndView("tabeldepartamente.html");

        List<Departament> departamente = departamentDao.findAll();
        modelAndView.addObject("departamente", departamente);
        return modelAndView;
    }

    @GetMapping("dept/delete/{id}")
    public ModelAndView deleteDepartament(@PathVariable(value="id") int id, Model model) {
        Departament departament = departamentDao.findById(id);
        departamentDao.delete(departament);
        model.addAttribute("departamente",departamentDao.findAll());
        return new ModelAndView("redirect:/tabeldepartamente");
    }

    @GetMapping("/add-new-departament")
    public ModelAndView AddNewDepartament(@RequestParam("nume_departament") String nume_departament) {

        departamentService.save_new_departament(nume_departament);
        return new ModelAndView("redirect:/tabeldepartamente");
    }

    @GetMapping("/tabelfunctii")
    public ModelAndView tabelFunctii() {
        ModelAndView modelAndView = new ModelAndView("tabelfunctii.html");

        List<Functie> functii = functieDao.findAll();
        modelAndView.addObject("functii", functii);
        return modelAndView;
    }

    @GetMapping("fct/delete/{id}")
    public ModelAndView deleteFunctie(@PathVariable(value="id") int id, Model model) {
        Functie functie = functieDao.findById(id);
        functieDao.delete(functie);
        model.addAttribute("functii",functieDao.findAll());
        return new ModelAndView("redirect:/tabelfunctii");
    }

    @GetMapping("/add-new-fct")
    public ModelAndView AddNewFunctie(@RequestParam("nume_fct") String nume_functie) {

        functieService.save_new_functie(nume_functie);
        return new ModelAndView("redirect:/tabelfunctii");
    }

    @GetMapping("/tabelcamere")
    public ModelAndView tabelCamere() {
        ModelAndView modelAndView = new ModelAndView("tabelcamere.html");

        List<Detalii_camera> detalii_camera = detalii_cameraDao.findAll();
        modelAndView.addObject("camere", detalii_camera);
        return modelAndView;
    }

    @GetMapping("camera/delete/{id}")
    public ModelAndView deleteCamera(@PathVariable(value="id") int id, Model model) {
        Detalii_camera detalii_camera = detalii_cameraDao.findById(id);
        detalii_cameraDao.delete(detalii_camera);
        model.addAttribute("camere",detalii_cameraDao.findAll());
        return new ModelAndView("redirect:/tabelcamere");
    }

    @GetMapping("/add-new-camera")
    public ModelAndView AddNewCamera(@RequestParam("tip") String tip,
                                     @RequestParam("pret") float pret,
                                     @RequestParam("capacitate") int capacitate) {

        detalii_cameraService.save_new_det_camera(tip, pret, capacitate);
        return new ModelAndView("redirect:/tabelcamere");
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
