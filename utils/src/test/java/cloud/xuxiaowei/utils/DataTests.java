package cloud.xuxiaowei.utils;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 删除 转储SQL文件 结构和数据 中的表结构
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
class DataTests {

	@Test
	void delete() {
		File file = new File("D:\\data.sql");

		List<String> list = new ArrayList<>();

		try (// 创建一个新FileReader，给定从中读取文件 。
				FileReader fileReader = new FileReader(file);

				// 创建一个使用默认大小输入缓冲器的缓冲字符输入流。
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {

			String readLine;
			while ((readLine = bufferedReader.readLine()) != null) {

				if (readLine.startsWith("-- Table structure for")) {
					list.remove(list.size() - 1);
					continue;
				}
				else if (readLine.startsWith("-- Records of ")) {
					list.remove(list.size() - 2);
					list.remove(list.size() - 2);
				}
				else if (readLine.startsWith("DROP TABLE IF EXISTS")) {
					continue;
				}
				else if (readLine.startsWith("CREATE TABLE")) {
					continue;
				}
				else if (readLine.startsWith("  `")) {
					continue;
				}
				else if (readLine.startsWith("  ")) {
					continue;
				}
				else if (readLine.startsWith(") ")) {
					continue;
				}

				list.add(readLine);
			}

		}
		catch (IOException e) {
			e.printStackTrace();
		}

		for (String text : list) {
			System.out.println(text);
		}
	}

}
