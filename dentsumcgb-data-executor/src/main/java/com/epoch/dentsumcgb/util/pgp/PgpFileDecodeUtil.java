package com.epoch.dentsumcgb.util.pgp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.util.io.Streams;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class PgpFileDecodeUtil {

    private static void decryptFile(String inputFileName, String keyFileName, char[] passwd, String defaultFileName)
            throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(inputFileName));
        InputStream keyIn = new ClassPathResource(keyFileName).getInputStream();
        decryptFile(in, keyIn, passwd, defaultFileName);
        keyIn.close();
        in.close();
    }

    /**
     * decrypt the passed in message stream
     */

    private static void decryptFile(InputStream in, InputStream keyIn, char[] passwd, String defaultFileName)
            throws IOException {
        in = org.bouncycastle.openpgp.PGPUtil.getDecoderStream(in);
        try {
            JcaPGPObjectFactory pgpF = new JcaPGPObjectFactory(in);
            PGPEncryptedDataList enc;
            Object o = pgpF.nextObject();

            if (o instanceof PGPEncryptedDataList) {
                enc = (PGPEncryptedDataList) o;
            } else {
                enc = (PGPEncryptedDataList) pgpF.nextObject();
            }

            Iterator it = enc.getEncryptedDataObjects();
            PGPPrivateKey sKey = null;
            PGPPublicKeyEncryptedData pbe = null;

            PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
                    org.bouncycastle.openpgp.PGPUtil.getDecoderStream(keyIn), new JcaKeyFingerprintCalculator());

            while (sKey == null && it.hasNext()) {
                pbe = (PGPPublicKeyEncryptedData) it.next();
                sKey = PGPUtil.findSecretKey(pgpSec, pbe.getKeyID(), passwd);
            }

            if (sKey == null) {
                throw new IllegalArgumentException("secret key for message not found.");
            }

            InputStream clear = pbe.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(sKey));
            JcaPGPObjectFactory plainFact = new JcaPGPObjectFactory(clear);
            Object message = plainFact.nextObject();

            if (message instanceof PGPCompressedData) {
                PGPCompressedData cData = (PGPCompressedData) message;
                JcaPGPObjectFactory pgpFact = new JcaPGPObjectFactory(cData.getDataStream());
                message = pgpFact.nextObject();
            }

            if (message instanceof PGPLiteralData) {
                PGPLiteralData ld = (PGPLiteralData) message;
                String outFileName = ld.getFileName();

                if (outFileName.length() == 0) {
                    outFileName = defaultFileName;
                } else {
                    outFileName = defaultFileName;
                }

                InputStream unc = ld.getInputStream();

                OutputStream fOut = new BufferedOutputStream(new FileOutputStream(outFileName));

                Streams.pipeAll(unc, fOut);

                fOut.close();

            } else if (message instanceof PGPOnePassSignatureList) {
                throw new PGPException("encrypted message contains a signed message - not literal data.");

            } else {
                throw new PGPException("message is not a simple encrypted file - type unknown.");
            }

            if (pbe.isIntegrityProtected()) {
                if (!pbe.verify()) {
                    log.error("message failed integrity check");
                } else {
                    log.error("message integrity check passed");
                }

            } else {
                log.error("no message integrity check");
            }

        } catch (PGPException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }

        }

    }

    private static void encryptFile(String outputFileName, String inputFileName, String encKeyFileName,
                                    boolean armor, boolean withIntegrityCheck)
            throws IOException, PGPException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName));
        PGPPublicKey encKey = PGPUtil.readPublicKey(encKeyFileName);
        encryptFile(out, inputFileName, encKey, armor, withIntegrityCheck);
        out.close();
    }

    private static void encryptFile(OutputStream out, String fileName, PGPPublicKey encKey,
                                    boolean armor, boolean withIntegrityCheck) throws IOException {
        if (armor) {
            out = new ArmoredOutputStream(out);
        }
        try {
            byte[] bytes = PGPUtil.compressFile(fileName, CompressionAlgorithmTags.ZIP);
            PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator(
                    new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));
            encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));
            OutputStream cOut = encGen.open(out, bytes.length);
            cOut.write(bytes);
            cOut.close();
            if (armor) {
                out.close();
            }
        } catch (PGPException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }
        }
    }

//遍历读取文件名
    public static List ReadFileName(String path) throws Exception {
        File f = new File(path); //新建文件实例
        File[] list = f.listFiles(); /* 此处获取文件夹下的所有文件 */
        List fileNameList = new ArrayList();
        if (null != list && list.length > 0) {
            for (int i = 0; i < list.length; i++) {
                fileNameList.add(list[i].getName());
                log.info("遍历后的文件名：" + fileNameList.get(i));
            }

        } else {
            throw new Exception("文件夹没有相应的文件");
        }
        return fileNameList;
    }


    /**
     *  test
     */
    public static void main(String[] s) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        boolean encryp = true;  //加密：true  解密：false
        if (encryp) {
            String outPath = "D:\\ideawork\\test\\word.DAT";
            String inputPath = "D:\\ideawork\\test\\word.txt";
            String publicKeys = "pub";  //公钥地址
            encryptFile(outPath, inputPath, publicKeys, true, true);
        } else {
            String inputPath;
            String outPath;
            String address = "D:\\ideawork\\test\\";
            String password = "dentsumcgb@payroll";  //私钥的Key
            String privateKeys = "secret";//私钥地址

            List<String> fileList = ReadFileName(address);

            if (null != fileList) {
                for (int i = 0; i < fileList.size(); i++) {
                    inputPath = address + fileList.get(i);  //被加密的文件
                    if (fileList.get(i).indexOf("DAT") != -1) {
                        outPath = address + fileList.get(i).replace(".DAT", "--.TXT");
                        System.out.println("解密第一个文件，要解密的文件：" + inputPath + "，解密出来的文件" + outPath);
                        decryptFile(inputPath, privateKeys, password.toCharArray(), outPath);
                    } else {
                        continue;
                    }
                }
            }
        }

    }

}