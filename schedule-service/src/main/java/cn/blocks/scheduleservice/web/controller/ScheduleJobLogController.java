package cn.blocks.scheduleservice.web.controller;
import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.scheduleservice.BaseQuery;
import cn.blocks.scheduleservice.model.ScheduleJobLog;
import cn.blocks.scheduleservice.service.IScheduleJobLogService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;


/**
* <li>文件名称: 定时任务日志 前端控制器</li>
*/
@RestController
@RequestMapping("/scheduleJobLog")
@Api(value = "定时任务日志接口")
public class ScheduleJobLogController {


    @Autowired
    private IScheduleJobLogService iScheduleJobLogService;


    /**
     * 列表
     */
    @GetMapping
    //@RequiresPermissions("scheduleJobLog:list" )
    @ApiOperation(value = "获取定时任务日志列表" )
    public BaseResp<Page<ScheduleJobLog>>  list(BaseQuery baseQuery){
        BaseResp<Page<ScheduleJobLog>> baseResult = new BaseResp<>();
        //查询列表数据
        Page page=new Page(baseQuery.getCurrentPage(),baseQuery.getPageSize());
        Page pageList=iScheduleJobLogService.selectPage(page,new EntityWrapper<ScheduleJobLog>());

        baseResult.setData(pageList);
        return baseResult;
    }

    /**
     * 信息
     */
    @GetMapping("/{logId}" )
    //@RequiresPermissions("scheduleJobLog:info" )
    @ApiOperation(value = "获取定时任务日志详情信息" )
    public BaseResp<ScheduleJobLog> info(@PathVariable("logId" ) Long logId){
        BaseResp<ScheduleJobLog> baseResult = new BaseResp<>();

        ScheduleJobLog scheduleJobLog = iScheduleJobLogService.selectById(logId);

        baseResult.setData(scheduleJobLog);
        return baseResult;
    }

    /**
     * 保存
     */
    @PostMapping
    //@RequiresPermissions("scheduleJobLog:save" )
    @ApiOperation(value = "新增定时任务日志信息" )
    public BaseResp save(@RequestBody  ScheduleJobLog scheduleJobLog){

        BaseResp<ScheduleJobLog> baseResult = new BaseResp<>();

        boolean retFlag = iScheduleJobLogService.insert(scheduleJobLog);

        baseResult.setData(scheduleJobLog);
        return baseResult;
    }

    /**
     * 修改
     */
    @PutMapping
    //@RequiresPermissions("scheduleJobLog:update" )
    @ApiOperation(value = "修改定时任务日志信息" )
    public BaseResp update(@RequestBody @PathVariable("scheduleJobLog" ) ScheduleJobLog scheduleJobLog){
        BaseResp<ScheduleJobLog> baseResult = new BaseResp<>();

        boolean retFlag = iScheduleJobLogService.updateById(scheduleJobLog);

        baseResult.setData(scheduleJobLog);
        return baseResult;
    }

    /**
     * 删除
     */
    @DeleteMapping("/{logId}" )
    //@RequiresPermissions("scheduleJobLog:delete" )
    @ApiOperation(value = "删除定时任务日志信息" )
    public BaseResp delete(@PathVariable("logId" ) Long logId){
        BaseResp<ScheduleJobLog> baseResult = new BaseResp<>();
        boolean retFlag = iScheduleJobLogService.deleteById(logId);

        return baseResult;
    }
}
