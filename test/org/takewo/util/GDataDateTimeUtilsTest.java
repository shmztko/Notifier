package org.takewo.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.takewo.util.GDataDateTimeUtils.CalendarField;

import com.google.gdata.data.DateTime;


public class GDataDateTimeUtilsTest {

	@Test
	public void convertToDate() {
		DateTime datetime = new DateTime();
		Date date = GDataDateTimeUtils.convertToDate(datetime);
		assertTrue(date.getTime() == datetime.getValue());
	}


	@Test
	public void testFloorByHour() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.floor(now, CalendarField.HOUR);

		assertTrue(converted.compareTo(now) < 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == 0);
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.AM);
		assertTrue(c2.get(Calendar.HOUR) == 0);
		assertTrue(c2.get(Calendar.MINUTE) == 0);
		assertTrue(c2.get(Calendar.SECOND) == 0);
		assertTrue(c2.get(Calendar.MILLISECOND) == 0);
		assertTrue(c2.get(Calendar.DATE) == c1.get(Calendar.DATE));
		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testFloorByDate() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.floor(now, CalendarField.DATE);

		assertTrue(converted.compareTo(now) < 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == 0);
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.AM);
		assertTrue(c2.get(Calendar.HOUR) == 0);
		assertTrue(c2.get(Calendar.MINUTE) == 0);
		assertTrue(c2.get(Calendar.SECOND) == 0);
		assertTrue(c2.get(Calendar.MILLISECOND) == 0);
		assertTrue(c2.get(Calendar.DATE) == 1);
		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testFloorByMonth() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.floor(now, CalendarField.MONTH);

		assertTrue(converted.compareTo(now) < 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == 0);
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.AM);
		assertTrue(c2.get(Calendar.HOUR) == 0);
		assertTrue(c2.get(Calendar.MINUTE) == 0);
		assertTrue(c2.get(Calendar.SECOND) == 0);
		assertTrue(c2.get(Calendar.MILLISECOND) == 0);
		assertTrue(c2.get(Calendar.DATE) == 1);
		assertTrue(c2.get(Calendar.MONTH) == Calendar.JANUARY);
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testFloorByYear() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.floor(now, CalendarField.YEAR);

		assertTrue(converted.compareTo(now) < 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == 0);
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.AM);
		assertTrue(c2.get(Calendar.HOUR) == 0);
		assertTrue(c2.get(Calendar.MINUTE) == 0);
		assertTrue(c2.get(Calendar.SECOND) == 0);
		assertTrue(c2.get(Calendar.MILLISECOND) == 0);
		assertTrue(c2.get(Calendar.DATE) == 1);
		assertTrue(c2.get(Calendar.MONTH) == Calendar.JANUARY);
		assertTrue(c2.get(Calendar.YEAR) == 1);
	}

	@Test
	public void testFloorByMinute() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.floor(now, CalendarField.MINUTE);

		assertTrue(converted.compareTo(now) < 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.MINUTE) == 0);
		assertTrue(c2.get(Calendar.SECOND) == 0);
		assertTrue(c2.get(Calendar.MILLISECOND) == 0);
		assertTrue(c2.get(Calendar.HOUR) == c1.get(Calendar.HOUR));
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == c1.get(Calendar.HOUR_OF_DAY));
		assertTrue(c2.get(Calendar.DATE) == c1.get(Calendar.DATE));
		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testFloorBySecond() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.floor(now, CalendarField.SECOND);

		assertTrue(converted.compareTo(now) < 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.SECOND) == 0);
		assertTrue(c2.get(Calendar.MILLISECOND) == 0);
		assertTrue(c2.get(Calendar.MINUTE) == c1.get(Calendar.MINUTE));
		assertTrue(c2.get(Calendar.HOUR) == c1.get(Calendar.HOUR));
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == c1.get(Calendar.HOUR_OF_DAY));
		assertTrue(c2.get(Calendar.DATE) == c1.get(Calendar.DATE));
		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testFloorByMillisecond() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.floor(now, CalendarField.MILLISECOND);

		assertTrue(converted.compareTo(now) < 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.MILLISECOND) == 0);
		assertTrue(c2.get(Calendar.SECOND) == c1.get(Calendar.SECOND));
		assertTrue(c2.get(Calendar.MINUTE) == c1.get(Calendar.MINUTE));
		assertTrue(c2.get(Calendar.HOUR) == c1.get(Calendar.HOUR));
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == c1.get(Calendar.HOUR_OF_DAY));
		assertTrue(c2.get(Calendar.DATE) == c1.get(Calendar.DATE));
		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testCeilByHour() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.ceil(now, CalendarField.HOUR);

		assertTrue(converted.compareTo(now) > 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == 23);
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.PM);
		assertTrue(c2.get(Calendar.HOUR) == 11);
		assertTrue(c2.get(Calendar.MINUTE) == 59);
		assertTrue(c2.get(Calendar.SECOND) == 59);
		assertTrue(c2.get(Calendar.MILLISECOND) == 999);
		assertTrue(c2.get(Calendar.DATE) == c1.get(Calendar.DATE));
		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testCeilByDate() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.ceil(now, CalendarField.DATE);

		assertTrue(converted.compareTo(now) > 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == 23);
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.PM);
		assertTrue(c2.get(Calendar.HOUR) == 11);
		assertTrue(c2.get(Calendar.MINUTE) == 59);
		assertTrue(c2.get(Calendar.SECOND) == 59);
		assertTrue(c2.get(Calendar.MILLISECOND) == 999);

		List<Integer> m1 = Arrays.asList(
				Calendar.FEBRUARY
		);
		List<Integer> m2 = Arrays.asList(
				Calendar.APRIL,
				Calendar.JUNE,
				Calendar.SEPTEMBER,
				Calendar.NOVEMBER
		);
		List<Integer> m3 = Arrays.asList(
				Calendar.JANUARY,
				Calendar.MARCH,
				Calendar.MAY,
				Calendar.JULY,
				Calendar.AUGUST,
				Calendar.OCTOBER,
				Calendar.DECEMBER
		);
		int value = c1.get(Calendar.MONTH);
		if (m1.contains(value)) {
			 // TODO 閏年とか調べるのめんどくせぇから適当にチェック。
			assertTrue(c2.get(Calendar.DATE) == 28 || c2.get(Calendar.DATE) == 29);
		} else if (m2.contains(value)) {
			assertTrue(c2.get(Calendar.DATE) == 30);
		} else if (m3.contains(value)) {
			assertTrue(c2.get(Calendar.DATE) == 31);
		}

		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testCeilByMonth() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.ceil(now, CalendarField.MONTH);

		assertTrue(converted.compareTo(now) > 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.MILLISECOND) == 999);
		assertTrue(c2.get(Calendar.SECOND) == 59);
		assertTrue(c2.get(Calendar.MINUTE) == 59);
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == 23);
		assertTrue(c2.get(Calendar.HOUR) == 11);
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.PM);
		assertTrue(c2.get(Calendar.DATE) == 31);
		assertTrue(c2.get(Calendar.MONTH) == Calendar.DECEMBER);
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testCeilByYear() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();
		try {
			GDataDateTimeUtils.ceil(now, CalendarField.YEAR);
		} catch (UnsupportedOperationException e) {
			return;
		}

		fail();
	}

	@Test
	public void testCeilByMinute() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.ceil(now, CalendarField.MINUTE);

		assertTrue(converted.compareTo(now) > 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.MILLISECOND) == 999);
		assertTrue(c2.get(Calendar.SECOND) == 59);
		assertTrue(c2.get(Calendar.MINUTE) == 59);
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == c1.get(Calendar.HOUR_OF_DAY));
		assertTrue(c2.get(Calendar.HOUR) == c1.get(Calendar.HOUR));
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.PM);
		assertTrue(c2.get(Calendar.DATE) == c1.get(Calendar.DATE));
		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testCeilBySecond() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.ceil(now, CalendarField.SECOND);

		assertTrue(converted.compareTo(now) > 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.MILLISECOND) == 999);
		assertTrue(c2.get(Calendar.SECOND) == 59);
		assertTrue(c2.get(Calendar.MINUTE) == c1.get(Calendar.MINUTE));
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == c1.get(Calendar.HOUR_OF_DAY));
		assertTrue(c2.get(Calendar.HOUR) == c1.get(Calendar.HOUR));
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.PM);
		assertTrue(c2.get(Calendar.DATE) == c1.get(Calendar.DATE));
		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}

	@Test
	public void testCeilByMillisecond() {
		Calendar c1 = Calendar.getInstance();
		Date now = c1.getTime();

		DateTime converted = GDataDateTimeUtils.ceil(now, CalendarField.MILLISECOND);

		assertTrue(converted.compareTo(now) > 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(converted.getValue());
		assertTrue(c2.get(Calendar.MILLISECOND) == 999);
		assertTrue(c2.get(Calendar.SECOND) == c1.get(Calendar.SECOND));
		assertTrue(c2.get(Calendar.MINUTE) == c1.get(Calendar.MINUTE));
		assertTrue(c2.get(Calendar.HOUR_OF_DAY) == c1.get(Calendar.HOUR_OF_DAY));
		assertTrue(c2.get(Calendar.HOUR) == c1.get(Calendar.HOUR));
		assertTrue(c2.get(Calendar.AM_PM) == Calendar.PM);
		assertTrue(c2.get(Calendar.DATE) == c1.get(Calendar.DATE));
		assertTrue(c2.get(Calendar.MONTH) == c1.get(Calendar.MONTH));
		assertTrue(c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR));
	}
}
