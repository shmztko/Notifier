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

import org.takewo.notifier.SkypeNotifier;
import org.takewo.util.PropertiesUtils;

import com.google.gdata.client.GoogleService;
import com.google.gdata.util.AuthenticationException;

/**
 * Googleのサービスを利用するクラスの親クラスです。
 * @author takewo
 */
public abstract class GoogleServiceNotifier extends SkypeNotifier {

	/**
	 * Googleのサービスを生成します。
	 * @param <T> Googleサービスを継承したタイプ
	 * @param clazz Googleサービスのクラス情報
	 * @param appName Googleのサービスを使用するアプリケーションの名前
	 * @return Googleサービス
	 * @throws AuthenticationException 認証失敗時の例外
	 */
	protected <T extends GoogleService> T createGoogleService(Class<T> clazz, String appName) throws AuthenticationException {
		try {
			T instance = clazz.getConstructor(String.class).newInstance(appName);
			instance.setUserCredentials(getGoogleId(), getPassword());
			return instance;

		} catch (Exception e) {
			throw new RuntimeException("Error while creating google service.", e);
		}
	}

	/**
	 * GoogleサービスのIDを取得します。
	 * @return GoogleサービスのID
	 */
	private String getGoogleId() {
		return PropertiesUtils.getString("google.email");
	}

	/**
	 * Googleサービスのパスワードを取得します。
	 * @return Googleサービスのパスワード
	 */
	private String getPassword() {
		return PropertiesUtils.getString("google.password");
	}
}
