package com.example.Restfull_Practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Restfull_Practice.dto.AddNumber;
import com.example.Restfull_Practice.dto.EchoRequest;

@SpringBootApplication
public class RestfullPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfullPracticeApplication.class, args);
    }

}



// Add this REST controller

@RestController
class GreetController {

    @GetMapping("/")
    public String greet() {
        return "Good Morning there";
    }

	@GetMapping("/admin")
	public String admin(){
		return "Good Morning Admin";
	}
	@PostMapping("/echo")
    public String echoMessage(@RequestBody EchoRequest request ) {
        return "Your Name:"+ request.getName()  + " Your message " + request.getMessage();
    }

     @PostMapping("/add")
     public String addNumber(@RequestBody AddNumber request){
		int sum = request.getNum1() + request.getNum2();
		return "The sum of your number hi are: " + sum;
	 }


	


	}

