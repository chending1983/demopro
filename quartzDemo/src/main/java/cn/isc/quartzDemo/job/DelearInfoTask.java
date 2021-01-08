package cn.isc.quartzDemo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 经销商店签约信息定时任务
 */
@Component("delearInfoTask")
public class DelearInfoTask {
    private static final Logger log = LoggerFactory.getLogger(DelearInfoTask.class);

    public void delearInfoServiceTest() {
        log.info("delearInfoServiceTest" + "is running......");
    }
}
