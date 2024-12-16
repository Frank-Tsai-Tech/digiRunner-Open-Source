package tpi.dgrv4.gateway.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tpi.dgrv4.gateway.service.AcIdPReviewService;

@RestController
public class AcIdPReviewController {
	@Autowired
	AcIdPReviewService service;
 

	@GetMapping(value = "/dgrv4/ssotoken/acidp/acIdPReview")
	public void acIdPReview(@RequestHeader HttpHeaders httpHeaders, 
			HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws Exception {
		
		service.acIdPReview(httpHeaders, httpReq, httpResp);
	}
}
