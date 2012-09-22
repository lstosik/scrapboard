package net.purevirtual.scrapboard;

import java.util.Arrays;
import org.bouncycastle.crypto.BlockCipher;

/**
 * Cipher-block chaining
 *
 */
public class CBC extends AbstractBlockMode {

    /**
     * 16 bytes = 128 bits
     */
    private static final int BLOCK_SIZE = 16;

    public byte[] encode(byte iv[], byte plaintext[], BlockCipher cipher) {
        byte[] output;
        int padSize = BLOCK_SIZE - plaintext.length % BLOCK_SIZE;
        int blocks = (plaintext.length + padSize) / BLOCK_SIZE;
        int outputSize = iv.length + plaintext.length + padSize;
        //System.out.println("outputSize="+outputSize);
        output = new byte[outputSize];
        System.arraycopy(iv, 0, output, 0, BLOCK_SIZE);
        byte[] inputBlock = new byte[BLOCK_SIZE];
        byte[] mod = Arrays.copyOf(iv, iv.length);
        //System.out.println("IV="+Hex.encodeHexString(iv));
        for (int b = 0; b < blocks; b++) {
            xor(mod, 0, plaintext, b * BLOCK_SIZE, inputBlock, 0, BLOCK_SIZE, (byte) padSize);
            cipher.processBlock(inputBlock, 0, output, b * BLOCK_SIZE + BLOCK_SIZE);
            System.arraycopy(output, b * BLOCK_SIZE + BLOCK_SIZE, mod, 0, BLOCK_SIZE);
        }

        return output;
    }

    public byte[] decode(byte cipherText[], BlockCipher cipher) {
        byte iv[] = Arrays.copyOf(cipherText, BLOCK_SIZE);
        //System.out.println("IV="+Hex.encodeHexString(iv));
        int blocks = cipherText.length / BLOCK_SIZE - 1;
        byte[] output = new byte[cipherText.length - BLOCK_SIZE];
        byte[] fragmentBlock = new byte[BLOCK_SIZE];
        byte[] mod = iv;
        for (int b = 0; b < blocks; b++) {
            cipher.processBlock(cipherText, b * BLOCK_SIZE + BLOCK_SIZE, fragmentBlock, 0);
            xor(mod, 0, fragmentBlock, 0, output, b * BLOCK_SIZE, BLOCK_SIZE, (byte) 0/*
                     * ignored
                     */);
            System.arraycopy(cipherText, b * BLOCK_SIZE + BLOCK_SIZE, mod, 0, BLOCK_SIZE);
        }
        byte lastByte = output[output.length - 1];
        return Arrays.copyOfRange(output, 0, output.length - lastByte);
    }

    private static void xor(byte[] i1, int offset1, byte message[], int messageOffset, byte output[], int outputOffset, int length, byte padByte) {
        for (int i = 0; i < length; i++) {
            if (messageOffset + i >= message.length) {
                output[outputOffset + i] = (byte) (i1[offset1 + i] ^ padByte);
            } else {
                output[outputOffset + i] = (byte) (i1[offset1 + i] ^ message[messageOffset + i]);
            }
        }
    }

    @Override
    public byte[] encode(byte[] iv, byte[] plaintext, BlockModeContext context) {
        return encode(iv, plaintext, context.getEncodingCipher());
    }

    @Override
    public byte[] decode(byte[] cipherText, BlockModeContext context) {
        return decode(cipherText, context.getDecodingCipher());
    }
}
