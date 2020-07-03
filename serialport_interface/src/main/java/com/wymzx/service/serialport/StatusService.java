package com.wymzx.service.serialport;
import com.wymzx.entity.PageResult;
import com.wymzx.pojo.serialport.Status;

import java.util.*;

/**
 * status业务逻辑层
 */
public interface StatusService {


    public List<Status> findAll();


    public PageResult<Status> findPage(int page, int size);


    public List<Status> findList(Map<String,Object> searchMap);


    public PageResult<Status> findPage(Map<String,Object> searchMap,int page, int size);


    public Status findById(Integer id);

    public void add(Status status);


    public void update(Status status);


    public void delete(Integer id);

}
