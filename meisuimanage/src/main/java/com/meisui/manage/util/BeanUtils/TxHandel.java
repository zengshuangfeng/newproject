package com.meisui.manage.util.BeanUtils;

public class TxHandel implements Runnable {

	@Override
	public void run() {
		while(true){
			
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
