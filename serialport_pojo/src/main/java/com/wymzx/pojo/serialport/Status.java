package com.wymzx.pojo.serialport;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * status实体类
 * @author Administrator
 *
 */
@Table(name="status")
public class Status implements Serializable{

	@Id
	private Integer id;//主键ID


	

	private String devid;//设备编码

	private String status;//系统状态

	private String runningstatus;//压缩机运行状态

	private String settemp;//设定温度

	private String gettemp;//采集温度

	private String suo;//锁开关状态

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevid() {
		return devid;
	}
	public void setDevid(String devid) {
		this.devid = devid;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getRunningstatus() {
		return runningstatus;
	}
	public void setRunningstatus(String runningstatus) {
		this.runningstatus = runningstatus;
	}

	public String getSettemp() {
		return settemp;
	}
	public void setSettemp(String settemp) {
		this.settemp = settemp;
	}

	public String getGettemp() {
		return gettemp;
	}
	public void setGettemp(String gettemp) {
		this.gettemp = gettemp;
	}

	public String getSuo() {
		return suo;
	}
	public void setSuo(String suo) {
		this.suo = suo;
	}


	
}
