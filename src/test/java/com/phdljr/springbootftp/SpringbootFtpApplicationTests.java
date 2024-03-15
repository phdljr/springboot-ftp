package com.phdljr.springbootftp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;

@SpringBootTest
class SpringbootFtpApplicationTests {

    @Autowired
    FtpRemoteFileTemplate ftpRemoteFileTemplate;

    @Test
    void upload_test() {
        ftpRemoteFileTemplate.execute(session -> {
            File file = new File("test.txt");
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                session.write(fileInputStream, "/HDD1/test");
            }
            return null;
        });
    }

    @Test
    void download_test() {
        ftpRemoteFileTemplate.execute(session -> {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                session.read("/HDD1/test", byteArrayOutputStream);
                System.out.println(byteArrayOutputStream);
            }
            return null;
        });
    }
}
