package cn.blocks.scheduleservice.controller;

import cn.blocks.commonmysql.model.po.BaseQuery;
import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.httpserver.annotation.AccessLog;
import cn.blocks.scheduleservice.model.dto.ScheduleJobDTO;
import cn.blocks.scheduleservice.model.po.ScheduleJobPO;
import cn.blocks.scheduleservice.quartz.ScheduleUtils;
import cn.blocks.scheduleservice.service.IScheduleJobService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.assertj.core.util.Lists;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <li>文件名称: 定时任务 前端控制器</li>
 */
@RestController
@RequestMapping("/schedule")
@Api(value = "定时任务接口")
public class ScheduleJobController {


    @Autowired
    private Scheduler scheduler;


    @Autowired
    private IScheduleJobService iScheduleJobService;

    /**
     * 列表
     */
    @AccessLog(desc = "/schedule/job,获取定时任务列表")
    @GetMapping("/job")
    @ApiOperation(value = "获取定时任务列表")
    public BaseResp<Page<ScheduleJobDTO>> list(BaseQuery baseQuery) {
        BaseResp<Page<ScheduleJobDTO>> baseResult = new BaseResp<>();
        //查询列表数据
        Page page = new Page(baseQuery.getCurrentPage(), baseQuery.getPageSize());
        Page<ScheduleJobPO> pageList = iScheduleJobService.selectPage(page, new EntityWrapper<ScheduleJobPO>());
        List<ScheduleJobPO> records = pageList.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return baseResult;
        }
        Page<ScheduleJobDTO> respPage = new Page(baseQuery.getCurrentPage(), baseQuery.getPageSize());
        respPage.setRecords(records.stream().map(ScheduleJobDTO::new).collect(Collectors.toList()));
        baseResult.setData(respPage);
        return baseResult;
    }


    /**
     * 保存
     */
    @AccessLog(desc = "/schedule/job,新增定时任务信息")
    @PostMapping("/job")
    @ApiOperation(value = "新增定时任务信息")
    public BaseResp save(@RequestBody ScheduleJobDTO scheduleJob) {
        iScheduleJobService.save(scheduleJob);
        return new BaseResp();
    }

    /**
     * 修改
     */
    @AccessLog(desc = "/schedule/job,修改定时任务信息")
    @PutMapping("/job")
    @ApiOperation(value = "修改定时任务信息")
    public BaseResp update(@RequestBody ScheduleJobDTO scheduleJob) {
        BaseResp<ScheduleJobDTO> baseResult = new BaseResp<>();
        iScheduleJobService.update(scheduleJob);;
        baseResult.setData(scheduleJob);
        return baseResult;
    }

    /**
     * 删除
     */
    @AccessLog(desc = "/schedule/job,删除定时任务信息")
    @DeleteMapping("/job")
    @ApiOperation(value = "删除定时任务信息")
    public BaseResp delete(@RequestBody Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        iScheduleJobService.deleteBatchIds(Lists.newArrayList(jobIds));
        return new BaseResp();
    }


    /**
     * 立即执行任务
     */
    @AccessLog(desc = "/schedule/run,立即执行任务")
    @PostMapping("/run")
    @ApiOperation(value = "立即执行任务")
    public BaseResp run(@RequestBody Long[] jobIds) {
        iScheduleJobService.run(jobIds);
        return new BaseResp();
    }


    /**
     * 暂停任务
     */
    @AccessLog(desc = "/schedule/pause,暂停任务")
    @PostMapping("/pause")
    @ApiOperation(value = "暂停任务")
    public BaseResp pause(@RequestBody Long[] jobIds) {
        iScheduleJobService.pause(jobIds);
        return new BaseResp();
    }


    /**
     * 恢复任务
     */
    @AccessLog(desc = "/schedule/restart,恢复任务")
    @PostMapping("/restart")
    @ApiOperation(value = "恢复任务")
    public BaseResp restart(@RequestBody Long[] jobIds) {
        iScheduleJobService.resume(jobIds);
        return new BaseResp();
    }


}
