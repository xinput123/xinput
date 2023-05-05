package com.xinput.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * 内存映射文件：可以创建和修改那些因为太大而无法加载到内存中的文件
 */
public class MappedIoDemo {

    private static int numOfInts = 4_000_000;
    private static int numOfUbuffInts = 100_000;
    private static Tester[] testers = {
            new Tester("Stream Write") {
                @Override
                public void test() {
                    try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("/Users/yuanlai/Desktop/1.txt"))))) {
                        for (int i = 0; i < numOfInts; i++) {
                            dos.writeInt(i);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
            new Tester("Mapped Write") {
                @Override
                public void test() {
                    try (FileChannel fc = new RandomAccessFile("/Users/yuanlai/Desktop/1.txt", "rw").getChannel()) {
                        IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                        for (int i = 0; i < numOfInts; i++) {
                            ib.put(i);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() {
                    try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("/Users/yuanlai/Desktop/1.txt")))) {
                        for (int i = 0; i < numOfInts; i++) {
                            dis.readInt();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() {
                    try (FileChannel fc = new FileInputStream(new File("/Users/yuanlai/Desktop/1.txt")).getChannel()) {
                        IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
                        while (ib.hasRemaining()) {
                            ib.get();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() {
                    try (RandomAccessFile raf = new RandomAccessFile(new File("/Users/yuanlai/Desktop/1.txt"), "rw")) {
                        raf.writeInt(1);
                        for (int i = 0; i < numOfUbuffInts; i++) {
                            raf.seek(raf.length() - 4);
                            raf.writeInt(raf.readInt());
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
            new Tester("Mapped Read/Write") {
                @Override
                public void test() {
                    try (
                            FileChannel fc = new RandomAccessFile(
                                    new File("/Users/yuanlai/Desktop/1.txt"), "rw").getChannel()
                    ) {
                        IntBuffer ib =
                                fc.map(FileChannel.MapMode.READ_WRITE,
                                        0, fc.size()).asIntBuffer();
                        ib.put(0);
                        for (int i = 1; i < numOfUbuffInts; i++)
                            ib.put(ib.get(i - 1));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
    };

    public static void main(String[] args) {
        Arrays.stream(testers).forEach(Tester::runTest);
    }

    private abstract static class Tester {
        private String name;

        Tester(String name) {
            this.name = name;
        }

        public void runTest() {
            System.out.println(name + ": ");
            long start = System.nanoTime();
            test();
            double duration = System.nanoTime() - start;
            System.out.format("%.3f%n", duration / 1.0e9);
        }

        public abstract void test();
    }
}
