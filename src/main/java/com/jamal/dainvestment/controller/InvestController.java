package com.jamal.dainvestment.controller;

import com.jamal.dainvestment.dto.InvestDto;
import com.jamal.dainvestment.service.InvestService;
import com.jamal.dainvestment.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

/**
 * This is a Javadoc comment
 * Controller for INVESTMENT Entity
 */

@Slf4j
@RestController
@RequestMapping(value = "/invest")
public class InvestController {
    @Autowired
    private InvestService investService;

    /*================= User Interface (ThymeLeaf) =================*/

    @GetMapping(value = "get")
    public ModelAndView getUserView() {
        ModelAndView modelAndView = new ModelAndView("getinvestment");
        modelAndView.addObject("allInvest", investService.findAll());

        return modelAndView;
    }

    @PostMapping("create")
    public RedirectView createInvestmet(@Valid InvestDto invest, BindingResult result) {
        log.info("===== Masuk ke Controller Create Investment");

        investService.create(invest);

        final String basePath = ServletUriComponentsBuilder.fromCurrentContextPath().build().getPath();

        return new RedirectView(basePath + "/invest/get");
    }
    /*==============================================================*/

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Validated InvestDto investment){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + "." + nameofCurrMethod);
        response.setMessage("Berhasil Membuat Data");

        log.info(String.valueOf(investment));

        response.setData(investService.create(investment));

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


        response.setData(investService.findAll());

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById (@PathVariable("id") String id){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + "." + nameofCurrMethod);
        response.setMessage("Berhasil Menampilkan Data Berdasarkan ID");

        response.setData(investService.findById(id));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Response> update (@PathVariable("id") String id, @RequestBody @Validated InvestDto investment)
    {

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + "." + nameofCurrMethod);
        response.setMessage("Berhasil Update Data");

        response.setData(investService.update(id, investment));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable("id") String id)
    {

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();


        Response response = new Response();
        response.setService(this.getClass().getName() + "." + nameofCurrMethod);
        response.setMessage("Data Berhasil dihapus");
        response.setData(investService.findById(id));

        investService.delete(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
