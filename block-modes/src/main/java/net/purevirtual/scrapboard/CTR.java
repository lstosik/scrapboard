package net.purevirtual.scrapboard;

import java.util.Arrays;
import org.bouncycastle.crypto.BlockCipher;

/**
 * Counter
 *
 */
public class CTR extends AbstractBlockMode {
    /**
     * 16 bytes = 128 bits
     */
    private static final int BLOCK_SIZE = 16;

    public byte[] encode(byte iv[], byte plaintext[], BlockCipher cipher) {
        byte[] output;
        //int padSize = BLOCK_SIZE - plaintext.length % BLOCK_SIZE;
        int blocks = (plaintext.length) / BLOCK_SIZE;
        if (plaintext.length % BLOCK_SIZE != 0) {
            blocks++;
        }
        int outputSize = iv.length + blocks * BLOCK_SIZE; //plaintext.length; //+ padSize;
        //System.out.println("outputSize="+outputSize);
        output = new byte[outputSize];
        System.arraycopy(iv, 0, output, 0, BLOCK_SIZE);
        byte[] mod = Arrays.copyOf(iv, iv.length);
        //System.out.println("IV="+Hex.encodeHexString(iv));
        for (int b = 0; b < blocks; b++) {
            cipher.processBlock(mod, 0, output, b * BLOCK_SIZE + BLOCK_SIZE);
            xor(output, b * BLOCK_SIZE + BLOCK_SIZE, plaintext, b * BLOCK_SIZE, output, b * BLOCK_SIZE + BLOCK_SIZE, BLOCK_SIZE);

            inc(mod);//System.arraycopy(output, b*BLOCK_SIZE+BLOCK_SIZE, mod, 0, BLOCK_SIZE);
        }

        return Arrays.copyOfRange(output, 0, plaintext.length + BLOCK_SIZE);
    }

    public byte[] decode(byte cipherText[], BlockCipher cipher) {
        byte iv[] = Arrays.copyOf(cipherText, BLOCK_SIZE);
        //System.out.println("IV="+Hex.encodeHexString(iv));
        int blocks = cipherText.length / BLOCK_SIZE - 1;
        if (cipherText.length % BLOCK_SIZE != 0) {
            blocks++;
        }
        byte[] output = new byte[cipherText.length - BLOCK_SIZE];
        byte[] fragmentBlock = new byte[BLOCK_SIZE];
        byte[] mod = iv;
        for (int b = 0; b < blocks; b++) {
            cipher.processBlock(mod, 0, fragmentBlock, 0);
            xor(fragmentBlock, 0, cipherText, b * BLOCK_SIZE + BLOCK_SIZE, output, b * BLOCK_SIZE, BLOCK_SIZE);
            inc(mod);
        }
        return Arrays.copyOfRange(output, 0, output.length);
    }

    private static void xor(byte[] i1, int offset1, byte message[], int messageOffset, byte output[], int outputOffset, int length) {
        for (int i = 0; i < length; i++) {
            if (messageOffset + i >= message.length || outputOffset + i >= output.length) {
                return;
            }
            output[outputOffset + i] = (byte) (i1[offset1 + i] ^ message[messageOffset + i]);
        }
    }

    private void inc(byte[] mod) {
        for (int i = mod.length - 1; i >= 0; i--) {
            mod[i] = (byte) (mod[i] + 1);
            if (mod[i] != (byte) 256) {
                return;
            }
        }
    }

    @Override
    public byte[] encode(final byte[] iv, final byte[] plaintext, final BlockModeContext context) {
        return encode(iv, plaintext, context.getEncodingCipher());
    }

    @Override
    public byte[] decode(final byte[] cipherText, final BlockModeContext context) {
        return decode(cipherText, context.getEncodingCipher());
    }
}
