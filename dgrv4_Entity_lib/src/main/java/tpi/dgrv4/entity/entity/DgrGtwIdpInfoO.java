package tpi.dgrv4.entity.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import tpi.dgrv4.codec.constant.RandomLongTypeEnum;
import tpi.dgrv4.common.utils.DateTimeUtil;
import tpi.dgrv4.entity.component.dgrSeq.DgrSeq;

@Entity
@Table(name = "dgr_gtw_idp_info_o")
public class DgrGtwIdpInfoO implements DgrSequenced {
	@Id
	@DgrSeq(strategy = RandomLongTypeEnum.YYYYMMDD)
	@Column(name = "gtw_idp_info_o_id")
	private Long gtwIdpInfoOId;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "idp_type")
	private String idpType;

	@Column(name = "status")
	private String status;

	@Column(name = "remark")
	private String remark;

	@Column(name = "idp_client_id")
	private String idpClientId;

	@Column(name = "idp_client_mima")
	private String idpClientMima;

	@Column(name = "idp_client_name")
	private String idpClientName;

	@Column(name = "well_known_url")
	private String wellKnownUrl;

	@Column(name = "callback_url")
	private String callbackUrl;

	@Column(name = "auth_url")
	private String authUrl;

	@Column(name = "access_token_url")
	private String accessTokenUrl;

	@Column(name = "scope")
	private String scope;

	@Column(name = "create_date_time")
	private Date createDateTime = DateTimeUtil.now();

	@Column(name = "create_user")
	private String createUser = "SYSTEM";

	@Column(name = "update_date_time")
	private Date updateDateTime;

	@Column(name = "update_user")
	private String updateUser;

	@Version
	@Column(name = "version")
	private Long version = 1L;

	@Override
	public Long getPrimaryKey() {
		return gtwIdpInfoOId;
	}
	//

	@Override
	public String toString() {
		return "DgrGtwIdpInfoO [gtwIdpInfoOId=" + gtwIdpInfoOId + ", clientId=" + clientId + ", idpType=" + idpType
				+ ", status=" + status + ", remark=" + remark + ", idpClientId=" + idpClientId + ", idpClientMima="
				+ idpClientMima + ", idpClientName=" + idpClientName + ", wellKnownUrl=" + wellKnownUrl
				+ ", callbackUrl=" + callbackUrl + ", authUrl=" + authUrl + ", accessTokenUrl=" + accessTokenUrl
				+ ", scope=" + scope + ", createDateTime=" + createDateTime + ", createUser=" + createUser
				+ ", updateDateTime=" + updateDateTime + ", updateUser=" + updateUser + ", version=" + version + "]";
	}

	public Long getGtwIdpInfoOId() {
		return gtwIdpInfoOId;
	}

	public void setGtwIdpInfoOId(Long gtwIdpInfoOId) {
		this.gtwIdpInfoOId = gtwIdpInfoOId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getIdpType() {
		return idpType;
	}

	public void setIdpType(String idpType) {
		this.idpType = idpType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIdpClientId() {
		return idpClientId;
	}

	public void setIdpClientId(String idpClientId) {
		this.idpClientId = idpClientId;
	}

	public String getIdpClientMima() {
		return idpClientMima;
	}

	public void setIdpClientMima(String idpClientMima) {
		this.idpClientMima = idpClientMima;
	}

	public String getIdpClientName() {
		return idpClientName;
	}

	public void setIdpClientName(String idpClientName) {
		this.idpClientName = idpClientName;
	}

	public String getWellKnownUrl() {
		return wellKnownUrl;
	}

	public void setWellKnownUrl(String wellKnownUrl) {
		this.wellKnownUrl = wellKnownUrl;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}