package msdingfield.estimationcalibrator.language;

import java.util.Locale;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Language implements Comparable<Language> {
	
	@NotNull
	@Pattern(regexp="[a-zA-Z]{3}", message="Must be valid 3 character ISO language code.")
	private String isoCode;
	
	@NotNull
	private String name;
	
	public Language() {
		
	}
	
	public Language(final String isoCode, final String name) {
		this.isoCode = isoCode;
		this.name = name;
	}
	
	public Language(Locale locale) {
		this(locale.getISO3Language(), locale.getDisplayLanguage());
	}
	
	public Language(Language other) {
		this.isoCode = other.isoCode;
		this.name = other.name;
	}
	
	public String getIsoCode() {
		return isoCode;
	}
	public String getName() {
		return name;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isoCode == null) ? 0 : isoCode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (isoCode == null) {
			if (other.isoCode != null)
				return false;
		} else if (!isoCode.equals(other.isoCode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Language rhs) {
		if (rhs == null) {
			return 1;
		}
		
		return this.name.compareTo(rhs.name);
	}
	@Override
	public String toString() {
		return "Language [isoCode=" + isoCode + ", name=" + name + "]";
	}
}
