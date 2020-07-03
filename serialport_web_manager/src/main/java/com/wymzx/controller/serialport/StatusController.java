package com.wymzx.controller.serialport;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wymzx.entity.PageResult;
import com.wymzx.entity.Result;
import com.wymzx.pojo.serialport.Status;
import com.wymzx.service.serialport.StatusService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Reference
    private StatusService statusService;

    @GetMapping("/findAll")
    public List<Status> findAll(){
        return statusService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Status> findPage(int page, int size){
        return statusService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Status> findList(@RequestBody Map<String,Object> searchMap){
        return statusService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Status> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  statusService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Status findById(Integer id){
        return statusService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Status status){
        statusService.add(status);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Status status){
        statusService.update(status);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        statusService.delete(id);
        return new Result();
    }

}
