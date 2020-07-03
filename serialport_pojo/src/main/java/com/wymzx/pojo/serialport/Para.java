package com.wymzx.pojo.serialport;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * para实体类
 * @author Administrator
 *
 */
@Table(name="para")
public class Para implements Serializable{

	@Id
	private Integer id;//主键ID


	

	private String devlocation;//设备地址

	private String timewait;//状态上传时间间隔

	private String timelate;//压缩机启动延时

	private String settemp;//设定温度

	private String tempcontrol;//温度控制偏差

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevlocation() {
		return devlocation;
	}
	public void setDevlocation(String devlocation) {
		this.devlocation = devlocation;
	}

	public String getTimewait() {
		return timewait;
	}
	public void setTimewait(String timewait) {
		this.timewait = timewait;
	}

	public String getTimelate() {
		return timelate;
	}
	public void setTimelate(String timelate) {
		this.timelate = timelate;
	}

	public String getSettemp() {
		return settemp;
	}
	public void setSettemp(String settemp) {
		this.settemp = settemp;
	}

	public String getTempcontrol() {
		return tempcontrol;
	}
	public void setTempcontrol(String tempcontrol) {
		this.tempcontrol = tempcontrol;
	}


	
}
