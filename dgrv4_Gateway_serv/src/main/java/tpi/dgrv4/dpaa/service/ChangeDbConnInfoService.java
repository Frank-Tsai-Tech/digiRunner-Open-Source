package tpi.dgrv4.dpaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zaxxer.hikari.HikariDataSource;

import tpi.dgrv4.common.utils.StackTraceUtil;
import tpi.dgrv4.gateway.keeper.TPILogger;

@Service
public class ChangeDbConnInfoService {
	@Autowired
	private ConfigurableApplicationContext applicationContext;

	public ChangeDbConnInfoService() {
		super();
	}

	/**
	 * 實際更換密碼的地方
	 * 
	 * @param un 帳號
	 * @param pw 密碼
	 */
	public void changeDbConnInfo(String un, String pw) {
		try {
			if (StringUtils.hasLength(pw) && StringUtils.hasLength(un)) {
				// 只要pw或un有值 就要更新
				// 從applicationContext取出使用中的DataSource 更換
				TPILogger.tl.info("Apply to switch the connection to username= " + un );
				HikariDataSource hikaridataSource = (HikariDataSource) applicationContext
						.getBean(HikariDataSource.class);

				// 調用 HikariConfigMXBean 來動態修改配置
				hikaridataSource.getHikariConfigMXBean().setPassword(pw);
				hikaridataSource.getHikariConfigMXBean().setUsername(un);
				// 調用 HikariPoolMXBean 來取得連線池的連線數（活躍、空閒和所有）、取得等待連線的執行緒數、掛起和恢復連線池、丟棄未使用連線等
				hikaridataSource.getHikariPoolMXBean().softEvictConnections();
			}
		} catch (Exception e) {
			TPILogger.tl.error(StackTraceUtil.logTpiShortStackTrace(e));

		}
	}
}
