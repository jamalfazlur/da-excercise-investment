package com.jamal.dainvestment.User;

import com.jamal.dainvestment.dto.UserDto;
import com.jamal.dainvestment.model.User;
import com.jamal.dainvestment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testFindAll() throws Exception{

        List<User> users= userService.findAll();
        log.info("size : "+users.size());

        for(User user: users){
            log.info("=========== HASIL FIND ALL ( #" + user.getUserId() + " ) ===========");
            log.info("======= Nama: " + user.getUserNama());
            log.info("======= Alamat: " + user.getUserAlamat());
            log.info("======= Saldo: Rp." + user.getUserSaldo());
        }
    }

    @Test
    public void testFindById() throws Exception{
        int id = 1;

        User user = userService.findById(id);
        log.info("=========== FIND By ID ===========");
        log.info("======= Nama: " + user.getUserNama());
        log.info("======= Alamat: " + user.getUserAlamat());
        log.info("======= Saldo: Rp." + user.getUserSaldo());

    }

    @Test
    public void createUser() throws Exception{
        UserDto user = new UserDto();
        user.setUserNama("Jamal Fazlur");
        user.setUserAlamat("Depok Bojongsari");
        user.setUserSaldo(4500000);

        userService.create(user);
        log.info("=========== SEDANG MEMBUAT USER ===========");
        log.info("======= Nama: " + user.getUserNama());
        log.info("======= Alamat: " + user.getUserAlamat());
        log.info("======= Saldo: Rp." + user.getUserSaldo());

        List<User> users= userService.findAll();
        int lastIndex = users.size()-1;

        User newUser = users.get(lastIndex);

        boolean boolNama;
        boolean boolAlamat;
        boolean boolSaldo;

        log.info("======= COMPARING : " + newUser.getUserNama() + " <> " + user.getUserNama());
        if(newUser.getUserNama().equals(user.getUserNama()) ){
            log.info("... Checking Name: As Expected");
            boolNama = true;
        } else {
            log.info("... Checking Name: Not As Expected");
            boolNama = false;
        }

        log.info("======= COMPARING : " + newUser.getUserAlamat() + " <> " + user.getUserAlamat());
        if(newUser.getUserAlamat().equals(user.getUserAlamat()) ){
            log.info("... Checking Alamat: As Expected");
            boolAlamat = true;
        } else {
            log.info("... Checking Alamat: Not As Expected");
            boolAlamat = false;
        }

        log.info("======= COMPARING : " + newUser.getUserSaldo() + " <> " + user.getUserSaldo());
        if ((newUser.getUserSaldo() == user.getUserSaldo())){
            log.info("... Checking Saldo: As Expected");
            boolSaldo = true;
        } else  {
            log.info("... Checking Saldo: Not As Expected");
            boolSaldo = false;
        }

        log.info("======= IS EVERYTHING OKAY? =========");
        log.info("NAMA: " + boolNama + ", ALAMAT: " + boolAlamat + ", SALDO: " + boolSaldo );
        if (boolNama && boolAlamat && boolSaldo){
            log.info("... SUCCESS");
        } else {
            log.warn("... FAILED");
        }

    }

    @Test
    public void updateUser() throws Exception {
        int id = 1;

        User user = userService.findById(id);

        log.info("=========== AKAN MENGUPDATE USER BERIKUT ===========");
        log.info("======= ID : " + user.getUserId());
        log.info("======= Nama : " + user.getUserNama());
        log.info("======= Alamat : " + user.getUserAlamat());
        log.info("======= Saldo : " + user.getUserSaldo());

/*        user.setUserNama("Rahman");
        user.setUserAlamat("Bogor");
        user.setUserSaldo(9500000);

        userService.update(id, user);
        log.info("=========== UPDATE ON PROCESS ===========");
        User userUpdated = userService.findById(id);

        log.info("=========== BERHASIL DIUPDATE MENJADI ===========");
        log.info("======= ID : " + userUpdated.getUserId());
        log.info("======= Nama : " + userUpdated.getUserNama());
        log.info("======= Alamat : " + userUpdated.getUserAlamat());
        log.info("======= Saldo : " + userUpdated.getUserSaldo());*/

    }

    @Test
    public void deleteUser() throws Exception {
        int id = 2;
        List<User> users= userService.findAll();

        log.info("=========== Available Users: ");
        for(int i = 0; i < users.size(); i++){
            log.info("#" + users.get(i).getUserId() + " - " + users.get(i).getUserNama());
        }

        log.info("... Trying to Delete User with ID: #" + id +" - " + users.get(id).getUserNama());
        userService.delete(id);

        log.info("=========== (New!) Available Users: ");
        List<User> newUsers= userService.findAll();
        for(int i = 0; i < newUsers.size(); i++){
            log.info("#" + newUsers.get(i).getUserId() + " - " + newUsers.get(i).getUserNama());
        }
    }

}

