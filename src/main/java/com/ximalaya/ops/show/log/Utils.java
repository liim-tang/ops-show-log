package com.ximalaya.ops.show.log;

import java.io.*;

/**
 * Created by nihao on 17/1/7.
 */
public class Utils {
    public static String readFromResource(String resource){
        InputStream in = null;
        BufferedReader reader = null;
        StringBuilder sb=new StringBuilder();
        try{
            in=Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            if(in==null){
                in=Utils.class.getResourceAsStream(resource);
            }
            if(in == null) {
                return null;
            }
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                sb.append(line+"\r\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    public static byte[] readByteArrayFromResource(String resource) {
        InputStream in = null;
        byte[] var2=null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            if(in == null) {
                return null;
            }
            var2 = readByteArray(in);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return var2;
    }
    public static byte[] readByteArray(InputStream input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] bytes=null;
        try {
            copy(input, output);
            bytes=output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
    public static long copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        long count = 0L;
        int n1;
        for(; -1 != (n1 = input.read(buffer)); count += (long)n1) {
            output.write(buffer, 0, n1);
        }
        return count;
    }
}
