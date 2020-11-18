// package com.example.demo;

// import com.example.demo.Model.Cuser;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.ReactiveAuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import reactor.core.publisher.Mono;

// @RestController
// @RequestMapping
// public class LoginController {
		
// 	private final ReactiveAuthenticationManager dbauthenticationManager;
    
//     @Autowired
//     public LoginController(@Qualifier("dbauth") ReactiveAuthenticationManager dbauthenticationManager) {
// 		this.dbauthenticationManager = dbauthenticationManager;
//     }
	
// 	@GetMapping(value = "/")
// 	public String HelloClient (){
// 		return "Hello Client";
// 	}

// 	@PostMapping(value = "/login")
// 	public Mono<ResponseEntity<?>> userLogin(@RequestBody Cuser user) throws Exception {
// 		UsernamePasswordAuthenticationToken loginToken = 
// 		new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPswd());
// 		try {
// 		return dbauthenticationManager.authenticate(loginToken).flatMap((auth)->{
// 			System.out.println("authenticated ? : " + auth.isAuthenticated());
// 				if(auth.isAuthenticated()){
// 					return Mono.just(ResponseEntity.ok("successfully logged in"));
// 				}else{
// 					return Mono.just(ResponseEntity.badRequest().body("invalid Credentials"));
// 				}
// 			});	
// 		} catch (BadCredentialsException e) {
// 			throw new Exception("invalid username or password",e);
// 		}
// 	}
// }
