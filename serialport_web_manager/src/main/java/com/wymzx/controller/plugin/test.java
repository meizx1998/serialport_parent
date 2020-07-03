package com.wymzx.controller.plugin;


import com.wymzx.pojo.serialport.Status;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class test {

    public static void main(String[] args) throws PortInUseException, NoSuchPortException, TooManyListenersException, IOException {
        sendinfo("FFFF0C770103DE031E58FFF7");
        new comlistener();
    }

    public static void sendinfo(String a) {
        //枚举类型，获取所有的通行端口，包括232（PORT_SERIAL）、485、并口等等
        Enumeration enumeration= CommPortIdentifier.getPortIdentifiers();
        while (enumeration.hasMoreElements()){
            //判断enumeration里面是否有更多的元素
            //获取下一个元素，该元素包含某个通信端口的所有信息
            CommPortIdentifier commPortIdentifier=
                    (CommPortIdentifier) enumeration.nextElement();
            //如果该端口的类型是串口
            if (commPortIdentifier.getPortType()==CommPortIdentifier.PORT_SERIAL) {
                //判断该串口的名称
                if (commPortIdentifier.getName().equals("COM2")) {
                    try {
                        //打开串口，获得该串口的serialPort对象
                        SerialPort serialPort =
                                (SerialPort) commPortIdentifier.open("", 2000);
                        //设置该串口参数，9600,8,1,n
                        serialPort.setSerialPortParams(38400, 8, 1, 0);
                        //获取输出流，利用输出流发送数据

                        OutputStream outputStream = serialPort.getOutputStream();
                        outputStream.write(toByteArray(a));
                        System.out.println(Arrays.toString(toByteArray(a)));
                        //一定要关闭串口，否则会阻塞该串口，直到你关闭程序
                        serialPort.close();
                        outputStream.close();

                    } catch (PortInUseException e) {
                        System.out.println("PortInUseException抛出，串口被使用");
                        e.printStackTrace();
                    } catch (UnsupportedCommOperationException e) {
                        System.out.println("UnsupportedCommOperationException抛出");
                        e.printStackTrace();
                    } catch (IOException e) {
                        System.out.println("IOException抛出");
                        e.printStackTrace();
                    }
                }
            }
        }

    }




    public static byte[] toByteArray(String hexString) {
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    public static class comlistener implements SerialPortEventListener{

        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {

            byte[] cache = new byte[1024];
            //记录已经到达串口COM21且未被读取的数据的字节（Byte）数。
            int availableBytes = 0;
            ArrayList<Byte> show=new ArrayList<>();

            //如果是数据可用的时间发送，则进行数据的读写
            if(serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE){
                try {
                    availableBytes = inputStream.available();
                    while(availableBytes > 0){
                        inputStream.read(cache);
                        for(int i = 0; i < cache.length && i < availableBytes; i++){
                            //解码并输出数据
//                          System.out.print(cache[i]);
                            show.add(cache[i]);

                        }
                        availableBytes = inputStream.available();
                    }
                    System.out.println();
                    System.out.println(show);
                    ArrayList<Byte> newshow=new ArrayList<>();
                    newshow.addAll(show);
                    new Thread(() -> {


                        try {

                            Byte aByte = newshow.get(4);
                            System.out.println(aByte);
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/serialport","root","root");

                            String sql="insert into status values(null,'a','a','a','a','a','a') ";
                            Statement statement=connection.createStatement();
                            statement.executeUpdate(sql);
                            statement.close();
                            connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }




                    }).start();
                    show.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        CommPortIdentifier com2=null;
        SerialPort serialPort2=null;
        InputStream inputStream=null;
        public comlistener() throws NoSuchPortException, PortInUseException, IOException, TooManyListenersException {
            com2=CommPortIdentifier.getPortIdentifier("COM2");
            serialPort2= (SerialPort) com2.open("Com2EventListener",1000);
            inputStream=serialPort2.getInputStream();
            serialPort2.addEventListener(this);
            serialPort2.notifyOnDataAvailable(true);
        }
    }

}

