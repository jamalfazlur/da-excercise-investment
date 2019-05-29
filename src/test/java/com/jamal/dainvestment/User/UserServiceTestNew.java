package com.jamal.dainvestment.User;

import com.jamal.dainvestment.dto.UserDto;
import com.jamal.dainvestment.exception.DataNotFoundException;
import com.jamal.dainvestment.exception.ErrorExceptionHandler;
import com.jamal.dainvestment.model.User;
import com.jamal.dainvestment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTestNew {
    @Autowired
    private UserService userService;

    @Test
    public void createUserTestUser() throws Exception{

        int userSizeBeforeCreateOne = userService.findAll().size(); //3

        UserDto createNewUser = new UserDto();
        createNewUser.setUserNama("Jameela");
        createNewUser.setUserAlamat("Papua Nugini");
        createNewUser.setUserSaldo(9500000);

        userService.create(createNewUser);

        List<User> listUser = userService.findAll(); //4
        int indexOfLastUser = listUser.size()-1; //3

        User user = listUser.get(indexOfLastUser);
        assertThat(user).isEqualToComparingFieldByField(createNewUser);

        log.info("======= Comparing Size ========");
        log.info("======= Size after (" + listUser.size() + ") > before (" + userSizeBeforeCreateOne + ")");
        assertThat(listUser.size()).isGreaterThan(userSizeBeforeCreateOne);

    }

    @Test
    public void findByIdUserTestUser() throws Exception {
        User user= userService.findById(1);

        assertThat(user)
                .hasFieldOrProperty("userId")
                .hasFieldOrProperty("userNama")
                .hasFieldOrProperty("userAlamat")
                .hasFieldOrProperty("userSaldo");
    }

    @Test
    public void findAllUserTestUser() throws Exception {
        List<User> users = userService.findAll();

        log.info("============= .: Cetak 3 user pertama :. =============");
        for(int i = 0; i < 3; i++) {
            log.info("ID: " + users.get(i).getUserId());
            log.info("Nama: " + users.get(i).getUserNama());
            log.info("Alamat: " + users.get(i).getUserAlamat());
            log.info("Saldo: " + users.get(i).getUserSaldo());
        }
        assertThat(users.size())
                .isGreaterThan(0);
    }

    @Test
    public void updateUserTestUser() throws Exception {
        int id = 1;

        User userOld = userService.findById(id);
        String namaOld = userOld.getUserNama();
        String alamatOld = userOld.getUserAlamat();
        int saldoOld = userOld.getUserSaldo();

        UserDto userNew = new UserDto();
        userNew.setUserId(id);
        userNew.setUserNama("Jamal Sudah Berubah");
        userNew.setUserAlamat("Tidak seperti dulu");
        userNew.setUserSaldo(250);

        userService.update(id, userNew);

        User userCompare = userService.findById(id);

        log.info("=== Comparing: ID#" + userCompare.getUserId() + " <> ID#" + id);
        assertThat(userCompare.getUserId())
                .isEqualTo(id);

        log.info("=== Comparing: " + userCompare.getUserNama() + " <> " + namaOld);
        assertThat(userCompare.getUserNama())
                .isNotEqualTo(namaOld);

        log.info("=== Comparing: " + userCompare.getUserAlamat() + " <> " + alamatOld);
        assertThat(userCompare.getUserAlamat())
                .isNotEqualTo(alamatOld);

        log.info("=== Comparing: " + userCompare.getUserSaldo() + " <> " + saldoOld);
        assertThat(userCompare.getUserSaldo())
                .isNotEqualTo(saldoOld);
    }

    @Test
    public void deleteUserTestUser() throws Exception {
        int id = 3;

        List<User> users = userService.findAll();
        log.info("Sebelum Delete: " + String.valueOf(users.size()));

        userService.delete(id);

        List<User> usersNew = userService.findAll();
        log.info("Setelah Delete: " + String.valueOf(usersNew.size()));

        assertThat(users.size())
                .isGreaterThan(usersNew.size());

    }

    @Test(expected = DataNotFoundException.class)
    public void findByIdFailed() throws Exception {
        int id = 9;

        User user = userService.findById(id);

    }

}
