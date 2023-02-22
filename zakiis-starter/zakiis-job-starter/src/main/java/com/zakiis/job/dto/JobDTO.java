package com.zakiis.job.dto;

import java.io.Serializable;

public class JobDTO implements Serializable {

	private static final long serialVersionUID = 7340640653421762652L;

	String jobName;
	String jobGroupName;
	String triggerKey;
	String jobStatus;
	String cron;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroupName() {
		return jobGroupName;
	}
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}
	public String getTriggerKey() {
		return triggerKey;
	}
	public void setTriggerKey(String triggerKey) {
		this.triggerKey = triggerKey;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	
}
