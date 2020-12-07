package com.ocr.axa.jlp.abernathy.web.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ocr.axa.jlp.abernathy.model.User;
import com.ocr.axa.jlp.abernathy.service.ConnectionService;
import com.ocr.axa.jlp.abernathy.service.UserService;
import com.ocr.axa.jlp.abernathy.web.exceptions.ControllerException;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger("generalController");

    @Autowired
    UserService userService;

    @Autowired
    ConnectionService connectionService;

    /**
     * 
     * @return List of all USER
     */
    
    @GetMapping(path = "/all")
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> usersFound = userService.findAll();
        logger.info(" get all user : OK");
        return usersFound;
    }
    
    /**
     * 
     * @param user (userName)
     * @return information for the user
     */
    @GetMapping
    @ResponseBody
    public User getUser(@RequestBody User user) {
        User userFound = userService.findUser(user);
        logger.info(" get user : OK");
        return userFound;
    }

    /**
     * 
     * @param user (userName, password required, pseudo)
     * @return user created
     */
    @PostMapping("/userInfo")
    public ResponseEntity<User> createUser(@RequestBody User user) {


        if (user.getUserName().isEmpty()) {
            logger.error("inscriptionPerson : KO");
            throw new ControllerException("userName is required");
        }

        if (user.getPassword().isEmpty()) {
            logger.error("inscriptionPerson : KO");
            throw new ControllerException("passeword is required");
        }

        User userAdded = userService.create(user);
        
        if (userAdded == null) {
            logger.error("inscriptionUser : KO");
            throw new ControllerException("UserName already exist");
        }
        else {
            logger.info("Add user OK " + user.toString());
            return new ResponseEntity(userAdded, HttpStatus.OK);
        }
      
        
    }

   /**
    * 
    * @param user (userName and password)
    * @return ok si user/password are matching
    */
    @GetMapping("/connect")
    public ResponseEntity<Boolean> connectUser(@RequestBody User user) {

        if (user.getUserName().isEmpty()) {
            logger.error("inscriptionPerson : KO");
            throw new ControllerException("userName is required");
        }
        if (user.getPassword().isEmpty()) {
            logger.error("inscriptionPerson : KO");
            throw new ControllerException("password is required");
        }

        if (connectionService.connectUser(user)) {
            logger.info("Connect user OK " + user.toString());
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            logger.error("Connect user KO " + user.toString());
            throw new ControllerException("user/password not exist");
        }
    }
}