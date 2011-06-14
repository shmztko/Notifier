package org.takewo.notifier;

import com.skype.Chat;
import com.skype.Skype;
import com.skype.SkypeException;

/**
 * Skypeへの変更通知クラスです。
 * @author takewo
 */
public abstract class SkypeNotifier extends Notifier {

	/**
	 * スカイプへ出力します。
	 * @param message 通知メッセージ
	 * @throws SkypeException スカイプとの接続例外
	 */
	protected void announce(final String message) throws SkypeException {
		for (Chat chat : Skype.getAllActiveChats()) {
			chat.send(message);
		}
	}
}
