/* This file is part of Eternity II Editor.
 * 
 * Eternity II Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Eternity II Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Eternity II Editor.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Eternity II Editor project is hosted on SourceForge:
 * http://sourceforge.net/projects/eternityii/
 * and maintained by Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */

package org.alcibiade.eternity.editor.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Since we'd like to avoid external dependencies, this will mock Apache Commons
 * IO API.
 */
public class IOUtil {

	public static String toString(InputStream is) throws IOException {
		StringBuilder builder = new StringBuilder();
		Reader reader = new InputStreamReader(is);
		int readBytes = 0;
		char[] buffer = new char[512];

		do {
			readBytes = reader.read(buffer);

			if (readBytes > 0) {
				builder.append(buffer, 0, readBytes);
			}
		} while (readBytes >= 0);

		return builder.toString();
	}

}
