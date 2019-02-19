
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	private String	positionsEN;
	private String	positionsES;
	private String	systemName;
	private String	bannerURL;
	private String	welcomeMessageEN;
	private String	welcomeMessageES;
	private String	PNDefaultCode;


	public Configuration() {
		super();
	}

	@NotBlank
	public String getPositionsEN() {
		return this.positionsEN;
	}
	public void setPositionsEN(final String positionsEN) {
		this.positionsEN = positionsEN;
	}

	@NotBlank
	public String getPositionsES() {
		return this.positionsES;
	}
	public void setPositionsES(final String positionsES) {
		this.positionsES = positionsES;
	}

	@NotBlank
	public String getSystemName() {
		return this.systemName;
	}
	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}

	@NotBlank
	@URL
	public String getBannerURL() {
		return this.bannerURL;
	}
	public void setBannerURL(final String bannerURL) {
		this.bannerURL = bannerURL;
	}

	@NotBlank
	public String getWelcomeMessageEN() {
		return this.welcomeMessageEN;
	}
	public void setWelcomeMessageEN(final String welcomeMessageEN) {
		this.welcomeMessageEN = welcomeMessageEN;
	}

	@NotBlank
	public String getWelcomeMessageES() {
		return this.welcomeMessageES;
	}
	public void setWelcomeMessageES(final String welcomeMessageES) {
		this.welcomeMessageES = welcomeMessageES;
	}

	@NotBlank
	public String getPNDefaultCode() {
		return this.PNDefaultCode;
	}
	public void setPNDefaultCode(final String pNDefaultCode) {
		this.PNDefaultCode = pNDefaultCode;
	}

}
