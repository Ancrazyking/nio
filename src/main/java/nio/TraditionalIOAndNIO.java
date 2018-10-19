package nio;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author afeng
 * @date 2018/10/19 21:58
 **/
public class TraditionalIOAndNIO
{


    /**
     * 传统IO读取文件
     * FileInputStream
     */
    @Test
    public void traditionalIO()
    {
        InputStream in = null;
        try
        {
            in = new BufferedInputStream(new FileInputStream("file/1.txt"));

            byte[] buffer = new byte[1024];

            int bytesRead = in.read(buffer);
            while (bytesRead != -1)
            {
                for (int i = 0; i < bytesRead; i++)
                {
                    System.out.print((char) buffer[i]);
                }
                bytesRead = in.read(buffer);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * nio读取文件
     */
    @Test
    public void nio() throws Exception
    {
        FileInputStream fis = new FileInputStream("file/1.txt");
        FileChannel fileChannel = fis.getChannel();
        //为缓冲区分配1024个字节
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int bytesRead = fileChannel.read(buffer);
        System.out.println(bytesRead);

        while (bytesRead != -1)
        {
            buffer.flip();
            while (buffer.hasRemaining())
            {
                System.out.print((char) buffer.get());
            }
            buffer.compact();
            bytesRead = fileChannel.read(buffer);
        }
    }
}
