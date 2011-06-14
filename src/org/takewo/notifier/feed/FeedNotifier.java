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
package org.takewo.notifier.feed;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.takewo.notifier.SkypeNotifier;
import org.takewo.util.PropertiesUtils;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;

/**
 * フィードを読込み、最新のエントリを通知します。
 * @author takewo
 * @see notifier.properties
 */
public class FeedNotifier extends SkypeNotifier {

	@Override
	public void execute() throws Exception {
		FeedFetcher fetcher = new HttpURLFeedFetcher();
		SyndFeed feed = fetcher.retrieveFeed(new URL(PropertiesUtils.getString("feed.url")));

		List<SyndEntry> hotEntries = new ArrayList<SyndEntry>();
		for (int i = 0; i < feed.getEntries().size(); i++) {
			SyndEntry entry = (SyndEntry)feed.getEntries().get(i);

			if (getLastNotifiedDate().compareTo(entry.getPublishedDate()) <= 0) {
				hotEntries.add(entry);
			}
		}

		if (!hotEntries.isEmpty()) {
			announce("=== " + feed.getTitle() + " ===");
			for (SyndEntry hotEntry : hotEntries) {
				announce(
					"[" + hotEntry.getTitle() + "] " + hotEntry.getLink()
				);
			}
		}
	}
}


