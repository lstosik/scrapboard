package net.purevirtual.scrapboard;

import net.purevirtual.scrapboard.AbstractBlockMode.BlockModeContext;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.crypto.prng.VMPCRandomGenerator;
import org.junit.Ignore;
import static org.junit.Assert.*;
@Ignore
public class BlockModeTestUtil {


    public static void testEncodeDecode(AbstractBlockMode blockMode) {
        System.out.println("testEncodeDecode");
        RandomGenerator x = new VMPCRandomGenerator();
        byte[] iv = new byte[16];
        byte[] key = new byte[16];
        x.nextBytes(key);
        BlockModeContext context = getAESContext(key);

        for (int i = 0; i <= 15; i++) {
            byte[] plaintext = new byte[32 + i];
            x.nextBytes(plaintext);
            x.nextBytes(iv);
            byte[] ciphertext = blockMode.encode(iv, plaintext, context);
            byte[] recoveredPlaintext = blockMode.decode(ciphertext, context);
            assertEquals(Hex.encodeHexString(plaintext), Hex.encodeHexString(recoveredPlaintext));
        }

    }
    
    public static BlockModeContext getAESContext(byte[] key) {
        BlockModeContext context = new BlockModeContext(new AESEngine(), new AESEngine());
        context.getEncodingCipher().init(true, new KeyParameter(key));
        context.getDecodingCipher().init(false, new KeyParameter(key));
        return context;
    }
    
    public static BlockModeContext getAESContext(String hexKey) throws DecoderException {
        return getAESContext(Hex.decodeHex(hexKey.toCharArray()));
    }
}