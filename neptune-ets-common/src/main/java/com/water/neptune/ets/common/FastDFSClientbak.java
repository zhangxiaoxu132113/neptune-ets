//package com.water.neptune.ets.common;
//
//import org.csource.common.MyException;
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.*;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * @author Zhang Miaojie
// * @version v1.0
// * @date 2019/8/9
// */
//public class FastDFSClientbak {
//	public static String conf_filename = "F:\\code\\test\\neptune-video\\neptune-video-core\\src\\main\\resources\\fdfs_client.conf";
//	public static String local_filename = "F:\\resources\\test\\e3d741da868a5128a42d7e03cb56e3ba_len39009900.mp4";
//	private static TrackerClient trackerClient;
//	private static TrackerServer trackerServer;
//	private static StorageClient storageClient;
//
//	static {
//		try {
//			ClientGlobal.init(conf_filename);
//			trackerClient = new TrackerClient();
//			trackerServer = trackerClient.getConnection();
//			storageClient = new StorageClient(trackerServer, null);
//		} catch (IOException | MyException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 上传文件
//	 *
//	 * @param uploadFile
//	 * @return
//	 * @throws IOException
//	 * @throws MyException
//	 */
//	public static String[] upload(String uploadFile) throws IOException, MyException {
//		NameValuePair nvp[] = new NameValuePair[] { new NameValuePair("age", "18"), new NameValuePair("sex", "male") };
//		String fileIds[] = storageClient.upload_file(uploadFile, "mp4", nvp);
//
//		System.out.println("组名：" + fileIds[0]);
//		System.out.println("路径: " + fileIds[1]);
//		return fileIds;
//	}
//
//	/**
//	 * <B>方法名称：</B>下载方法<BR>
//	 * <B>概要说明：</B>通过文件id进行下载<BR>
//	 *
//	 * @param fileId
//	 *            文件id
//	 * @return 返回InputStream
//	 */
//	public static InputStream download(String groupName, String fileId) {
//		try {
//			StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, groupName);
//			StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
//			byte[] bytes = storageClient1.download_file1(fileId);
//			InputStream inputStream = new ByteArrayInputStream(bytes);
//			return inputStream;
//		} catch (Exception ex) {
//			return null;
//		}
//	}
//}
