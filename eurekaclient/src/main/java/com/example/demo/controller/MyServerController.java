package com.example.demo.controller;

import com.example.demo.service.RemoteServer;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyServerController {
    @Autowired
    private RemoteServer remoteServer;

    @GetMapping("/server/{id}")
    public String server(@PathVariable("id") int id) {

        return remoteServer.server(id);
    }
}