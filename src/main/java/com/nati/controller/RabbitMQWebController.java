package com.nati.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nati.model.Employee;
import com.nati.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/producer/")
public class RabbitMQWebController {

    private static Logger logger = LoggerFactory.getLogger(RabbitMQWebController.class);

    @Autowired
    RabbitMQSender rabbitMQSender;

    @PostMapping(value = "/create")
    public ResponseEntity<String> producer(@RequestBody Employee emp) {
//        Employee emp = new Employee();
//        emp.setEmpLastName(empLastName);
//        emp.setEmpName(empName);
        rabbitMQSender.send(emp);
        String msg = "Message sent to the RabbitMQ Successfully";
        logger.info(msg);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
}
