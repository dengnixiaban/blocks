package cn.blocks.scheduleservice.web.controller;

import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.scheduleservice.BaseQuery;
import cn.blocks.scheduleservice.model.ScheduleJob;
import cn.blocks.scheduleservice.quartz.ScheduleUtils;
import cn.blocks.scheduleservice.service.IScheduleJobService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.assertj.core.util.Lists;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;



/**
 * <li>文件名称: 定时任务 前端控制器</li>
 */
@RestController
@RequestMapping("/scheduleJob")
@Api(value = "定时任务接口")
public class ScheduleJobController {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private IScheduleJobService iScheduleJobService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation(value = "获取定时任务列表")
    public BaseResp<Page<ScheduleJob>> list(BaseQuery baseQuery) {
        BaseResp<Page<ScheduleJob>> baseResult = new BaseResp<>();
        //查询列表数据
        Page page = new Page(baseQuery.getCurrentPage(), baseQuery.getPageSize());
        Page pageList = iScheduleJobService.selectPage(page, new EntityWrapper<ScheduleJob>());
        if (CollectionUtils.isEmpty(pageList.getRecords())) {
            baseResult.setCode("无数据");
            return baseResult;
        }
        baseResult.setData(pageList);
        return baseResult;
    }


    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增定时任务信息")
    public BaseResp save(@RequestBody ScheduleJob scheduleJob) {

        BaseResp<ScheduleJob> baseResult = new BaseResp<>();
        iScheduleJobService.save(scheduleJob);

        baseResult.setMsg("保存成功");
        baseResult.setCode("200");
        return baseResult;
    }

    /**
     * 修改
     */
    @PutMapping
    @ApiOperation(value = "修改定时任务信息")
    public BaseResp update(@RequestBody @PathVariable("scheduleJob") ScheduleJob scheduleJob) {
        BaseResp<ScheduleJob> baseResult = new BaseResp<>();

        iScheduleJobService.update(scheduleJob);;

        baseResult.setData(scheduleJob);
        return baseResult;
    }

    /**
     * 删除
     */
    @DeleteMapping
    @ApiOperation(value = "删除定时任务信息")
    public BaseResp delete(@RequestBody Long[] jobIds) {
        BaseResp<ScheduleJob> baseResult = new BaseResp<>();
        for(Long jobId : jobIds){
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        iScheduleJobService.deleteBatchIds(Lists.newArrayList(jobIds));
        return baseResult;
    }
    /**
     * 立即执行任务
     */
    @PostMapping("/run")
    @ApiOperation(value = "立即执行任务")
    public BaseResp run(@RequestBody Long[] jobIds) {
        BaseResp<ScheduleJob> baseResult = new BaseResp<>();
        iScheduleJobService.run(jobIds);

        return baseResult;
    }
    /**
     * 暂停任务
     */
    @PostMapping("/pause")
    @ApiOperation(value = "暂停任务")
    public BaseResp pause(@RequestBody Long[] jobIds) {
        BaseResp<ScheduleJob> baseResult = new BaseResp<>();
        iScheduleJobService.pause(jobIds);

        return baseResult;
    }
    /**
     * 恢复任务
     */
    @PostMapping("/restart")
    @ApiOperation(value = "恢复任务")
    public BaseResp restart(@RequestBody Long[] jobIds) {
        BaseResp<ScheduleJob> baseResult = new BaseResp<>();
        iScheduleJobService.resume(jobIds);
        return baseResult;
    }
}
