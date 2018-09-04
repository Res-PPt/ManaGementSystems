package entity;

import java.sql.Date;

/**
 * 基础信息表
 * @author Administrator
 *
 */
public class AppInfo {
	private AppInfoData data = new AppInfoData();
	public int getId() {
		return data.id;
	}
	public void setId(int id) {
		this.data.id = id;
	}
	public String getSoftwareName() {
		return data.softwareName;
	}
	public void setSoftwareName(String softwareName) {
		this.data.softwareName = softwareName;
	}
	public String getAPKName() {
		return data.APKName;
	}
	public void setAPKName(String aPKName) {
		data.APKName = aPKName;
	}
	public String getSupportROM() {
		return data.supportROM;
	}
	public void setSupportROM(String supportROM) {
		this.data.supportROM = supportROM;
	}
	public String getInterfaceLanguage() {
		return data.interfaceLanguage;
	}
	public void setInterfaceLanguage(String interfaceLanguage) {
		this.data.interfaceLanguage = interfaceLanguage;
	}
	public double getSoftwareSize() {
		return data.softwareSize;
	}
	public void setSoftwareSize(double softwareSize) {
		this.data.softwareSize = softwareSize;
	}
	public Date getUpdateDate() {
		return data.updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.data.updateDate = updateDate;
	}
	public int getDevld() {
		return data.devld;
	}
	public void setDevld(int devld) {
		this.data.devld = devld;
	}
	public String getAppInfo() {
		return data.appInfo;
	}
	public void setAppInfo(String appInfo) {
		this.data.appInfo = appInfo;
	}
	public int getStatus() {
		return data.status;
	}
	public void setStatus(int status) {
		this.data.status = status;
	}
	public Date getOnSaleDate() {
		return data.onSaleDate;
	}
	public void setOnSaleDate(Date onSaleDate) {
		this.data.onSaleDate = onSaleDate;
	}
	public Date getOffSaleDate() {
		return data.offSaleDate;
	}
	public void setOffSaleDate(Date offSaleDate) {
		this.data.offSaleDate = offSaleDate;
	}
	public int getFlatformId() {
		return data.flatformId;
	}
	public void setFlatformId(int flatformId) {
		this.data.flatformId = flatformId;
	}
	public int getCategoryLevel3() {
		return data.categoryLevel3;
	}
	public void setCategoryLevel3(int categoryLevel3) {
		this.data.categoryLevel3 = categoryLevel3;
	}
	public int getDownloads() {
		return data.downloads;
	}
	public void setDownloads(int downloads) {
		this.data.downloads = downloads;
	}
	public int getCreatedBy() {
		return data.createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.data.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return data.creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.data.creationDate = creationDate;
	}
	public int getModifyBy() {
		return data.modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.data.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return data.modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.data.modifyDate = modifyDate;
	}
	public int getCategoryLevel1() {
		return data.categoryLevel1;
	}
	public void setCategoryLevel1(int categoryLevel1) {
		this.data.categoryLevel1 = categoryLevel1;
	}
	public int getCategoryLevel2() {
		return data.categoryLevel2;
	}
	public void setCategoryLevel2(int categoryLevel2) {
		this.data.categoryLevel2 = categoryLevel2;
	}
	public String getLogoPicPath() {
		return data.logoPicPath;
	}
	public void setLogoPicPath(String logoPicPath) {
		this.data.logoPicPath = logoPicPath;
	}
	public String getLogoLocPath() {
		return data.logoLocPath;
	}
	public void setLogoLocPath(String logoLocPath) {
		this.data.logoLocPath = logoLocPath;
	}
	public int getVersionId() {
		return data.versionId;
	}
	public void setVersionId(int versionId) {
		this.data.versionId = versionId;
	}
	public String getFlatformName() {
		return data.flatformName;
	}
	public void setFlatformName(String flatformName) {
		this.data.flatformName = flatformName;
	}
	public String getCategoryLevel1Name() {
		return data.categoryLevel1Name;
	}
	public void setCategoryLevel1Name(String categoryLevel1Name) {
		this.data.categoryLevel1Name = categoryLevel1Name;
	}
	public String getCategoryLevel2Name() {
		return data.categoryLevel2Name;
	}
	public void setCategoryLevel2Name(String categoryLevel2Name) {
		this.data.categoryLevel2Name = categoryLevel2Name;
	}
	public String getCategoryLevel3Name() {
		return data.categoryLevel3Name;
	}
	public String getVersionNo() {
		return data.versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.data.versionNo = versionNo;
	}
}
