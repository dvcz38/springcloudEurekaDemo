package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;  //ribbon:负载均衡器

    public List<User> getUsers(){

        //选择调用的服务的名称
        //ServiceInstance:封装了服务的基本信息，如：ip、端口号
        ServiceInstance si = loadBalancerClient.choose("eureka-provider");
        //拼接访问服务的url
        StringBuffer sb = new StringBuffer();

        //http://localhost:9090/user
        sb.append("http://").append(si.getHost()).append(":").append(si.getPort()).append("/user");

        //SpringMVC RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<User>> type = new ParameterizedTypeReference<List<User>>() {
        };

        //ResponseEntity：封装了返回值信息
        ResponseEntity<List<User>> entity = restTemplate.exchange(sb.toString(), HttpMethod.GET, null, type);
        return entity.getBody();
    }
}
