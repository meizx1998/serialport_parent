package com.wymzx.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wymzx.dao.StatusMapper;
import com.wymzx.entity.PageResult;
import com.wymzx.pojo.serialport.Status;
import com.wymzx.service.serialport.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusMapper statusMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Status> findAll() {
        return statusMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Status> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Status> statuss = (Page<Status>) statusMapper.selectAll();
        return new PageResult<Status>(statuss.getTotal(),statuss.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Status> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return statusMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Status> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Status> statuss = (Page<Status>) statusMapper.selectByExample(example);
        return new PageResult<Status>(statuss.getTotal(),statuss.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Status findById(Integer id) {
        return statusMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param status
     */
    public void add(Status status) {
        statusMapper.insert(status);
    }

    /**
     * 修改
     * @param status
     */
    public void update(Status status) {
        statusMapper.updateByPrimaryKeySelective(status);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        statusMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Status.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 设备编码
            if(searchMap.get("devid")!=null && !"".equals(searchMap.get("devid"))){
                criteria.andLike("devid","%"+searchMap.get("devid")+"%");
            }
            // 系统状态
            if(searchMap.get("status")!=null && !"".equals(searchMap.get("status"))){
                criteria.andLike("status","%"+searchMap.get("status")+"%");
            }
            // 压缩机运行状态
            if(searchMap.get("runningstatus")!=null && !"".equals(searchMap.get("runningstatus"))){
                criteria.andLike("runningstatus","%"+searchMap.get("runningstatus")+"%");
            }
            // 设定温度
            if(searchMap.get("settemp")!=null && !"".equals(searchMap.get("settemp"))){
                criteria.andLike("settemp","%"+searchMap.get("settemp")+"%");
            }
            // 采集温度
            if(searchMap.get("gettemp")!=null && !"".equals(searchMap.get("gettemp"))){
                criteria.andLike("gettemp","%"+searchMap.get("gettemp")+"%");
            }
            // 锁开关状态
            if(searchMap.get("suo")!=null && !"".equals(searchMap.get("suo"))){
                criteria.andLike("suo","%"+searchMap.get("suo")+"%");
            }

            // 主键ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
