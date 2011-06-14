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
package org.takewo.notifier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import org.takewo.util.Logger;
import org.takewo.util.NumericUtils;
import org.takewo.util.PropertiesUtils;

import com.google.gdata.util.common.base.StringUtil;

/**
 * 変更通知クラスの親クラスです。
 * @author takewo
 */
public abstract class Notifier extends TimerTask {

	/** 最後に通知が成功した日時 */
	private Date lastNotifiedDate;

	/** ロガー */
	private Logger logger;

	/** yyyy-MM-dd HH:mm:ss 形式の日付フォーマッタ */
	protected SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Detector の開始日付を取得します。<br>
	 * 継承先クラスでオーバライドされるかもしれないよ。
	 * @return 開始日付
	 */
	public Date getStartDate() {
		return new Date();
	}

	/**
	 * Detectorの実行間隔を取得します。<br>
	 * 継承先クラスでオーバライドされるかもしれないよ。
	 * @return 実行間隔
	 */
	public long getPeriod() {
		final String classSpecifiedPeriod = PropertiesUtils.getStringSafe(getClass().getCanonicalName() + ".fetch.interval.minutes");
		final String periodMinutes = StringUtil.isEmpty(classSpecifiedPeriod) ? PropertiesUtils.getString("default.fetch.interval.minutes") : classSpecifiedPeriod;
		if (!NumericUtils.isPositiveReal(periodMinutes)) {
			throw new IllegalArgumentException("Property \"fetch.interval.minutes\" must positive real number.");

		} else {
			return (long) (Double.parseDouble(periodMinutes) * 60 * 1000);
		}
	}

	/**
	 * 対象の検知クラスが有効かどうかを判定します。
	 * @return 有効なら true, 無効なら false
	 */
	public boolean isEnabled() {
		return Boolean.parseBoolean(PropertiesUtils.getString(getClass().getCanonicalName() + ".enabled"));
	}

	@Override
	public void run() {
		try {
			getLogger().info("実行開始");
			execute();
			getLogger().info("実行完了");

			lastNotifiedDate = new Date(scheduledExecutionTime());

		} catch (Exception e) {
			getLogger().info("実行異常終了");
			getLogger().error("Unexpected error while detecting.", e);
		}
	}

	/**
	 * 通知処理を行います。
	 * @throws Exception 想定外の例外が発生したとき。
	 */
	protected abstract void execute() throws Exception;

	/**
	 * 通知を外部へと出力します。
	 * @param message 通知メッセージ
	 * @throws Exception 通知時の例外
	 */
	protected abstract void announce(final String message) throws Exception;

	/**
	 * 最後に通知が成功した日時を取得します。
	 * @return 最後に通知が成功した日時
	 */
	protected Date getLastNotifiedDate() {
		return lastNotifiedDate == null ? new Date() : lastNotifiedDate;
	}

	/**
	 * ロガーを取得します。
	 * @return ロガー
	 */
	protected Logger getLogger() {
		if (logger == null) {
			logger = createLogger();
		}
		return logger;
	}

	/**
	 * ロガーを生成します。
	 * @return ロガー
	 */
	private Logger createLogger() {
		return Logger.create(getClass());
	}

}
