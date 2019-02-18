package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity 
@Access(AccessType.PROPERTY)
public class Configuration {

	private String positionsEN;
	private String positionsES;
	private String systemName;
	private String bannerURL;
	private String welcomeMessageEN;
	private String welcomeMessageES;
	private String PNDefaultCode;
	
	public Configuration() {
		super();
	}
	
	
	@NotBlank
	public String getPositionsEN() {
		return positionsEN;
	}
	public void setPositionsEN(String positionsEN) {
		this.positionsEN = positionsEN;
	}
	
	@NotBlank
	public String getPositionsES() {
		return positionsES;
	}
	public void setPositionsES(String positionsES) {
		this.positionsES = positionsES;
	}
	
	@NotBlank
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	@NotBlank
	@URL
	public String getBannerURL() {
		return bannerURL;
	}
	public void setBannerURL(String bannerURL) {
		this.bannerURL = bannerURL;
	}
	
	@NotBlank
	public String getWelcomeMessageEN() {
		return welcomeMessageEN;
	}
	public void setWelcomeMessageEN(String welcomeMessageEN) {
		this.welcomeMessageEN = welcomeMessageEN;
	}
	
	@NotBlank
	public String getWelcomeMessageES() {
		return welcomeMessageES;
	}
	public void setWelcomeMessageES(String welcomeMessageES) {
		this.welcomeMessageES = welcomeMessageES;
	}
	
	@NotBlank
	public String getPNDefaultCode() {
		return PNDefaultCode;
	}
	public void setPNDefaultCode(String pNDefaultCode) {
		PNDefaultCode = pNDefaultCode;
	}
	
	
	
	
}
