package com.wt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;



public class DataProcess {
	private String minLat = "";
	private String maxLat = "";
	private String minLng = "";
	private String maxLng = "";
	final String LatLngFilePath = "C:\\Users\\Administrator\\Desktop\\data\\skew_boundary.txt";
	final static String workersReadPath = "C:\\Users\\Administrator\\Desktop\\data\\worker\\workers0.txt";
	final static String workersWritePath = "C:\\Users\\Administrator\\Desktop\\data\\result\\worker\\workers.txt";
	final static String tasksReadPath = "C:\\Users\\Administrator\\Desktop\\data\\task\\tasks0.txt";
	final static String tasksWritePath = "C:\\Users\\Administrator\\Desktop\\data\\result\\task\\tasks.txt";
	
	/*
	 * 读入经纬度边界
	 * */
	@Test
	public void readLatAndLng() {
		try {
			FileReader latLngReader = new FileReader(LatLngFilePath);
			BufferedReader reader = new BufferedReader(latLngReader);
			String line = reader.readLine();
			String[] str = line.split("\\s+");
			minLat = str[0];
			maxLat = str[2];
			minLng = str[1];
			maxLng = str[3];
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 坐标系转换，从经纬度的坐标系转化为100*100的坐标系
	 * */
	@Test
	public long transform(String readPath, String writePath) {
		long num = 0;
		try {
			FileReader fileReader = new FileReader(readPath);
			BufferedReader reader = new BufferedReader(fileReader);
			
			File file = new File(writePath);
			File fileParent = file.getParentFile();
			if(!fileParent.exists()) fileParent.mkdirs();
			file.createNewFile();
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fw);
			
			String line = null;
			Coordinate location = null;
			while((line = reader.readLine()) != null) {
				String[] str = line.split("\\s+");
				location = new Coordinate(str[0], str[1]);
				location.transform(minLat, maxLat, minLng, maxLng);
				writer.write(location.cx + " " + location.cy);
				writer.newLine();
				num++;
			}
			fileReader.close();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}
	
	public static void main(String[] args) {
		DataProcess dp = new DataProcess();
		dp.readLatAndLng();
		System.out.println("转换：");
		long numWorkers = dp.transform(workersReadPath, workersWritePath);
		System.out.println("worker 的数量是: " + numWorkers);
		long numTasks = dp.transform(tasksReadPath, tasksWritePath);	
		System.out.println("task 的数量是: " + numTasks);
	}
	
}




















