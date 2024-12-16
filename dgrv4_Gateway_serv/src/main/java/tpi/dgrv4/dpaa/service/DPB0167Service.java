package tpi.dgrv4.dpaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tpi.dgrv4.codec.utils.RandomSeqLongUtil;
import tpi.dgrv4.common.constant.TsmpDpAaRtnCode;
import tpi.dgrv4.common.exceptions.TsmpDpAaException;
import tpi.dgrv4.common.utils.DateTimeUtil;
import tpi.dgrv4.common.utils.StackTraceUtil;
import tpi.dgrv4.dpaa.vo.DPB0167Req;
import tpi.dgrv4.dpaa.vo.DPB0167Resp;
import tpi.dgrv4.entity.entity.DgrGtwIdpInfoL;
import tpi.dgrv4.entity.entity.TsmpClient;
import tpi.dgrv4.entity.repository.DgrGtwIdpInfoLDao;
import tpi.dgrv4.entity.repository.TsmpClientDao;
import tpi.dgrv4.gateway.component.IdPHelper;
import tpi.dgrv4.gateway.constant.DgrDataType;
import tpi.dgrv4.gateway.keeper.TPILogger;
import tpi.dgrv4.gateway.vo.TsmpAuthorization;

@Service
public class DPB0167Service {

	@Autowired
	private DgrGtwIdpInfoLDao dgrGtwIdpInfoLDao;
	
	@Autowired
	private TsmpClientDao tsmpClientDao;

	private TPILogger logger = TPILogger.tl;

	public DPB0167Resp updateGtwIdPInfo_ldap(TsmpAuthorization authorization, DPB0167Req req) {
		DPB0167Resp resp = new DPB0167Resp();
		try {
			String id = req.getId();
			String clientId = req.getClientId();
			chekParm(id, clientId);
			
			Long longId = RandomSeqLongUtil.toLongValue(id);
			String iconFile = StringUtils.hasLength(req.getIconFile()) ? req.getIconFile() : IdPHelper.DEFULT_ICON_FILE;
			DgrGtwIdpInfoL dgrGtwIdpInfoL = getDgrGtwIdpInfoLDao().findById(longId).orElse(null);
			if (dgrGtwIdpInfoL == null) {
				throw TsmpDpAaRtnCode._1298.throwing();
			}
			dgrGtwIdpInfoL.setRemark(req.getRemark());
			dgrGtwIdpInfoL.setStatus(req.getStatus());
			dgrGtwIdpInfoL.setLdapDn(req.getLdapDn());
			dgrGtwIdpInfoL.setLdapTimeout(req.getLdapTimeout());
			dgrGtwIdpInfoL.setLdapUrl(req.getLdapUrl());
			dgrGtwIdpInfoL.setIconFile(iconFile);
			dgrGtwIdpInfoL.setPageTitle(req.getPageTitle());
			dgrGtwIdpInfoL.setUpdateDateTime(DateTimeUtil.now());
			dgrGtwIdpInfoL.setUpdateUser(authorization.getUserName());
			dgrGtwIdpInfoL.setLdapBaseDn(req.getLdapBaseDn());
			dgrGtwIdpInfoL = getDgrGtwIdpInfoLDao().saveAndFlush(dgrGtwIdpInfoL);

			// in-memory, 用列舉的值傳入值
			TPILogger.updateTime4InMemory(DgrDataType.CLIENT.value());

		} catch (TsmpDpAaException e) {
			throw e;
		} catch (Exception e) {
			logger.error(StackTraceUtil.logStackTrace(e));
			throw TsmpDpAaRtnCode._1286.throwing();
		}
		return resp;
	}

	private void chekParm(String id, String clientId) {
		if (!StringUtils.hasLength(id)) {
			throw TsmpDpAaRtnCode._2025.throwing("id");
		}

		if (!StringUtils.hasLength(clientId)) {
			throw TsmpDpAaRtnCode._2025.throwing("clientId");
		}
		
		TsmpClient client = getTsmpClientDao().findById(clientId).orElse(null);
		if (client == null) {
			throw TsmpDpAaRtnCode._1344.throwing();
		}
	}

	protected DgrGtwIdpInfoLDao getDgrGtwIdpInfoLDao() {
		return dgrGtwIdpInfoLDao;
	}
	
	protected TsmpClientDao getTsmpClientDao() {
		return tsmpClientDao;
	}
}
