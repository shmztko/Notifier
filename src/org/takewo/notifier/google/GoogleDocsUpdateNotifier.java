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

import com.google.gdata.client.Query;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.ILink.Rel;
import com.google.gdata.data.ILink.Type;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;

/**
 * GoogleDocsのフィードを読込み、直近の更新を通知します。
 * @author takewo
 * @see notifier.properties
 */
public class GoogleDocsUpdateNotifier extends GoogleServiceNotifier {

	@Override
	public void execute() throws Exception {
		final DocsService service = createGoogleService(DocsService.class, getClass().getSimpleName());

		final Query query = new Query(new URL(PropertiesUtils.getString("google.documents.feeds.url")));
		query.setUpdatedMin(new DateTime(getLastNotifiedDate()));

		final DocumentListFeed documentList = service.getFeed(query, DocumentListFeed.class);
		for (DocumentListEntry document : documentList.getEntries()) {

			StringBuilder builder = new StringBuilder("ドキュメントが更新されました。\n");
			builder.append("名前 : ").append(document.getTitle().getPlainText()).append("\n");
			builder.append("更新日 : ").append(formatter.format(new Date(document.getUpdated().getValue()))).append("\n");
			builder.append("リンク : ").append(document.getLink(Rel.ALTERNATE, Type.HTML).getHref());

			announce(builder.toString());
		}
	}
}
