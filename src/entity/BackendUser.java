package entity;

import java.sql.Date;

/**
 * ��̨�û���
 * @author Administrator
 *
 */
public class BackendUser {
	private int id;//����
	private int appId;//appId����Դ�ڣ�app_info�������id��
	private String versionNo;//�汾��
	private String versionInfo;//�汾����
	private int publishStatus;//����״̬����Դ�ڣ�data_dictionary��1 ������ 2 �ѷ��� 3 Ԥ������
	private String downloadLink;//��������
	private double versionSize;//�汾��С����λ��M��
	private int createdBy;//�����ߣ���Դ��dev_user��������Ϣ����û�id��
	private Date creationDate;//����ʱ��
	private int modifyBy;//�����ߣ���Դ��dev_user��������Ϣ����û�id��
	private Date modifyDate;//���¸���ʱ��
	private String apkLocPath;//apk�ļ��ķ������洢·��
	private String apkFileName;//�ϴ���apk�ļ�����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getVersionInfo() {
		return versionInfo;
	}
	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}
	public int getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public double getVersionSize() {
		return versionSize;
	}
	public void setVersionSize(double versionSize) {
		this.versionSize = versionSize;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getApkLocPath() {
		return apkLocPath;
	}
	public void setApkLocPath(String apkLocPath) {
		this.apkLocPath = apkLocPath;
	}
	public String getApkFileName() {
		return apkFileName;
	}
	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}
	
}
