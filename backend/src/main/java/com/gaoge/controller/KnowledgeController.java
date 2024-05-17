package com.gaoge.controller;

import com.gaoge.common.Result;
import com.gaoge.common.StatusCode;
import com.gaoge.entity.Knowledge;
import com.gaoge.service.KnowledgeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author 高歌
 * @Description 知识模块
 * @Date 2021-08-24
 */
@Api(tags = "知识模块接口")
@RestController
@RequestMapping("/knowledge")
@CrossOrigin
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;

    //分页查询所有知识
    @ApiOperation(value = "分页查询所有知识")
    @GetMapping("/{pageNum}")
    public Result<PageInfo<Knowledge>> findPage(@PathVariable Integer pageNum) {
        PageInfo<Knowledge> knowledgePageInfo = knowledgeService.findPage(pageNum);
        return new Result<PageInfo<Knowledge>>(true, StatusCode.OK, "查询成功", knowledgePageInfo);
    }

    //添加知识
    @ApiOperation(value = "添加知识")
    @PostMapping()
    @PreAuthorize("hasAuthority('expert')")
    public Result add(@RequestBody Knowledge knowledge) {
        knowledgeService.add(knowledge);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    //根据id修改知识
    @ApiOperation(value = "根据id修改知识")
    @PutMapping("/{id}")
    public Result update(@RequestBody Knowledge knowledge,
                         @PathVariable("id") Integer id) {
        knowledgeService.update(knowledge, id);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    //根据id删除知识
    @ApiOperation(value = "根据id删除知识")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        knowledgeService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //根据登录用户查询知识
    @ApiOperation(value = "根据登录用户查询知识")
    @GetMapping("/selectByUsername")
    public Result selectByUsername() {
        //获取登陆的用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        List<Knowledge> knowledges = knowledgeService.selectByUsername(name);
        return new Result(true, StatusCode.OK, "查询成功", knowledges);
    }

    //根据id查询商品信息
    @ApiOperation(value = "根据id查询商品信息")
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable("id") Integer id) {
        Knowledge knowledge = knowledgeService.selectById(id);
        return new Result(true, StatusCode.OK, "查询成功", knowledge);
    }
}
