package com.netchus.domain;

import java.util.Date;

import lombok.Data;

@Data
public class EnvironmentVO {



	int id;
	int useFlag;
	String name;
	String SO2;
	String CO;
	String O3;
	String NO2;
	String PM10;
	String PM25;
	String PM1;
	String TC;
	String humidity;
	String sensorId;
	String dataTime;
	Date regDate;
	

	
	
	
}
