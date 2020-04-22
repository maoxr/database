package com.momo.springboot.database.main;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@Slf4j
public class DemoRestController {
    @Autowired
    private DemoService demoService;

    @ApiOperation("向数据库实例添加数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "0：异常", defaultValue = "1", required = true)
    })
    @RequestMapping(value = "/save/{state}", method = RequestMethod.GET)
    @ResponseBody
    public void test(@PathVariable(name = "state") int state) {
        demoService.save(state);
    }

    @GetMapping("/find")
    @ApiOperation("查询")
    public void find() {
        demoService.find();
    }

    @ApiOperation("向第一个数据库实例添加数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "0：异常", defaultValue = "1", required = true)
    })
    @GetMapping("/save-first/{state}")
    @RequestMapping(value = "/save-first/{state}", method = RequestMethod.GET)
    @ResponseBody
    public void saveFirst(@PathVariable(name = "state") int state) {
        demoService.saveFirst(state);
    }

    @ApiOperation("向第一个数据库实例修改数据——updateByHql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "name", value = "名称", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "state", value = "0：异常", defaultValue = "1", required = true)
    })
    @RequestMapping(value = "/updateByHql-first/{id}", method = RequestMethod.GET)
    public void updateByHqlFrist(@PathVariable(name = "id") String id, @RequestParam(name = "name") String name,
                                 @RequestParam(name = "state") int state) {
        log.info("id=" + id);
        log.info("name=" + name);
        log.info("state=" + state);
        System.out.println("sssssssssssssssssssss" + id);
        demoService.updateByHql_first(id, name, state);
    }


    @ApiOperation("向第二个数据库实例添加数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "0：异常", defaultValue = "1", required = true)
    })
    @GetMapping("/save-second/{state}")
    public void saveSecond(@PathVariable(name = "state") int state) {
        demoService.saveSecond(state);
    }

    @ApiOperation("向第二个数据库实例删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "state", value = "0：异常", defaultValue = "1", required = true)
    })
    @GetMapping("/delete-second/{id}")
    public void deleteSecond(@PathVariable(name = "id") String id, @RequestParam(name = "state") int state) {
        demoService.delete_second(id, state);
    }

    @ApiOperation("向第二个数据库实例更新数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "code", value = "编码", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "state", value = "0：异常", defaultValue = "1", required = true)
    })
    @GetMapping("/updae-second/{id}")
    public void updateSecond(@PathVariable(name = "id") String id, @RequestParam(name = "code") String code,
                             @RequestParam(name = "state") int state) {
        demoService.update_second(id, code, state);
    }


    @ApiOperation("向第三个数据库实例添加数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "0：异常", defaultValue = "1", required = true)
    })
    @GetMapping("/save-third/{id}")
    public void saveThird(@PathVariable(name = "state") int state) {
        demoService.saveThird(state);
    }
}
