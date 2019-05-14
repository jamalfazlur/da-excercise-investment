package com.jamal.dainvestment.controller;

import com.jamal.dainvestment.model.User;
import com.jamal.dainvestment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.jamal.dainvestment.util.Response;

/**
 * This is a Javadoc comment
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Validated User user){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameofCurrMethod);
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
        response.setService(this.getClass().getName() + nameofCurrMethod);
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
        response.setService(this.getClass().getName() + nameofCurrMethod);
        response.setMessage("Berhasil Menampilkan Data Berdasarkan ID");


        response.setData(userService.findById(id));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Response> update (@PathVariable("id") Integer id, @RequestBody @Validated User user)
    {

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameofCurrMethod);
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
        response.setService(this.getClass().getName() + nameofCurrMethod);
        response.setMessage("Data Berhasil dihapus");
        response.setData(userService.findById(id));

        userService.delete(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
