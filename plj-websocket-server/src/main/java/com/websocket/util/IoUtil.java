package com.websocket.util;

import java.io.*;

/**
 * @author 潘要东
 * @create 2018-04-02 13:33
 * @descriptions <p>I／O操作辅助工具类</p>
 */
public final class IoUtil {

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/27 15:36
     * @descriptions <p>关闭输出流</p>
     * @params [os]
     * @return void
     ****************************************************************************************************/
    public static void closeOutputStream(OutputStream os) {
        if (os == null) {
            return;
        }
        try {
            os.close();
        } catch (IOException ioe) {
        }
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/27 15:37
     * @descriptions <p>关闭输入流</p>
     * @params [is]
     * @return void
     ****************************************************************************************************/
    public static void closeInputStream(InputStream is) {
        if (is == null) {
            return;
        }
        try {
            is.close();
        } catch (IOException ioe) {
        }
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/27 15:37
     * @descriptions <p>关闭 {@link Writer}</p>
     * @params [writer]
     * @return void
     ****************************************************************************************************/
    public static void closeWriter(Writer writer) {
        if (writer == null) {
            return;
        }
        try {
            writer.close();
        } catch (IOException ioe) {
        }
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/27 15:38
     * @descriptions <p>关闭 {@link Reader}</p>
     * @params [reader]
     * @return void
     ****************************************************************************************************/
    public static void closeReader(Reader reader) {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        } catch (IOException e) {
        }
    }
}
