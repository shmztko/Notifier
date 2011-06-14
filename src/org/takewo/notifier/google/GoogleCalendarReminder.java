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
package org.takewo.notifier.google;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.takewo.util.GDataDateTimeUtils;
import org.takewo.util.GDataDateTimeUtils.CalendarField;
import org.takewo.util.PropertiesUtils;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.ILink.Rel;
import com.google.gdata.data.ILink.Type;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;

/**
 * GoogleCalendarのフィードを読込み、今日の予定があるかどうかを通知します。
 * @author takewo
 * @see notifier.properties
 */
public class GoogleCalendarReminder extends GoogleServiceNotifier {

	@Override
	public Date getStartDate() {
		final Calendar cal = Calendar.getInstance();
		// TODO 日時設定をプロパティファイルへだす？
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 10, 0, 0);
		return cal.getTime();
	}

	@Override
	public void execute() throws Exception {
		final CalendarService service = createGoogleService(CalendarService.class, getClass().getSimpleName());
		CalendarQuery query = new CalendarQuery(new URL(PropertiesUtils.getString("org.takewo.notifier.google.GoogleCalendarReminder.query")));

		final Date today = new Date();
		query.setMinimumStartTime(GDataDateTimeUtils.floor(today, CalendarField.HOUR));
		query.setMaximumStartTime(GDataDateTimeUtils.ceil(today, CalendarField.HOUR));

		final CalendarEventFeed feeds = service.getFeed(query, CalendarEventFeed.class);
		for (CalendarEventEntry entry : feeds.getEntries()) {
			StringBuilder builder = new StringBuilder("今日の予定\n");

			builder.append("予定 : ").append(entry.getTitle().getPlainText()).append("\n");

			for (When when : entry.getTimes()) {
				builder.append("開始 :").append(formatter.format(new Date(when.getStartTime().getValue()))).append("\n");
				builder.append("終了 :").append(formatter.format(new Date(when.getEndTime().getValue()))).append("\n");
			}

			builder.append("リンク : ").append(entry.getLink(Rel.ALTERNATE, Type.HTML).getHref());

			announce(builder.toString());
		}
	}

}
