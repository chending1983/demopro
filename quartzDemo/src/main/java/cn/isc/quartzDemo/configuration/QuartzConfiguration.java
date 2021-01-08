 package cn.isc.quartzDemo.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz cluster configuration
 * 
 * @author cd
 * @date 2021/01/08
 */
@Configuration
 public class QuartzConfiguration {
    
    /**
     * 创建集群状态下的quartz配置文件擦着
     * #============================================================================
     * # Configure Main Scheduler Properties 
     * #============================================================================
     * 
     * org.quartz.scheduler.instanceName = MyClusteredScheduler 
     * org.quartz.scheduler.instanceId = AUTO
     * 
     * #============================================================================ 
     * # Configure ThreadPool
     * #============================================================================
     * 
     * org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool 
     * org.quartz.threadPool.threadCount = 25
     * org.quartz.threadPool.threadPriority = 5
     * 
     * #============================================================================ 
     * # Configure JobStore
     * #============================================================================
     * 
     * org.quartz.jobStore.misfireThreshold = 60000
     * 
     * org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX 
     * org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.oracle.OracleDelegate 
     * org.quartz.jobStore.useProperties = false
     * org.quartz.jobStore.dataSource = myDS 
     * org.quartz.jobStore.tablePrefix = QRTZ_
     * org.quartz.jobStore.isClustered = true 
     * org.quartz.jobStore.clusterCheckinInterval = 20000
     * 
     * #============================================================================ 
     * # Configure Datasources
     * #============================================================================ 
     * org.quartz.dataSource.myDS.driver =
     * oracle.jdbc.driver.OracleDriver 
     * org.quartz.dataSource.myDS.URL = jdbc:oracle:thin:@polarbear:1521:dev
     * org.quartz.dataSource.myDS.user = quartz 
     * org.quartz.dataSource.myDS.password = quartz
     * org.quartz.dataSource.myDS.maxConnections = 5 
     * org.quartz.dataSource.myDS.validationQuery=select 0 from dual
     * 
     * @param
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        // quartz参数
        Properties prop = new Properties();
        // 设置schedule实例名
        prop.put("org.quartz.scheduler.instanceName", "testDemo");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        // worker thread pool configuration , thread name like SchedulerName_Worker_threadnumber
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "20");// 线程总数
        prop.put("org.quartz.threadPool.threadPriority", "5");
        // JobStore configuration
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        // cluster configuration
        prop.put("org.quartz.jobStore.isClustered", "true");
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        factory.setQuartzProperties(prop);
        factory.setSchedulerName("demoschedual");
        // delay launch 5 secs
        factory.setStartupDelay(5);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        // option : QuartzScheduler update existed job when startup.
        factory.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为true
        factory.setAutoStartup(true);
        return factory;
    }


}
