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

import java.util.Calendar;
import java.util.Date;

import com.google.gdata.data.DateTime;

/**
 * GData API の DateTimeクラスに関する便利メソッドを集めたクラスです。
 * @author takewo
 */
public final class GDataDateTimeUtils {

	/**
	 * DateTimeオブジェクトを、java.util.Dateオブジェクトへ変換します。
	 * @param datetime GDataライブラリのDateTimeオブジェクト
	 * @return java.util.Dateオブジェクト
	 */
	public static Date convertToDate(final DateTime datetime) {
		return new Date(datetime.getValue());
	}

	/**
	 * 指定されたFieldで、日付を切り上げます。<br>
	 * e.g. ceil(2011/06/07 18:14:45.234 の日付オブジェクト, CalendarField.HOUR) => 2011/06/07 23:59:59.999
	 * @param date 切り上げる対象の日付オブジェクト
	 * @param field どこから切り上げるかの指定
	 * @return 切り上げた後のDateTimeオブジェクト
	 */
	public static DateTime ceil(final Date date, final CalendarField field) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		switch (field) {
		case YEAR:
			throw new UnsupportedOperationException("Operation fo CalendarField#YEAR does not work correctly");

		case MONTH:
			c.set(Calendar.MONTH, c.getActualMaximum(Calendar.MONTH));

		case DATE:
			c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));

		case HOUR:
			c.set(Calendar.HOUR, c.getActualMaximum(Calendar.HOUR));
			c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));

		case MINUTE:
			c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));

		case SECOND:
			c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));

		case MILLISECOND:
			c.set(Calendar.MILLISECOND, c.getActualMaximum(Calendar.MILLISECOND));
			break;
		default:
			throw new IllegalArgumentException("Unsupported calendar field. -> " + field);
		}

		return new DateTime(c.getTime());
	}

	/**
	 * 指定されたFieldで、日付を切り捨てます。<br>
	 * e.g. ceil(2011/06/07 18:14:45.234 の日付オブジェクト, CalendarField.HOUR) => 2011/06/07 00:00:00.000
	 * @param date 切り捨てる対象の日付オブジェクト
	 * @param field どこから切り捨てるかの指定
	 * @return 切り捨てた後のDateTimeオブジェクト
	 */
	public static DateTime floor(final Date date, final CalendarField field) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		switch (field) {
		case YEAR:
			c.set(Calendar.YEAR, c.getActualMinimum(Calendar.YEAR));

		case MONTH:
			c.set(Calendar.MONTH, c.getActualMinimum(Calendar.MONTH));

		case DATE:
			c.set(Calendar.DATE, c.getActualMinimum(Calendar.DATE));

		case HOUR:
			c.set(Calendar.HOUR, c.getActualMinimum(Calendar.HOUR));
			c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));

		case MINUTE:
			c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));

		case SECOND:
			c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));

		case MILLISECOND:
			c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
			break;
		default:
			throw new IllegalArgumentException("Unsupported calendar field. -> " + field);
		}

		return new DateTime(c.getTime());
	}

	/**
	 * GDataDateTimeUtilsで使用される、CalendarクラスのField指定用定数。<br>
	 * {@link Calendar#DATE}などの、定数だと意図しないField値が渡された場合に、
	 * 適切な例外を投げることができないので。
	 * @author takewo
	 */
	public enum CalendarField {
		/** 年 */
		YEAR,
		/** 月 */
		MONTH,
		/** 日 */
		DATE,
		/** 時 */
		HOUR,
		/** 分 */
		MINUTE,
		/** 秒 */
		SECOND,
		/** ミリ秒 */
		MILLISECOND;
	}
}
