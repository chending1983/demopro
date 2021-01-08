package cn.isc.quartzDemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.isc.quartzDemo.mapper.SysJobLogMapper;
import cn.isc.quartzDemo.model.SysJobLog;

/**
 * 定时任务调度日志信息 服务层
 * 
 * @author wujia
 */
@Service
public class SysJobLogServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(SysJobLogServiceImpl.class);

    @Autowired
    private SysJobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog
     *            调度日志信息
     * @return 调度任务日志集合
     */
    @Transactional(rollbackFor = Exception.class)
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog) {
        log.info("");
        return jobLogMapper.selectJobLogList(jobLog);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobLogId
     *            调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Transactional(rollbackFor = Exception.class)
    public SysJobLog selectJobLogById(Long jobLogId) {
        return jobLogMapper.selectJobLogById(jobLogId);
    }

    /**
     * 新增任务日志
     * 
     * @param jobLog
     *            调度日志信息
     */
    @Transactional
    public void addJobLog(SysJobLog jobLog) {
        jobLogMapper.insertJobLog(jobLog);
    }

    /**
     * 批量删除调度日志信息
     * 
     * @param logIds
     *            需要删除的数据ID
     * @return 结果
     */
    public int deleteJobLogByIds(Long[] logIds) {
        return jobLogMapper.deleteJobLogByIds(logIds);
    }

    /**
     * 删除任务日志
     * 
     * @param jobId
     *            调度日志ID
     */
    public int deleteJobLogById(Long jobId) {
        return jobLogMapper.deleteJobLogById(jobId);
    }

    /**
     * 清空任务日志
     */
    public void cleanJobLog() {
        jobLogMapper.cleanJobLog();
    }
}
