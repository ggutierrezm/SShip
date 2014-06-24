/*
    Shitty ship it's a free software licensed under GPLv3 license
    Copyright (C) 2014  Guillermo Gutierrez Morote <ggmorote@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.fraguels.s_ship;

import java.util.HashMap;
import javax.xml.parsers.*;

import org.w3c.dom.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class CLanguage {

	private static final String LANGUAGES_FILE = "data/languages.xml";
	private static final String DEFAULT_LANGUAGE = "en_EN";
	
	private static HashMap<String, String> _language = null;
	private static String _languageName = null;
	
	private CLanguage() {	
	}
		
	
	public String getLanguage() {
		return _languageName;
	}

	public static String _T(String key) {
		String string;
		if (_language == null)
		{
			_language = new HashMap<String, String>();	
			_languageName = java.util.Locale.getDefault().toString();
			if (!loadLanguage(_languageName)) {
				loadLanguage(DEFAULT_LANGUAGE);
				_languageName = DEFAULT_LANGUAGE;
			}
		}
		if (_language != null) {
			// Look for string in selected language
			string = _language.get(key);
			
			if (string != null) {
				return string;
			}
		}
	
		// Key not found, return the key itself
		return key;
	}
	
	public String getString(String key, Object... args) {
		return String.format(_T(key), args);
	}
	
	public static boolean loadLanguage(String languageName) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			FileHandle fileHandle = Gdx.files.internal(LANGUAGES_FILE);
			Document doc = db.parse(fileHandle.read());
			
			Element root = doc.getDocumentElement();
			
			NodeList languages = root.getElementsByTagName("language");
			int numLanguages = languages.getLength();
			
			for (int i = 0; i < numLanguages; ++i) {
				Node language = languages.item(i);
				
				if (language.getAttributes().getNamedItem("name").getTextContent().equals(languageName)) {
					_language.clear();
					Element languageElement = (Element)language;
					NodeList strings = languageElement.getElementsByTagName("string");
					int numStrings = strings.getLength();
					
					for (int j = 0; j < numStrings; ++j) {
						NamedNodeMap attributes = strings.item(j).getAttributes();
						String key = attributes.getNamedItem("key").getTextContent();
						String value = attributes.getNamedItem("value").getTextContent();						
						value = value.replace("<br />", "\n");
						_language.put(key, value);
					}
					
					return true;
				}
			}
		}
		catch (Exception e) {
			System.out.println("Error loading languages file " + LANGUAGES_FILE);
			return false;
		}
		
		return false;
	}
}
