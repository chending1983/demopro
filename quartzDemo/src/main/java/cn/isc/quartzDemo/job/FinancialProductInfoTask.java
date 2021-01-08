package cn.isc.quartzDemo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 金融产品信息推送信息定时任务
 */
@Component("financialProductInfoTask")
public class FinancialProductInfoTask {
    private static final Logger log = LoggerFactory.getLogger(FinancialProductInfoTask.class);

    public void putDerivativesInfo() {
        log.info("putDerivativesInfo" + "is running......");
    }

}
