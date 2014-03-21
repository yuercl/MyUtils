package com.yuer.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;

public class ClickRobot {

	@Test
	public void testRobot() {
		try {
			Robot robot = new Robot();
			for (int i = 0; i < 1200; i++) {
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE); // 释放按键
				Thread.sleep(10);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
