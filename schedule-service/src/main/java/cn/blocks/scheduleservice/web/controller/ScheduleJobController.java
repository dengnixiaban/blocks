package cn.blocks.scheduleservice.web.controller;
import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.scheduleservice.BaseQuery;
import cn.blocks.scheduleservice.model.ScheduleJob;
import cn.blocks.scheduleservice.service.IScheduleJobService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;

import java.util.Locale;

/**
* <li>文件名称: 定时任务 前端控制器</li>
*/
@RestController
@RequestMapping("/scheduleJob")
@Api(value = "定时任务接口")
public class ScheduleJobController {


    @Autowired
    private IScheduleJobService iScheduleJobService;
    /**
     * 列表
     */
    @GetMapping
    //@RequiresPermissions("scheduleJob:list" )
    @ApiOperation(value = "获取定时任务列表" )
    public BaseResp<Page<ScheduleJob>> list(BaseQuery baseQuery){
        BaseResp<Page<ScheduleJob>> baseResult = new BaseResp<>();
        //查询列表数据
        Page page=new Page(baseQuery.getCurrentPage(),baseQuery.getPageSize());
        Page pageList=iScheduleJobService.selectPage(page,new EntityWrapper<ScheduleJob>());
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
    //@RequiresPermissions("scheduleJob:save" )
    @ApiOperation(value = "新增定时任务信息" )
    public BaseResp save(@RequestBody  ScheduleJob scheduleJob){

        BaseResp<ScheduleJob> baseResult = new BaseResp<>();

        boolean retFlag = iScheduleJobService.insert(scheduleJob);

        baseResult.setData(scheduleJob);
        return baseResult;
    }

    /**
     * 修改
     */
    @PutMapping
    //@RequiresPermissions("scheduleJob:update" )
    @ApiOperation(value = "修改定时任务信息" )
    public BaseResp update(@RequestBody @PathVariable("scheduleJob" ) ScheduleJob scheduleJob){
        BaseResp<ScheduleJob> baseResult = new BaseResp<>();

        boolean retFlag = iScheduleJobService.updateById(scheduleJob);

        baseResult.setData(scheduleJob);
        return baseResult;
    }

    /**
     * 删除
     */
    @DeleteMapping("/{jobId}" )
    //@RequiresPermissions("scheduleJob:delete" )
    @ApiOperation(value = "删除定时任务信息" )
    public BaseResp delete(@PathVariable("jobId" ) Long jobId){
        BaseResp<ScheduleJob> baseResult = new BaseResp<>();
        boolean retFlag = iScheduleJobService.deleteById(jobId);

        return baseResult;
    }
}
