package msdingfield.estimationcalibrator.language;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class Languages {
	private Languages() {}
	
	private static final LanguageList allLanguages = new LanguageList();
	private static final Map<String, Language> isoToLanguage = Maps.newHashMap();
	
	static {
		List<Locale> locales = Lists.newArrayList(Locale.getAvailableLocales());
		List<Language> languages = Lists.transform(locales, new Function<Locale,Language>(){
			@Override public Language apply(Locale locale) {
				return new Language(locale.getISO3Language(), locale.getDisplayLanguage(locale));
			}});
		TreeSet<Language> sort = new TreeSet<Language>(languages);
		
		allLanguages.addAll(sort);
		for (final Language language : languages) {
			isoToLanguage.put(language.getIsoCode(), language);
		}
	}
	
	public static LanguageList allLanguages() {
		return allLanguages;
	}
	
	public static Language languageForIsoCode(final String isoCode) {
		return isoToLanguage.get(isoCode);
	}
}
