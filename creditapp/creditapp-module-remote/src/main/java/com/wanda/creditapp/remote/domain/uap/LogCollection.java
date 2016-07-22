package com.wanda.creditapp.remote.domain.uap;

public class LogCollection {
	// ip String N IP地址
	private String ip;

	// terminal String N PC/APP
	private String terminal;

	// vendor String N 设备厂商. 三星、HTC
	private String vendor;

	// IMEI String N 安卓设备标识
	private String IMEI;

	// deviceId String N IOS设备号
	private String deviceId;

	// OS String N 操作系统
	private String OS;

	// screenSize String N 屏幕尺寸
	private String screenSize;

	// network String N 手机网络型号
	private String network;

	// mac String N PC设备号或路由器设备号
	private String mac;

	// GPS String N 经、纬度，逗号分隔
	private String GPS;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOS() {
		return OS;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getGPS() {
		return GPS;
	}

	public void setGPS(String gPS) {
		GPS = gPS;
	}

}
