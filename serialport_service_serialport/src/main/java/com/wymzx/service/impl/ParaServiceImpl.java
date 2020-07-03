package com.wymzx.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wymzx.dao.ParaMapper;
import com.wymzx.entity.PageResult;
import com.wymzx.pojo.serialport.Para;
import com.wymzx.service.serialport.ParaService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ParaServiceImpl implements ParaService {

    @Autowired
    private ParaMapper paraMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Para> findAll() {
        return paraMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Para> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Para> paras = (Page<Para>) paraMapper.selectAll();
        return new PageResult<Para>(paras.getTotal(),paras.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Para> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return paraMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Para> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Para> paras = (Page<Para>) paraMapper.selectByExample(example);
        return new PageResult<Para>(paras.getTotal(),paras.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Para findById(Integer id) {
        return paraMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param para
     */
    public void add(Para para) {
        paraMapper.insert(para);
    }

    /**
     * 修改
     * @param para
     */
    public void update(Para para) {
        paraMapper.updateByPrimaryKeySelective(para);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        paraMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 设备地址
            if(searchMap.get("devlocation")!=null && !"".equals(searchMap.get("devlocation"))){
                criteria.andLike("devlocation","%"+searchMap.get("devlocation")+"%");
            }
            // 状态上传时间间隔
            if(searchMap.get("timewait")!=null && !"".equals(searchMap.get("timewait"))){
                criteria.andLike("timewait","%"+searchMap.get("timewait")+"%");
            }
            // 压缩机启动延时
            if(searchMap.get("timelate")!=null && !"".equals(searchMap.get("timelate"))){
                criteria.andLike("timelate","%"+searchMap.get("timelate")+"%");
            }
            // 设定温度
            if(searchMap.get("settemp")!=null && !"".equals(searchMap.get("settemp"))){
                criteria.andLike("settemp","%"+searchMap.get("settemp")+"%");
            }
            // 温度控制偏差
            if(searchMap.get("tempcontrol")!=null && !"".equals(searchMap.get("tempcontrol"))){
                criteria.andLike("tempcontrol","%"+searchMap.get("tempcontrol")+"%");
            }

            // 主键ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
