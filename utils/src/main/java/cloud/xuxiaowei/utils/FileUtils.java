package cloud.xuxiaowei.utils;

import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
public class FileUtils {

    /**
     * 获取文件大小（含单位）
     * @param fileSize 文件原始大小
     * @return 文件大小（含单位）
     */
    public static String formatFileSize(long fileSize) {
        String result;
        if (fileSize >= 1024 * 1024 * 1024) {
            result = String.format("%.2fG", (double) fileSize / (1024 * 1024 * 1024));
        }
        else if (fileSize >= 1024 * 1024) {
            result = String.format("%.2fM", (double) fileSize / (1024 * 1024));
        }
        else if (fileSize >= 1024) {
            result = String.format("%.2fK", (double) fileSize / 1024);
        }
        else {
            result = String.format("%d bytes", fileSize);
        }
        return result;
    }

	/**
	 * 向压缩包中添加文件内容并指定文件名（包含文件路径）
	 * <p>
	 * 可以使用下面代码来创建压缩包输出流（注意关闭流）
	 * <p>
	 * <code>
	 *  FileOutputStream fos = new FileOutputStream("D:\\files.zip");
	 *  ZipOutputStream zos = new ZipOutputStream(fos);
	 * </code>
	 * @param zos 压缩包输出流
	 * @param zipEntryName 压缩包内部文件名（包含文件路径）
	 * @param text 文件内容
	 * @throws IOException 向压缩包添加文件异常
	 */
	public static void write(ZipOutputStream zos, String zipEntryName, String text) throws IOException {
		ZipEntry entry = new ZipEntry(zipEntryName);
		zos.putNextEntry(entry);
		byte[] bytes = text.getBytes();
		zos.write(bytes, 0, bytes.length);
		zos.closeEntry();
	}

	/**
	 * 读取文件
	 * @param file 文件
	 * @return 返回 文件内容
	 */
	public static String reader(File file) {
		StringBuilder stringBuilder = new StringBuilder();
		try (// 创建一个新FileReader，给定从中读取文件 。
				FileReader fileReader = new FileReader(file);
				// 创建一个使用默认大小输入缓冲器的缓冲字符输入流。
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			String readLine;
			// 读取文本行。 的线被认为是由一个进料线中的任何一个被终止（“\n”），回车（“\r”），或回车立即由换行遵循。
			while ((readLine = bufferedReader.readLine()) != null) {
				stringBuilder.append(System.lineSeparator()).append(readLine);
			}
		}
		catch (IOException e) {
			String path = file.getPath();
			throw new CloudRuntimeException("读取文件异常：{}", path, e);
		}
		return stringBuilder.toString();
	}

	/**
	 * 读取文件
	 * @param pathname 文件路径
	 * @return 返回 文件内容
	 */
	public static String reader(String pathname) {
		File file = new File(pathname);
		return reader(file);
	}

	/**
	 * 根据 URL 路径 下载文件
	 * @param urlPath URL
	 * @param filePath 路径
	 * @return 文件大小
	 */
	public static int downloadFile(String urlPath, String filePath) {
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			URL url = new URL(urlPath);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			conn.connect();
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = conn.getInputStream();
				outputStream = new FileOutputStream(filePath);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length);
				}
				log.info("URL：{} 文件：{} 大小：{}", urlPath, filePath, conn.getContentLength());
				return conn.getContentLength();
			}
			else {
				log.error("下载 URL：{} 失败，状态码：{}", urlPath, conn.getResponseCode());
				return 0;
			}
		}
		catch (MalformedURLException e) {
			log.error("创建下载 URL：{} 异常：", urlPath, e);
			throw new RuntimeException(e);
		}
		catch (IOException e) {
			log.error("打开 URL：{} 链接异常：", urlPath, e);
			throw new RuntimeException(e);
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
					log.error("关闭 inputStream 异常：", e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				}
				catch (IOException e) {
					log.error("关闭 outputStream 异常：", e);
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	/**
	 * 解压 ZIP
	 * @param filePath 压缩文件夹路径
	 * @param zipDir 解压后的路径
	 */
	public static void unzip(String filePath, String zipDir) {

		try (ZipFile zipfile = new ZipFile(filePath)) {
			Enumeration<? extends ZipEntry> dir = zipfile.entries();

			String name;
			ZipEntry entry;

			while (dir.hasMoreElements()) {
				entry = dir.nextElement();

				if (entry.isDirectory()) {
					name = entry.getName();
					name = name.substring(0, name.length() - 1);
					File fileObject = new File(zipDir + File.separator + name);
					if (!fileObject.exists()) {
						boolean mkdir = fileObject.mkdirs();
						if (!mkdir) {
							log.error("创建解压目录 {} 失败！", fileObject.getAbsolutePath());
							throw new RuntimeException("创建解压目录失败！");
						}
					}
				}
			}

			int bufferSize = 1024;

			Enumeration<? extends ZipEntry> e = zipfile.entries();
			while (e.hasMoreElements()) {
				entry = e.nextElement();
				if (entry.isDirectory()) {
					continue;
				}
				String entryName = entry.getName();
				if ("".equals(entryName.trim())) {
					log.warn("entryName is null or empty, entry={}", entry);
					continue;
				}
				try (InputStream inputStream = zipfile.getInputStream(entry);
						OutputStream outputStream = new FileOutputStream(zipDir + File.separator + entryName);
						BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream,
								bufferSize)) {

					int count;
					byte[] dataByte = new byte[bufferSize];

					while ((count = bufferedInputStream.read(dataByte, 0, bufferSize)) != -1) {
						bufferedOutputStream.write(dataByte, 0, count);
					}

				}
				catch (FileNotFoundException ex) {
					log.error("文件不存在异常：{}", filePath, ex);
					throw new RuntimeException(ex);
				}
				catch (IOException ex) {
					log.error("解压异常：{}", filePath, ex);
					throw new RuntimeException(ex);
				}
			}

		}
		catch (IOException e) {
			log.error("创建压缩文件异常：{}", filePath, e);
			throw new RuntimeException(e);
		}

	}

}
