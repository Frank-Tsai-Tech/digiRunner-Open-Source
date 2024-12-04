package tpi.dgrv4.dpaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tpi.dgrv4.common.exceptions.TsmpDpAaException;
import tpi.dgrv4.common.vo.BeforeControllerReq;
import tpi.dgrv4.common.vo.BeforeControllerResp;
import tpi.dgrv4.dpaa.service.AA1202Service;
import tpi.dgrv4.dpaa.util.ControllerUtil;
import tpi.dgrv4.dpaa.vo.AA1202Req;
import tpi.dgrv4.dpaa.vo.AA1202Resp;
import tpi.dgrv4.gateway.vo.TsmpBaseReq;
import tpi.dgrv4.gateway.vo.TsmpBaseResp;
import tpi.dgrv4.gateway.vo.TsmpHttpHeader;

/***
 * 
 * API次數-時間分析
 * 
 * @author min
 *
 */
@RestController
public class AA1202Controller {

	@Autowired
	private AA1202Service service;
		
	
	@PostMapping(value = "/dgrv4/11/AA1202", params = {"before"}, //
		consumes = MediaType.APPLICATION_JSON_VALUE, //
		produces = MediaType.APPLICATION_JSON_VALUE)
	public TsmpBaseResp<BeforeControllerResp> queryAPITimesAndTime_before(@RequestHeader HttpHeaders headers //
			, @RequestBody TsmpBaseReq<BeforeControllerReq> req) {
		return ControllerUtil.getReqConstraints(req, new AA1202Req());
	}
		
	/**
	 * 
	 * 
	 * @param headers
	 * @param req
	 * @return
	 */
	
	@PostMapping(value = "/dgrv4/11/AA1202", //
		consumes = MediaType.APPLICATION_JSON_VALUE, //
		produces = MediaType.APPLICATION_JSON_VALUE)
	public TsmpBaseResp<AA1202Resp> queryAPITimesAndTime(@RequestHeader HttpHeaders headers //
			, @RequestBody TsmpBaseReq<AA1202Req> req) {
		TsmpHttpHeader tsmpHttpHeader = ControllerUtil.toTsmpHttpHeader(headers);
		AA1202Resp resp = null;
		try {
					
			ControllerUtil.validateRequest(tsmpHttpHeader.getAuthorization(), req);
			
			resp = service.queryAPITimesAndTime(tsmpHttpHeader.getAuthorization(), req.getBody(), req.getReqHeader());
		} catch (Exception e) {
			throw new TsmpDpAaException(e, req.getReqHeader());
		}
	
		return ControllerUtil.tsmpResponseBaseObj(req.getReqHeader(), resp);
	}
}
