//package com.water.neptune.ets.common;
//
//import lombok.extern.slf4j.Slf4j;
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.*;
//import org.springframework.core.io.ClassPathResource;
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
//@Slf4j
//public class FastDFSClient {
//
//	static {
//		try {
//			String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
//			ClientGlobal.init(filePath);
//		} catch (Exception e) {
//			log.error("FastDFS Client Init Fail!", e);
//		}
//	}
//
//	public static String[] upload(String uploadFile) {
//		try {
//			NameValuePair nvp[] = new NameValuePair[] { new NameValuePair("age", "18"),
//					new NameValuePair("sex", "male") };
//			String fileIds[] = getTrackerClient().upload_file(uploadFile, "mp4", nvp);
//
//			System.out.println("组名：" + fileIds[0]);
//			System.out.println("路径: " + fileIds[1]);
//			return fileIds;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public static FileInfo getFile(String groupName, String remoteFileName) {
//		try {
//			StorageClient storageClient = getTrackerClient();
//			return storageClient.get_file_info(groupName, remoteFileName);
//		} catch (IOException e) {
//			logger.error("IO Exception: Get File from Fast DFS failed", e);
//		} catch (Exception e) {
//			logger.error("Non IO Exception: Get File from Fast DFS failed", e);
//		}
//		return null;
//	}
//
//	public static InputStream downFile(String groupName, String remoteFileName) {
//		try {
//			StorageClient storageClient = getTrackerClient();
//			byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
//			InputStream ins = new ByteArrayInputStream(fileByte);
//			return ins;
//		} catch (IOException e) {
//			logger.error("IO Exception: Get File from Fast DFS failed", e);
//		} catch (Exception e) {
//			logger.error("Non IO Exception: Get File from Fast DFS failed", e);
//		}
//		return null;
//	}
//
//	public static void deleteFile(String groupName, String remoteFileName) throws Exception {
//		StorageClient storageClient = getTrackerClient();
//		int i = storageClient.delete_file(groupName, remoteFileName);
//		logger.info("delete file successfully!!!" + i);
//	}
//
//	public static StorageServer[] getStoreStorages(String groupName) throws IOException {
//		TrackerClient trackerClient = new TrackerClient();
//		TrackerServer trackerServer = trackerClient.getConnection();
//		return trackerClient.getStoreStorages(trackerServer, groupName);
//	}
//
//	public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
//		TrackerClient trackerClient = new TrackerClient();
//		TrackerServer trackerServer = trackerClient.getConnection();
//		return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
//	}
//
//	public static String getTrackerUrl() throws IOException {
//		return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":"
//				+ ClientGlobal.getG_tracker_http_port() + "/";
//	}
//
//	private static StorageClient getTrackerClient() throws IOException {
//		TrackerServer trackerServer = getTrackerServer();
//		StorageClient storageClient = new StorageClient(trackerServer, null);
//		return storageClient;
//	}
//
//	private static TrackerServer getTrackerServer() throws IOException {
//		TrackerClient trackerClient = new TrackerClient();
//		TrackerServer trackerServer = trackerClient.getConnection();
//		return trackerServer;
//	}
//}
