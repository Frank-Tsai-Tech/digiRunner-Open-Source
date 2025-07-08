package tpi.dgrv4.common.utils.autoInitSQL.Initializer;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import tpi.dgrv4.common.utils.DateTimeUtil;
import tpi.dgrv4.common.utils.StackTraceUtil;
import tpi.dgrv4.common.utils.autoInitSQL.vo.TsmpRoleVo;
@Service
public class TsmpRoleTableInitializer {

    private  List<TsmpRoleVo> tsmpRolelist = new LinkedList<>();
    
	public List<TsmpRoleVo> insertTsmpRole() {
		try {
			String roleId;
			String roleName;
			String roleAlias;
			String createUser;
			createTsmpRole((roleId = "1000"), (roleName = "ADMIN"), (roleAlias = "Administrator"), (createUser = "manager"));
			createTsmpRole((roleId = "1001"), (roleName = "FrontPageDashboard"), (roleAlias = "FrontPageDashboard"), (createUser = "manager"));
			

		} catch (Exception e) {
			StackTraceUtil.logStackTrace(e);
			throw e;
		}
		return tsmpRolelist;
	}
    
	protected void createTsmpRole(String roleId, String roleName, String roleAlias, String createUser) {
		TsmpRoleVo tsmpRole = new TsmpRoleVo();
		tsmpRole.setRoleId(roleId);
		tsmpRole.setRoleName(roleName);
		tsmpRole.setRoleAlias(roleAlias);
		tsmpRole.setCreateUser(createUser);
		tsmpRole.setCreateTime(DateTimeUtil.now());
		tsmpRolelist.add(tsmpRole);

	}
    
}
