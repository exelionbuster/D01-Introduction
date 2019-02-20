
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	private String	systemName, bannerURL, welcomeMessage, PNDefaultCode, language;


	public Configuration() {
		super();
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
	public String getWelcomeMessage() {
		return this.welcomeMessage;
	}
	public void setWelcomeMessageEN(final String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	@NotBlank
	public String getPNDefaultCode() {
		return this.PNDefaultCode;
	}
	public void setPNDefaultCode(final String pNDefaultCode) {
		this.PNDefaultCode = pNDefaultCode;
	}

	@NotBlank
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

}
