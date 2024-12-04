package tpi.dgrv4.dpaa.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import jakarta.annotation.PostConstruct;
import tpi.dgrv4.common.constant.TsmpDpApptJobStatus;
import tpi.dgrv4.common.utils.DateTimeUtil;
import tpi.dgrv4.common.utils.StackTraceUtil;
import tpi.dgrv4.entity.entity.TsmpDpApptJob;
import tpi.dgrv4.entity.entity.TsmpDpApptRjob;
import tpi.dgrv4.entity.repository.DgrDashboardEsLogDao;
import tpi.dgrv4.entity.repository.TsmpDpApptJobDao;
import tpi.dgrv4.entity.repository.TsmpDpApptRjobDao;
import tpi.dgrv4.entity.repository.TsmpReqResLogHistoryDao;
import tpi.dgrv4.gateway.keeper.TPILogger;

@Component
public class ReportInit {
	
	@Autowired
    private TsmpDpApptJobDao tsmpDpApptJobDao;
	@Autowired
    private TsmpDpApptRjobDao tsmpDpApptRjobDao;
	@Autowired
	private TsmpReqResLogHistoryDao tsmpReqResLogHistoryDao;
	@Autowired
	private DgrDashboardEsLogDao dgrDashboardEsLogDao;
	private DateFormat dfSSS = new SimpleDateFormat("SSS");
	private DateFormat yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@PostConstruct
    public void init() {
		try {
			//因為在換版可能REPORT_BATCH還在執行中,會造成之後報表排程一直無法被執行
			checkReportJob();
			checkReportRjob();//上一個method會改掉週期排程處於執行中的狀態,此方法是預防排程先變成取消了
		}catch(Exception e) {
			TPILogger.tl.error(StackTraceUtil.logStackTrace(e));
		}
		
		//在本機去連DEV環境9萬筆資料要執行半小時,並且其他程序會無法執行該table,客戶端預設分頁為50萬筆,目前無大量資料,故不執行
		/*try {
			//20231115因有舊客戶關係,1年後此段可刪除,因為資料只保留1年,testcase要改
			//因為在測sql server時,發現有毫秒不精準的問題,所以都改為0
			processMs();
		}catch(Exception e) {
			TPILogger.tl.error(StackTraceUtil.logStackTrace(e));
		}*/
	}
	
	private void checkReportJob() {
		List<TsmpDpApptJob> jobList = getTsmpDpApptJobDao().findByRefItemNoAndStatus("REPORT_BATCH", TsmpDpApptJobStatus.RUNNING.value());
		if(!CollectionUtils.isEmpty(jobList)) {
			TPILogger.tl.info("REPORT_BATCH job running");
			jobList.forEach(jobVo->{
				jobVo.setStatus(TsmpDpApptJobStatus.CANCEL.value());
				jobVo.setUpdateDateTime(DateTimeUtil.now());
				jobVo.setUpdateUser("REPORT_INIT");
			});
			getTsmpDpApptJobDao().saveAll(jobList);
			TPILogger.tl.info("REPORT_BATCH job status change cancel id:" + jobList.stream().map( TsmpDpApptJob::getApptJobId )
            .collect( Collectors.toList() ));
			
			String periodUid = jobList.get(0).getPeriodUid();
			if(StringUtils.hasText(periodUid)) {
				TsmpDpApptRjob rjobVo = getTsmpDpApptRjobDao().findById(periodUid).orElse(null);
				if(rjobVo != null) {
					if("3".equals(rjobVo.getStatus())) {
						rjobVo.setStatus("1");
						rjobVo.setUpdateDateTime(DateTimeUtil.now());
						rjobVo.setUpdateUser("REPORT_INIT");
						getTsmpDpApptRjobDao().save(rjobVo);
						TPILogger.tl.info("Report Schedule job status change start");
					}
				}
			}
		}
	}
	
	private void checkReportRjob() {
		List<TsmpDpApptRjob> jobList = getTsmpDpApptRjobDao().findByRjobName("Report Schedule");
		if(!CollectionUtils.isEmpty(jobList)) {
			TPILogger.tl.info("REPORT_BATCH cycle job running");
			jobList.forEach(jobVo->{
				if("3".equals(jobVo.getStatus())) {
					jobVo.setStatus("1");
					jobVo.setUpdateDateTime(DateTimeUtil.now());
					jobVo.setUpdateUser("REPORT_INIT");
				}
			});
			getTsmpDpApptRjobDao().saveAll(jobList);
			TPILogger.tl.info("Report Schedule job status change start");
		}
	}
	
//	@Transactional
//	private void processMs() throws ParseException {
//		//會取多筆來判斷,是避免前幾筆舊資料剛好是0毫秒
//		List<TsmpReqResLogHistory> hisotryList = getTsmpReqResLogHistoryDao().findTop20By();
//		boolean isUpdate = false;
//		for(TsmpReqResLogHistory vo : hisotryList) {
//			if(!dfSSS.format(vo.getRtime()).equals("000")) {
//				isUpdate = true;
//				break;
//			}
//		}
//		if(isUpdate) {
//			TPILogger.tl.info("TsmpReqResLogHistory have non 0 ms data");
//			hisotryList = getTsmpReqResLogHistoryDao().findAll();
//			TPILogger.tl.info("TsmpReqResLogHistory size is " + hisotryList.size());
//			for(TsmpReqResLogHistory vo : hisotryList) {
//				String strDate = yyyyMMddHHmmssSSS.format(vo.getRtime());
//				strDate = strDate.substring(0,19) + ".000";
//				vo.setRtime(yyyyMMddHHmmssSSS.parse(strDate));
//			}
//
//			getTsmpReqResLogHistoryDao().saveAll(hisotryList);
//
//			TPILogger.tl.info("TsmpReqResLogHistory updated 0 ms end");
//		}
//
//		List<DgrDashboardEsLog> esLogList = getDgrDashboardEsLogDao().findTop20By();
//		isUpdate = false;
//		for(DgrDashboardEsLog vo : esLogList) {
//			if(!dfSSS.format(vo.getRtime()).equals("000")) {
//				isUpdate = true;
//				break;
//			}
//		}
//		if(isUpdate) {
//			TPILogger.tl.info("DgrDashboardEsLog have non 0 ms data");
//			esLogList = getDgrDashboardEsLogDao().findAll();
//			TPILogger.tl.info("DgrDashboardEsLog size is " + esLogList.size());
//			for(DgrDashboardEsLog vo : esLogList) {
//				String strDate = yyyyMMddHHmmssSSS.format(vo.getRtime());
//				strDate = strDate.substring(0,19) + ".000";
//				vo.setRtime(yyyyMMddHHmmssSSS.parse(strDate));
//			}
//
//			getDgrDashboardEsLogDao().saveAll(esLogList);
//
//			TPILogger.tl.info("DgrDashboardEsLog updated 0 ms end");
//		}
//	}

	protected TsmpDpApptJobDao getTsmpDpApptJobDao() {
		return tsmpDpApptJobDao;
	}

	protected TsmpReqResLogHistoryDao getTsmpReqResLogHistoryDao() {
		return tsmpReqResLogHistoryDao;
	}

	protected DgrDashboardEsLogDao getDgrDashboardEsLogDao() {
		return dgrDashboardEsLogDao;
	}

	protected TsmpDpApptRjobDao getTsmpDpApptRjobDao() {
		return tsmpDpApptRjobDao;
	}
	
	
}
