package cloud.xuxiaowei.utils;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 处理依赖分析
 * <p>
 * 1. mvn clean install
 * <p>
 * 2. <code>mvn dependency:tree > D:/tree.txt</code>
 * <p>
 * 3. 运行 {@link #main()}
 * <p>
 * 其他命令
 * <p>
 * 下载 源码 sources：<code>mvn dependency:sources</code>
 * <p>
 * 下载 文档 javadoc：<code>mvn dependency:resolve -Dclassifier=javadoc</code>
 * <p>
 * 列举插件依赖：<code>mvn dependency:resolve-plugins</code>
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
class DependenciesTests {

	@Test
	void main() {
		File file = new File("D:/tree.txt");
		StringBuilder stringBuilder = new StringBuilder();
		try (// 创建一个新FileReader，给定从中读取文件 。
				FileReader fileReader = new FileReader(file);

				// 创建一个使用默认大小输入缓冲器的缓冲字符输入流。
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {

			String readLine;
			// 读取文本行。 的线被认为是由一个进料线中的任何一个被终止（“\n”），回车（“\r”），或回车立即由换行遵循。
			while ((readLine = bufferedReader.readLine()) != null) {

				if (readLine.contains("[INFO] cloud.xuxiaowei")) {

				}
				else if (readLine.contains("[INFO] +- ")) {

				}
				else if ("[INFO] ".equals(readLine)) {

				}
				else {
					continue;
				}

				readLine = readLine.replace("[INFO] ", "").replace("+- ", "│        └──");
				stringBuilder.append(System.lineSeparator()).append(readLine);

			}

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(stringBuilder);
	}

	@Test
	void set() {
		File file = new File("D:/tree.txt");
		Set<String> set = new HashSet<>();
		try (// 创建一个新FileReader，给定从中读取文件 。
				FileReader fileReader = new FileReader(file);

				// 创建一个使用默认大小输入缓冲器的缓冲字符输入流。
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {

			String readLine;
			// 读取文本行。 的线被认为是由一个进料线中的任何一个被终止（“\n”），回车（“\r”），或回车立即由换行遵循。
			while ((readLine = bufferedReader.readLine()) != null) {

				if (readLine.contains("[INFO] cloud.xuxiaowei")) {

				}
				else if (readLine.contains("[INFO] +- ")) {

				}
				else if ("[INFO] ".equals(readLine)) {

				}
				else {
					continue;
				}
				if (readLine.contains("cloud.xuxiaowei")) {
					continue;
				}
				set.add(readLine);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : set) {
			System.out.println(line);
		}
	}

}
