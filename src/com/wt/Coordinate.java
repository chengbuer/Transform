package com.wt;

public class Coordinate {
	private double x;
	private double y;
	private final double minx = 0;
	private final double maxx = 100;
	private final double miny = 0;
	private final double maxy = 100;
	public double cx;
	public double cy;
	
	
	public Coordinate(String x, String y) {
		this.x = Double.parseDouble(x);
		this.y = Double.parseDouble(y);
	}
	
	public void transform(String minX, String maxX, String minY, String maxY) {
		double mix = Double.parseDouble(minX);
		double max = Double.parseDouble(maxX);
		double miy = Double.parseDouble(minY);
		double may = Double.parseDouble(maxY);
		double lenx = max - mix;
		double leny = may - miy;
		this.x = ((this.x - mix) / lenx) * maxx;
		this.y = ((this.y - miy) / leny) * maxy;
		toInt();
	}
	
	public void toInt() {
		this.cx =  x;
		this.cy =  y;
	}
}
