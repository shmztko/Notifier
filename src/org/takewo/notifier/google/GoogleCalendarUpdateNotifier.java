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
import java.util.Date;

import org.takewo.util.PropertiesUtils;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.ILink.Rel;
import com.google.gdata.data.ILink.Type;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;

/**
 * GoogleCalendarのフィードを読込み、直近の更新を通知します。
 * @author takewo
 * @see notifier.properties
 */
public class GoogleCalendarUpdateNotifier extends GoogleServiceNotifier {

	@Override
	public void execute() throws Exception {

		final CalendarService service = createGoogleService(CalendarService.class, getClass().getSimpleName());

		final CalendarQuery query = new CalendarQuery(new URL(PropertiesUtils.getString("google.calendar.feeds.url")));
		query.setUpdatedMin(new DateTime(getLastNotifiedDate()));

		final CalendarEventFeed eventList = service.getFeed(query, CalendarEventFeed.class);

		for (CalendarEventEntry event : eventList.getEntries()) {
			StringBuilder builder = new StringBuilder("カレンダーが更新されました。\n");
			builder.append("名前 : ").append(event.getTitle().getPlainText()).append("\n");
			builder.append("更新日 : ").append(formatter.format(new Date(event.getUpdated().getValue()))).append("\n");

			for (When when : event.getTimes()) {
				builder.append("開始 :").append(formatter.format(new Date(when.getStartTime().getValue()))).append("\n");
				builder.append("終了 :" + formatter.format(new Date(when.getEndTime().getValue()))).append("\n");
			}

			builder.append("リンク : ").append(event.getLink(Rel.ALTERNATE, Type.HTML).getHref());

			announce(builder.toString());
		}
	}
}
