package com.mop.game.connection.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.mop.game.connection.pns.PNSNetPusher;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.Payload;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.transmission.NotificationThreads;

public class PushNotificationServer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		List<PushedNotification> notifications = Push.alert("װ��ǿ��ħfֵ100��2��", "3war.dev.apns.p12", "renrengames54", false, "6372e56bedc4b65d69f6fd5a96cb4a42cb93770e631c361fe0aec07efdc78e1e");
//		for (PushedNotification pushedNotification : notifications) {
//			Device device = pushedNotification.getDevice();
//			System.out.println(device.getDeviceId());
//		}
		
		
//		PushNotificationServer server = new PushNotificationServer("3war.dev.apns.p12", "renrengames54", false);
		PushNotificationServer server = new PushNotificationServer(PNSNetPusher.class.getResource("/").getPath()+"3war.dev.apns.p12", "renrengames54", true);
//		PushNotificationServer server = new PushNotificationServer("3wardistapns2.p12", "renrengames54", true);

		Payload payload = PushNotificationPayload.alert("乱世V5！！！");
		List<Device> devices = new ArrayList<Device>();
		//zhengying ipod release
//		devices.add(new BasicDevice("d1c50afdc67b0338aaca03b5e20783e77a2fe709361415c12de55cc27dd37921"));
		
//		devices.add(new BasicDevice("6372e56bedc4b65d69f6fd5a96cb4a42cb93770e631c361fe0aec07efdc78e1e"));
//		devices.add(new BasicDevice("91eac761f5ec9daff037b87eb2032c14fd4d50da64fac94cbcb126ffec3629b1"));
		
		//test ipad
		devices.add(new BasicDevice("9cef320b8457ba42f8dcc59458499787029b80f1e503093662a4e8068617910e"));
//		devices.add(new BasicDevice("8d702a4d4749a6b709b5488caf64a453f720391f37d6c0b41cc5562b3227c1d4"));
//	
//		//����
//		devices.add(new BasicDevice("dcb15e3e241b5dc18cc43838fe39a5d3d57e3338e06bc15c55ca7695397e589a"));
//		//����
//		devices.add(new BasicDevice("f371e1d8ae791aa45b94c4815ccf1ecce74b1ffb2b474054886ea936279cb86f"));
		//С��
//		devices.add(new BasicDevice("d5a2a4d2ce0fc42585e5e49255341f30cd26e7241faa090f6eddcc87fb6e6dff"));
		
		
		server.send(devices, payload);
		
//		//3warjp
//		PushNotificationServer server = new PushNotificationServer("3warjpapnsdev.p12", "renrengames54", false);
////		PushNotificationServer server = new PushNotificationServer("3warjpapnsdist.p12", "renrengames54", true);
//		Payload payload = PushNotificationPayload.alert("�������£��ҵ����£�");
//		List<Device> devices = new ArrayList<Device>();
//		devices.add(new BasicDevice("6372e56bedc4b65d69f6fd5a96cb4a42cb93770e631c361fe0aec07efdc78e1e"));
//		devices.add(new BasicDevice("91eac761f5ec9daff037b87eb2032c14fd4d50da64fac94cbcb126ffec3629b1"));
//		devices.add(new BasicDevice("6372e56bedc4b65d69f6fd5a96cb4a42cb93770e631c361fe0aec07efdc78e1e"));
//		
//		server.send(devices, payload);
		
	}
	
    AppleNotificationServer server = null;
	public PushNotificationServer(Object keystore, String password, boolean production) {
	       /* Gather communication details */ 
		try {
			server = new AppleNotificationServerBasicImpl(keystore, password, production);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
		
	}

	public void send (List<Device> devices, Payload payload) {

	       /* Create 30 threads to spread the work evenly */ 
	        NotificationThreads work = new NotificationThreads(server, payload, devices, 30); 
	 

	       /* Start all work threads simultaneously */ 
	        work.start();
	 

	       /* Wait for all threads to finish their work */ 
	        try {
				work.waitForAllThreads();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 

	       /* Get a list of all pushed notifications */ 
	        List<PushedNotification> notifications = work.getPushedNotifications();
            for (PushedNotification notification : notifications) {
                if (notification.isSuccessful()) {
                        /* Apple accepted the notification and should deliver it */  
                        System.out.println("Push notification sent successfully to: " +
                                                        notification.getDevice().getToken());
                        /* Still need to query the Feedback Service regularly */  
                } else {
                        String invalidToken = notification.getDevice().getToken();
                        /* Add code here to remove invalidToken from your database */  
                        System.out.println("Push notification sent failed to: " + invalidToken);
                }
        }
	 

	}	
}
