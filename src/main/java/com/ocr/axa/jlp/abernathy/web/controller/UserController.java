package com.ocr.axa.jlp.abernathy.web.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param id
     * @return information for the user
     */
    @GetMapping(path = "/id")
    @ResponseBody
    public User getUser(@RequestParam long id) throws ControllerException {
        Optional<User> userFound = userService.findUser(id);
        if (!userFound.isPresent()){
            logger.error("user not found");
            throw new ControllerException(("user not found"));
        }
        else {
            logger.info(" get user : OK");
            return userFound.get();
        }
    }

    /**
     * @param username (username)
     * @return information for the user
     */
    @GetMapping(path = "/username")
    @ResponseBody
    public User getUserByUsername(@RequestParam String username) throws ControllerException {
        User userFound = userService.findByUsername(username);
        if (userFound == null){
            logger.error("user not found");
            throw new ControllerException(("user not found"));
        }
        else {
            logger.info(" get user : OK");
            return userFound;
        }
    }

    /**
     * @param user (userName, password required, pseudo)
     * @return user created
     */
    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user) throws ControllerException {

        if ((user.getUsername().isEmpty() ) || (user.getPassword().isEmpty()))  {
            logger.error("inscriptionPerson : KO");
            throw new ControllerException("userName/firstName is required");
        }

        User userAdded = userService.create(user);

        if (userAdded == null) {
            logger.error("inscriptionUser : KO");
            throw new ControllerException("UserName already exist");
        } else {
            logger.info("Add user OK " + user.toString());
            return new ResponseEntity(userAdded, HttpStatus.OK);
        }


    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user) throws ControllerException {
        Optional<User> userToFind = userService.findUser(user.getId());
        if (!userToFind.isPresent()) {
            logger.error("user not found");
            throw new ControllerException(("user not found"));
        }

        return userService.saveUser(user);

    }

    @PostMapping("/delete/id")
    public User deleteUser(@RequestParam long id) throws ControllerException {
        Optional<User> userToFind = userService.findUser(id);
        if (!userToFind.isPresent()) {
            logger.error("user not found");
            throw new ControllerException(("user not found"));
        }
        return userService.deleteUser(userToFind.get());
    }

        /**
         *
         * @param user (userName and password)
         * @return ok si user/password are matching
         */
        @GetMapping("/connect")
        public ResponseEntity<Boolean> connectUser (@RequestBody User user) throws ControllerException {

            if (user.getUsername().isEmpty()) {
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
