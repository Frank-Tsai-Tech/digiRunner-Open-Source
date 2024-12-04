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
import tpi.dgrv4.dpaa.service.DPB0204Service;
import tpi.dgrv4.dpaa.vo.DPB0204Req;
import tpi.dgrv4.dpaa.vo.DPB0204Resp;
import tpi.dgrv4.gateway.util.ControllerUtil;
import tpi.dgrv4.gateway.vo.TsmpBaseReq;
import tpi.dgrv4.gateway.vo.TsmpBaseResp;
import tpi.dgrv4.gateway.vo.TsmpHttpHeader;

@RestController
public class DPB0204Controller {
	@Autowired
	private DPB0204Service service;

	
	@PostMapping(value = "/dgrv4/11/DPB0204", params = { "before" }, //
			consumes = MediaType.APPLICATION_JSON_VALUE, //
			produces = MediaType.APPLICATION_JSON_VALUE)
	public TsmpBaseResp<BeforeControllerResp> createGtwIdPInfo_jdbc_before(@RequestHeader HttpHeaders headers //
			, @RequestBody TsmpBaseReq<BeforeControllerReq> req) {
		try {
			return ControllerUtil.getReqConstraints(req, new DPB0204Req());
		} catch (Exception e) {
			throw new TsmpDpAaException(e, req.getReqHeader());
		}
	}

	
	@PostMapping(value = "/dgrv4/11/DPB0204", //
			consumes = MediaType.APPLICATION_JSON_VALUE, //
			produces = MediaType.APPLICATION_JSON_VALUE)
	public TsmpBaseResp<DPB0204Resp> createGtwIdPInfo_jdbc(@RequestHeader HttpHeaders headers //
			, @RequestBody TsmpBaseReq<DPB0204Req> req) {
		TsmpHttpHeader tsmpHttpHeader = ControllerUtil.toTsmpHttpHeader(headers);
		DPB0204Resp resp = null;
		try {
			ControllerUtil.validateRequest(tsmpHttpHeader.getAuthorization(), req);
			resp = service.createGtwIdPInfo_jdbc(tsmpHttpHeader.getAuthorization(), req.getBody());
		} catch (Exception e) {
			throw new TsmpDpAaException(e, req.getReqHeader());
		}

		return ControllerUtil.tsmpResponseBaseObj(req.getReqHeader(), resp);
	}
}
