package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author afeng
 * @date 2018/10/18 14:09
 **/
public class FileChannelDemo
{
    public static void main(String[] args) throws Exception
    {
        //Java中提供的对文件内容的访问,
        // 既可以读文件也可以写文件,可以访问访问文件的任意位置
        RandomAccessFile accessFile = new RandomAccessFile("file/1.txt", "rw");

        //通道类似于io中的流,既可以从通道中读取数据,又可以写数据到通道
        FileChannel inChannel = accessFile.getChannel();

        //字节缓冲区为48B
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(byteBuffer);

        while (bytesRead != -1)
        {
            //System.out.println(bytesRead);
            //首先读数据到Buffer,然后反转Buffer,接着从Buffer中读取数据
            byteBuffer.flip();

            while (byteBuffer.hasRemaining())
            {
                System.out.print((char) byteBuffer.get());
            }

            byteBuffer.clear();
            bytesRead = inChannel.read(byteBuffer);
        }
        accessFile.close();
    }

}
