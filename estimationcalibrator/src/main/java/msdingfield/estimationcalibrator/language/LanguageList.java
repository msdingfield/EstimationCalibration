package msdingfield.estimationcalibrator.language;

import java.util.ArrayList;
import java.util.Collection;

public class LanguageList extends ArrayList<Language> {
	private static final long serialVersionUID = -8444236346332591040L;

	public LanguageList() {
		super();
	}

	public LanguageList(Collection<? extends Language> c) {
		super(c);
	}

	public LanguageList(int initialCapacity) {
		super(initialCapacity);
	}

}
