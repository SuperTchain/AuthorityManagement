package com.lx.authoritymanagement.controller;

import com.lx.authoritymanagement.annotations.RecordOperation;
import com.lx.authoritymanagement.service.LogService;
import com.lx.authoritymanagement.utils.Result;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName OperationController
 * @Description TODO
 * @Author ASUS
 * @Date 2020/6/5 12:55
 * @Version 1.0
 */
@Controller
@Api(tags = "日志记录")
@RequestMapping("/log")
public class LogController {

    /**
     * 引入日志
     */
    private static Logger logger = Logger.getLogger(LogController.class);

    /**
     * 引入service
     */
    @Autowired
    private LogService logService;

    /**
     * 跳转到日志界面
     *
     * @return 日志界面
     */
    @GetMapping("/toLogList")
    @ApiOperation(value = "跳转到日志列表界面")
    public String toLogList() {
        return "log/logList";
    }

    /**
     * 跳转带查看界面
     *
     * @return 查看日志界面
     */
    @GetMapping("/toViewLog")
    @ApiOperation(value = "跳转到日志查看界面")
    public String toViewLog() {
        return "log/ViewLog";
    }

    @GetMapping("/findAllLog")
    @ResponseBody
    @ApiOperation(value = "查询所有日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "查询所有日志", url = "/log/findAllLog")
    public Result findAllLog(Integer page, Integer limit) {
        Result result = logService.findAllLogByPage(page, limit);
        logger.info("查询日志列表成功");
        return result;
    }

    @PostMapping("/batchDelete")
    @ResponseBody
    @ApiOperation(value = "批量删除日志")
    @RecordOperation(name = "批量删除日志", url = "/log/batchDelete")
    public Result batchDeleteByLogId(@ApiParam(name = "ids", value = "日志id数组") String[] ids) {
        System.out.println(ids);
        Result result = logService.batchDeleteByLogId(ids);
        logger.info("成功批量删除日志");
        result.setStatus(200);
        result.setItem("批量删除成功");
        return result;
    }
}
