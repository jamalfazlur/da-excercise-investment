package com.jamal.dainvestment.controller;

import com.jamal.dainvestment.dto.UserDto;
import com.jamal.dainvestment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import com.jamal.dainvestment.util.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

/**
 * This is a Javadoc comment
 * Controller for USER Entity
 */

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*================= User Interface (ThymeLeaf) =================*/

    @GetMapping(value = "get")
    public ModelAndView getUserView() {
        ModelAndView modelAndView = new ModelAndView("getuser");
        modelAndView.addObject("allUser", userService.findAll()); // obj allUser = userService.findAll();

        return modelAndView;
    }

    @GetMapping(value = "get/{id}")
    public ModelAndView getUserDetailView(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("getuserdetail");
        modelAndView.addObject("userDetail", userService.findById(id));

        return modelAndView;
    }

    @GetMapping(value = "create")
    public ModelAndView postUserView() {
        ModelAndView modelAndView = new ModelAndView("createuser");
        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView postUser(@Valid UserDto user, BindingResult result) {
        log.info("===== Masuk ke Mode POST =======");
        ModelAndView modelAndView = new ModelAndView("createuser");

        log.info("Nama: " + user.getUserNama());
        log.info("Alamat: " + user.getUserAlamat());
        log.info("Saldo: Rp. " + user.getUserSaldo());

        userService.create(user);
        modelAndView.addObject("allUser", userService.findAll());

        modelAndView.setViewName("getuser");

        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public RedirectView deleteUser(@PathVariable("id") int id) {
        log.info("===== Masuk ke Mode DELETE =======");
        log.info("DELETE ID: " + id);
        userService.delete(id);

        final String basePath = ServletUriComponentsBuilder.fromCurrentContextPath().build().getPath();

        return new RedirectView(basePath + "/user/get");
    }

    /*==============================================================*/

    @PostMapping
    ResponseEntity<Response> create (@Valid @RequestBody UserDto user) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + "." + nameofCurrMethod);
        response.setMessage("Berhasil Membuat Data");

        response.setData(userService.create(user));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    ResponseEntity<Response> findAll(){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + "." + nameofCurrMethod);
        response.setMessage("Berhasil Menampilkan Seluruh Data");


        response.setData(userService.findAll());

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById (@PathVariable("id") Integer id){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + "." + nameofCurrMethod);
        response.setMessage("Berhasil Menampilkan Data Berdasarkan ID");


        response.setData(userService.findById(id));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Response> update (@PathVariable("id") Integer id, @RequestBody @Validated UserDto user)
    {

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + "." + nameofCurrMethod);
        response.setMessage("Berhasil Update Data");

        response.setData(userService.update(id, user));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable("id") Integer id)
    {

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();


        Response response = new Response();
        response.setService(this.getClass().getName() + "." + nameofCurrMethod);
        response.setMessage("Data Berhasil dihapus");
        response.setData(userService.findById(id));

        userService.delete(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
