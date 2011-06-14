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

import java.util.regex.Pattern;

/**
 * 数値系の便利メソッドを集めたクラスです。
 * @author takewo
 */
public final class NumericUtils {

	// MEMO : 例外を拾ってうにゃうにゃするのはいやなので、正規表現を使う。
	/** 整数かどうかを判定するための正規表現 */
	private static final Pattern INTEGER_REGEXP = Pattern.compile("-?[0-9]+");
	/** 実数かどうかを判定するための正規表現 */
	private static final Pattern REAL_REGEXP = Pattern.compile("-?[0-9]($|\\.[0-9]+|[0-9]*)");
	/** 正の実数かどうかを判定するための正規表現 */
	private static final Pattern POSITVE_REAL_REGEXP = Pattern.compile("[0-9]($|\\.[0-9]+|[0-9]*)");

	/**
	 * 整数かどうかを判定します。
	 * @param number 判定用数値の文字列表現
	 * @return 整数なら true, それ以外は false
	 */
	public static boolean isInteger(final String number) {
		return INTEGER_REGEXP.matcher(number).matches();
	}

	/**
	 * 実数かどうかを判定します。
	 * @param number 判定用数値の文字列表現
	 * @return 実数なら true, それ以外は false
	 */
	public static boolean isReal(final String number) {
		return REAL_REGEXP.matcher(number).matches();
	}

	/**
	 * 正の実数かどうかを判定します。
	 * @param number 判定用数値の文字列表現
	 * @return 正の実数なら true, それ以外は false
	 */
	public static boolean isPositiveReal(final String number) {
		return POSITVE_REAL_REGEXP.matcher(number).matches();
	}
}
