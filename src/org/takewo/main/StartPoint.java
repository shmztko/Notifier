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
package org.takewo.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.takewo.notifier.Notifier;
import org.takewo.notifier.feed.FeedNotifier;
import org.takewo.notifier.google.GoogleCalendarReminder;
import org.takewo.notifier.google.GoogleCalendarUpdateNotifier;
import org.takewo.notifier.google.GoogleDocsUpdateNotifier;
import org.takewo.util.PropertiesUtils;

import com.google.gdata.util.common.base.StringUtil;

/**
 * このアプリのスタートポイントです。
 * @author takewo
 */
public class StartPoint {

	/** 通知処理を行うクラスの一覧 */
	private static final List<Notifier> NOTIFIER = new ArrayList<Notifier>();
	static {
		NOTIFIER.add(new GoogleDocsUpdateNotifier());
		NOTIFIER.add(new FeedNotifier());
		NOTIFIER.add(new GoogleCalendarUpdateNotifier());
		NOTIFIER.add(new GoogleCalendarReminder());
	}

	/**
	 * このアプリが実行されるときに呼び出されます。
	 * @param args コマンドライン引数
	 */
	public static void main(final String[] args) {

		setProxy();

		Timer timer = new Timer();
		for (Notifier notifier : NOTIFIER) {
			if (notifier.isEnabled()) {
				timer.schedule(notifier, notifier.getStartDate(), notifier.getPeriod());
			}
		}
	}

	/**
	 * プロキシの設定を行います。
	 * @see detector.properties
	 */
	private static void setProxy() {
		final String proxyHost = PropertiesUtils.getString("proxy.host");
		if (!StringUtil.isEmpty(proxyHost)) {
			System.setProperty("http.proxyHost", proxyHost);
			System.setProperty("https.proxyHost", proxyHost);
		}

		final String proxyPort = PropertiesUtils.getString("proxy.port");
		if (!StringUtil.isEmpty(proxyPort)) {
			System.setProperty("http.proxyPort", proxyPort);
			System.setProperty("https.proxyPort", proxyPort);
		}

		final String proxyException = PropertiesUtils.getString("proxy.exception");
		if (!StringUtil.isEmpty(proxyException)) {
			System.setProperty("http.nonProxyHosts", proxyException);
			System.setProperty("https.nonProxyHosts", proxyException);
		}
	}

}
