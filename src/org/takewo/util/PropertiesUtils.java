/*
 *    Copyright 2011 Shimizu Takeo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.takewo.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * notifier.properties からプロパティを取得する為のユーティリティクラスです。
 * @author takewo
 */
public final class PropertiesUtils {

	/** リソースバンドル */
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("notifier");

	/**
	 * プロパティファイルから、キー値に対応する文字列を取得します。
	 * @param key プロパティのキー値
	 * @return プロパティファイルから取得した文字列
	 */
	public static String getString(final String key) {
		return BUNDLE.getString(key);
	}

	/**
	 * プロパティファイルから、キー値に対応する文字列を例外を送出することなく取得します。
	 * @param key プロパティのキー値
	 * @return プロパティファイルから取得した文字列。プロパティファイルにキー値がない場合には、空文字。
	 */
	public static String getStringSafe(final String key) {
		try {
			return getString(key);
		} catch (MissingResourceException e) {
			return "";
		}
	}

	/**
	 * プロパティファイルから、与えられたパラメータを埋め込みだ、キー値に対応する文字列を取得します。
	 * @param key プロパティのキー値
	 * @param params 文字列に埋め込む値
	 * @return プロパティファイルから取得した文字列
	 */
	public static String getString(final String key, final Object ... params) {
		return MessageFormat.format(getString(key), params);
	}

	/**
	 * このクラスがインスタンス化されるときに呼び出されます。<br>
	 * 外部からインスタンス化されないように、private 宣言しています。
	 */
	private PropertiesUtils() {
	}
}
