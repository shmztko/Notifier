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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Logger {

	private SimpleDateFormat fotmatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String className;

	public static Logger create(Class<?> clazz) {
		return new Logger(clazz.getSimpleName());
	}

	public void debug(final String message) {
		log("DEBUG", message);
	}

	public void info(final String message) {
		log("INFO", message);
	}

	public void error(final String message) {
		log("ERROR", message);
	}

	public void error(final String message, final Throwable th) {
		log("ERROR", message);
		th.printStackTrace();
	}

	private void log(final String level, final String message) {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(fotmatter.format(Calendar.getInstance().getTime())).append("]");
		builder.append("[").append(className).append("]");
		builder.append("[").append(level).append("] ");
		builder.append(message);

		System.out.println(builder.toString());
	}

	private Logger(final String className) {
		this.className = className;
	}

}